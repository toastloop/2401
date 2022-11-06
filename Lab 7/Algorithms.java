/**
 * > The class contains three methods for searching an array of doubles. The first method is a linear
 * search, the second is a binary search, and the third is a recursive binary search
 */
public class Algorithms{

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