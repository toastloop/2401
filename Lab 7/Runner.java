// Importing the LocalDateTime class from the java.time package.
import java.time.LocalDateTime;
// Importing the DateTimeFormatter class from the java.time.format package.
import java.time.format.DateTimeFormatter;
// Importing the ArrayList class from the java.util package.
import java.util.ArrayList;
// Importing the Arrays class from the java.util package.
import java.util.Arrays;

/**
 * It generates a report of the results of a series of tests of the performance of the linear search,
 * binary search, and recursive binary search functions
 */
public class Runner {

    /**
     * It generates a report of the results of a series of tests of the performance of the
     * linear search, binary search, and recursive binary search functions
     */
    public static void main(String[] args) {
        ArrayList<String> report = generateReport(1000, 1000000, 1000, 100);
        printReport(report);
        //writeReport(report, "Report", false);
    }

    /**
     * This function creates an array of random numbers between a minimum and maximum value
     * 
     * @param length the length of the array
     * @param min The minimum value of the array
     * @param max the maximum value of the array
     * @return The array is being returned.
     */
    public static double[] createArray(int length, int min, int max) {

        double[] array = new double[length];

        for (int i = 0; i < array.length; i++) {
            array[i] = Math.random() * (max - min) + min;
        }

        return array;

    }

    /**
     * It creates an array of random numbers, sorts it, then runs a linear search, binary search, and
     * recursive binary search on a random number from the array. It does this a number of times, and
     * then returns the average time it took to run each search
     * 
     * @param items the number of items in the array
     * @param runs The number of times to run the search algorithms.
     * @return The time it takes to run the linear, binary, and recursive binary searches.
     */
    public static String run(int items, int runs){

        Timer timer = new Timer();

        timer.startTimer();
        double[] array = createArray(items, 0, 9999);
        timer.endTimer();
        long createArray = timer.getTime();
        timer.resetTimer();

        timer.startTimer();
        Arrays.sort(array);
        timer.endTimer();
        long sortArray = timer.getTime();
        timer.resetTimer();

        long linear = 0;
        long binary = 0;
        long recursive = 0;

        for (int j = 0; j < runs; j++) {

            double search = array[(int) Math.round(Math.random() * (array.length - 1))];

            timer.startTimer();
            Algorithms.linearSearch(array, search);
            timer.endTimer();
            linear += timer.getTime();
            timer.resetTimer();

            timer.startTimer();
            Algorithms.binarySearch(array, search);
            timer.endTimer();
            binary += timer.getTime();
            timer.resetTimer();

            timer.startTimer();
            Algorithms.recursiveBinary(array, search);
            timer.endTimer();
            recursive += timer.getTime();
            timer.resetTimer();

        }

        String output = items + ",";
        output += (linear / runs) + ",";
        output += (binary / runs) + ",";
        output += (recursive / runs) + ",";
        output += createArray + ",";
        output += sortArray;

        return output;

    }

    /**
     * It generates a report of the performance of the search algorithms
     * 
     * @param start the starting number of items to be searched
     * @param end the number of items to search for
     * @param period the number of items to add to the arraylist each time
     * @param runs number of times to run the test
     * @return An ArrayList of Strings
     */
    public static ArrayList<String> generateReport(int start, int end, int period, int runs) {

        ArrayList<String> lines = new ArrayList<String>();

        lines.add("number of items,linear search,binary search,recursive search,creation time,sort time");

        for (int i = start; i <= end; i = i + period) {
            lines.add(run(i, runs));
        }

        try(File file = new File("reports/report-"+DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss").format(LocalDateTime.now())+".csv")){
            if(!file.exists())
                file.create();
            file.write(lines);
        } catch(Exception e){
            System.out.println(e.getLocalizedMessage());
            System.exit(1);
        }

        return lines;

    }

    /**
     * The function takes an ArrayList of Strings as an argument and prints each element of the
     * ArrayList to the console
     * 
     * @param contents The contents of the report.
     */
    public static void printReport(ArrayList<String> contents){
        contents.forEach(System.out::println);
    }

    /**
     * It takes an ArrayList of Strings, a String name, and a boolean date, and writes the contents of
     * the ArrayList to a file with the name of the String name, and the date if the boolean date is
     * true
     * 
     * @param contents An ArrayList of Strings that will be written to the file.
     * @param name The name of the file.
     * @param date boolean - whether or not to include the date in the file name
     */
    public static void writeReport(ArrayList<String> contents, String name, boolean date) {

        String dateString = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss").format(LocalDateTime.now());
        String fileName = name + (date ? dateString : "") + ".csv";

        try(File file = new File(fileName)){

            if (file.exists()) {

                if (file.delete())
                    file.create();
                else
                    throw new Exception("Unable to delete file " + file.path());

            } else {
                file.create();
            }

            file.write(contents);
            file.read().forEach(System.out::println);

        } catch (Exception e) {

            System.out.println(e.getLocalizedMessage());
            System.exit(1);

        }
    }
}
