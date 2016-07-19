/*************************************************************
 *  Tester class: EffectsTest.java
 * 		user submits command-line arguments in reference to
 * 		input files in program directory
 * 
 * 		Command-line arguments:
 * 			0: tetons1.ppm
 * 			1: tetons2.ppm
 * 			2: tetons3.ppm
 * 			[. . .]
 * 
 *************************************************************/

import java.io.*;
import java.util.*;

public class EffectsTest {

	public static void main(String[] args) throws IOException {
		
		boolean loopProgram = true;
		
		System.out.println("******************************");
		System.out.println("**** PPM FILE MANIPULATOR ****");
		System.out.println("******************************\n");
		
		while(loopProgram) {
			
			try {
				
				// create and fill array list with files
				ArrayList<File> listFiles = new ArrayList<File>();
				for(int i = 0; i < args.length; i++) {
					listFiles.add(new File(args[i]));
				}
				
				Scanner in;
				int op = 0;
				Effects e = new Effects();
				
				// check to call filter method
				if(listFiles.size() > 2) {
					System.out.println("Performing filter operation...");
					e.filter(listFiles, "fixedimage.ppm");
					System.out.println("Filter operation complete.");
					
					loopProgram = false;
				}
				else if(listFiles.size() == 2) {
					System.out.println("Either");
					System.out.println("       (a) submit more than 2 input "
							+ "files to remove");
					System.out.println("       (b) or submit 1 input file \n");
					System.out.println("***Run again***");
					
					loopProgram = false;
				}
				
				// check to call grayscale or horizontal flip methods
				else if(listFiles.size() == 1) {
					
					System.out.println("Select operation:");
					System.out.println("   1. Grayscale");
					System.out.println("   2. Horizontal flip");
					
					in = new Scanner(System.in);
					op = in.nextInt();
					in.close();
					
					// user selects an operation
					boolean selectLoop = false;
					do {
						if(op == 1) {
							e.grayscale(listFiles, "grayscaleimage.ppm");
							System.out.println("Grayscale operation complete.");
							selectLoop = false;
							if(selectLoop == false) { return; }
						}
						else if(op == 2) {
							e.horizontalFlip(listFiles, "flipimage.ppm");
							System.out.println("Horizontal flip operation "
									+ "complete.");
							selectLoop = false;
							if(selectLoop == false) { return; }
						}
						else {
							System.out.println("\nInvalid input; enter 1 or 2 "
									+ "to select an operation\n");
							System.out.println("***Run again***");
							selectLoop = true;
						}
					} while(selectLoop = true);
				}	
			} 
			
			// in case user doesn't submit the proper filename of input file
			catch (IOException e) {
				System.out.println("\nInvalid arguments; check names of input "
						+ "file(s).\n");
				System.out.println("***Run again***");
				loopProgram = false;
			} 
			
			// in case input file is empty
			catch (NoSuchElementException e) {
				System.out.println("Problem detected in file(s) submitted; "
						+ "check file contents for divergences from .ppm "
						+ "conventions");
				System.out.println("***Run again***");
				loopProgram = false;
			}
			
		}
	}
}