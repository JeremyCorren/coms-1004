/*************************************************************
 *	class: Effects.java
 *		This class receives file input, reads it in,
 *		performs one of number of different manipulative
 *		operations on it, and writes a new file
 *
 *************************************************************/

import java.io.*;
import java.util.*;

public class Effects {
	
	private int num_Files;
	private BufferedWriter out;
	private ArrayList<Pixel[][]> imageArrays; // array list of 2D arrays
	private int rows;
	private int columns;
	private Pixel[][] inPixels; // array of pixel objects
	private Pixel[][] finalImage;
	private Scanner in;
	
	public Effects() {
		imageArrays = new ArrayList<Pixel[][]>();
		rows = 640;
		columns = 500;
		inPixels = new Pixel[rows][columns];
		finalImage = new Pixel[rows][columns];
	}
	
public Pixel[][] read(File file) throws FileNotFoundException {
		
		// process header of ppm file
		in = new Scanner(file);
		in.nextLine();
		columns = in.nextInt();
        rows = in.nextInt();
        in.nextLine();
        in.nextLine();
        
        inPixels = new Pixel[rows][columns];
		
		// populate a 2D array of pixel objects
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns && in.hasNextInt(); j++) {
				int R = in.nextInt();
				int G = in.nextInt();
				int B = in.nextInt();
				
				// create a pixel object
				Pixel pixel = new Pixel(R, G, B);
				inPixels[i][j] = pixel;
			}
		}
		in.close();
		
		return inPixels;
	}
	
	public void filter(ArrayList<File> files, String outputFilename) 
			throws IOException {
		
		num_Files = files.size();

		// read in files and store each in 2D arrays in the array list
		for(int i = 0; i < num_Files; i++) {
			imageArrays.add(read(files.get(i)));
		}
		
		// cycle through 2D array
		for(int m = 0; m < rows; m++) {
			for(int n = 0; n < columns; n++) {
				
				// create and fill each array for each file with pixels
				Pixel[] pix = new Pixel[num_Files];
				for (int k = 0; k < num_Files; k++) {
					pix[k] =  imageArrays.get(k)[m][n];
				}
				// initialize final image array after taking mode
				finalImage[m][n] = mode(pix);
			}
		}
		write(finalImage, outputFilename);
	}
	
	public Pixel mode(Pixel[] pix) {
    	
        Pixel pObject = new Pixel(0, 0, 0);
        int maxCount = 0;
        
        // cycle through array of all pixels for each image
        for (int i = 0; i < pix.length; i++) {
        	int count = 0;
        	for (int j = i + 1; j < pix.length; j++) {
        		// compare individual RGB values
        		if (pix[i].getR() == pix[j].getR() && 
        			pix[i].getG() == pix[j].getG() && 
        			pix[i].getB() == pix[j].getB()) {
        				count++;
        			}
        	}
        	if (count > maxCount) {
                maxCount = count;
                pObject = pix[i];
            }
        }
        return pObject;
    }
	
	public void grayscale(ArrayList<File> files, String outputFilename) 
			throws IOException {
		
		num_Files = files.size();
		
		// read in files and store each in 2D arrays to the array list
		for (int i = 0; i < num_Files; i++) {
			imageArrays.add(read(files.get(i)));
		}
		
		// cycle through array and set elements to average of RGB values
		for(int m = 0; m < rows; m++) {
			for(int n = 0; n < columns; n++) {
				imageArrays.get(0)[m][n].setAverage();
				finalImage[m][n] = imageArrays.get(0)[m][n];
			}	
		}
		write(finalImage, outputFilename);
	}
	
	public void horizontalFlip(ArrayList<File> files, String outputFilename) 
			throws IOException {
		
		num_Files = files.size();
		
		// read in files and store each in 2D arrays to the array list
		for (int i = 0; i < num_Files; i++) {
			imageArrays.add(read(files.get(i)));
		}
		
		// perform pixel shuffle algorithm to produce mirror image
		for(int m = 0; m < rows; m++) {
			for(int n = 0; n < columns / 2; n++) {
				Pixel temp = inPixels[m][n];
				finalImage[m][n] = inPixels[m][columns-n-1];
				finalImage[m][columns-n-1] = temp;
			}
		}
		write(finalImage, outputFilename);
	}

	public void write(Pixel[][] fixedImage, String filename) 
			throws IOException {
		
		// create array for fixed image
		fixedImage = new Pixel[rows][columns];
		fixedImage = finalImage;
		
		// create writer for fixed file
		File fixedFile = new File(filename);
		out = new BufferedWriter(new FileWriter(fixedFile));
		
		// write header
		out.write("P3");
		out.newLine();
		out.write(columns + " " + rows);
		out.newLine();
		out.write("255");
		out.newLine();
		
		// write pixels
		int i = 0, j = 0;
		boolean loopCheck = true;
		while (loopCheck == true) {
			int R = fixedImage[i][j].getR();
			int G = fixedImage[i][j].getG();
			int B = fixedImage[i][j].getB();
			
			out.write(R + "   " + G + "   " + B + "   ");
			j++;
			
			if (j >= columns) {
				i++;
				j = 0;
			}
			if (i >= rows) {
				loopCheck = false;
			}
		}
		out.close();
	}
}