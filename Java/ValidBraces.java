
import java.util.*;
public class ValidBraces {
	static String[] braces(String[] values) {
        if(values == null || values.length == 0) {
            return new String[] {};
        }
        
        String[] res = new String[values.length];
        for(int i = 0; i < values.length; i++) {
            Stack<Character> stack = new Stack<>();
            for(int j = 0; j < values[i].length(); j++) {
                if(stack.isEmpty()) {
                    stack.push(values[i].charAt(j));
                }
                else {  // if stack is not empty
                    if(stack.peek() == '{' && values[i].charAt(j) == '}' ||
                       stack.peek() == '[' && values[i].charAt(j) == ']' ||
                       stack.peek() == '(' && values[i].charAt(j) == ')'
                      ) {
                        stack.pop();
                    }
                    else {
                        stack.push(values[i].charAt(j));
                    }
                }
            }
            res[i] = (stack.isEmpty()) ? "YES" : "NO";
        }
        
        return res;
    }
}
