/*
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"
 */

public class AddBinary {
	
	public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        // traverse the longest binary backwards
        for (int i = 0; i < Math.max(a.length(), b.length()) || carry != 0; i++) { 
            int ia = (i >= a.length()) ? 0 : a.charAt(a.length() - i - 1) - '0'; //converting the char to its actual int value, otherwise the returned int is its ascii value
            int ib = (i >= b.length()) ? 0 : b.charAt(b.length() - i - 1) - '0';
            sb.insert(0, (ia + ib + carry) % 2);
            carry = (ia + ib + carry) / 2;
        }
        return sb.toString();
    }
	
	public String addBinary2(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            
            if (j >= 0) 
            	sum += b.charAt(j--) - '0';
            if (i >= 0) 
            	sum += a.charAt(i--) - '0';
            
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry == 1) 
        	sb.append(carry);
        
        return sb.reverse().toString();
    }
}
