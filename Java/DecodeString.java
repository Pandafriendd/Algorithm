/*
Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. 
Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k.
For example, there won't be input like 3a or 2[4].
 */


public class DecodeString {
	
	public static void main (String[] args) {
		String s = "3b2a4c";
		System.out.println(decode(s));  // bba
		// 3b2b4b
	}


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
