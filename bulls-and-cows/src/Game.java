//**********************************************
// This is the Game class for the Bull and Cows
// program. You will need to modify this template
//
//**********************************************

import java.util.Scanner;
	
public class Game{
	
	private int turns;
	private Oracle computer;
	private Scanner input;
	private String userGuess;
	
	// constructor
    public Game() {
		turns = 0;
		computer = new Oracle();
    }

    // game manager
    public void playGame() {
    	// starting interface
    	System.out.println("*****************************************");
		System.out.println("** Hello user, welcome to Bulls & Cows **");
		System.out.println("*****************************************");
		
		// game continues until user guesses computer's number
		while(computer.getBulls() != 4) {
			playOneTurn();
		}
		if(computer.getBulls() == 4) {
			System.out.println("Congratulations, you guessed correctly!\n");
			System.out.println("It took you " + turns + " turn(s).");
		}
    }
    
	// simulate a turn of Bulls and Cows
    private void playOneTurn() {

		// read input from user
		System.out.println("Enter a 4 digit number: \n");
		input = new Scanner(System.in);
		userGuess = input.next();
		
		// send user input as argument to bulls and cows methods
		computer.howManyBulls(userGuess);
		computer.howManyCows(userGuess);
		
		// print result
		System.out.println("Your guess contains " + computer.getBulls() + 
			" bull(s) and " + computer.getCows() + " cow(s)...\n");
		
		// add 1 to turns counter
		turns++;
    }

    public void setPattern(String solution) {
        computer.setPattern(solution);
    }

    public String getPattern() {
        return computer.getPattern();
    }
}