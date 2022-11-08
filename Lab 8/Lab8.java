import java.io.File;
import java.util.Scanner;

public class Lab8 {
    public static void main(String[] args) {
        String fileName = "input.txt";
        Box[] allBoxes = getBoxArrayFromDataFile(fileName);

        if (allBoxes != null) {
            System.out.println("Number of boxes in the array: " +
                    allBoxes.length);
        } else {
            System.out.println("No array constructed. Array is null. ");
        }

        System.out.println("The boxes are as follows:");
        displayAllBoxes(allBoxes);
        System.out.println("Sorting the array using bubbleSort");

        long start = System.nanoTime();
        bubbleSort(allBoxes);
        long end = System.nanoTime();
        long bubbleSortTime = (end - start);

        System.out.println("The array after bubble sort:");
        displayAllBoxes(allBoxes);

        System.out.println("Re-constructing the array from the input file.");
        allBoxes = getBoxArrayFromDataFile(fileName);
        if (allBoxes != null) {
            System.out.println("Number of boxes in the array: " +
                    allBoxes.length);
        } else {
            System.out.println("No array constructed. Array is null. ");
        }
        System.out.println("The boxes after re-reading the input file");
        displayAllBoxes(allBoxes);
        System.out.println("Sorting the array using selectionSort");
        start = System.nanoTime();
        selectionSort(allBoxes);
        end = System.nanoTime();
        long selectionSortTime = (end - start);

        System.out.println("The array after selection sort:");
        displayAllBoxes(allBoxes);

        System.out.println("Re-constructing the array from the input file.");
        allBoxes = getBoxArrayFromDataFile(fileName);
        if (allBoxes != null) {
            System.out.println("Number of boxes in the array: " +
                    allBoxes.length);
        } else {
            System.out.println("No array constructed. Array is null. ");
        }
        System.out.println("The boxes after re-reading the input file");
        displayAllBoxes(allBoxes);
        System.out.println("Sorting the array using mergeSort");
        start = System.nanoTime();
        mergeSort(allBoxes);
        end = System.nanoTime();
        long mergeSortTime = (end - start);

        System.out.println("The array after merge sort:");
        displayAllBoxes(allBoxes);

        System.out.println("********* Runtime summary: ***********");
        System.out.println("Time taken by bubble sort: " + bubbleSortTime + " ns");
        System.out.println("Time taken by selection sort: " + selectionSortTime + " ns");
        System.out.println("Time taken by merge sort: " + mergeSortTime + " ns");
    }

    /**
     * Change the body of this method to arrange the Box
     * objects in the array parameter in ascending order of
     * their volumes.
     * The method must use bubble sort.
     * 
     * @param theBoxes
     */
    static void bubbleSort(Box[] theBoxes) {
        // Setting the boolean value of swap to false.
        boolean swap;
        // This is a for loop that is going through the array of Box objects and
        // comparing the values of
        // theBoxes[i] and theBoxes[j] and setting the min value to the smaller value.
        for (int i = 0; i < theBoxes.length - 1; i++) {
            // reset swap to false
            swap = false;
            for (int j = 0; j < theBoxes.length - i - 1; j++) {
                if (theBoxes[j].compareTo(theBoxes[j + 1]) == 1) {
                    Box temp = theBoxes[j];
                    theBoxes[j] = theBoxes[j + 1];
                    theBoxes[j + 1] = temp;
                    // Set swap to true, since we swapped an item
                    swap = true;
                }
            }
            // If we haven't swapped any items, break the loop
            if (!swap){
                break;
            }
        }
    }

    /**
     * Change the body of this method to arrange the Box
     * objects in the array parameter in ascending order of
     * their volumes.
     * The method must use selection sort.
     * 
     * @param theBoxes
     */
    static void selectionSort(Box[] theBoxes) {
        // This is a for loop that is going through the array of Box objects and
        // comparing the values of
        // theBoxes[i] and theBoxes[j] and setting the min value to the smaller value.
        for (int i = 0; i < theBoxes.length; i++) {
            // Setting the min value to the current index of the array.
            int min = i;
            // This is comparing the values of theBoxes[i] and theBoxes[j] and setting the
            // min value to the smaller
            // value.
            for (int j = i + 1; j < theBoxes.length; j++) {
                if (theBoxes[min].compareTo(theBoxes[j]) == 1) {
                    min = j;
                }
            }
            // Swapping the values of theBoxes[i] and theBoxes[min]
            Box temp = theBoxes[i];
            theBoxes[i] = theBoxes[min];
            theBoxes[min] = temp;
        }
    }

    /**
     * Change the body of this method to arrange the Box
     * objects in the array parameter in ascending order of
     * their volumes.
     * The method must use merge sort.
     * 
     * @param theBoxes
     */
    static void mergeSort(Box[] theBoxes) {
        mergeSort(theBoxes, 0, theBoxes.length - 1);
    }

    /**
     * If the left position is less than the right position, set the middle of the
     * array, call the method
     * using the left side of the array, call the method using the right side of the
     * array, and merge the
     * two sorted sides of the array together
     * 
     * @param theBoxes the array of Box objects
     * @param left     the left side of the array
     * @param right    the right side of the array
     */
    public static void mergeSort(Box[] theBoxes, int left, int right) {
        // Checking to see if the left side of the array is less than the right side of
        // the array.
        if (left < right) {
            // Finding the middle of the array.
            int middle = left + (right - left) / 2;
            // Calling the mergeSort method using the left side of the array.
            mergeSort(theBoxes, left, middle);
            // Calling the mergeSort method using the right side of the array.
            mergeSort(theBoxes, middle + 1, right);
            // Calling the mergeArray method using the left, middle, and right indexes of
            // the array.
            mergeArray(theBoxes, left, middle, right);
        }
    }

    /**
     * The mergeArray function takes in an array of Boxes, the left and right
     * indexes, and the middle
     * index. It creates two arrays, one for the left side and one for the right
     * side. It then fills the
     * left and right side arrays with the values from theBoxes. It then compares
     * the values in the left
     * and right side arrays and puts the smaller value into theBoxes. It then fills
     * theBoxes with the
     * rest of the values in the left and right side arrays
     * 
     * @param theBoxes the array of Box objects to be sorted
     * @param left     the left most index of the array
     * @param middle   the middle of the array
     * @param right    the right most index of the array
     */
    public static void mergeArray(Box[] theBoxes, int left, int middle, int right) {

        // Creating two arrays, one for the left side and one for the right side.
        Box[] leftSide = new Box[middle - left + 1];
        Box[] rightSide = new Box[right - middle];

        // Creating two arrays, one for the left side and one for the right side. It
        // then fills the
        // left and right side arrays with the values from theBoxes.
        for (int i = 0; i < leftSide.length; i++){
            leftSide[i] = theBoxes[left + i];
        }
        for (int j = 0; j < rightSide.length; j++){
            rightSide[j] = theBoxes[middle + 1 + j];
        }

        // Setting the left, right positions to 0 and boxes to left pointer.
        int l = 0;
        int r = 0;
        int b = left;

        // Comparing the values in the left and right side arrays and putting the
        // smaller value into theBoxes.
        while (l < leftSide.length && r < rightSide.length) {
            if (leftSide[l].compareTo(rightSide[r]) <= 0) {
                theBoxes[b] = leftSide[l];
                l++;
            } else {
                theBoxes[b] = rightSide[r];
                r++;
            }
            b++;
        }

        // This is filling the boxes array with the rest of the left and right array.
        while (l < leftSide.length) {
            theBoxes[b] = leftSide[l];
            l++;
            b++;
        }
        while (r < rightSide.length) {
            theBoxes[b] = rightSide[r];
            r++;
            b++;
        }
    }

    /**
     * Display width, length, height, volume of
     * each Box object in a sequence they appear
     * in the array of the parameter.
     * 
     * @param theBoxes
     */
    static void displayAllBoxes(Box[] theBoxes) {
        System.out.println("(The box dimensions are displayed below)");
        System.out.println("---------------------------------");
        // Going through the array of Box objects and printing out the values of the Box
        // objects.
        for (Box box : theBoxes) {
            System.out.println(box.toString());
        }
        System.out.println("---------------------------------\n");
    }

    /**
     * It reads a file, creates a Box array, and returns the array
     * 
     * @param fileName The name of the file that contains the data.
     * @return An array of Box objects.
     */
    static Box[] getBoxArrayFromDataFile(String fileName) {

        // Creating a new file object with the name of the file that is passed in.
        File file = new File(fileName);
        // Create a Box array using the size of the number of lines in the file
        Box[] theBoxes = new Box[lines(file)];

        // Creating a new scanner object that is reading the file.
        try (Scanner fileScanner = new Scanner(file)) {

            // Loop through the file until you reach the end
            for (int i = 0; fileScanner.hasNextLine(); i++) {
                // Store the line in a string variable to pass to the scanner
                String line = fileScanner.nextLine();
                // Create a new scanner object that reads the line to parse
                try(Scanner scanner = new Scanner(line)){
                    // Grab the width, heigh, and length from the line as doubles
                    double width = scanner.nextDouble();
                    double height = scanner.nextDouble();
                    double length = scanner.nextDouble();
                    // Create a new object in the box array with the width, height,
                    // and length from the current line in the file.
                    theBoxes[i] = new Box(width, height, length);
                }
                catch(Exception e){
                    e.getLocalizedMessage();
                }
                
            }

        }
        // Catch any exceptions that may be thrown by the scanner
        catch (Exception e) {
            // Print out the message of the exception
            e.getLocalizedMessage();
        }

        // return the array of boxes
        return theBoxes;
    }

    /**
     * It returns the number of lines in a file
     * 
     * @param file The file to be read.
     * @return The number of lines in the file.
     */
    static int lines(File file) {
        int lines = 0;
        // open a scanner for the given file
        try (Scanner scan = new Scanner(file)) {
            // loop through all of the lines of the file increasing the
            // line count for each iteration until the next line is null
            while (scan.hasNextLine()){
                if (scan.nextLine() != null){
                    lines++;
                }
            }
            // return the number of lines in the file
            return lines;
        }
        // Catch any exceptions that may be thrown by the scanner
        catch (Exception e) {
            // Print out the message of the exception
            System.out.println(e.getLocalizedMessage());
            // if there was a file problem return -1
            return -1;
        }
    }
}
