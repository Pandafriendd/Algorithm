/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:
Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:
Input: "()"
Output: true

Example 2:
Input: "()[]{}"
Output: true

Example 3:
Input: "(]"
Output: false

Example 4:
Input: "([)]"
Output: false

Example 5:
Input: "{[]}"
Output: true
 */

import java.util.*;
public class ValidParentheses {
	
	public static boolean isValid(String s) {
        if(s == null || s.length() == 0)
            return true;
        
        Stack<Character> stack = new Stack<>();
        
        if(s.charAt(0) == ')' || s.charAt(0) == '}' || s.charAt(0) == ']')
            return false;
        
        for(int i = 0; i < s.length(); i++) {
            if(stack.isEmpty())
                stack.push(s.charAt(i));
            else {
                if(stack.peek() == '(' && s.charAt(i) == ')' || 
                        stack.peek() == '[' && s.charAt(i) == ']' || 
                        stack.peek() == '{' && s.charAt(i) == '}') {
                    stack.pop();
                }
                else {
                    stack.push(s.charAt(i));
                }
            }
        }
        
        return stack.isEmpty();
    }
	
	public static void main(String[] args) {
		isValid("{[]}");
	}
}
