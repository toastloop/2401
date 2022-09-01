/*
    Name: Lab2_Knowlton
    Author Name: Matthew Knowlton
    Class: CS2401 Elementary Data Structures and Algorithms
    CRN: 18418
    Lab Number: 2
    Date: 1 September 2022
*/

// import the scanner and file libraries
import java.util.Scanner;
import java.io.File;

public class Lab2_Knowlton{

    // stores the correct version of the 12x12 times table.
    public static int[][] timesArray = makeArray();
    // states whether the table in the file is correct.
    public static boolean correct = false;

    public static void main(String[] args) {

        // stores the resource for the input file.
        File file = null;
        // stores the scanner for the input file.
        Scanner fileScanner = null;
        // stores the file contents in a 12x12 2d-array
        int[][] fileContents = new int[12][12];

        // wrapped file and scanner variables to catch file exceptions
        try{

            // initialize  the  file and fileScanner.
            file = new File("Lab2_Inputfile2_Knowlton.txt");
            fileScanner = new Scanner(file);

        }
        // catch file  and scanner related exceptions
        catch(Exception e){

            // print the error message and exit with a non-zero code.
            System.out.println(e.getMessage());
            System.exit(1);

        }

        // stores the current row number of the file
        int row = 0;

        // loop  through each line of the file using fileScanner
        while(fileScanner.hasNextLine()){

            // store the current line in a variable to use with scanner.
            String line = fileScanner.nextLine();
            // create a scanner to parse through the line  we just stored.
            Scanner lineScanner = new Scanner(line);
            // col (int) stores the current column of the row.
            int col = 0;

            // loop through line to grab each integer.
            while(lineScanner.hasNextInt()){
                // store the integer in the  corresponding row and column in fileContents
                fileContents[row][col] = lineScanner.nextInt();
                // increment the column number
                col++;
            }

            // close the line scanner
            lineScanner.close();
            // increment the row number
            row++;
        }

        // close the file scanner.
        fileScanner.close();

        // check the correctness of the array
        checkArray(fileContents);
        // check the correctness of the selected row
        checkRow(fileContents, 3);
        // check the correctness of the selected column
        checkColumn(fileContents, 2);
        // print the triangle form of the array.
        printTriangle(fileContents);

    }

    // generates a 12x12 times table as a 2d integer array
    public static int[][] makeArray() {

        // 2d-array to  hold times table
        int[][] calcArray = new int[12][12];

        // loop from 0 to 11 (to get a 12 row array)
        for(int i = 0; i < 12; i++){

            // loop from 0 to 11 (to get a 12 column row)
            for(int j = 0; j < 12; j++){
                // multiplies the row number (i) + 1 with  the column number (j) + 1 to get the correct result 
                calcArray[i][j] = (i+1)*(j+1);
            }

        }

        // returns the created 2d-array
        return calcArray;

    }

    // loops through 2d-array to check for correctness against the generated table
    public static void checkArray(int[][] checkArray){

        // stores the number of errors encountered.
        int errCount = 0;

        // loop through each row of the array
        for(int i = 0; i < checkArray.length; i++){

            // loop  through each column of the  array
            for(int j = 0; j < checkArray[0].length; j++){

                // check if the cell in the provided table doesn't match the cell in the generated table
                if(checkArray[i][j] != Lab2_Knowlton.timesArray[i][j]){
                    // print a message with the line number, column number, current value, and correct value.
                    System.out.println("Row "+ i + ", Column " + j + ": " + checkArray[i][j] + " should be " + Lab2_Knowlton.timesArray[i][j]);
                    // increment the error count
                    errCount++;
                }

            }

        }

        // if the error count is above zero, set the correct boolean to false, else set to true
        Lab2_Knowlton.correct = (errCount > 0) ? false : true;
        // print out the total number of mistakes encountered
        System.out.println("Total number of mistakes: " + errCount);
 
    }

    // checks the given row in the provided array for correctness
    public static void checkRow(int[][] checkArray, int rowNum){

        // stores if the row is correct or not.
        boolean correct = true;

        // loop through the columns in the given row.
        for(int j = 0; j < checkArray[rowNum].length; j++){

            // check the value against the generated table
            if(checkArray[rowNum][j] != Lab2_Knowlton.timesArray[rowNum][j]){
                // if it doesn't match set correct to false
                correct = false;
            }

        }

        // Print if the row is correct or not using the correct variable
        if(!correct){
            System.out.println("Row " + rowNum + " is not correct.");
        } else {
            System.out.println("Row " + rowNum + " is correct.");
        }
 
    }

    // checks the correctness of the given column
    public static void checkColumn(int[][] checkArray, int colNum){

        // stores if the column is correct or not.
        boolean correct = true;

        // loop the the given column
        for(int i = 0; i < checkArray.length; i++){

            // check the value against the generated table
            if(checkArray[i][colNum] != Lab2_Knowlton.timesArray[i][colNum]){
                // if it doesn't match set correct to false
                correct = false;
            }

        }

        // Print if column is correct or not using the correct variable
        if(!correct){
            System.out.println("Column " + colNum + " is not correct.");
        } else {
            System.out.println("Column " + colNum + " is correct.");
        }
 
    }
 
    // prints a triangle form of a square array if the table is correct
    public static void printTriangle(int[][] printArray){

        // check if the table is correct
        if(Lab2_Knowlton.correct){

            // loop through each row
            for(int i = 0; i < printArray.length; i++){

                // loop through each column until the row number matches the column number
                for(int j = 0; j <= i; j++){
                    // print the value with a space
                    System.out.print(printArray[i][j] + " ");
                }

                // print a new line at the end of each row
                System.out.println();

            }
 
        }
 
    }

}