
import java.util.Arrays;

class Lab10_Runner{
  public static void main(String[] args){
    System.out.println("---------------------------");       
    BinaryTree bst = new BinaryTree();

    bst.insertBST("Pluto");
    bst.insertBST("Mercury");
    bst.insertBST("Jupiter"); 
    bst.insertBST("Neptune");
    bst.insertBST("Saturn");    
    bst.insertBST("Venus");        
    bst.insertBST("Earth"); 
    bst.insertBST("Mars"); 
    bst.insertBST("Uranus");
    
    System.out.println("Printing BST:");    
    bst.printBT();
    System.out.println("---------------------------");
    
    System.out.println("Inserting Pluto in BST:");    
    System.out.println(""+bst.insertBST("Pluto"));
    System.out.println("---------------------------");
        
    System.out.print("Total number of nodes in the BST: ");    
    System.out.println(bst.size());            
    System.out.println("---------------------------");        
    
    System.out.print("Searching for Mars in the BST: \n");
    System.out.println(""+bst.searchBT("Mars"));
    System.out.println("---------------------------");
    
    System.out.print("Searching for Phobes in the BST: \n");
    System.out.println(""+bst.searchBT("Phobes"));
    System.out.println("---------------------------");
    
    System.out.println("Printing BST (ascending order): ");
    bst.printAscending();    
    System.out.println("---------------------------"); 
    
    System.out.println("Printing BST (descending order): ");
    bst.printDescending();
    System.out.println("---------------------------");    
        
    System.out.println("Retrieving the content of the tree from a String array: ");
    String[] strArray = bst.getAsArray();
    System.out.println( Arrays.toString(strArray));

    System.out.println("---------------------------");     
  }  
}


