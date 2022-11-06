import java.io.File;
import java.util.Scanner;

public class Lab8 {
  public static void main(String[] args){
    String fileName = "input.txt";        
    Box[] allBoxes = getBoxArrayFromDataFile(fileName);
    
    if (allBoxes!=null){
      System.out.println("Number of boxes in the array: "+
            allBoxes.length);
    }else{
      System.out.println("No array constructed. Array is null. ");
    }
    
    System.out.println("The boxes are as follows:");    
    displayAllBoxes(allBoxes);
    System.out.println("Sorting the array using bubbleSort");
    
    long start = System.nanoTime();
    bubbleSort(allBoxes);
    long end = System.nanoTime();
    long bubbleSortTime=(end-start);    
    
    System.out.println("The array after bubble sort:");    
    displayAllBoxes(allBoxes);    
    
    System.out.println("Re-constructing the array from the input file.");
    allBoxes = getBoxArrayFromDataFile(fileName);
    if (allBoxes!=null){
      System.out.println("Number of boxes in the array: "+
            allBoxes.length);
    }else{
      System.out.println("No array constructed. Array is null. ");
    }
    System.out.println("The boxes after re-reading the input file");
    displayAllBoxes(allBoxes);        
    System.out.println("Sorting the array using selectionSort");
    start = System.nanoTime();
    selectionSort(allBoxes);
    end = System.nanoTime();
    long selectionSortTime=(end-start);
    
    System.out.println("The array after selection sort:");    
    displayAllBoxes(allBoxes);     
    
    System.out.println("Re-constructing the array from the input file.");
    allBoxes = getBoxArrayFromDataFile(fileName);
    if (allBoxes!=null){
      System.out.println("Number of boxes in the array: "+
            allBoxes.length);
    }else{
      System.out.println("No array constructed. Array is null. ");
    }
    System.out.println("The boxes after re-reading the input file");
    displayAllBoxes(allBoxes);        
    System.out.println("Sorting the array using mergeSort");
    start = System.nanoTime();    
    mergeSort(allBoxes);
    end = System.nanoTime();
    long mergeSortTime=(end-start);
    
    System.out.println("The array after merge sort:");    
    displayAllBoxes(allBoxes);        
    
    System.out.println("********* Runtime summary: ***********");
    System.out.println("Time taken by bubble sort: "+bubbleSortTime+" ns");    
    System.out.println("Time taken by selection sort: "+selectionSortTime+" ns");
    System.out.println("Time taken by merge sort: "+mergeSortTime+" ns");
  }
  
  /**
   * Change the body of this method to arrange the Box
   * objects in the array parameter in ascending order of
   * their volumes.
   * The method must use bubble sort.
   * @param theBoxes
   */
  static void bubbleSort(Box[] theBoxes) {
    boolean swap;
    for (int i = 0; i < theBoxes.length - 1; i++) {
      swap = false;
      for (int j = 0; j < theBoxes.length - i - 1; j++) {
        if(theBoxes[j].compareTo(theBoxes[j + 1]) == 1){
          Box temp = theBoxes[j];
          theBoxes[j] = theBoxes[j + 1];
          theBoxes[j + 1] = temp;
          swap = true;
        }
      }
      if(swap == false) break;
    }
  }
  
  /**
   * Change the body of this method to arrange the Box
   * objects in the array parameter in ascending order of
   * their volumes.
   * The method must use selection sort.
   * @param theBoxes
   */  
  static void selectionSort(Box[] theBoxes) {
    for (int i = 0; i < theBoxes.length; i++) {
      int min = i;
      for (int j = i + 1; j < theBoxes.length; j++) {
        if(theBoxes[min].compareTo(theBoxes[j]) == 1) min = j;
      }
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
   * @param theBoxes
   */    
  static void mergeSort(Box[] theBoxes) {
    mergeSort(theBoxes, 0, theBoxes.length);
  }
  public static void mergeSort(Box[] theBoxes, int left, int right){
    int middle = (left+right)/2;
    if(left == right) return;
    mergeSort(theBoxes, left, middle);
    mergeSort(theBoxes , middle + 1, right);
    mergeArray(theBoxes, left, middle, right);
  }

  public static void mergeArray(Box[] theBoxes, int left, int middle, int right){
    Box[] leftSide = new Box[middle - left + 1];
    Box[] rightSide = new Box[right - middle];
    // populate left and right arrays;
    for(int i = 0; i < ((leftSide.length > rightSide.length) ? leftSide.length : rightSide.length); i++){
      if(i < leftSide.length) leftSide[i] = theBoxes[left + i];
      if(i < rightSide.length) rightSide[i] = theBoxes[middle + i];
    }
    int j = 0, l = 0, r = 0;
    for (; l < leftSide.length && r < rightSide.length; j++) {
      if(leftSide[l].compareTo(rightSide[r]) == 1){
        theBoxes[j] = leftSide[l];
        l++;
      }
      else{
        theBoxes[j] = rightSide[r];
        r++;
      }
    }

    while(l < leftSide.length){
      theBoxes[j] = leftSide[l];
      j++;
      l++;
    }

    while(r < rightSide.length){
      theBoxes[j] = rightSide[r];
      j++;
      r++;
    }
  }
  

  /**
   * Display width, length, height, volume of
   * each Box object in a sequence they appear
   * in the array of the parameter.
   * @param theBoxes
   */
  static void displayAllBoxes(Box[] theBoxes){
    for(Box box : theBoxes){
      System.out.println(box.toString());
    }
  } 

  
  static Box[] getBoxArrayFromDataFile (String fileName){

    File file = new File(fileName);
    Box[] theBoxes = new Box[lines(file)];

    try (Scanner fileScanner = new Scanner(file)) {
      

      for (int i = 0; fileScanner.hasNextLine(); i++) {
        String line = fileScanner.nextLine();
        Scanner scanner = new Scanner(line);
        double width = scanner.nextDouble();
        double height = scanner.nextDouble();
        double length = scanner.nextDouble();
        scanner.close();
        theBoxes[i] = new Box(width, height, length);
      }

    } catch (Exception e) {
      e.getLocalizedMessage();
    }

    return theBoxes;
  }

  static int lines(File file){
    try(Scanner scan=new Scanner(file)){
      for(int lines = 1; scan.nextLine()!=null; lines++) {
        if(!scan.hasNextLine()) return lines;
      }
    }catch(Exception e){ 
      System.out.println(e.getLocalizedMessage()); 
    }
    return -1;
  }
}
