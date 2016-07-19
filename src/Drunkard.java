/** 
 * Drunkard.java
 *
 * Objects of class Drunkard are used to simulate 
 * random behavior in a coordinate system
 * 
*/

import java.util.Random;

public class Drunkard {
	private int startingAve;
	private int startingStreet;
	private int currentAve;
	private int currentStreet;
	private int direction; // 0 = NORTH, 1 = EAST, 2 = SOUTH, 3 = WEST

	// Constructs the starting position of drunkard in coordinate grid
	public Drunkard(int ave, int st) {
		startingAve = ave;
		currentAve = startingAve;
		startingStreet = st;
		currentStreet = startingStreet;
	}

	// Drunkard moves randomly to an adjacent intersection
	public void step() {
		Random r = new Random();
		direction = r.nextInt(4);
		
		if(direction == 0) { // NORTH
			currentStreet++;
		}
		else if(direction == 1) { // EAST
			currentAve++;
		}
		else if(direction == 2) { // SOUTH
			currentStreet--;
		}
		else if(direction == 3) { // WEST
			currentAve--;
		}
	}

	// Drunkard moves 100 intersections, each decision random 
	public void fastForward(int steps) {
		for(int i = 0; i < steps; i++) {
			step();
		}
	}
	
	// returns current coordinate position 
	public String getLocation() {
		String leftParenth = "(";
		String comma = ", ";
		String rightParenth = ")";
		String location = leftParenth + currentAve + comma + currentStreet + rightParenth;
		return location;
	}
	
	// returns Manhattan distance between 
	public int howFar() {
		return (Math.abs(startingAve - currentAve)) + (Math.abs(startingStreet - currentStreet));
	}
}