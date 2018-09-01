/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
Example 1:
Input: "3+2*2"
Output: 7

Example 2:
Input: " 3/2 "
Output: 1

Example 3:
Input: " 3+5 / 2 "
Output: 5

Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.
 */
import java.util.*;
public class BasicCalculatorII {
	public int calculate(String s) {
		s = s.replaceAll(" ", ""); //clear the string first, it does not add any time complexity to the original function and therefore has little performance impact.
		
		int len = s.length();
		if(s == null || s.length() == 0)
			return 0;
		
		Stack<Integer> stack = new Stack<>();
		int num = 0;
		char sign = '+';
		
		/*
		 lets say: + a * b - c 
		 So it pushes +a into the stack when it encounters * and assign the sign to * after that , 
		 then it calculates b ...and when it encounters - it pops a out and does the multiplication and pushes it back into stack.
		 */
		for(int i = 0; i < len; i++) {
			if(Character.isDigit(s.charAt(i))) {
				num = num * 10 + s.charAt(i) - '0';  // numbers in the input can contain multiple digits like 12+3
			}
			if(!Character.isDigit(s.charAt(i)) || i == len - 1) {
				if(sign == '-') {
					stack.push(-num);
				}
				if(sign == '+') {
					stack.push(num);
				}
				if(sign == '*') {
					stack.push(stack.pop() * num);
				}
				if(sign == '/') {
					stack.push(stack.pop() / num);
				}
				sign = s.charAt(i);
				num = 0;
			}
		}
		
		int res = 0;
		for(int i : stack) {
			res += i;
		}
		
		return res;
	}
	
	
	// without stack
	public int calculate2(String s) {
		s = s.replaceAll("\\s", "");
		
        if(s == null || s.length() == 0) {
            return 0;
        }
        
        long prevNum = 0;
        int sum = 0;
        char prevOp = '+';
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                int val = c - '0';
                while(i + 1 < s.length() && Character.isDigit(s.charAt(i+1))) {
                    val = val * 10 + (s.charAt(i+1) - '0');
                    i++;
                }
                if (prevOp == '+') {
                    sum += prevNum;
                    prevNum = val;
                } else if (prevOp == '-') {
                    sum += prevNum;
                    prevNum = -val;
                } else if (prevOp == '*') {
                    prevNum = prevNum * val;
                } else if (prevOp == '/') {
                    prevNum = prevNum/val;
                }
            } 
            else {
                prevOp = c;
            }
        }
        
        sum += prevNum;
        return sum;
    }
}
