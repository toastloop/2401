/*
    Name: Lab3_Runner_Knowlton
    Author Name: Matthew Knowlton
    Class: CS2401 Elementary Data Structures and Algorithms
    CRN: 18418
    Lab Number: 3
    Date: 19 September 2022
*/

/*
    Import necessary java libraries

    BufferedReader to turn the file into a list of strings
    FileNotFoundException to throw if the file is not found
    FileReader to read the file into the BufferedReader
    IOException to throw if there's a problem with BufferedReader
    Path to get the absolute path of the file
    DecimalFormat to format double values
    List to create a list of strings from the file
    ListIterator to iterate over the list of strings from the file
    Scanner to parse string into double value
    Collectors to use the Collection interface with Buffered Reader
*/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Lab3_Runner_Knowlton{
    public static void main(String[] args) {
        // Define an array of Box Objects
        Box[] boxArray = null;

        // Try to catch any problems
        try{
            // Use readFile to populate the array of box objects
            boxArray = readFile("input.txt");
        }
        // Catch if the file is not found
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        //  Catch if there's a problem with the Buffered Reader
        catch(IOException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }

        // Generate the report about the boxes
        generateReport(boxArray);
    }

    // Reads the file and returns an array of Boxes with the values from each line
    public static Box[] readFile(String fileName) throws FileNotFoundException, IOException{
        // Turn the fileName into the absolute path of the file.
        String filePath = Path.of(fileName).toAbsolutePath().toString();

        // Create a buffered reader to read the file
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        // Use collector to turn the file into a List of strings
        List<String> fileLines = reader.lines().collect(Collectors.toList());

        // Create an iterator to go over the list of strings
        ListIterator<String> lineIterator =  fileLines.listIterator();

        // Initialize an array of Boxes with the size equal to the number of lines in the file.
        Box[] boxArray = new Box[fileLines.size()];

        // Try to catch any problems
        try{

            // Iterate over each line in the file.
            for (int index = 0; index < fileLines.size() && lineIterator.hasNext(); index++) {

                // Create a scanner to parse the current line.
                Scanner lineScanner = new Scanner(lineIterator.next().toString());

                // Initialize length, width, and height using the scanner
                double length = lineScanner.nextDouble();
                double width = lineScanner.nextDouble();
                double height = lineScanner.nextDouble();

                // Check  for negative values
                if(length < 0 || width < 0 || height < 0){

                    // close the line scanner to prevent leaking
                    lineScanner.close();

                    // throw an exception about the negative value
                    throw new Exception("There is a negative value on Row " + (index + 1));
                }

                // Create a box object at the index of the current line.
                boxArray[index] = new Box(index, length, width, height);
            }
        }
        // Catch any exceptions.
        catch(Exception e){
            // Print the exception message
            System.out.println(e.getMessage());

            // Close the buffered reader.
            reader.close();

            // Exit the program with a non-zero value
            System.exit(1);
        }

        // Close the buffered reader to prevent leaks.
        reader.close();

        // Return the array of Box objects
        return boxArray;
    }

    // Calculate the average surface area of the boxes
    public static double averageSurfaceArea(Box[] boxArray){
        // holds the sum of the surface areas.
        double sum = 0;

        // Loops over each box
        for (Box box : boxArray) {
            // adds the surface area to the sum
            sum += box.getSurfaceArea();
        }

        // returns the sum of the surface areas divided by the number of boxes
        return (sum / boxArray.length);
    }

    // Compares the boxes by surface area and returns the largest box
    public static Box largestBoxBySurfaceArea(Box[] boxArray){
        // Sets the largest box to the first box
        Box largestBox = boxArray[0];

        // loops through each box
        for (Box box : boxArray) {
            // compares the current box to the largest box
            if(box.compareTo(largestBox) > 0){
                // if the current box is larger set the largest box to the current box
                largestBox = box;
            }
        }

        // return the largest box
        return largestBox;
    }

    // Finds the boxes who have a larger than average surface area and returns the indexes.
    public static String largerAverageBoxes(Box[] boxArray){

        // Holds the average surface area to compare against.
        double averageSurfaceArea = averageSurfaceArea(boxArray);

        // Holds the list of larger box indexes
        String largerAverage = "";

        // loop through each box
        for (Box box : boxArray) {
            // checks if the surface area is larger than the average
            if(box.getSurfaceArea() > averageSurfaceArea){
                // if it is, add the index the list
                largerAverage = (largerAverage.isEmpty()) ? box.getIndex() + "" : largerAverage + "," + box.getIndex();
            }
        }

        // return the list of indexes
        return largerAverage;
    }

    // Finds the number of boxes whose surface area is larger than average
    public static int numLargerAverageBoxes(Box[] boxArray){
        // holds the average surface area
        double averageSurfaceArea = averageSurfaceArea(boxArray);

        // holds the count of boxes whose surface area is larger than average
        int numLargerAverage = 0;

        // loop through each box
        for (Box box : boxArray) {
            // compare the box surface area against the average
            if(box.getSurfaceArea() > averageSurfaceArea){
                // if it is larger than increase the count by one
                numLargerAverage++;
            }
        }

        // return the count
        return numLargerAverage;
    }

    // finds the boxes whose surface area is smaller than average and returns their indexes
    public static String smallerAverageBoxes(Box[] boxArray){
        // holds the average surface area
        double averageSurfaceArea = averageSurfaceArea(boxArray);

        // holds the list of indexes for the smaller than average boxes
        String smallerAverage = "";

        // loop through each box
        for (Box box : boxArray) {
            // compare the surface area of the current box against the average
            if (box.getSurfaceArea() < averageSurfaceArea){
                // if the current surface area is smaller than average add the index to the list.
                smallerAverage = (smallerAverage.isEmpty()) ? box.getIndex() + "" : smallerAverage + "," + box.getIndex();
            }
        }

        // return the list of indexes
        return smallerAverage;
    }

    // finds the number of boxes whose surface area is smaller than average
    public static int numSmallerAverageBoxes(Box[] boxArray){
        // holds the average surface area
        double averageSurfaceArea = averageSurfaceArea(boxArray);

        // holds the count of the number of boxes
        int numSmallerAverage = 0;

        // loop through each box
        for (Box box : boxArray) {
            // compare the current box surface area against the average
            if(box.getSurfaceArea() < averageSurfaceArea){
                //if it's smaller increase the count by one
                numSmallerAverage++;
            }
        }

        //return the count
        return numSmallerAverage;
    }

    // Formats a double value into a formatted decimal string
    public static String decimalFormat(double value){
        // creates a copy of the formatter
        DecimalFormat df = new DecimalFormat("0.0");
        // returns the value of the double in a formatted decimal string
        return df.format(value);
    }

    // Generates and prints a report for the list of boxes
    public static void generateReport(Box[] boxArray){
        // Print the average surface area of the boxes
        System.out.println("\nAverage Surface Area: " + decimalFormat(averageSurfaceArea(boxArray)) + "\n");

        // Print the list of indexes of boxes with larger than average surface area
        System.out.println("Box Indices larger than average surface area: " + largerAverageBoxes(boxArray));

        // Print the index, surface area, and dimensions of the largest box
        Box largestBox = largestBoxBySurfaceArea(boxArray);
        System.out.print("The largest box has index: " + largestBox.getIndex() + ", ");
        System.out.print("surface area: " + decimalFormat(largestBox.getSurfaceArea()) + ", ");
        System.out.print("and dimensions: length is " + decimalFormat(largestBox.getLength()) + ", ");
        System.out.print("width is " + decimalFormat(largestBox.getWidth()) + ", ");
        System.out.print("and height is " + decimalFormat(largestBox.getHeight()) + ".\n");

        // Print the number of boxes with larger than average surface area
        System.out.println("Number of boxes with larger than average surface area: " + numLargerAverageBoxes(boxArray));

        // Print the number of boxes with smaller than average surface area
        System.out.println("Number of boxes with smaller than average surface area: " + numSmallerAverageBoxes(boxArray));
    }
}