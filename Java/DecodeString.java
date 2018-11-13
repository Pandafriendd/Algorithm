/*
Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. 
Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
For example, there won't be input like 3a or 2[4].

Examples:
s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */

import java.util.*;

public class DecodeString {
    	
	public static void main (String[] args) {
		String s = "3[a]2[b4[F]c]";
		System.out.println(decode00(s));  // bba
		// 3b2b4b
	}
	
	
	
	/*
		using one stack for storing the count, one StringBuilder for storing the chars 
		read through the input string, for each step:
			case 1: c is digit: push it into countStack
			case 2: c is char:  append it into sb
			case 3: c is [ or ]
				3.1: [: push num into countStack
				3.2: ]: should append to res
				
	 	cannot handle 3[a2[c]]... but 3[1[a]2[ac]]
	 */
	public static String decode0(String s) {  
		Deque<StringBuilder> countStack = new ArrayDeque<>();
		StringBuilder res = new StringBuilder();
		
		int i = 0;
		StringBuilder sb = new StringBuilder();  // store temp nums and chars
		while (i < s.length()) {
			char c = s.charAt(i);		
			if (Character.isDigit(c)) {  // case 1
				sb.append(c);
			} else if (!Character.isDigit(c) && c != '[' && c != ']') { // case 2
				sb.append(c);
			} else {  // case 3
				if (c == '[') {  // num is end
					countStack.push(sb);
				} else {   // c == ']'
					String countString = countStack.pop().toString();
					int count = Integer.parseInt(countString);
					String chars = "";
					if (sb.length() == 0) { // !!! if sb == "" meaning we meet scenarios like ]], should handle this scenario differently
						chars = res.toString();
						count--;
					} else {
						chars = sb.toString();
					}
					while (count > 0) {
						res.append(chars);
						count--;
					}
				}
				sb = new StringBuilder();
			}
			i++;
		}
		
		return res.toString();
	}
	
	// improved 3[a2[c]]  => accaccacc
	// 3[a]2[b4[F]c]
	public static String decode00(String s) {  
		StringBuilder chars = new StringBuilder();
        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<StringBuilder> resStack = new ArrayDeque<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            } else if (s.charAt(idx) == '[') {
                resStack.push(chars);
                chars = new StringBuilder();;
                idx++;
            } else if (s.charAt(idx) == ']') {
                StringBuilder temp = resStack.pop();
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(chars.toString());
                }
                chars = temp;
                idx++;
            } else {  // c == char
            	chars.append(s.charAt(idx++));
            }
        }
        return chars.toString();
	}
	
	// others solution
	private int i = 0;
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                i++;
                String str = decodeString(s);
                for (int k = 0; k < num; k++) 
                    sb.append(str);
                num = 0;
            } else if (c == ']') {
                return sb.toString();
            } else {  // c is char
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
	

	// didn't handle brackets: [], not quite correct solution
  public static String decode(String s) {
    // base case
    if (s.length() == 1) {
      return s;
    }

    StringBuilder sb = new StringBuilder();

    if (!Character.isDigit(s.charAt(0))) {
      sb.append(s.charAt(0));
      sb.append(decode(s.substring(1)));
    } else {
      int i = 0;
      int num = 0;
      while (Character.isDigit(s.charAt(i))) {
        num = num * 10 + s.charAt(i) - '0';
        i++;
      }
      String left = decode(s.substring(i));
      for (int j = num; j > 0; j--) {
        sb.append(left);
      }
    }
    return sb.toString();
  }
  

}
