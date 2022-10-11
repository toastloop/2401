import java.io.FileWriter;

class PlanetLinkedList {
  private Planet head;

  PlanetLinkedList() {
  }

  PlanetLinkedList(Planet initial) {
    head = initial;
  }

  PlanetLinkedList(String n, long d, int m) {
    head = new Planet(n, d, m);
  }

  void insert(Planet insertee, int pos) {
    // holds the current planet we are checking
    Planet currentPlanet = this.head;

    // if we want to replace the first planet
    if (pos == 0) {
      // set the head variable to the new planet
      this.head = insertee;
      // set the next pointer to the old planet
      this.head.next = currentPlanet;
    }
    // check if the position isn't valid
    else if (pos > this.countNodes()) {
      // print an error message
      System.out.println("Position " + pos + " is not valid. Nothing changed.");
    }
    // else is the position is valid
    else {

      // loop through the planets until we get to the correct position
      for(int count = 1; (count < pos && (currentPlanet = currentPlanet.next) != null); count++);

      // store the next planet temporarily
      Planet temp = currentPlanet.next;
      // set the next planet to the one we want to insert
      currentPlanet.next = insertee;
      // set the new planet's next planet to the old planet
      currentPlanet.next.next = temp;
    }
  }

  void remove(int pos) {
    // holds the current planet we are checking
    Planet currentPlanet = this.head;

    // if we are trying to remove the first planet
    if (pos == 0) {
      // set the new first planet to the second planet
      this.head = this.head.next;
    }
    // check if the position isn't valid
    else if (pos > this.countNodes()) {
      // print an error message
      System.out.println("Position " + pos + " is not valid. Nothing changed.");
    }
    // else is the position is valid
    else {

      // loop through each planet until we get to the one before the planet we want to remove
      for(int count = 1; (count < (pos - 1) && (currentPlanet = currentPlanet.next) != null); count++);

      // Set the next planet to the one after next (essentially removing the planet)
      currentPlanet.next = currentPlanet.next.next;
    }
  }

  int countNodes() {
    // holds the current planet we are checking
    Planet currentPlanet = this.head;
    // create a count variable
    int count;

    // loop through the planets until the current planet equals null, increase count each time
    for(count = 1; ((currentPlanet = currentPlanet.next) != null); count++);

    // return count
    return count;
  }

  void printLL() {
    // holds the current planet we are checking
    Planet currentPlanet = this.head;

    // Print the top separator of the block
    System.out.println("-------- Content of the linked list --------");

    // loop through the planets
    do {

      // print a separator and then the string version of the planet
      System.out.println("*****************\n" + currentPlanet);

    } while((currentPlanet = currentPlanet.next) != null);

    // Print the top separator of the block
    System.out.println("-------- End of content of the linked list --------\n");
  }

  void swap(int pos1, int pos2) {

    // check if either of the given positions are invalid
    if (pos1 > this.countNodes() || pos2 > this.countNodes()) {
      // print out an error message saying which position is invalid
      System.out.println("The position " + ((pos1 > this.countNodes()) ? pos1 : pos2) + " is invalid.\nReturning without any change.");
      // return void
      return;
    }

   // holds the current planet we are checking
    Planet currentPlanet = this.head;
    // holds the planet before the first planet we want to swap
    Planet pointer1 = null;
    // holds the planet before the second planet we want to swap
    Planet pointer2 = null;
    // holds the current position to check against
    int position = 0;

    // loop through the planets
    do{

      // if the position matches the one before the first planet we want to swap assign it to pointer 1
      if(position == pos1 - 1) pointer1 = currentPlanet;
      // if the position matches the one before the second planet we want to swap assign it to pointer 2
      if(position == pos2 - 1) pointer2 = currentPlanet;

    } while(((currentPlanet = currentPlanet.next) != null && (position++) < this.countNodes()));

    // holds the first planet we want to swap
    Planet temp = (pos1 == 0) ? this.head : pointer1.next;
    // if we want to swap the first planet we have to assign the second planet to the head
    if(pos1 == 0) this.head = pointer2.next;
    // otherwise set the next link for the pointer1 planet to planet 2
    else pointer1.next = pointer2.next;
    // set the next pointer for planet 2 to planet 1
    pointer2.next = temp;

    // holds the link to the next planet after the first
    Planet next = (pos1 == 0) ? this.head.next : pointer1.next.next;
    // if we want to swap the first planet we have to set the next link of the head planet
    if(pos1 == 0) this.head.next = pointer2.next.next;
    // otherwise set the next link for planet1 to the one from planet 2
    else pointer1.next.next = pointer2.next.next;
    // set the next link for planet 2 to the one from planet 1
    pointer2.next.next = next;
  }

  void search(String n) {
    // holds the current planet we are checking
    Planet currentPlanet = this.head;
    // holds the current position to check against
    int position = 0;

    // loop through the list of planets
    do{

      // check if the current planet name matches the name given
      if (currentPlanet.getName().equalsIgnoreCase(n)) {
        // print the position number
        System.out.println("The record with name " + n + " is found in position " + position + ".");
        // return void
        return;
      }

    } while((currentPlanet = currentPlanet.next) != null && position++ < this.countNodes());

    // print a message if we didn't find a planet that matches
    System.out.println("The name " + n + " is not found in the linked list.");
  }

  void search(long d) {
    // holds the current planet we are checking
    Planet currentPlanet = head;
    // holds the current position to check against
    int position = 0;

    // loop through the list of planets
    do{

      // check if the current planet diameter matches the diameter given
      if (currentPlanet.getDiameter() == d) {
        // print the position number
        System.out.println("The record with diameter " + d + " is found in position " + position + ".");
        // return void
        return;
      }

    } while((currentPlanet = currentPlanet.next) != null && position++ < this.countNodes());

    // print a message if we didn't find a planet that matches
    System.out.println("The record with diameter " + d + " is not found in the linked list.");
  }

  void writeLinkedListToFile(String filename) {
    // holds the current planet we are checking
    Planet currentPlanet = head;
    // holds the string we are going to write to the file
    String output = "";

    // loop through the planets updating the current planet each time
    do {

      // add a new line (if it's not the first), the planet name, diameter, and number of moons on their own respective lines
      output += ((currentPlanet != this.head) ? "\n" : "") + currentPlanet.getName() + "\n" + currentPlanet.getDiameter() + "\n" + currentPlanet.getMoon();

    } while((currentPlanet = currentPlanet.next) != null);

    // try to open the given file
    try (FileWriter writer = new FileWriter(filename)){
      // write the planet information to the file
      writer.write(output);
      // flush the contents to the file
      writer.flush();
      // output message
      System.out.println("Wrote the LL to output file: " + filename);
    } catch( Exception e){
      // print error message
      System.out.println(e.getMessage());
      // exit the program
      System.exit(1);
    }
  }

}
