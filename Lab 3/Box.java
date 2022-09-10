/*
    Name: Box
    Author Name: Matthew Knowlton
    Class: CS2401 Elementary Data Structures and Algorithms
    CRN: 18418
    Lab Number: 3
    Date: 8 September 2022
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
        // Set all of the given values to their respetive parameters
        this.index = index;
        this.length = length;
        this.width = width;
        this.height = height;
    }

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
    public double getSurfaceArea(){
        return 2 * ((this.length * this.width) + (this.length * this.height) + (this.width * this.height));
    }

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

    public int compareTo(Object o){
        Box givenBox = (Box) o;
        return (this.getSurfaceArea() > givenBox.getSurfaceArea()) ? 1 : (this.getSurfaceArea() < givenBox.getSurfaceArea()) ? -1 : 0;
    }
}