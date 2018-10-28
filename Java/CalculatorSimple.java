
import java.util.*;

//my good solution
public class CalculatorSimple {
	public static int calculator(String input) {
	    int num1 = 0;
	    int num2 = 0;
	    char preop = ' ';
	    boolean isNum1End = false;
	    for (int i = 0; i < input.length(); i++) {
	      char c = input.charAt(i);
	      if (Character.isDigit(c)) {
	    	if (!isNum1End) {
	    		num1 = num1 * 10 + c - '0';
	    	}
	    	else {
	    		num2 = num2 * 10 + c - '0';
	    	}  
	      }     
	      if (!Character.isDigit(c) || i == input.length() - 1) { // c is not Digit, else is WRONG!
	    	if (!isNum1End) {
	    		isNum1End = true;
	    	}
	        if (preop == '+') {
	          num1 = num1 + num2;
	        }
	        else if (preop == '-') {
	          num1 = num1 - num2;
	        }
	        preop = c;
	        num2 = 0;
	      }
	    }
	    return num1;	    
	  }
	  
	  public static void main(String[] args) {
	    int res = calculator("5+60");
	    System.out.println(res);
	  }
}
