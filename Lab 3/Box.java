/*
    Name: Box
    Author Name: Matthew Knowlton
    Class: CS2401 Elementary Data Structures and Algorithms
    CRN: 18418
    Lab Number: 3
    Date: 19 September 2022
*/

public class Box {
    // Holds the index of the box in the array
    private int index;
    // Holds the length of the box
    private double length;
    // Holds the height of the box
    private double height;
    // Holds the width of the box
    private double width;
    
    // Constructor to make a box with no parameters
    Box(){
    }
    // Constructor with all of the parameters to make the box
    Box(int index, double length, double width, double height){
        // Set all of the given values to their respective parameters
        this.index = index;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    // Getters/Accessors return the value of the requested variable
    public int getIndex(){
        return this.index;
    }
    public double getHeight(){
        return this.height;
    }
    public double getWidth(){
        return this.width;
    }
    public double getLength(){
        return this.length;
    }
    // Calculates the surface area of the box and returns the value
    public double getSurfaceArea(){
        return 2 * (this.length * this.width + this.length * this.height + this.width * this.height);
    }

    // Setters/Mutators change the value of the requested variable and do not return a value
    public void setIndex(int index){
        this.index = index;
    }
    public void setHeight(double height){
        this.height = height;
    }
    public void setWidth(double width){
        this.width = width;
    }
    public void setLength(double length){
        this.length = length;
    }

    /*  
        Takes in a box object and compares it's surface area to the current box.
        Returns 1 if the current box is larges, -1 if the given box is larger
        and zero if the two boxes are equal.
    */
    public int compareTo(Object o){
        Box givenBox = (Box) o;
        return (this.getSurfaceArea() > givenBox.getSurfaceArea()) ? 1 : (this.getSurfaceArea() < givenBox.getSurfaceArea()) ? -1 : 0;
    }
}