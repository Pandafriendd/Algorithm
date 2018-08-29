/*
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
 */
import java.util.*;


/*
There are three challenges: 1. remove minimum parenthesis 2. the result is valid 3. and without duplicates.
 */
public class RemoveInvalidParentheses {
	
	/*
BFS:
The idea is straightforward, with the input string s, we generate all possible states by removing one ( or ), check if they are valid, 
if found valid ones on the current level, put them to the final result list and we are done, otherwise, add them to a queue and carry on to the next level.
The good thing of using BFS is that we can guarantee the number of parentheses that need to be removed is minimal, also no recursion call is needed in BFS.

Time complexity:
In BFS we handle the states level by level, in the worst case, we need to handle all the levels, 
we can analyze the time complexity level by level and add them up to get the final complexity.
On the first level, there's only one string which is the input string s, let's say the length of it is n, to check whether it's valid, we need O(n) time. 
On the second level, we remove one ( or ) from the first level, so there are C(n, n-1) new strings, each of them has n-1 characters, and for each string, 
we need to check whether it's valid or not, thus the total time complexity on this level is (n-1) x C(n, n-1). 
Come to the third level, total time complexity is (n-2) x C(n, n-2), so on and so forth...

Finally we have this formula:
T(n) = n x C(n, n) + (n-1) x C(n, n-1) + ... + 1 x C(n, 1) = n x 2^(n-1). So, time: O(n x 2^(n-1).)

120ms
	 */
	 public List<String> removeInvalidParentheses(String s) {
	      List<String> res = new ArrayList<>();
	      
	      // sanity check
	      if (s == null) return res;
	      
	      Set<String> visited = new HashSet<>(); //ensure no duplicate
	      Queue<String> queue = new LinkedList<>(); // store same level strings
	      
	      // initialize
	      queue.add(s);
	      visited.add(s);
	      
	      boolean found = false;
	      
	      while (!queue.isEmpty()) {
	        s = queue.poll();
	        
	        if (isValid(s)) {
	          res.add(s);    // found an answer, add to the result
	          found = true;
	        }
	      
	        if (found) //this ensures once we've found a valid parentheses pattern, we don't do any further bfs using items pending in the queue since any further bfs would only yield strings of smaller length. However the items already in queue need to be processed since there could be other solutions of the same length.
	        	continue;
	      
	        // generate all possible states
	        for (int i = 0; i < s.length(); i++) {
	          // we only try to remove left or right paren
	          if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;
	        
	          String t = s.substring(0, i) + s.substring(i + 1); // i = 0 delete s.charAt(0); i = 1 delete s.charAt(1); ... ; i = s.len - 1, delete s.charAt(len - 1) 
	        
	          if (!visited.contains(t)) {
	            // for each state, if it's not visited, add it to the queue
	            queue.add(t);
	            visited.add(t);
	          }
	        }
	      }
	      
	      return res;
	    }
	    
	    // helper function checks if string s contains valid parantheses
	    static boolean isValid(String s) {
	      int count = 0;
	    
	      for (int i = 0; i < s.length(); i++) {
	        char c = s.charAt(i);
	        if (c == '(') count++;
	        if (c == ')' && count-- == 0) 
	        	return false;
	      }
	    
	      return count == 0;
	    }
	    
	    
	    /*
BFS optimal
We can speed up and get rid of the hash table by generating unique strings only. 
There are two types of duplicates:
1. First is due to removing the same set of characters in different order. For example, "(()(()", remove 0th then 3rd or remove 3rd then 0th both generates "()()". 
So we can enforce an order by keeping the last removal index and remove after it only. 
2. The other is handling consecutive same chars, say, "(()". We get the same string by removing either the 0th or 1st '('. We can just remove the 0th (the first one)

10ms
	     */
	    public List<String> removeInvalidParentheses2(String s) {
	        if (isValid(s))
	            return Collections.singletonList(s);
	        
	        List<String> ans = new ArrayList<>();	        
	        Queue<Tuple> queue = new LinkedList<>(); //The queue only contains invalid middle steps
	        queue.add(new Tuple(s, 0, ')')); //The 3-Tuple is (string, startIndex, lastRemovedChar)
	        
	        while (!queue.isEmpty()) {
	            Tuple x = queue.poll();
	            //Observation 2, start from last removal position
	            for (int i = x.start; i < x.string.length(); ++i) {
	                char ch = x.string.charAt(i);
	                //Not parentheses
	                if (ch != '(' && ch != ')') continue;
	                
	                //Observation 1, do not repeatedly remove from consecutive ones
	                if (i != x.start && x.string.charAt(i - 1) == ch) continue;
	                
	                //Observation 3, do not remove a pair of valid parentheses
	                if (x.removed == '(' && ch == ')') continue;
	                
	                String t = x.string.substring(0, i) + x.string.substring(i + 1);
	                //Check isValid before add
	                if (isValid(t))
	                    ans.add(t);
	                //Avoid adding leaf level strings
	                else if (ans.isEmpty())
	                    queue.add(new Tuple(t, i, ch));
	            }
	        }
	        return ans;
	    }
	    
	    private class Tuple {
	        public final String string;
	        public final int start;
	        public final char removed;

	        public Tuple(String string, int start, char removed) {
	            this.string = string;
	            this.start = start;
	            this.removed = removed;
	        }
	    }
	    
	    
	    /*
DFS solutions
A naive DFS is to generate all the 2^n substrings. We use hash table to remove duplicates. and then return the longest ones. It is less efficient than BFS because DFS does not guarantee shortest path. 
So we cannot stop after the first valid strings as in BFS.
	     */
	    
	    /*
DFS optimal:
The naive DFS approaches are not efficient at all since they generate strings of all lengths. 
BFS only removes the minimum number of strings so we should be able to do the same thing in DFS. 
Since the problem asks to remove minimum number of parenthesis, it is natural to compute the min number first and then use it to guide the DFS. 
This guarantees we only generate valid strings. The other challenge is to handle duplicate strings. 
We still use hash table to remove duplicates. The disadvantage is that duplicates are generated so the search space is not minimal.

1. Limit max removal rmL and rmR for backtracking boundary. Otherwise it will exhaust all possible valid substrings, not shortest ones.
2. Scan from left to right, avoiding invalid strs (on the fly) by checking num of open parens.
3. If it's '(', either use it, or remove it.
4. If it's ')', either use it, or remove it.
5. Otherwise just append it.
6. Lastly set StringBuilder to the last decision point.

In each step, make sure:
1. i does not exceed s.length().
2. Max removal rmL rmR and num of open parens are non negative.
3. De-duplicate by adding to a HashSet.
	     */
	    public List<String> removeInvalidParentheses333(String s) {
	        int rmL = 0, rmR = 0;
	        for (int i = 0; i < s.length(); i++) {
	            if (s.charAt(i) == '(') {
	                rmL++;
	            } else if (s.charAt(i) == ')') {
	                if (rmL != 0) {
	                    rmL--;
	                } else {
	                    rmR++;
	                }
	            }
	        }
	        Set<String> res = new HashSet<>();
	        dfs(s, 0, res, new StringBuilder(), rmL, rmR, 0);
	        return new ArrayList<String>(res);
	    }

	    public void dfs(String s, int i, Set<String> res, StringBuilder sb, int rmL, int rmR, int open) {
	        if (rmL < 0 || rmR < 0 || open < 0) {
	            return;
	        }
	        if (i == s.length()) {
	            if (rmL == 0 && rmR == 0 && open == 0) {
	                res.add(sb.toString());
	            }        
	            return;
	        }

	        char c = s.charAt(i); 
	        int len = sb.length();

	        if (c == '(') {
	            dfs(s, i + 1, res, sb, rmL - 1, rmR, open);		    // not use (
	        	dfs(s, i + 1, res, sb.append(c), rmL, rmR, open + 1);       // use (

	        } else if (c == ')') {
	            dfs(s, i + 1, res, sb, rmL, rmR - 1, open);	            // not use  )
	        	dfs(s, i + 1, res, sb.append(c), rmL, rmR, open - 1);  	    // use )

	        } else {
	            dfs(s, i + 1, res, sb.append(c), rmL, rmR, open);	
	        }

	        sb.setLength(len);  // backtracking       
	    }
}
