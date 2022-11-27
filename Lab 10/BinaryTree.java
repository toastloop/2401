
class BinaryTree{
  BTNode root; // The binary tree root
  
  int count; // Number of elements currently in the node
  
  BinaryTree(){
  }
  
  BinaryTree(String str){
    root = new BTNode(str);
  }
  
  /**
   * @return Number of elements in the binary 
   * search tree.
   */
  public int size(){
	// Change the body of this method    
    return this.size(this.root);
  }

  private int size(BTNode node){
    return (node == null) ? 0 : 1 + size(node.left) + size(node.right);
  }
  
  /**
   * Insert the string in the parameter into
   * the Binary Search Tree.
   * @param str
   * @return true if insertion is successful.
   */
  public boolean insertBST(String str){
    // Change the body of this method    
    return this.insertBST(this.root, str);
  }

  private boolean insertBST(BTNode node, String str){
    if(node == null){
      this.root = new BTNode(str);
      return true;
    }
    int compare = node.data.toString().compareTo(str);
    if(compare > 1){
      if(node.left == null){
        node.left = new BTNode(str);
        return true;
      }
      else insertBST(node.left, str);
    }
    else if(compare < 1){
      if(node.right == null){
        node.right = new BTNode(str);
        return true;
      }
      else insertBST(node.right, str);
    }
    return false;
  }
  
  /**
   * Return an array of strings containing the 
   * string content elements of the tree.
   * Order of the strings in the array does not matter.
   * @return a String array 
   */
  public String[] getAsArray(){
        // Change the body of this method    
    String[] array = new String[this.size()];
    return getAsArray(this.root, array, 0);
  }

  private String[] getAsArray(BTNode node,  String[] array, int pointer){
    if(node.left != null) getAsArray(node.left, array, 0);
    array[pointer] = node.data.toString();
    if(node.right != null) getAsArray(node.right, array, 0);
    return array;
  }

  
  /**
   * Print the binary tree in the format
   * shown in the output.
   */
  
  public void printBT(){
   // Change the body of this method    
   printBT(this.root, "");
  }
  public void printBT(BTNode node, String prefix){
    System.out.println(node.data.toString());
    if(node.left != null) printBT(node.left, prefix + "-");
    if(node.right != null) printBT(node.right, prefix + "-");
  }
  
  /**
  * Search the binary tree for the given string. 
  * @param str
  * @return true if str is in the binary search tree.
  */ 
  
  public boolean searchBT(String str){
    // Change the body of this method
    return searchBT(this.root, str);
  }

  private boolean searchBT(BTNode node, String str){
    int compare = node.data.toString().compareTo(str);
    System.out.print(compare + " ");
    if(node.left == null && node.right == null) return false;
    if(compare > 0 && node.left != null) searchBT(node.left, str);
    else if(compare < 0 && node.right != null) searchBT(node.right, str);
    else if(compare == 0) return true;
    return false;
  }
  
  /**
   * Print the elements of the binary
   * search tree in ascending order.
   */
  public void printAscending(){
     // Change the body of this method
     printAscending(this.root); 
  }
  private void printAscending(BTNode node){
    if(node.left != null) printAscending(node.left);
    System.out.println(node.data.toString());
    if(node.right != null) printAscending(node.right);
  }
  
  
  /**
   * Print the elements of the binary
   * search tree in descending order.
   */  
  public void printDescending(){
        // Change the body of this method
        printDescending(this.root);
  } 
  private void printDescending(BTNode node){
    if(node.right != null) printDescending(node.right);
    System.out.println(node.data.toString());
    if(node.left != null) printDescending(node.left);
  }
}
