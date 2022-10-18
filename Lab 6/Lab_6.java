class Lab_6{  
  
   /**
   * Do not change the main method.
   */
  public static void main(String[] args){
    System.out.println("\n-----------Start------------\n");
    System.out.println("Testing method: countDigits");
    System.out.println("---------------------------");
    System.out.println("qnRoo0fXVt: "+countDigits("qnRoo0fXVt"));
    System.out.println("UTEP 2022 Go Miners!: "+countDigits("UTEP 2022 Go Miners!"));
    System.out.println("  :"+countDigits("  "));
    System.out.println("mtQNDhsWU1: "+countDigits("mtQNDhsWU1")); 
    
    System.out.println();    
    System.out.println("Testing method: hasCapital");
    System.out.println("-------------------------------");
    System.out.println("uteP: "+hasCapital("uteP"));
    System.out.println("CS 2401: "+hasCapital("CS 2401")); 
    System.out.println("eoenw: "+hasCapital("eoenw"));
    System.out.println("ExjQCPn: "+hasCapital("ExjQCPn"));
    
    System.out.println();      
    System.out.println("Testing method: checkPalindrome");
    System.out.println("-------------------------------");
    System.out.println("alala: "+checkPalindrome("alala"));
    System.out.println("nursesrun: "+checkPalindrome("nursesrun"));
    System.out.println("nurses Run: "+checkPalindrome("nurses Run"));
    System.out.println("level: "+checkPalindrome("level"));

    System.out.println();      
    System.out.println("Testing method: reverseString");
    System.out.println("---------------------------");
    System.out.println("xof nworb kciuq: "+reverseString("xof nworb kciuq"));
    System.out.println("cotton candy: "+reverseString("cotton candy"));
    System.out.println("ETEP reniM: "+reverseString("ETEP reniM"));
    
    System.out.println();  
    System.out.println("Testing method: updateString");
    System.out.println("-------------------------------");
    System.out.println(updateString(
          "Computer science is no more about computers than astronomy is about telescopes", 
          "aeiou"));
    System.out.println(updateString(
          "He%ll*o, W(o)rld.", "*,%()"));
    System.out.println(updateString(
          "1I2 3t4h5i6n7k8, 9t0h@e#r%e^f*ore 1I1 2a3m5.", 
          "1234567890@%#^*+"));
    System.out.println("\n-----------End------------\n");  
  }
  
  
  
  /**
   * Write a recursive method to return number of digits in a string. 
   * @param str
   * @return 
   */
  static int countDigits(String str){
    // Write your code here.

    // count holds the number of digits in a string
    int count = 0;
    // base case: if the string is empty, we don't have any more characters to check so return a 0
    if(str.isEmpty()) return 0;
    // if the first character in the string is a digit, set count to 1 and call the method using the string without the character we checked.
    if(Character.isDigit(str.charAt(0))) count = 1 + countDigits(str.substring(1));
    // if the first character isn't a digit, set count to call the method using the string without the character we checked.
    else count = countDigits(str.substring(1));

    // return the count of digits
    return count;
  }
  
  
  
  /**
   * Write a recursive method to find if a string contains
   * any capital letter (any letter between A-Z).
   * @param str
   * @return 
   */
  
  static boolean hasCapital(String str){
      // Write your code here.

      // if the character is an uppercase immediately return true
      if(!str.isEmpty() && Character.isUpperCase(str.charAt(0))) return true;
      // if the character is not uppercase call the method using the string without the character we checked.
      else if(!str.isEmpty()) return hasCapital(str.substring(1));

      // base case: if the string is empty, we don't have any more characters to check
      return false;
  }
  
  /** 
   * Write a recursive method to check if a given string
   * is a Palindrome.
   * @param str
   * @return 
   */
  static boolean checkPalindrome(String str){
     // Write your code here.

      //base case: if there is less than 2 characters left than the string is a palindrome so return true
      if(str.length() < 2) return true;
      //if the character at the beginning of the string matches the one at the end of the string
      //continue looking by calling the method with a string excluding the characters we already checked.
      else if(str.charAt(0) == str.charAt(str.length() - 1)) return checkPalindrome(str.substring(1, str.length() - 1));

      //if the character at the beginning of the string does not match the one at the end of the string return false
      return false;
  }
  
  
  /** 
   * Write a recursive method to reverse the order
   * of a given string.
   * @param str
   * @return 
   */
  
  static String reverseString(String str){
       // Write your code here.

       // base case: if the given string is empty there's nothing left to check so return an empty string
      if(str.isEmpty()) return "";
      // set the string to the last character and then call the method with the the string minus the last character
      str = str.charAt(str.length() - 1) + reverseString(str.substring(0, str.length() - 1));

      // return the string
      return str;
  }
  
  /**
   * Write a recursive method that will return 
   * a string containing only the characters of a given
   * string but not in another string.
   * 
   * You are not allowed to use the replace or 
   * replaceAll method of the String class.
   * 
   * The returned string must contain the characters 
   * of the given string in the same sequence they
   * appear in the given String.
   */
  
  static String updateString(String given,
          String unexpectedCh){
    // Write your code here.

    // base case: if the given string is empty there's nothing left to check so return an empty string
    if(given.isEmpty()) return "";
    // if the string of unexpected chars contains the first character from the given string
    // set given string to call the method with the string minus the first character
    if(unexpectedCh.contains(given.subSequence(0, 1))) given = updateString(given.substring(1), unexpectedCh);
    // if the string of unexpected chars doesn't contain the first character from the given string
    // set given string to the first character and call the method with the string minus the first character
    else given = given.subSequence(0, 1) + updateString(given.substring(1), unexpectedCh);

    // return the given string
    return given;
  }
}

