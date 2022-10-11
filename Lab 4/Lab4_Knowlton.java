/*
    Name: Lab4_Knowlton
    Author Name: Matthew Knowlton
    Class: CS2401 Elementary Data Structures and Algorithms
    CRN: 18418
    Lab Number: 4
    Date: 29 September 2022
*/

/*
    Import necessary java libraries

    BufferedReader to turn the file into a list of strings
    FileNotFoundException to throw if the file is not found
    FileReader to read the file into the BufferedReader
    IOException to throw if there's a problem with BufferedReader
    Path to get the absolute path of the file
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
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Lab4_Knowlton {
    public static void main(String[] args) {
        String[][] data = null;

        try {
            data = readFile("input.csv");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        System.out.println("\n------------Analyzing capital names------------");
        largestCapital(data);
        mostVowels(data);
        System.out.println("\n--------------Analyzing population--------------");
        mostPopulated(data);
        secMostPopulated(data);
        thirdMostPopulated(data);
        System.out.println();

    }

    // Reads the file and returns an array of Boxes with the values from each line
    public static String[][] readFile(String fileName) throws FileNotFoundException, IOException {
        String filePath = Path.of(fileName).toAbsolutePath().toString();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        List<String> fileLines = reader.lines().collect(Collectors.toList());
        ListIterator<String> lineIterator = fileLines.listIterator();
        String[][] dataArray = new String[fileLines.size()][3];
        try {
            for (int index = 0; index < fileLines.size() && lineIterator.hasNext(); index++) {
                Scanner lineScanner = new Scanner(lineIterator.next().toString()).useDelimiter(",");
                dataArray[index][0] = lineScanner.next();
                dataArray[index][1] = lineScanner.next();
                dataArray[index][2] = lineScanner.next();
                lineScanner.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            reader.close();
            System.exit(1);
        }
        reader.close();
        return dataArray;
    }

    public static void largestCapital(String[][] data) {
        int largestRow = 0;
        int loop = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i][1].length() > data[largestRow][1].length()) {
                largestRow = i;
            }
            loop++;
        }
        System.out.println("\nCapital with the largest name: " + data[largestRow][1]);
        System.out.println("The loop(s) ran: " + loop + " times.");
    }

    public static void mostVowels(String[][] data) {

        /* holds the row index, number of vowels, and number of types of vowels */
        int maxVowelRow = 0;
        int maxVowelNumbers = 0;
        int maxVowelTypes = 0;
        /* number of loops */
        int loop = 0;

        for (int row = 0; row < data.length; row++) {

            int rowVowelTypes = 0;
            int rowVowelNumbers = 0;
            String cityName = data[row][1].toLowerCase();

            /* check vowels */
            if (cityName.contains("a")) {
                /* add one to vowel type */
                rowVowelTypes++;
                /* remove all of the vowels from the string */
                cityName = cityName.replace("a", "");
            }
            if (cityName.contains("e")) {
                rowVowelTypes++;
                cityName = cityName.replace("e", "");
            }
            if (cityName.contains("i")) {
                rowVowelTypes++;
                cityName = cityName.replace("i", "");
            }
            if (cityName.contains("o")) {
                rowVowelTypes++;
                cityName = cityName.replace("o", "");
            }
            if (cityName.contains("u")) {
                rowVowelTypes++;
                cityName = cityName.replace("u", "");
            }
            /* total number of vowels is the original length - the new length */
            rowVowelNumbers = data[row][1].length() - cityName.length();

            if ((rowVowelNumbers > maxVowelNumbers)
                    || (rowVowelNumbers == maxVowelNumbers && rowVowelTypes > maxVowelTypes)) {
                maxVowelRow = row;
                maxVowelNumbers = rowVowelNumbers;
                maxVowelTypes = rowVowelTypes;
            }

            loop++;
        }
        System.out.println("\nCapital with the most vowel: " + data[maxVowelRow][1]);
        System.out.println("The loop(s) ran: " + loop + " times.");
    }

    public static void mostPopulated(String[][] data) {
        int maxPopulationNumber = 0;
        int maxPopulationRow = 0;
        int loop = 0;
        for (int row = 0; row < data.length; row++) {
            int population = Integer.parseInt(data[row][2]);
            if (population > maxPopulationNumber) {
                maxPopulationNumber = population;
                maxPopulationRow = row;
            }
            loop++;
        }
        System.out.println("\nMost populated capital: " + data[maxPopulationRow][1]);
        System.out.println("The population is: " + data[maxPopulationRow][2]);
        System.out.println("The loop(s) ran: " + loop + " times.");
    }

    public static void secMostPopulated(String[][] data) {
        /* holds max row information */
        int maxPopulationNumber = 0;
        int maxPopulationRow = 0;
        /* holds second most row information */
        int secondPopulationNumber = 0;
        int secondPopulationRow = 0;
        /* holds loop num */
        int loop = 0;
        /* loop through rows */
        for (int row = 0; row < data.length; row++) {
            int population = Integer.parseInt(data[row][2]);
            /* if it's larger than the biggest capital */
            if (population > maxPopulationNumber) {
                /* make the previous first second */
                secondPopulationNumber = maxPopulationNumber;
                secondPopulationRow = maxPopulationRow;
                /* make the previous first second */
                maxPopulationNumber = population;
                maxPopulationRow = row;
            } else if (population > secondPopulationNumber) {
                secondPopulationNumber = population;
                secondPopulationRow = row;
            }
            loop++;
        }
        System.out.println("\nMost populated capital: " + data[secondPopulationRow][1]);
        System.out.println("The population is: " + data[secondPopulationRow][2]);
        System.out.println("The loop(s) ran: " + loop + " times.");
    }

    public static void thirdMostPopulated(String[][] data) {
        /* holds max row information */
        int maxPopulationNumber = 0;
        int maxPopulationRow = 0;
        /* holds second most row information */
        int secondPopulationNumber = 0;
        int secondPopulationRow = 0;
        /* holds third most row information */
        int thirdPopulationNumber = 0;
        int thirdPopulationRow = 0;
        /* holds loop num */
        int loop = 0;
        /* loop through rows */
        for (int row = 0; row < data.length; row++) {
            int population = Integer.parseInt(data[row][2]);
            /* if it's larger than the biggest capital */
            if (population > maxPopulationNumber) {
                /* make the previous second third */
                thirdPopulationNumber = secondPopulationNumber;
                thirdPopulationRow = secondPopulationRow;
                /* make the previous first second */
                secondPopulationNumber = maxPopulationNumber;
                secondPopulationRow = maxPopulationRow;
                /* insert the new max */
                maxPopulationNumber = population;
                maxPopulationRow = row;
            } else if (population > secondPopulationNumber) {
                /* make the previous second third */
                thirdPopulationNumber = secondPopulationNumber;
                thirdPopulationRow = secondPopulationRow;
                /* insert the previous second */
                secondPopulationNumber = population;
                secondPopulationRow = row;
            } else if (population > thirdPopulationNumber) {
                /* insert the new third */
                thirdPopulationNumber = population;
                thirdPopulationRow = row;
            }
            loop++;
        }
        System.out.println("\nMost populated capital: " + data[thirdPopulationRow][1]);
        System.out.println("The population is: " + data[thirdPopulationRow][2]);
        System.out.println("The loop(s) ran: " + loop + " times.");
    }

}