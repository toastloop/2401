/*
    Name: Lab1_Knowlton
    Author Name: Matthew Knowlton
    Class: CS2401 Elementary Data Structures and Algorithms
    CRN: 18418
    Lab Number: 1
    Date: 30 August 2022
*/

// Import File and Scanner libraries
import java.io.File;
import java.util.Scanner;

public class Lab1_Knowlton{
    public static void main(String[] args){

            File inputFile = null;
            Scanner inputScanner = null;

            // Try to open a file and scanner
            try{
                // Open the file resource for the given input file
                inputFile = new File("Lab1_Inputfile1_Knowlton.txt");
                // Opens a scanner to scan through the given input file
                inputScanner = new Scanner(inputFile);
            }

            // Catch any exceptions
            catch(Exception e){

                // Print the Exception Message
                System.out.println(e.getMessage());

                // Exit the program with a non-zero code
                System.exit(1);

            }

            
            // Holds the sum and number of grades of each midterm (column) in order to calculate average.
            int firstMidtermSum = 0;
            int firstMidtermNum = 0;
            int secondMidtermSum = 0;
            int secondMidtermNum = 0;
            int thirdMidtermSum = 0;
            int thirdMidtermNum = 0;

            // Holds the student (row) number, increases on each loop
            int studentNum =  1;

            // Loop while the file scanner has the next line of the file.
            while(inputScanner.hasNextLine()){

                // Read the line of the file into a string variable
                String line = inputScanner.nextLine();
                // Open a scanner to scan  through the given line of the file.
                Scanner lineScanner = new Scanner(line);

                // Holds the maximum grade from the student (row)
                int maxGrade = 0;
                // Holds the assignment number (column)
                int assignmentNum = 0;

                try{

                    // Loop while the line scanner has the next integer in the line.
                    while(lineScanner.hasNextInt()){

                        // Read the grade into an integer variable
                        int grade = lineScanner.nextInt();

                        // Throw an Exception if it encounters a Negative Number
                        if(grade < 0){
                            throw new Exception("Student " + studentNum + " has a negative grade.");
                        }
                        // If it's not a negative number continue to storing the grade
                        else {
                        
                            // Switch based on the assignment number (column)
                            switch(assignmentNum){

                                // First Midterm Grade
                                case  0:
                                    firstMidtermSum +=  grade;
                                    firstMidtermNum++;
                                break;

                                //  Second Midterm Grade
                                case  1:
                                    secondMidtermSum +=  grade;
                                    secondMidtermNum++;
                                break;

                                // Third Midterm Grade
                                case  2:
                                    thirdMidtermSum +=  grade;
                                    thirdMidtermNum++;
                                break;

                                // Default value
                                default:
                                    throw new Exception("Number of grades exceeds number of midterms");

                            }

                            // Check if the grade is greater than  the maximum grade so far.
                            if(grade > maxGrade){
                                // Assign maximum  grade to the current grade.
                                maxGrade = grade;
                            }

                        }

                        // Increase the assignment number (column) counter.
                        assignmentNum++;

                    }

                    // Close the scanner for the line
                    lineScanner.close();

                    if(assignmentNum < 3){

                        // Throw an exception stating that the student has missing grades.
                        throw new Exception("Student " + studentNum + " has missing grades.");

                    } else {

                        //  Print the student number (row) and the highest grade in the row
                        System.out.println("Student " + studentNum + " highest score: " + maxGrade);

                    }

                }
                catch(Exception  e){
                    System.out.println(e.getMessage());
                }

                // Increase the student number (row) counter.
                studentNum++;
            }

            // close the scanner for the input file.
            inputScanner.close();

            // Print out the average grade for each midterm (column)
            System.out.println("Average of the first midterm: " + (firstMidtermSum / firstMidtermNum));
            System.out.println("Average of the second midterm: " + (secondMidtermSum / secondMidtermNum));
            System.out.print("Average of the third midterm: " + (thirdMidtermSum / thirdMidtermNum));

    }
}