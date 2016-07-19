/*************************************************************
 *	class: Pixel.java
 *		This class constructs a pixel object, each associated
 *		with a set of instance variables corresponding to
 *		red, green, or blue color-values
 *
 *************************************************************/

public class Pixel {

	private int red;
	private int green;
	private int blue;
	
	public Pixel (int r, int g, int b) {
		red = r;
		green = g;
		blue = b;
	}
	
	// accessor methods return individual RGB values
	
	public int getR() {
		return red;
	}
	
	public int getG() {
		return green;
	}
	
	public int getB() {
		return blue;
	}
	
	public void setAverage() {
        int AVG = (red + green + blue)/3;
        red = AVG;
        green = AVG;
        blue = AVG;        
    }
}