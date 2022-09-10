/*
    Name: Lab3_Runner_Knowlton
    Author Name: Matthew Knowlton
    Class: CS2401 Elementary Data Structures and Algorithms
    CRN: 18418
    Lab Number: 3
    Date: 8 September 2022
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

public class Lab3_Runner_Knowlton{
    public static void main(String[] args) {
        Box[] boxArray = null;
        try{
            boxArray = readFile("input.txt");
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
        generateReport(boxArray);
    }
    public static Box[] readFile(String fileName) throws FileNotFoundException{
        String filePath = Path.of(fileName).toAbsolutePath().toString();
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        Box[] boxArray = new Box[6];
        int row = 0;
        do{
            String line = scanner.nextLine();
            Scanner lineScanner = new Scanner(line);
            boxArray[row] = new Box(row, lineScanner.nextDouble(), lineScanner.nextDouble(), lineScanner.nextDouble());
            lineScanner.close();
            row++;
        } while(scanner.hasNextLine());
        scanner.close();
        return boxArray;
    }
    public static double averageSurfaceArea(Box[] boxArray){
        int sum = 0;
        for (Box box : boxArray) {
            sum += box.getSurfaceArea();
        }
        return (sum / boxArray.length);
    }
    public static Box largestBoxBySurfaceArea(Box[] boxArray){
        Box largestBox = boxArray[0];
        for (Box box : boxArray) {
            if(box.compareTo(largestBox) > 0){
                largestBox = box;
            }
        }
        return largestBox;
    }
    public static void generateReport(Box[] boxArray){
        double averageSurfaceArea = averageSurfaceArea(boxArray);
        String largerAverage = "";
        int numLargerAverage = 0;
        String smallerAverage = "";
        int numSmallerAverage = 0;
        for (Box box : boxArray) {
            if(box.getSurfaceArea() > averageSurfaceArea){
                largerAverage = (largerAverage.isEmpty()) ? box.getIndex() + "" : largerAverage + "," + box.getIndex();
                numLargerAverage++;
            }
            else if (box.getSurfaceArea() < averageSurfaceArea){
                smallerAverage = (smallerAverage.isEmpty()) ? box.getIndex() + "" : smallerAverage + "," + box.getIndex();
                numSmallerAverage++;
            }
        }
        System.out.println("\nAverage Surface Area: " + averageSurfaceArea + "\n");
        System.out.println("Number of Boxes with Larger Surface Area: " + numLargerAverage);
        System.out.println("Boxes with Larger Surface Area: " + largerAverage + "\n");
        System.out.println("Number of Boxes with Smaller Surface Area: " + numSmallerAverage);
        System.out.println("Boxes with Smaller Surface Area: " + smallerAverage + "\n");

        Box largestBox = largestBoxBySurfaceArea(boxArray);
        System.out.print("The largest box has index: " + largestBox.getIndex() + ", "); 
        System.out.print("surface area: " + largestBox.getSurfaceArea() + ", ");
        System.out.print("and dimensions: length is " + largestBox.getLength() + ", ");
        System.out.print("width is " + largestBox.getWidth()+", ");
        System.out.print("and height is " + largestBox.getHeight() + ".\n");

    }
}