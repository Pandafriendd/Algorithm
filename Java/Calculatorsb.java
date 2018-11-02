
import java.util.*;

//my dumb solution
public class calculatorsb {
	public static int calculator(String input) {
	    int num1 = Integer.MAX_VALUE;
	    int num2 = 0;
	    char op = '*';
	    boolean isNum1End = false;
	    String n1 = "";
		String n2 = "";
	    for (int i = 0; i < input.length(); i++) {
	      char c = input.charAt(i);
	      
	      if (!isNum1End && Character.isDigit(c)) {
	        n1 += c;
	      }
	      else if (Character.isDigit(c)) {
	        n2 += c;
	      }
	      else { // c is not Digit
	    	isNum1End = true;
	        if (op == '+') {
	          num1 = (num1 == Integer.MAX_VALUE) ? Integer.parseInt(n1) : num1;
		      num2 = Integer.parseInt(n2);
	          num1 = num1 + num2;
	          n2 = "";
	        }
	        else if (op == '-') {
	          num1 = (num1 == Integer.MAX_VALUE) ? Integer.parseInt(n1) : num1;
		      num2 = Integer.parseInt(n2);
	          num1 = num1 - num2;
	          n2 = "";
	        }

	        if (c == '+') {
	          op = '+';
	        }
	        else if (c == '-') {
	          op = '-';
	        }
	      }
	      
	      if (i == input.length() - 1) {  // !!!!! don't forget!!
	    	  if (op == '+') {
	    		  num1 = (num1 == Integer.MAX_VALUE) ? Integer.parseInt(n1) : num1;
			      num2 = Integer.parseInt(n2);
		          num1 = num1 + num2;
		      }
		      else if (op == '-') {
		    	  num1 = (num1 == Integer.MAX_VALUE) ? Integer.parseInt(n1) : num1;
			      num2 = Integer.parseInt(n2);
		          num1 = num1 - num2;
		      }
	      }
	    }
	    return num1;	    
	  }
	  
	  public static void main(String[] args) {
	    int res = calculator("1-10-10+19+1-1");
	    System.out.println(res);
	  }
}
