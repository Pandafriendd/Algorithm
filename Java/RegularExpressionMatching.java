/*
Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:
s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.

Example 1:
Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

Example 3:
Input:
s = "ab"
p = ".*"
Output: true
Explanation: .* means zero or more (*) of any character (.)

Example 4:
Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".

Example 5:
Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
 */

enum Result {
    TRUE, FALSE
}

/*
problem: 
1. a** valid?
 */
public class RegularExpressionMatching {
	
	/*
Approach 1: Recursion
Intuition
If there were no Kleene stars (the * wildcard character for regular expressions), 
the problem would be easier - we simply check from left to right if each character of the text matches the pattern.
When a star is present, we may need to check many different suffixes of the text and see if they match the rest of the pattern. 
A recursive solution is a straightforward way to represent this relationship.

If a star is present in the pattern, it will be in the second position p[1]. 
Then, we may ignore this part of the pattern, or delete a matching character in the text. 
If we have a match on the remaining strings after any of these operations, then the initial inputs matched.
	 */
	public boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) 
        	return text.isEmpty();
        
        boolean first_match = (!text.isEmpty() &&
                               (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
        
        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } 
        else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }
	
	/*
Approach 2: Dynamic Programming
As the problem has an optimal substructure, it is natural to cache intermediate results. 
We ask the question: dp(i, j): does text[i:] and pattern[j:] match? 
We can describe our answer in terms of answers to questions involving smaller strings.

We proceed with the same recursion as in Approach 1, instead of call match(text[i:], pattern[j:]) many times, 
we use dp(i, j) to handle those calls , saving us expensive string-building operations and allowing us to cache the intermediate results.
	 */
	
	//Top-Down Variation
    public boolean isMatch2(String text, String pattern) {
    	Result[][] memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern, memo);
    }

    public boolean dp(int i, int j, String text, String pattern, Result[][] memo) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        if (j == pattern.length()){
            ans = i == text.length();
        } 
        else{
            boolean first_match = (i < text.length() &&
                                   (pattern.charAt(j) == text.charAt(i) ||
                                    pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                ans = (dp(i, j+2, text, pattern, memo) ||
                       first_match && dp(i+1, j, text, pattern, memo));
            } 
            else {
                ans = first_match && dp(i+1, j+1, text, pattern, memo);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }
    
    
    //Bottom-Up Variation
    //Time: O(M*N), let M,N be the lengths of the text and the pattern respectively
    public boolean isMatch3(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--) {
            for (int j = pattern.length() - 1; j >= 0; j--) {
                boolean first_match = (i < text.length() &&
                                       (pattern.charAt(j) == text.charAt(i) ||
                                        pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*'){
                    dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j];
                } 
                else {
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }
    
    //Bootom-up 2
    public boolean isMatch4(String text, String pattern) {
    	boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
    	dp[0][0] = true;
    	
    	// deals with text is empty string and patterns like a* or a*b* or a*b*c*
    	for(int i = 1; i < dp[0].length; i++) {
    		if(pattern.charAt(i - 1) == '*') {
    			dp[0][i] = dp[0][i - 2];
    		}
    	}
    	
    	for(int i = 1; i < dp.length; i++) {
    		for(int j = 1; j < dp[0].length; j++) {
    			if(pattern.charAt(j - 1) == '.' || pattern.charAt(j - 1) == text.charAt(i - 1)) {
    				dp[i][j] = dp[i - 1][j - 1];
    			}
    			else if(pattern.charAt(j - 1) == '*') {
    				dp[i][j] = dp[i][j - 2]; // 0 occurrence
    				if(pattern.charAt(j - 2) == '.' || pattern.charAt(j - 2) == text.charAt(i - 1)) {
    					dp[i][j] = dp[i][j] | dp[i - 1][j];
    				}
    			}
    			else {
    				dp[i][j] = false;
    			}
    		}
    	}
    	
    	return dp[text.length()][pattern.length()];
    }
    
    public static void main(String[] args) {
    	RegularExpressionMatching r = new RegularExpressionMatching();
    	System.out.println(r.isMatch4("aaaasfdsdfasdfsasdasdsa", ".*"));
    }
    
}
