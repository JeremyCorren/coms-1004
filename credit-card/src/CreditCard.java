/**
 * CreditCard.java
 *
 * Objects of class CreditCard are used to 
 * determine the validity of a credit card number
 * 
 * @author Jeremy Corren
 */

public class CreditCard {
	
	// declare instance variables
	private String ccNumber;
	private boolean valid;
	private int errorCode;
	
	private boolean checksum1;
	private boolean checksum2;
	private boolean checksum3;
	private boolean checksum4;
	private boolean checksum5;
	private boolean checksum6;

	// Constructs a credit card number from input
	public CreditCard(String number) {
		ccNumber = number;
		valid = false;
		errorCode = 0;
		
		checksum1 = true;
		checksum2 = true;
		checksum3 = true;
		checksum4 = true;
		checksum5 = true;
		checksum6 = true;
	}

	// checks if credit card number is valid or not
	public void check() {
		
		// create substrings of credit card number and convert to integer type
		int digit1 = Integer.parseInt(ccNumber.substring(0, 1));
		int digit2 = Integer.parseInt(ccNumber.substring(1, 2));
		int digit3 = Integer.parseInt(ccNumber.substring(2, 3));
		int digit4 = Integer.parseInt(ccNumber.substring(3, 4));
		int digit5 = Integer.parseInt(ccNumber.substring(4, 5));
		int digit6 = Integer.parseInt(ccNumber.substring(5, 6));
		int digit7 = Integer.parseInt(ccNumber.substring(6, 7));
		int digit8 = Integer.parseInt(ccNumber.substring(7, 8));
		int digit9 = Integer.parseInt(ccNumber.substring(8, 9));
		int digit10 = Integer.parseInt(ccNumber.substring(9, 10));
		int digit11 = Integer.parseInt(ccNumber.substring(10, 11));
		int digit12 = Integer.parseInt(ccNumber.substring(11, 12));
		
		// for creating substrings of two digits that will be converted and treated as a single integer
		int digit1And2 = Integer.parseInt(ccNumber.substring(0, 2));
		int digit7And8 = Integer.parseInt(ccNumber.substring(6, 8));
		
		// perform security checksums
		if(digit1 != 4) { 
			checksum1 = false;
		}
		if(digit4 != digit5 + 1) { 
			checksum2 = false;
		}
		if(digit1 * digit5 * digit9 != 24) {
			checksum3 = false;
		}
		if((digit1 + digit2 + digit3 + digit4 + digit5 + digit6 + digit7 + digit8 + digit9 + digit10 + digit11 + digit12) % 4 != 0) {
			checksum4 = false;
		}
		if(digit1 + digit2 + digit3 + digit4 != digit9 + digit10 + digit11 + digit12 - 1) {
			checksum5 = false; 
		}
		if(digit1And2 + digit7And8 != 100) { 
			checksum6 = false; 
		}
		
		// certify validity 
		if(checksum1 == true && checksum2 == true && checksum3 == true && checksum4 == true && checksum5 == true && checksum6 == true) {
			valid = true;
		}
		else
			valid = false;
	}
	
	// returns boolean value
	public boolean isValid() {
		return valid;
	}
	
	// return error code
	public int getErrorCode() {
		
		if(checksum6 == false) { errorCode = 6; }
		if(checksum5 == false) { errorCode = 5; }
		if(checksum4 == false) { errorCode = 4; }
		if(checksum3 == false) { errorCode = 3; }
		if(checksum2 == false) { errorCode = 2; }
		if(checksum1 == false) { errorCode = 1; }

		return errorCode;
	}
}