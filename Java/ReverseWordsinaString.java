/*
 Given an input string, reverse the string word by word.

Example:  

Input: "the sky is blue",
Output: "blue is sky the".
Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.
Follow up: For C programmers, try to solve it in-place in O(1) space.
 */
public class ReverseWordsinaString {
	
	/*
	 * Trim input string
	 * split it with a space
	 * traversal backward
	 * trim result to remove last space
	 */
	public String reverseWords(String s) {
		if(s == null || s.length() == 0)
			return "";
		
		s = s.trim();
		StringBuilder sb = new StringBuilder();
		//String[] words = s.split(" "); //this code is wrong when there are many spaces between word and word, since it only split one space 
		String[] words = s.split(" +");   // !!!! splitting on the regex for one-or-more whitespace
		for(int i = words.length - 1; i >= 0; i--) {
			if(!words[i].equals("")) {
				sb.append(words[i]);
				if(i != 0)
					sb.append(" ");
			}
		}
		
		return sb.toString();
	}
	
	//this is not the fastest or most memory efficient way to solve the problem, but optimizations should only be done when they are needed. Readable code is usually more important than efficient code.
	public String reverseWords111(String s) {
		String[] parts = s.trim().split(" +");  //regular expression. + means at least 1 so in this case " +" means at least one space
		String out = "";
		for (int i = parts.length - 1; i > 0; i--) {
		    out += parts[i] + " ";
		}
		return out + parts[0];
	}
	
	
	/**
	* If space, continue
    * If not, get the word and insert to the front of result
    * note that result may not contain spaces before or after
    */
   public String reverseWords2(String s) {
       if (s == null || s.length() == 0) return "";
       String res = "";
       for (int i = 0; i < s.length(); i++) {
           char c = s.charAt(i);
           if (c == ' ') 
        	   continue;
           else {
               StringBuilder word = new StringBuilder();
               while (i < s.length()) {
                   c = s.charAt(i);
                   if (c == ' ') 
                	   break; // now i at space
                   word.append(c);
                   i++;
               }
               res = res.length() == 0 ? word.toString() : word.toString() + " " + res; // insert to front of res
           }
       }
       return res;
   }
   
   // approach 3: 1. reverse whole string. 2. reverse word one by one
   public String reverseWords222(String s) {
	   if (s == null) return null;
	    
	    char[] a = s.toCharArray();
	    int n = a.length;
	    
	    // step 1. reverse the whole string
	    reverse(a, 0, n - 1);
	    // step 2. reverse each word
	    reverseWords(a, n);
	    // step 3. clean up spaces
	    return cleanSpaces(a, n);
   }
	  
	void reverseWords(char[] a, int n) {
	    int i = 0, j = 0;
	      
	    while (i < n) {
	      while (i < j || i < n && a[i] == ' ') i++; // skip spaces
	      while (j < i || j < n && a[j] != ' ') j++; // skip non spaces
	      reverse(a, i, j - 1);                      // reverse the word
	    }
	  }
	  
	  // trim leading, trailing and multiple spaces
	  String cleanSpaces(char[] a, int n) {
	    int i = 0, j = 0;
	      
	    while (j < n) {
	      while (j < n && a[j] == ' ') j++;             // skip spaces
	      while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
	      while (j < n && a[j] == ' ') j++;             // skip spaces
	      if (j < n) a[i++] = ' ';                      // keep only one space
	    }
	  
	    return new String(a).substring(0, i);
	  }
	  
	  // reverse a[] from a[i] to a[j]
	  private void reverse(char[] a, int i, int j) {
	    while (i < j) {
	      char t = a[i];
	      a[i++] = a[j];
	      a[j--] = t;
	    }
	  }
}
