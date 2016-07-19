//***********************************
// This is a template for your Oracle
// class
//
//***********************************

import java.util.Random;

public class Oracle {
    
    private String pattern;
	private int bulls;
	private int cows;

	// constructor
    public Oracle() {
		// call random number generator to initialize computer number
		randomNumberGenerator();
		
		bulls = 0;
		cows = 0;
    }
	
	// random number generator goes here
	private void randomNumberGenerator() {
		Random r = new Random();

		int digit1 = r.nextInt(10);
		int digit2 = r.nextInt(10);
		while(digit1 == digit2) {
			digit2 = r.nextInt(10);
		}
		int digit3 = r.nextInt(10);
		while(digit3 == digit1 || digit3 == digit2) {
			digit3 = r.nextInt(10);
		}
		int digit4 = r.nextInt(10);
		while(digit4 == digit1 || digit4 == digit2 
		  || digit4 == digit3) {
			digit4 = r.nextInt(10);
		}
		
		// concatenate digits into String
		String empty = "";
		pattern = empty + digit1 + digit2 + digit3 + digit4;
	}
	
	// complete
    public void setPattern(String solution){
        pattern = solution;
    }
	
	// complete
    public String getPattern(){
        return pattern;
    }
	
	// number of bulls for guess
    public void howManyBulls(String guess) {
		bulls = 0;
		
		// compare digits in each position
		for(int i = 0; i<4; i++) {
			if (Integer.parseInt(guess.substring(i, i+1)) == 
			  Integer.parseInt(pattern.substring(i, i+1))) {
				bulls++;
			}
		}
    }
	
	public int getBulls() {
		return bulls;
	}
	
	// number of cows for guess
    public void howManyCows(String guess) {
		cows = 0;
		
			// extract and convert digits of guess
		int g1 = Integer.parseInt(guess.substring(0,1));
		int g2 = Integer.parseInt(guess.substring(1,2));
		int g3 = Integer.parseInt(guess.substring(2,3));
		int g4 = Integer.parseInt(guess.substring(3,4));
			
			// extract and convert digits of computer's number
		int c1 = Integer.parseInt(pattern.substring(0,1));
		int c2 = Integer.parseInt(pattern.substring(1,2));
		int c3 = Integer.parseInt(pattern.substring(2,3));
		int c4 = Integer.parseInt(pattern.substring(3,4));
			
			// compare individual digits to determine number of cows
		if ((g1 == c2 || g1 == c3 || g1 == c4) && (g1 != c1 &&
		  g1 != g2 && g1 != g3 && g1 != g4)) { 
			cows++; 
		}
		if ((g2 == c1 || g2 == c3 || g2 == c4) && (g2 != c2 && 
		  g2 != g1 && g2 != g3 && g2 != g4)) { 
			cows++; 
		}
		if ((g3 == c1 || g3 == c2 || g3 == c4) && (g3 != c3 && 
		  g3 != g1 && g3 != g2 && g3 != g4)) { 
			cows++; 
		}
		if ((g4 == c1 || g4 == c2 || g4 == c3) && (g4 != c4 && 
		  g4 != g1 && g4 != g2 && g4 != g3)) { 
			cows++; 
		}
    }
	
	public int getCows() {
		return cows;
	}
}