
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;


class StackRunner{
  
  public static void main(String[] args) throws Exception{
    // Write your code    
    String[] strArray = {
      "b (()(()))(())",
      "b ())()(())",
      "b(i-j)+3(10-8)-2(3+1)",
      "b (())((()()",
      "b (()()))()"
    };
    boolean[] expected = {
      true,
      false,
      true,
      false,
      false
    };
    for (int i = 0; i < strArray.length; i++) {
      System.out.println(strArray[i] + " : " + (isBalanced(strArray[i]) == expected[i] ? "PASSED" : "FAILED"));
    }

    String[] strArray2 = {
      "p 10 2 / 5 *",
      "p 5 10 - 3 9 - * 5 4 - 4 2 / + / 2 27 + 300 3 / / -",
      "p t b *",
      "p 15 8 / + ",
      "p 15 8 4 / +",
      "p 5 10 / 11"
    };
    double[] expected2 = {
      25.0,
      9.71,
      0.0,
      0.0,
      17.0,
      0.0
    };
    for (int i = 0; i < strArray.length; i++) {
      System.out.println(strArray2[i] + " : " + (evaluatePostfix(strArray2[i]) == expected2[i] ? "PASSED" : "FAILED"));
    }

    String[] strArray3 = {
      "b (()(()))(())",
      "b ())()(())",
      "b(i-j)+3(10-8)-2(3+1)",
      "b (())((()()",
      "b (()()))()",
      "(()()))()",
      "p 10 2 / 5 *",
      "p 5 10 - 3 9 - * 5 4 - 4 2 / + / 2 27 + 300 3 / / -",
      "p t b *",
      "p 15 8 / + ",
      "p 15 8 4 / +",
      "p 5 10 / 11"
    };
    boolean[] expected3 = {
      true,
      true,
      true,
      true,
      true,
      true,
      true,
      true,
      true,
      true,
      true,
      true      
    };
    for (int i = 0; i < strArray3.length; i++) {
      System.out.println("----------------");
      System.out.println("The line is: " + strArray3[i]);
      boolean valid = true;
      boolean balanced = true;
      StringTokenizer tokenizer = new StringTokenizer(strArray3[i].replaceAll("[^A-za-z ]", ""));
      if(!strArray3[i].replaceAll("[^bp]", "").isEmpty()){
        
      }
      else while(tokenizer.hasMoreTokens()){
        String token = tokenizer.nextToken();
        if(token.contains("p")){
          double result = evaluatePostfix(strArray3[i].replaceAll("[A-Za-z]", ""));
          System.out.println("The evaulation of this postfix expression is: " + result);
        }
        else if(token.contains("b")){
          System.out.println("Checking the balance of the expression.");
          System.out.println("The expression: " + strArray3[i].substring(1).trim());
          boolean balance = isBalanced(strArray3[i]);
          System.out.println("The expression is: "+ (balanced ? "balanced" : "imbalanced") + ".");
        }
        else{
          System.out.println("The operator " + token + " is not recognizable.");
          break;
        }
      }
      //System.out.println(strArray3[i] + " : " + (valid == expected3[i] ? "PASSED" : "FAILED"));
    }
    

  } 
  
  /**
   * Evaluate the postfix expression in the incoming String.
   * Operators and operands are separated by spaces. See
   * the assignment description for further details.
   * @param postfix
   * @return the evaluated postfix value.
   */  
  static double evaluatePostfix(String postfix){
    Stack stack = new Stack();
    StringTokenizer tokenizer = new StringTokenizer(postfix);
    boolean invalid = false;
    double result = 0.0;
    while (tokenizer.hasMoreTokens()) {
      String token = tokenizer.nextToken();
      double first = 0.0;
      double second = 0.0;
      if(isNumber(token)) stack.push(Double.parseDouble(token));
      else if(!Character.isLetter(token.charAt(0))){
        if(stack.isEmpty()){
          invalid = true;
          System.out.println("Not enough operand in stack to apply the operator " + token);
          break;
        }
        else first = Double.parseDouble(stack.pop().toString());
        if(stack.isEmpty()){
          invalid = true;
          System.out.println("Not enough operand in stack to apply the operator " + token);
          break;
        }
        else second = Double.parseDouble(stack.pop().toString());

        if(token.equals("^")){
          stack.push(Double.toString(Math.pow(second, first)));
        }
        else if(token.equals("*")){
          stack.push(Double.toString((second * first)));
          
        }
        else if(token.equals("/")){
          stack.push(Double.toString((second / first)));
          
        }
        else if(token.equals("+")){
          stack.push(Double.toString((second + first)));
        }
        else if(token.equals("-")){
          stack.push(Double.toString((second - first)));
        }
        else{
          invalid = true;
          System.out.println("Not enough operand in stack to apply the operator " + token);
          break;
        }

      }
      else if(!token.equals("p") && !token.equals("b")){

      }
    }

    if(!stack.isEmpty()){
      result = Double.parseDouble(stack.pop().toString());
      
    }
    if(!stack.isEmpty()){
      System.out.println("The postfix expression has extra elements.");
      result = 0.0;
    }

    if(invalid){
      System.out.println("The line contains an invalid postfix expression.");
      result = 0.0;
    }
    return result;
  }
  
  /**
   * The method checks if a sting is a double 
   * precision number.
   * @param tk
   * @return true if the param string is a double precision number
   *         false otherwise.
   */
  static boolean isNumber(String tk){
    if (tk==null)
      return false;
    tk=tk.trim();
    if (tk=="")
      return false;
    
    try{
      double number = Double.parseDouble(tk);
    }catch(NumberFormatException excp){
      return false;
    }
    return true;
  }
  
  /**
   * The method should consider only start '(' and
   * end parentheses ')'. Other characters in the
   * incoming parameter string should be ignored.
   * The method must use a Stack to determine
   * if the incoming string contains a balanced
   * expression or not. See
   * the assignment description for further details.   
   * @param expr
   * @return true if the expression in the String is balanced
   * otherwise, false.
   */
  static boolean isBalanced(String expr){
   int count = 0;
   for(char currChar : expr.toCharArray()){
    if(currChar == '(') count++;
    if(currChar == ')') count--;
    if(count < 0) return false;
   }
   return (count > 0) ? false : true;
  }
  
  
}


