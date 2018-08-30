/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false
 */
public class ValidPalindrome {
	public boolean isPalindrome(String s) {
        if(s.isEmpty())
        	return true;
        
        int start = 0;
        int end = s.length() - 1;
        char cStart, cEnd;
        while(start < end) {
        	cStart = s.charAt(start);
        	cEnd = s.charAt(end);
        	if(!Character.isLetterOrDigit(cStart)) { // not alphanumeric characters at start
        		start++;
        	}
        	else if(!Character.isLetterOrDigit(cEnd)) { // not alphanumeric characters at end
        		end--;
        	}
        	else {
        		if(Character.toLowerCase(cStart) != Character.toLowerCase(cEnd))
        		{
        			return false;
        		}
        		start++;
        		end--;
        	}
        }
        return true;
    }
	
	// without using built-in method:
	public boolean isPalindrome2(String s) {
		if(s.isEmpty())
			return true;
		s = clearString(s);
		int start = 0, end = s.length() - 1;
		while(start < end) {
			if(s.charAt(start++) != s.charAt(end--))
				return false;
		}
		return true;
	}
	
	// convert string with only lower case and no special character
	private String clearString(String s) {
		String res = "";
		for(int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if((ch >= 'a' && s.charAt(i) <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9'))
				res += Character.toLowerCase(ch);
		}
		return res;
	}
}
