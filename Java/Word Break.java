/*
Word Break
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
*/

class WordBreak {


	/*
Approach 1: Brute Force
Algorithm
The naive approach to solve this problem is to use recursion and backtracking. 
For finding the solution, we check every possible prefix of that string in the dictionary of words, 
if it is found in the dictionary, then the recursive function is called for the remaining portion of that string. 
And, if in some function call it is found that the complete string is in dictionary, then it will return true.

Time complexity: O(n^n) Consider the worst case where s = "aaaaaaa" 
and every prefix of s is present in the dictionary of words, then the recursion tree can grow upto n^n
Space complexity: O(n). The depth of the recursion tree can go upto n. 
	*/
    public boolean wordBreak(String s, List<String> wordDict) {
        return helper(s, new HashSet<>(wordDict), 0);
    }

    private boolean helper(String s, Set<String> wordDictSet, int start) {
    	if(start == s.length())
    		return true;

    	for(int end = start + 1; end <= s.length(); end++) {
    		if(wordDictSet.contains(s.substring(start, end)) && helper(s, wordDictSet, end)) {
    			return true;
    		}
    	}

    	return false;
    }


    /*
Approach 2: Recursion with memoization
Algorithm
In the previous approach we can see that many subproblems were redundant, 
i.e we were calling the recursive function multiple times for a particular string. 
To avoid this we can use memoization method, where an array memomemo is used to store the result of the subproblems. 
Now, when the function is called again for a particular string, 
value will be fetched and returned using the memo array, if its value has been already evaluated.
With memoization many redundant subproblems are avoided 
and recursion tree is pruned and thus it reduces the time complexity by a large factor.

Time complexity : O(n^2) Size of recursion tree can go up to n^2
​Space complexity : O(n). The depth of recursion tree can go up to n. 
    */
	public boolean wordBreak(String s, List<String> wordDict) {
        return helper(s, new HashSet(wordDict), 0, new Boolean[s.length()]);
    }

    public boolean helper(String s, Set<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end)) && helper(s, wordDict, end, memo)) {
                return memo[start] = true;
            }
        }
        return memo[start] = false;
    }


    /*
Approach 3: Using Breadth-First-Search
Visualize the string as a tree where each node represents the prefix upto index endend. 
Two nodes are connected only if the substring between the indices linked with those nodes is also a valid string 
which is present in the dictionary.

Time complexity : O(n^2) For every starting index, the search can continue till the end of the given string.

Space complexity : O(n). Queue of atmost n size is needed. 
    */
	public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (visited[start] == false) {
                for (int end = start + 1; end <= s.length(); end++) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                        queue.add(end);
                        if (end == s.length()) {
                            return true;
                        }
                    }
                }
                visited[start] = true;
            }
        }
        return false;
    }

    /*
We can use a graph to represent the possible solutions. 
The vertices of the graph are simply the positions of the first characters of the words and each edge actually represents a word. 
For example, the input string is "nightmare", there are two ways to break it, "night mare" and "nightmare". 
The graph would be

0-->5-->9

The question is simply to check if there is a path from 0 to 9. 
The most efficient way is traversing the graph using BFS with the help of a queue and a hash set. 
The hash set is used to keep track of the visited nodes to avoid repeating the same work.

For this problem, the time complexity is O(n^2) and space complexity is O(n) 
This idea can be used to solve the problem word break II. We can simple construct the graph using BFS, 
save it into a map and then find all the paths using DFS.

Optimizations:
only traverse max_len rather than to the end of s to find end.
mark visited when enqueue rather than dequeue.
    */
	public boolean wordBreak(String s, List<String> wordDict) {
        int max_len = -1;
        for (String word : wordDict)
            max_len = Math.max (max_len, word.length ());

        Set<String> wordDictSet = new HashSet(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        queue.add(0);
        visited[0] = true;
        
        while (!queue.isEmpty()) {
            int start = queue.remove();
            for (int end = start + 1; end <= s.length () && end - start <= max_len; end++) {
                if (wordDictSet.contains(s.substring(start, end)) && !(end < s.length () && visited[end])) {
                    if (end == s.length()) {
                        return true;
                    }
                    queue.add(end);
                    visited[end] = true;
                }
            }
        }
        return false;
    }

    /*
Approach 4: Using Dynamic Programming
initialize the element dp[0] as true, since the null string is always present in the dictionary, 
and the rest of the elements of dp as false. 

Time complexity : O(n^2) Two loops are their to fill dp array.
Space complexity : O(n). Length of dp array is n+1.
    */
	public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;  
        for (int i = 1; i <= s.length(); i++) { //the current substring s' into smaller substrings s'(0,j) and s'(j+1,i) 
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
