/*
 Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
 */
public class LongestPalindromicSubstring {
	
/*
Approach 1: Longest Common Substring
We could see that the longest common substring method fails when there exists a reversed copy of a non-palindromic substring in some other part of SS. 
To rectify this, each time we find a longest common substring candidate, 
we check if the substring’s indices are the same as the reversed substring’s original indices. 
If it is, then we attempt to update the longest palindrome found so far; if not, we skip this and find the next candidate.

This gives us an O(n^2) Dynamic Programming solution which uses O(n^2) space (could be improved to use O(n) space)
*/
	
/*
Approach 2: Brute force: to pick all possible starting and ending positions for a substring, and verify if it is a palindrome.
Time: O(n^3). Assume that n is the length of the input string. There are a total of Cn2 = n(n-1)/2 such substrings. 
(excluding the trivial solution where a character itself is a palindrome)
Since verifying each substring takes O(n), so O(n^3)
 */
	
/*
Approach 3: Dynamic Programming. 
To improve over the brute force solution, we first observe how we can avoid unnecessary re-computation while validating palindromes. 
Consider the case "ababa". If we already knew that "bab" is a palindrome, it is obvious that "ababa" must be a palindrome since the two left and right end letters are the same.
 
We define P(i,j) as following:

P(i,j)={
		true, if the substring Si…Sj is a palindrome
		false, otherwise.
	   }
Therefore, P(i,j)=(P(i+1,j−1) and Si == Sj)

The base cases are:
P(i,i)=true 
P(i,i+1)=(S ​i ​​ == S ​i+1 ​​ )

This yields a straight forward DP solution, which we first initialize the one and two letters palindromes, and work our way up finding all three letters palindromes, and so on...

Time: O(n^2)   Space: O(n^2)

 */

/*
 dp(i, j) represents whether s(i ... j) can form a palindromic substring, 
 dp(i, j) is true when s(i) equals to s(j) and s(i+1 ... j-1) is a palindromic substring. 
 When we found a palindrome, check if it's the longest one. Time complexity O(n^2).	
 */
	public String longestPalindromeDP(String s) {
		  int len = s.length();
		  String res = null;
		    
		  boolean[][] dp = new boolean[len][len];
		    
		  for (int i = len - 1; i >= 0; i--) { //if you start from the head of the array, you don't know dp[i + 1][j - 1] it's true or false
		    for (int j = i; j < len; j++) {
		    	//j-i == 0, only a character is a palindrome, 
		    	//j-i == 1 and s.charAt(i) == s.charAt(j), ij is a palindrome, 
		    	//j-i == 2 and s.charAt(i) == s.charAt(j), no matter what between i and j, i#j is a palindrome
		      dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
		            
		      if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
		        res = s.substring(i, j + 1);
		      }
		    }
		  }
		    
		  return res;
		}
    
	/**
     * O(n^2) Time, O(1) Space
     * Expand from center character and center of two chars
     * Update result according to the returned length
     */
    public String longestPalindromeC(String s) {
        if (s == null || s.length() == 0) return "";
        String longest = s.substring(0, 1);
        int len = s.length();
        for (int i = 0; i < len - 1; i++) {
            String s1 = expandAroundCenter(s, i, i);
            if (s1.length() > longest.length()) 
            	longest = s1;
            String s2 = expandAroundCenter(s, i, i + 1);
            if (s2.length() > longest.length()) 
            	longest = s2;
        }
        return longest;
    }

    /**
     * Search for range in both direction
     */
    private String expandAroundCenter(String s, int i, int j) {  //i: left    j: right
        int l = i;
        int r = j;
        int n = s.length();
        while (l >= 0 && r <= n - 1 && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return s.substring(l + 1, r); // note the range is from l + 1 to r - 1
    }
    
    
    /*
We observe that a palindrome mirrors around its center. 
Therefore, a palindrome can be expanded from its center, and there are only 2n - 1 such centers. 
why there are 2n - 1 but not n centers? 
The reason is the center of a palindrome can be in between two letters. 
Such palindromes have even number of letters (such as "abba") and its center are between the two 'b's.
     */
    public String longestPalindrome2222(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter2(s, i, i);
            int len2 = expandAroundCenter2(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter2(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
    
    /**
     * Manacher's Algorithm, O(n) Time.
     * S = abba => T = #a#b#b#a#.
     */
    public String longestPalindrome111(String s) {
        if (s == null || s.length() == 0) return "";

        int len = s.length();
        int max = 0; // max length
        String res = "";
        
        for (int i = 1; i <= 2 * len - 1; i++) { // skip two #s
            int count = 1;
            while (i - count >= 0 && i + count <= 2 * len && get(s, i - count) == get(s, i + count)) count++;
            count--; // there will be one extra count for the outbound #
            if (count > max) { // update max and result when longer is found
                res = s.substring((i - count) / 2, (i + count) / 2);
                max = count;
            }
        }

        return res;
    }
    
    /**
     * Insert char to the original input string
     * If the index is even, return #
     * If the index is odd, return char in the original string
     */
    private char get(String s, int i) {
        if (i % 2 == 0) return '#';
        else return s.charAt(i / 2);
    }

    /**
     * Manacher's Algorithm, O(n) Time.
     * S = abba => T = ^#a#b#b#a#$.
     * http://www.felix021.com/blog/read.php?2040
     * http://leetcode.com/2011/11/longest-palindromic-substring-part-ii.html
     */
    public String longestPalindromeB(String s) {
        String t = preProcess(s);
        int n = t.length();
        int[] p = new int[n];
        int range = 0, center = 0;

        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * center - i; // mirror of i to center
            p[i] = range > i ? Math.min(range - i, p[mirror]) : 0;
            while (t.charAt(i + 1 + p[i]) == t.charAt(i - 1 - p[i])) p[i]++;
            if (i + p[i] > range) {
                center = i;
                range = i + p[i];
            }
        }

        int maxLen = 0;
        int centerIdx = 0;
        for (int i = 1; i < n - 1; i++) {
            if (p[i] > maxLen) {
                maxLen = p[i];
                centerIdx = i;
            }
        }
        return s.substring((centerIdx - 1 - maxLen) / 2, (centerIdx - 1 + maxLen) / 2);
    }

    private String preProcess(String s) {
        int n = s.length();
        if (n == 0) return "^$";
        String res = "^";
        for (int i = 0; i < n; i++) {
            res += "#" + s.substring(i, i + 1);
        }
        res += "#$";
        return res;
    }

    
    
    
  //approach 1
  	private int lo, maxLen;
  	
  	public String longestPalindrome(String s) {
  		int len = s.length();
  		if (len < 2)
  			return s;
  		
  	    for (int i = 0; i < len-1; i++) {
  	     	extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
  	     	extendPalindrome(s, i, i+1); //assume even length.
  	    }
  	    return s.substring(lo, lo + maxLen);
  	}
  	
  	private void extendPalindrome(String s, int j, int k) {
  		while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
  			j--;
  			k++;
  		}
  		if (maxLen < k - j - 1) {
  			lo = j + 1;
  			maxLen = k - j - 1;
  		}
  	}
  	
 // approach without global variables
 	public String longestPalindrome2(String s) {
         String max = "";
         for (int i = 0; i < s.length(); i++) {
             String s1 = extend(s, i, i), s2 = extend(s, i, i + 1);
             if (s1.length() > max.length()) 
             	max = s1;
             if (s2.length() > max.length()) 
             	max = s2;
         }
         return max;
     }
     
     private String extend(String s, int i, int j) {
         for (; i >= 0 && j < s.length(); i--, j++) {
             if (s.charAt(i) != s.charAt(j)) 
             	break;
         }
         return s.substring(i + 1, j);
     }
}
