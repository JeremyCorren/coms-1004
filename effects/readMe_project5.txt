jdc2189_project5 readMe.txt
****************************

The Effects program is meant to perform file manipulation operations on either a set of images or a single image, which are input in .ppm format. The program can take an indeterminate number of input files for its primary ‘filter’ function, which eliminates ‘unwanted objects’ from an image by way of a majority rules algorithm.

EffectsTest.java

	The tester class first stores a list of files (the filenames dictated by user-submitted command-line arguments) in an array list. If more than 2 files are present, the filter() method in the Effects class is called. In the case of a single file given as input, the program prompts the user to select either the grayscale() or horizontalFlip() operation, both methods called from the Effects class. Else clauses print error statements in the case of invalid user input.
	Additionally, catch clauses are responsible for handling either ‘IO’ exceptions or ‘no such element’ exceptions in the case of invalid command-line arguments or improper file contents, respectively. Both clauses print error messages and terminate the program.

Pixel.java

	The Pixel class simply creates a pixel object, with which RGB color-value instance variables are associated. The integers that make up a .ppm file will be read in and stored in these instance variables in order for the program to later on deal with pixel objects.

Effects.java

	If any of the manipulation operations are selected, the read() method is called, which first reads in the header of the .ppm file to define the columns and rows of each image’s pixel content. As the integers are read in, they are stored as RGB color-values, which will become instance variables of the pixel object that is created for each 3-value iteration. Each pixel object of RGB values is stored in a 2D array whose dimensions correspond to the specified rows and columns.
	The filter() method, after calling this read() method, cycles through each 2D pixel-object array and grabs the objects, storing them in a 1D array. A mode() method is called on this 1D array, during which individual RGB values are compared and the mode is determined. Another 2D array is created for the final image, which contains only the pixels which a majority of the input files contained. This will eliminate ‘unwanted objects.’ This image is then written to the output file.
	The write() method receives the final image 2D array, and creates a file for it to be written to. After creating a header for the .ppm file in accordance with .ppm convention, it gets every RGB value from each pixel object and writes it to the file.
	The grayscale() method follows a similar reading-in protocol, but for a single input file. It cycles through a 2D array of pixel objects for the single image, setting pixel-object elements of another 2D array for the final image to the average of RGB values, achieved by calling the setAverage() method of the Pixel class. This image is then written to the output file with write().
	The horizontalFlip() method follows the reading-in protocol for one input file. While cycling through the 2D array of pixel objects for the single image, it performs a shuffle algorithm on each pixel, with the indices of the surrounding nested for-loop structured such that the result will be a mirror image. This image is then written to the output file with write().