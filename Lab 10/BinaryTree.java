
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
    // call the private method
    return this.s(this.root);
  }
  private int s(BTNode n){return(this.count=(n==null)?0:1+s(n.left)+s(n.right));}
  /*
  private int size(BTNode node){
    // if the node is null set count to zero, otherwise set count to 1 plus the size of
    // the left branch plus the size the right branch, return the result
    return (this.count = (node == null) ? 0 : 1 + size(node.left) + size(node.right));
  }
  */
  
  /**
   * Insert the string in the parameter into
   * the Binary Search Tree.
   * @param str
   * @return true if insertion is successful.
   */
  public boolean insertBST(String str){
    // call the private method
    return this.i(this.root, str);
  }
  private boolean i(BTNode n, String s){
    BTNode in = new BTNode(s);if(this.root==null)return(this.root=in)==this.root;int c=n.data.toString().compareTo(s);if(c>0)return(n.left == null)?((n.left=in)==n.left):i(n.left,s);else if(c<0)return(n.right == null)?((n.right=in)==n.right):i(n.right,s);return false;
  }
  /*
  private boolean insertBST(BTNode node, String str){
    // if the root is null, create a root node
    if(this.root == null){
      this.root = new BTNode(str);
      return true;
    }
    // compare the string the current node
    int compare;
    // if the current node is larger
    if(this.root == null) return ((this.root = new BTNode(str)) == this.root);
    if((compare = node.data.toString().compareTo(str)) > 0) return ((node.left == null) ? ((node.left = new BTNode(str)) == node.left): insertBST(node.left, str));
    else if(compare < 0) return ((node.right == null) ? ((node.right = new BTNode(str)) == node.right): insertBST(node.right, str));
    // if the node equals the current node return false
    // binary trees cannot contain duplicate values
    return false;
  }
  */
  
  /**
   * Return an array of strings containing the 
   * string content elements of the tree.
   * Order of the strings in the array does not matter.
   * @return a String array 
   */
  public String[] getAsArray(){
    // create a new array of the size of the tree
    String[] array = new String[this.size()];
    // assign values to the array
    this.g(this.root, array, 0);
    // return the array
    return array;
  }
  private int g(BTNode n,String[] a,int p){
    if(n==null)return p;a[p]=n.data.toString();p=g(n.left,a,++p);p=g(n.right,a,p);return p;
  }
  /*
  private int getAsArray(BTNode node, String[] array, int pointer){
    // if the node is null return the current pointer
    if (node == null) return pointer;
    // assign the current node to the array using the pointer;
    array[pointer] = node.data.toString();
    // assign the left side recursively
    pointer = getAsArray(node.left, array, ++pointer);
    // assign the right side recursively
    pointer = getAsArray(node.right, array, pointer);
    // return the pointer
    return pointer;
  }
  */

  /**
   * Print the binary tree in the format
   * shown in the output.
   */
  
  public void printBT(){
    // call the private method
    this.p(this.root, "");
  }
  private void p(BTNode n,String p){
    if(n==null){System.out.println(p+"-");return;}System.out.println(p+"----"+n.data.toString());p(n.left,p+" ");p(n.right,p+" ");
  }
  /*
  private void printBT(BTNode node, String prefix){
    // if the node is null just print a dash
    if(node == null){
      System.out.println(prefix + "-");
      return;
    }
    // print the prefix spaces with the current node
    System.out.println(prefix + "----" + node.data.toString());
    // traverse the left side
    printBT(node.left, prefix + " ");
    // traverse the right side
    printBT(node.right, prefix + " ");
  }
  */
  
  /**
  * Search the binary tree for the given string. 
  * @param str
  * @return true if str is in the binary search tree.
  */ 
  
  public boolean searchBT(String str){
    // call the private method
    return this.e(this.root, str);
  }
  private boolean e(BTNode n,String s){int c=n.data.toString().compareTo(s);if(c==0)return true;else if(c>0&&n.left!=null)return e(n.left,s);else if(c<0&&n.right!=null)return e(n.right,s);else return false;}
  /*
  private boolean searchBT(BTNode node, String str){
    // compare the node to the search string
    int compare = node.data.toString().compareTo(str);
    // if they are equal return true
    if(compare == 0) return true;
    // if the node is greater than the search continue down the left branch
    else if(compare > 0 && node.left != null) return searchBT(node.left, str);
    // if the node is greater than the search continue down the right branch
    else if(compare < 0 && node.right != null) return searchBT(node.right, str);
    // if the node isn't the same as the search but there's no left or right branch
    else return false;
  }
  */
  
  /**
   * Print the elements of the binary
   * search tree in ascending order.
   */
  public void printAscending(){
    this.a(this.root); 
  }
  private void a(BTNode n){if(n==null)return;a(n.left);System.out.println(n.data.toString());a(n.right);}
  /* 
  private void printAscending(BTNode node){
    // if the node is null return
    if(node == null) return;
    // traverse down the left branch
    printAscending(node.left);
    // print the current node
    System.out.println(node.data.toString());
    // traverse down the right branch
    printAscending(node.right);
  }
  */
  
  
  /**
   * Print the elements of the binary
   * search tree in descending order.
   */  
  public void printDescending(){
    this.d(this.root);
  }
  private void d(BTNode n){if(n==null)return;d(n.right);System.out.println(n.data.toString());d(n.left);}
  /*
  private void printDescending(BTNode node){
    // if the node is null return
    if(node == null) return;
    // traverse down the right branch
    printDescending(node.right);
    // print the current node
    System.out.println(node.data.toString());
    // traverse down the left branch
    printDescending(node.left);
  }
  */

}
