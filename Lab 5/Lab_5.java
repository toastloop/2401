import java.io.File;
import java.util.Scanner;

class Lab_5 {
  public static void main(String[] args) {
    // You are not allowed to change the main method.

    String inputfilename = "input.txt";
    String outputfilename = "output.txt";
    PlanetLinkedList solarSystem = constructLLFromFile(inputfilename);

    solarSystem.printLL();

    System.out.println();

    System.out.println(
        "Number of nodes in the linked list: "
            + solarSystem.countNodes());

    System.out.println();

    System.out.println("I will find diameter 4879 in the linked list.");
    solarSystem.search(4879);
    System.out.println();

    System.out.println("I will find diameter 12756 in the linked list.");
    solarSystem.search(12756);
    System.out.println();

    System.out.println("I will find Neptune in the linked list.");
    solarSystem.search("Neptune");
    System.out.println();

    System.out.println("I will find Pluto in the linked list.");
    solarSystem.search("Pluto");
    System.out.println();

    System.out.println("I will find Jupiter in the linked list.");
    solarSystem.search("Jupiter");
    System.out.println();

    System.out.println("I am going to remove the current head.");
    solarSystem.remove(0);

    System.out.println();

    solarSystem.printLL();

    System.out.println();

    System.out.println("I am going to insert a new record in position 4.");

    Planet aNewPlanet = new Planet("Jupiter", 142984, 67);
    solarSystem.insert(aNewPlanet, 4);

    System.out.println();

    solarSystem.printLL();

    System.out.println("I am going to insert a new planet in position 5.");

    aNewPlanet = new Planet("Uranus", 51118, 27);
    solarSystem.insert(aNewPlanet, 5);

    System.out.println();

    solarSystem.printLL();

    System.out.println("I am going to insert a new planet in position 7.");

    aNewPlanet = new Planet("Neptune", 49528, 14);
    solarSystem.insert(aNewPlanet, 7);

    System.out.println();

    solarSystem.printLL();

    System.out.println();
    System.out.println("I am going to swap position 0 and 3.");
    solarSystem.swap(0, 3);

    System.out.println();
    solarSystem.printLL();

    System.out.println();
    System.out.println("I am going to swap position 1 and 7.");
    solarSystem.swap(1, 7);

    System.out.println();
    System.out.println("I am going to swap position 1 and 4.");
    solarSystem.swap(1, 4);

    System.out.println();
    solarSystem.printLL();

    System.out.println();
    System.out.println("I am going to write the linked list to file: " + outputfilename);
    solarSystem.writeLinkedListToFile(outputfilename);

  }

  static PlanetLinkedList constructLLFromFile(String theInputFile) {
    // print the constructing statement
    System.out.println("Constructing the linked list from " + theInputFile);
    // holds the file we want to scan through for the planet information
    File file = new File(theInputFile);
    // holds a linked list of planets using the file information
    PlanetLinkedList linkedList = new PlanetLinkedList();

    // try to create a scanner of the file
    try (Scanner fileScanner = new Scanner(file)) {

      // loop through the file until we run out of lines
      for(int index = 0; fileScanner.hasNextLine(); index++){
        // insert a new planet in the list using the next 3 lines of the file
        linkedList.insert(new Planet(fileScanner.nextLine(), Long.parseLong(fileScanner.nextLine()), Integer.parseInt(fileScanner.nextLine())), index);
      }

    }
    // catch any errors
    catch (Exception e) {
      // print the error message
      System.out.println(e.getMessage());
      // exit the program
      System.exit(1);
    }

    //return the list of planets
    return linkedList;
  }

}
