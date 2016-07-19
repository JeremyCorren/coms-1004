// updated

public class Deck {

    private Card[] cards;
    private int cardsDrawn;

    // blueprint for deck
    public Deck() {
        cards = new Card[52];
        cardsDrawn = 0;
        initializeDeck();
    }
    
    public void initializeDeck() {

    	// initialize deck, one suit at a time
        int j = 0;
        for(int i = 1; i < 14; i++) {
            cards[j] = new Card(i, "Clubs");
            j++;
        }
        for(int i = 1; i < 14; i++) {
            cards[j] = new Card(i, "Diamonds");
            j++;
        }
        for(int i = 1; i < 14; i++) {
            cards[j] = new Card(i, "Hearts");
            j++;
        }
        for(int i = 1; i < 14; i++) {
            cards[j] = new Card(i, "Spades");
            j++;
        }
    }
    
    // draw card from top of deck
    public Card draw() {
    	cardsDrawn++;
    	return cards[cardsDrawn-1];
    }
    
    // randomize deck
    public void shuffle() {
        for(int i = 0; i < 1000; i++) {
           int r1 = (int) (Math.random() * (cards.length));
           int r2 = (int) (Math.random() * (cards.length));
           Card temp = cards[r1];
           cards[r1] = cards[r2];
           cards[r2] = temp;
       }
    }
    
    public int getCardsDrawn() {
        return cardsDrawn;
    }
    
    // create string concatenated from 52 descriptor strings for each card
    public String toString() {
        String deckInfo = cards[0].toString() + "\n";
        for(int i = 1; i < cards.length; i++) {
            deckInfo += cards[i].toString() + "\n";
        }
        return deckInfo;
    }
}