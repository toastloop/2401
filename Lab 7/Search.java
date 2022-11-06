// Importing the ArrayList class from the java.util package.
import java.util.ArrayList;
// Importing the Arrays class from the java.util package.
import java.util.Arrays;

/**
 * It generates a report of the results of a series of tests of the performance of the linear search,
 * binary search, and recursive binary search functions
 */
public class Search {

    // It's a variable that is used to store the contents of the report.
    public static ArrayList<String> contents = new ArrayList<String>();
    // It's a variable that is used to store the start time of the timer.
    public static long start = 0;
    // It's a variable that is used to store the end time of the timer.
    public static long end = 0;
    // It's a variable that is used to determine if the timer is running.
    public static boolean running = false;

    /**
     * It generates a report of the results of a series of tests of the performance of the
     * linear search, binary search, and recursive binary search functions
     */
    public static void main(String[] args) {
        // Generates a report from an array size of 1000 to an array size of 1000000 
        // for every 1000 array size increase running 100 times per increase
        Search.generateReport(1000, 1000000, 1000, 100);
        // Print the report to the console
        Search.printReport();
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

        // Creating an array of doubles with given length.
        double[] array = new double[length];

        // Iterating through the array and assign each element a random
        // value between min and max
        for (int i = 0; i < array.length; i++) {
            array[i] = Math.random() * (max - min) + min;
        }

        // Return array
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

        double[] array = Search.createArray(items, 0, 9999);

        // Start a timer
        Search.startTimer();
        // Sort the array using Arrays.sort method
        Arrays.sort(array);
        // End the timer.
        Search.endTimer();
        // Set the time to sort the array
        long sortArray = Search.getTime();
        // Reset the timer.
        Search.resetTimer();

        // holds the sum of the time to complete the linear search runs
        long linear = 0;
        // holds the sum of the time to complete the binary search runs
        long binary = 0;
        // holds the sum of the time to complete the recursive binary search runs
        long recursive = 0;

        for (int j = 0; j < runs; j++) {

            // Set the search variable to a value within the area using a random index
            double search = array[(int) Math.round(Math.random() * (array.length - 1))];

            // Start a timer
            Search.startTimer();
            // Searching for the value of search in the array using the recursive binary method.
            Search.linearSearch(array, search);
            // End the timer.
            Search.endTimer();
            // Add the run time to the sum variable for linear search.
            linear += Search.getTime();
            // Reset the timer
            Search.resetTimer();

            // Start a timer
            Search.startTimer();
            // Searching for the value of search in the array using the binary method.
            Search.binarySearch(array, search);
            // End the timer.
            Search.endTimer();
            // Add the run time to the sum variable for binary search.
            binary += Search.getTime();
            // Reset the timer
            Search.resetTimer();

            // Start a timer
            Search.startTimer();
            // Searching for the value of search in the array using the recursive binary method.
            Search.recursiveBinary(array, search);
            // End the timer.
            Search.endTimer();
            // Add the run time to the sum variable for recursive binary search.
            recursive += Search.getTime();
            // Reset the timer
            Search.resetTimer();

        }

        // It's creating a new String variable called output and
        // sets it to the number of items, the average linear time,
        // the average binary time, the average recursive binary time,
        // and the time it took to sort the array
        String output = items + ",";
        output += (linear / runs) + ",";
        output += (binary / runs) + ",";
        output += (recursive / runs) + ",";
        output += sortArray;

        // It's returning the output variable.
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

        // It's creating a new ArrayList of Strings and assigning it to the contents variable.
        Search.contents = new ArrayList<String>();

        // It's adding the string "items,linear,binary,recursive,sort" to the contents array.
        Search.contents.add("items,linear,binary,recursive,sort");

        // It's looping starting at the start value and ends at the end value, every period.
        for (int i = start; i <= end; i = i + period) {
            // It's adding the result of the run function to the contents array.
            Search.contents.add(run(i, runs));
        }

        // It's returning the contents array.
        return Search.contents;

    }

    /**
     * The function takes an ArrayList of Strings as an argument and prints each element of the
     * ArrayList to the console
     * 
     * @param contents The contents of the report.
     */
    public static void printReport(){
        // It's printing each element of the contents array to the console.
        Search.contents.forEach(System.out::println);
    }

    /**
     * This function starts the timer by setting the start time to the current time, and setting the
     * end time to 0
     */
    public static void startTimer() {

        // It's setting the start time to the current time.
        Search.start = System.nanoTime();
        // It's checking if the end time is not 0, and if it's not, it's setting the end time to 0.
        if(Search.end != 0) Search.end = 0;
        // It's setting the running variable to true.
        Search.running = true;

    }
    /**
     * This function resets the timer
     */
    public static void resetTimer() {

        // It's setting the start time to 0.
        Search.start = 0;
        // It's setting the end time to 0.
        Search.end = 0;
        // It's setting the running variable to false.
        Search.running = false;

    }
    /**
     * If the timer is running, stop it and record the time
     */
    public static void endTimer() {

        // It's checking if the start time is 0, or the timer is not running, and if either of those
        // are true, it resets the timer.
        if(Search.start == 0 || !Search.running) Search.resetTimer();
        // It's setting the end time to the current time.
        Search.end = System.nanoTime();
        // It's setting the running variable to false.
        Search.running = false;

    }

    /**
     * If the start time is 0, or the end time is 0, or the timer is running, return -1. Otherwise, return
     * the difference between the end time and the start time
     * 
     * @return The time difference between the start and end time.
     */
    public static long getTime() {

        // Checking if the start time is 0, or the end time is 0, or the timer is running, and if any
        // of those are true, it returns -1. Otherwise, it returns the difference between the end time
        // and the start time.
        return (Search.start == 0 || Search.end == 0 || Search.running == true) ? -1 : Search.end - Search.start;

    }

    /**
     * > Loop through each element in the array and compare it to the search value. If they are equal,
     * return the index value of the current element. If we finished the loop and haven't found the search,
     * return -1
     * 
     * @param array the array to search through
     * @param search The value we are searching for in the array
     * @return The index of the element that matches the search value.
     */
    public static int linearSearch(double[] array, double search) {
        // Loop through each element in the array
        for (int i = 0; i < array.length; i++) 
            // compare the value of the current element to the search value
            if(array[i] == search) 
                // if they are equal return the index value of the current element
                return i;
        // if we finished the loop and haven't found the search, return -1
        return -1;
    }

    /**
     * If the middle index value is less than the search, increase the left index to the element after the
     * middle index. If the middle index value is larger than the search, decrease the right index to the
     * element before the middle index
     * 
     * @param array the array to search
     * @param search the value we are searching for in the array
     * @return The index of the search value in the array.
     */
    public static int binarySearch(double[] array, double search) {
        // start with the left index at the first element in the array
        int left = 0;
        // and the right index at the last element in the array
        int right = array.length - 1;
        // while the left index is less than or equal to the right index
        while(left <= right){
            // calculate the middle index using the left and right
            int middle = left + (right - left) / 2;
            // if the middle index is equal to the search return the index
            if(array[middle] == search) return middle;
            // if the middle index value is less than the search increase the left index
            // to the element after the middle index
            if(array[middle] < search) left = middle + 1;
            // if the middle index value is larger than the search decrease the right index
            // to the element before the middle index
            else right = middle - 1;
        }
        // if we finished the comparisons and did not find the search, return -1
        return -1;
    }
    
    /**
     * The function takes in an array, a starting index, an ending index, and a search value. It
     * returns the index of the search value if it is found in the array, otherwise it returns -1
     * 
     * @param array the array to search
     * @param search the value to search for
     * @return The index of the search value.
     */
    public static int recursiveBinary(double[] array, double search) {
        // Calling the recursiveBinarySearch function with the array, the left index, the right index,
        // and the search value.
        return recursiveBinarySearch(array, 0, array.length, search);
    }

    /**
     * If the search value is less than the middle value, search the left half of the array, if the search
     * value is greater than the middle value, search the right half of the array
     * 
     * @param array the array to search
     * @param left the left index of the array
     * @param right the right index of the array
     * @param search the value to search for
     * @return The index of the search value in the array.
     */
    public static int recursiveBinarySearch(double[] array, int left, int right, double search){
        // if the right value is less than 1, the search is not in the array so return -1
        if(right < 1) return -1;
        // calculate the middle index using the left and right
        int middle = left + (right - left) / 2;
        // if the middle index is equal to the search return the index
        if(array[middle] == search) return middle;
        // if the middle index value is less than the search increase the left index
        // to the element after the middle index and call the function again
        if(array[middle] < search) return recursiveBinarySearch(array, middle + 1, right, search);
        // if the middle index value is larger than the search decrease the right index
        // to the element before the middle index and call the function again
        else return recursiveBinarySearch(array, left, middle - 1, search);
    }

}