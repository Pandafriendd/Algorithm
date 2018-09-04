/*
Given two strings s and t, determine if they are both one edit distance apart.

Note: 

There are 3 possiblities to satisify one edit distance apart:
Insert a character into s to get t
Delete a character from s to get t
Replace a character of s to get t

Example 1:
Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.

Example 2:
Input: s = "cab", t = "ad"
Output: false
Explanation: We cannot get t from s by only one step.

Example 3:
Input: s = "1203", t = "1213"
Output: true
Explanation: We can replace '0' with '1' to get t.
 */
public class OneEditDistance {
	
	/*
	 * There're 3 possibilities to satisfy one edit distance apart: 
	 * 
	 * 1) Replace 1 char:
	 	  s: a B c
	 	  t: a D c
	 * 2) Delete 1 char from s: 
		  s: a D  b c
		  t: a    b c
	 * 3) Delete 1 char from t
		  s: a   b c
		  t: a D b c
	 */
	public boolean isOneEditDistance(String s, String t) {
	    for (int i = 0; i < Math.min(s.length(), t.length()); i++) { 
	    	if (s.charAt(i) != t.charAt(i)) {
	    		if (s.length() == t.length()) // s has the same length as t, so the only possibility is replacing one char in s and t
	    			return s.substring(i + 1).equals(t.substring(i + 1));
	    		
				else if (s.length() < t.length()) // t is longer than s, so the only possibility is deleting one char from t
					return s.substring(i).equals(t.substring(i + 1));	 
	    		
				else // s is longer than t, so the only possibility is deleting one char from s
					return t.substring(i).equals(s.substring(i + 1));
	    	}
	    }
	    
	    //All previous chars are the same, the only possibility is deleting the end char in the longer one of s and t. 
	    //In other words, longer one should only has only one char more than smaller one, otherwise return false
	    return Math.abs(s.length() - t.length()) == 1;        
	}
	//assume s is shorter
	public boolean isOneEditDistance2(String s, String t) {
    	if(s==null || t==null) 
    		return false;
    	if(s.length()>t.length()) 
    		return isOneEditDistance(t, s);//assume s is shorter
    	
        for(int i=0;i<s.length();i++) {
        	if(s.charAt(i)!=t.charAt(i)) {
        		return s.substring(i).equals(t.substring(i+1)) ||  // t delete one
        				s.substring(i+1).equals(t.substring(i+1)); // replace one (same len)
        	}
        }
        if(t.length() == s.length() + 1) //special case
        	return true;
        return false;
    }
	
	
	/*
The basic idea is we keep comparing s and t from the beginning, once there's a difference, 
we try to 
1. replace s(i) with t(j) or 
2. insert t(j) to s(i) 
and see if the rest are the same.
Example: i and j are the two pointers of S and T, we found that 'b' != 'c' and we try to replace it:

     i                           i
S: a c d      replace       S: a b d
T: a b c d   --------->     T: a b c d    --->  "d" != "cd", no good
     j                           j
now we try to insert T(j) to S(i) and we get:

     i                           i
S: a c d      insert        S: a b c d
T: a b c d   --------->     T: a b c d    --->  "cd" == "cd", viola!
     j                           j
To keep the code simple, we make s is always shorter than t, so we don't need to try deletion.
	 */
	public boolean isOneEditDistance3(String s, String t) {
		  if (s == null || t == null)
		    return false;
		      
		  if (s.length() > t.length())
		    return isOneEditDistance(t, s);
		      
		  int i = 0, j = 0;
		  
		  while (i < s.length() && j < t.length()) {
		    if (s.charAt(i) != t.charAt(j)) {
		      // we try to replace s[i] with t[j] or insert t[j] to s[i] then compare the rest and see if they are the same
		      return s.substring(i + 1).equals(t.substring(j + 1)) ||
		             s.substring(i).equals(t.substring(j + 1));
		    }
		    
		    i++; 
		    j++;
		  }
		  
		  return t.length() - j == 1;
		}
}
