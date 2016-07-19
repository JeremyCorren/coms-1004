import java.util.Scanner;

public class Game {

	private Deck deck;
	private Player player;
	private Dealer dealer;
	private Scanner input;
	
	private boolean again; // keeps track of multiple rounds in game
	private int playerCounter; // keep track of player hits
	private int dealerCounter; // keep track of dealer hits

	// constructor
	public Game() {
		// create objects to invoke methods
		deck = new Deck();
		player = new Player();
		dealer = new Dealer();
		
		again = true;
		playerCounter = 0;
		dealerCounter = 0;
	}

	public void play() {
		
		// create new deck and shuffle it
		deck = new Deck();
		deck.shuffle();
				
		// clear hands/totals from previous rounds
		clearHands();

		// text interface
		System.out.println("\n************************");
		System.out.println("BlackJack Simulator v1.0");
		System.out.println("************************\n");
		System.out.println("BEGIN ROUND...\n");

		// deal hands
		dealHands();
		// print hands
		printHands();
		// reset hit counters
		playerCounter = 0;
		dealerCounter = 0;
		
		// check for Blackjack in initial hand
		checkBlackJack();
		// ask user to hit or stand
		if (again == true) {
			hitOrStand();
		}
		else {
			return;
		}
	}

	// deal cards to player and dealer
	private void dealHands() {
		
		// deal initial hand to player
		for (int i = 0; i <=1; i++) {
			player.receiveHand(deck.draw()); 
		}

		// deals hands to dealer
		for (int i = 0; i <=1; i++) { 
			dealer.receiveHand(deck.draw()); 
		}
	}

	// clear hands/totals from previous games
	private void clearHands() {
		// discard hands for player and dealer
		player.discardHand();
		dealer.discardHand();
	}

	// prints the initial cards of player and dealer
	private void printHands() {
		// player
		System.out.println("--Your initial hand consists of: "
				+ player.getHand());
		player.total();
		System.out.println("Your total is: " + player.getTotal());
		
		// dealer
		dealer.total();
		System.out.print("--The dealer reveals his first card: ");
		System.out.println("[" + dealer.revealFirst() + "]\n");
	}
	
	// check for Blackjack in player's initial hand
	private void checkBlackJack() {
		// player
		if (player.getTotal() == 21 && again == true) {
			System.out.println("**Blackjack!**");
			System.out.println("Compare with dealer...");
			// show dealer's initial hand and score
			System.out.println("--The dealer reveals both of his cards...");
			System.out.println(dealer.getHand());
			System.out.println("The dealer's total is: ");
			System.out.println(dealer.getTotal());
			
			// compare scores
			whoWins();
		}
	}

	// ask for user input
	private void hitOrStand() {
		int inputInt = 2;
		if (player.getTotal() == 21) {
			// if player hits 21 he must immediately stand
			inputInt = 2;
		}
		
		// offer player to hit or stand before he hits 21
		if(player.getTotal() < 21) {
			System.out.println("\tHit or stand... "
					+ "(Enter '1' to hit / '2' to stand)\n");
		
			input = new Scanner(System.in);
			inputInt = input.nextInt();
		}
		
		// player hits
		if (inputInt == 1) {

			// boost change
			player.hit(deck.draw());
			player.total();
			System.out.println("--Your hand now consists of "
					+ player.getHand());
			System.out.println("Your total is: " + player.getTotal() + "\n");
			playerCounter++;
			checkPlayer();
		}
		
		// player stands
		else if (inputInt == 2) {
			System.out.println("Now moving to the dealer...\n");
			System.out.println("--The dealer's hand consists of: " 
					+ dealer.getHand());

			dealer.total();
			System.out.println("The dealer's total is: " + dealer.getTotal());
			evaluateDealer();
		}
		
		// in case of incorrect user input
		else {
			System.out.println("## Bad input... Please enter either 1 or 2 ##");
			hitOrStand();
		}
	}

	// check if player busted or if player can choose to hit again
	private void checkPlayer() {
		if (player.getTotal() > 21) {
			System.out.println("--You busted...");
			youLose();
		}
		if (player.getTotal() == 21 && playerCounter != 0) {
			whoWins();
		}
		if (again == true && player.getTotal() < 21) {
			hitOrStand();
		}
	}

	// move to dealer's hand
	private void evaluateDealer() {
		dealer.total();
		if(again == true) {
			
			if(dealer.getTotal() == 21) {
				System.out.println("The dealer got **Blackjack!**");
				whoWins();
			}
			
			// see if dealer hits or not
			dealerHitCheck();
			
			// evaluate dealer's hand
			if (dealer.getTotal() >= 17 && dealer.getTotal() < 21) {
				System.out.println("Dealer stands--");
				whoWins();
			}
			else if (dealer.getTotal() > 21) {
				System.out.println("The dealer busted--");
				youWin();
			}
			else if (dealer.getTotal() == 21 && dealerCounter != 0) {
				whoWins();
			}
		}		
	}
	
	// if dealer is under 17, must hit
	private void dealerHitCheck() {
		while (dealer.getTotal() < 17) {
			System.out.println("The dealer's hand is less than 17... he hits");
			dealer.hit(deck.draw());
			dealer.total();
			dealerCounter++;

			System.out.println("--The dealer's cards are: " + dealer.getHand());
			System.out.println("The dealer's total is: " + dealer.getTotal()
					+ "\n");
		}
	}
	
	// determines victor of a round
	private void whoWins() {
		System.out.println("Evaluate scores...\n");
		player.total();
		
		// print final scores
		System.out.println(  "Our final scores are:");
		System.out.println("   Player: " + player.getTotal());
		System.out.println("   Dealer: " + dealer.getTotal() + "\n");

		// special comparison: someone got Blackjack
		compareWhenBlackJackPresent();
		
		// generic comparison
		compareWhenBlackJackAbsent();
	}
	
	// check for cases in which either dealer, player, or both got Blackjack
	private void compareWhenBlackJackPresent() {
		if (player.getTotal() == 21 && dealer.getTotal() == 21
				&& playerCounter == 0 && dealerCounter == 0 && again == true) {
			System.out.println("Both you and the dealer both got **Blackjack**");
			push();
		}
		else if (player.getTotal() <= 21 && dealer.getTotal() == 21
			    && dealerCounter == 0 && again == true) {
			System.out.println("The dealer got blackjack -- you did not.");
			youLose();
		}
		else if (player.getTotal() == 21 && dealer.getTotal() <= 21
				&& playerCounter == 0 && again == true) {
			System.out.println("You got blackjack -- the dealer did not.");
			youWin();
		}
		else if (player.getTotal() == 21 && dealer.getTotal() < 21
				&& playerCounter == 0 && again == true) {
			System.out.println("Your hand was better than the dealer's hand."); // win
			youWin();
		}
	}
	
	// check for cases in which generally non-Blackjack scores are compared
	private void compareWhenBlackJackAbsent() {
		if (player.getTotal() <= 21 && player.getTotal() > dealer.getTotal()
				&& again == true) {
			System.out.println("Your hand was better than the dealer's hand.");
			youWin();
		}
		else if (dealer.getTotal() <= 21 
				&& player.getTotal() < dealer.getTotal() && again == true) {
			System.out.println("The dealer's hand was better than your hand.");
			youLose();
		}
		else if (player.getTotal() <= 21 
				&& player.getTotal() == dealer.getTotal() && again == true) {
			System.out.println("Your hand tied the dealer's.");
			push();
		}
	}

	// in the case of a tie
	private void push() {
		System.out.println("\nPush! No one wins...\n");
		playAgain();
	}
	
	// in the case that you win
	private void youWin() {
		System.out.println("\nCongratulations, you won this round...\n");
		playAgain();
	}
	
	// in the case that you lose
	private void youLose() {
		System.out.println("\nSorry, you lost this round...\n");
		playAgain();
	}
	
	// ask user to play or again or quit
	private void playAgain() {
		System.out.println("\n\tPlay again? "
				+ "(Enter '1' for yes / '2' for no)\n");
		input = new Scanner(System.in);
		int inputInt = input.nextInt();
		
		if (inputInt == 1) {
			again = true;
			play();
		}
		else if (inputInt == 2) {
			again = false;
			System.out.println("Play again soon!");
		}
		else {
			System.out.println("## Bad input... Please enter either 1 or 2 ##");
			playAgain();
		}
	}
}