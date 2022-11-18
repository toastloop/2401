import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class StackRunner {

    public static void main(String[] args) throws Exception{

        // open the input file in the file reader.
        FileReader fileReader = new FileReader("input.txt");
        // open the buffered reader using the file reader
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        // read the lines from the file into an array
        Object[] lines = bufferedReader.lines().toArray();
        // close the buffered reader
        bufferedReader.close();
        // close the file reader
        fileReader.close();

        // loop through each line
        for (Object line : lines) {

            // skip empty lines
            if(line.toString().trim().isEmpty()) continue;

            // grab the first character as the operator
            char operator = line.toString().charAt(0);
            // set expression by removing the operator from the beginning
            String expression = line.toString().substring(1).trim();

            // print the currrent line
            System.out.println("The line is: " + line);

            // switch which function to call based on the operator
            switch(operator){

                // postfix
                case 'p':

                    // evaluate the postfix expression
                    double result = evaluatePostfix(expression);
                    // if the result isn't negative infinity (the error state) print the result
                    if(result != Double.NEGATIVE_INFINITY) System.out.println("The evaulation of this postfix expression is: " + result);
                    // otherwise print an error message
                    else System.out.println("The line contains an invalid postfix expression.");

                    break;
                
                // balanced
                case 'b':

                    // print the checking message
                    System.out.println("Checking the balance of the expression.");
                    // print the expression without the operator
                    System.out.println("The expression: " + expression);
                    // print the state of balance of the expression
                    System.out.println("The expression is: "+ (isBalanced(expression) ? "balanced" : "imbalanced") + ".");

                    break;

                // neither
                default:

                    // print that we are skipping this line
                    System.out.println("The line does not start with a 'b' or a 'p'. This program will skip this line.");

                    break;
    
            }

            // print the bottom line
            System.out.println("-------------------------");

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

        // create a stack to hold the numbers
        Stack stack = new Stack();
        // parse the string into tokens
        StringTokenizer tokenizer = new StringTokenizer(postfix);
        // holds a flag if the postfix is invalid
        boolean invalid = false;

        // loop through the tokenns in the string
        while (tokenizer.hasMoreTokens()) {
    
            // grab the current token
            String token = tokenizer.nextToken();
    
            // if the token is a number
            if(isNumber(token)){

                // push the token to the stack
                stack.push(Double.parseDouble(token));

            }
            // if the token is a valid operation
            else if(token.matches("[\\*\\/\\-\\+]")){

                // holds the first value off the stack or negative infinity if the stack is empty
                double first = (!stack.isEmpty()) ? Double.parseDouble(stack.pop().toString()) : Double.NEGATIVE_INFINITY;
                // holds the second value off the stack or negative infinity if the stack is empty
                double second = (!stack.isEmpty()) ? Double.parseDouble(stack.pop().toString()) : Double.NEGATIVE_INFINITY;

                // check if the first or second value was unable to pull a value off of the stack
                if(first == Double.NEGATIVE_INFINITY || second == Double.NEGATIVE_INFINITY){

                    // set the invalid flag to true
                    invalid = true;
                    // print the error
                    System.out.println("Not enough operand in stack to apply the operator " + token);
                    // break the loop
                    break;

                }

                // find the correct operation and execute it, push the result back onto the stack
                switch(token){

                    // multiplication
                    case "*": 
                        stack.push(Double.toString((second * first))); 
                        break;

                    // division
                    case "/": 
                        stack.push(Double.toString((second / first))); 
                        break;

                    // addition
                    case "+": 
                        stack.push(Double.toString((second + first))); 
                        break;

                    // subtraction
                    case "-": 
                        stack.push(Double.toString((second - first))); 
                        break;

                }

            }
            // if the token is not a valid operator
            else {

                // set the invalid flag to true
                invalid = true;
                // print out the error
                System.out.println("The operator " + token + " is not recognizable.");
                // break the loop
                break;

            }

        }

        // if the stack isn't empty, grab the remaining value off of the stack otherwise set it to negative infinity
        double result = (!stack.isEmpty()) ? Double.parseDouble(stack.pop().toString()) : Double.NEGATIVE_INFINITY;
        
        // if the stack isn't empty, we had too many numbers in the expression
        if(!stack.isEmpty()) System.out.println("The postfix expression has extra elements.");

        // if the result is valid and the stack is empty return the result otherwise return negative infinity
        return (!invalid && stack.isEmpty()) ? result : Double.NEGATIVE_INFINITY;

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

        // create a stack to hold the opening parenthesis
        Stack stack = new Stack();

        // loop through each character in the string
        for(char currChar : expr.toCharArray()){

            // if it's an opening parenthesis, add it to the stack
            if(currChar == '(') stack.push("(");

            // if it's a closing parenthesis
            else if(currChar == ')')

                //  pop an element off of the stack to match the parenthesis if the stack isn't empty
                if(!stack.isEmpty()) stack.pop();
                // return false otherwise because there is no matching parenthesis on the stack
                else return false;

        }

        // return true if the stack is empty because everything is matched otherwise return false
        return stack.isEmpty() ? true : false;

    }

}
