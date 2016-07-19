import java.util.ArrayList;

public class Dealer {

    private ArrayList<Card> hand;
    private int handTotal;
	
    public Dealer() {
    	handTotal = 0;
    	hand = new ArrayList<Card>();
    }
    
    // receive initial hand
    public void receiveHand(Card card) {
    	hand.add(card);
    }
    
    // show first card to player
    public Card revealFirst() {
    	return hand.get(0);
    }
    
    public ArrayList<Card> getHand() {
    	return hand;
    }
    
    // add card to hand
    public void hit(Card card) {
    	hand.add(card);
    }
    
    // calculate total value of hand
    public void total() {
    	handTotal = 0;
    	boolean acePresent = false;
    	
    	// cycle through hand
    	for(int i = 0; i < hand.size(); i++) {
    		int value = hand.get(i).getValue();
    		// set jack, queen, king to 10
    		if(value > 10) {
    			value = 10;
    		}
    		// check for aces and set to 1
    		if(value == 1) {
    			acePresent = true;
    			value = 1;
    		}
    		handTotal = handTotal + value;
    	}
    	// if hand is less than 
    	if(handTotal <= 11 && acePresent) {
    		handTotal += 10;
    	}
    }
    
    public int getTotal() {
        return handTotal;
    }
    
    // remove all cards from hand
    public void discardHand() {
    	hand.clear();
    }
}