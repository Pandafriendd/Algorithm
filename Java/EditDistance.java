
/*
 * Let following be the function definition :-

f(i, j) := minimum cost (or steps) required to convert first i characters of word1 to first j characters of word2

Case 1: word1[i] == word2[j], i.e. the ith the jth character matches.
f(i, j) = f(i - 1, j - 1)

Case 2: word1[i] != word2[j], then we must either insert, delete or replace, whichever is cheaper
f(i, j) = 1 + min { f(i, j - 1), f(i - 1, j), f(i - 1, j - 1) }

1.f(i, j - 1) represents insert operation
2. f(i - 1, j) represents delete operation
3. f(i - 1, j - 1) represents replace operation
Here, we consider any operation from word1 to word2. It means, when we say insert operation, we insert a new character after word1 that matches the jth character of word2. 
So, now have to match i characters of word1 to j - 1 characters of word2. Same goes for other 2 operations as well.

Note that the problem is symmetric. The insert operation in one direction (i.e. from word1 to word2) is same as delete operation in other. So, we could choose any direction.

Above equations become the recursive definitions for DP.

Base Case:

f(0, k) = f(k, 0) = k

Below is the direct bottom-up translation of this recurrent relation. It is only important to take care of 0-based index with actual code

time: O(mn) m is word1's len, n is word2's len
space: O(mn)
 */

public class EditDistance {
	
	/*
	laioffer DFS solution, 3 branches dfs (replace, delete, insert), kind of brute force solution
	m = w1.len, n =  w2.len 
	time: O(3^(m + n))  space: O(m + n)
	 */
	public int editDFS(String word1, String word2) {
		// base case
		if (word1.isEmpty()) {
			return word2.length();
		}
		if (word2.isEmpty()) {
			return word1.length();
		}
		
		// corner case: when the first elements of w1 and w2 are same
		if (word1.charAt(0) == word2.charAt(0)) {
			return editDFS(word1.substring(1), word2.substring(1));
		}
		
		int replace = editDFS(word1.substring(1), word2.substring(1)) + 1;  // + 1!!!!
		int delete = editDFS(word1.substring(1), word2) + 1;
		int insert = editDFS(word1, word2.substring(1)) + 1;
		
		return Math.min(Math.min(replace, delete), insert);
	}
	
	/*
	 my DP
	 M[i][j] represents the min edit distance between word1's first i elements (from index 0 to i - 1) and word2's first j elements (from index 0 to j - 1)
	 base case: 
	 	both are empty: M[0][0] = 0; 
	 	or either is empty: M[i][0] = i; M[0][j] = j;
	 induction rules:
	 	M[i][j] =  M[i - 1][j - 1], if (word1.charAt(i - 1) == word2.charAt(j - 1)) !!!  be careful about the index!!!
	 		       min(M[i - 1][j - 1], M[i - 1][j], M[i][j - 1]) + 1, otherwise
	 
	 time: O(m * n), space: O(m * n)
	 */
	public int editDistance(String word1, String word2) {
		int[][] M = new int[word1.length() + 1][word2.length() + 1];
		
		// handle base case
		for (int i = 1; i <= word1.length(); i++) {
			M[i][0] = i;
		}
		for (int j = 1; j <= word2.length(); j++) {
			M[0][j] = j;
		}
		
		for (int i = 1; i <= word1.length(); i++) {
			for (int j = 1; j <= word2.length(); j++) {
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					M[i][j] = M[i - 1][j - 1];
				} else {
					int replace = M[i - 1][j - 1] + 1;
					int delete = M[i - 1][j] + 1;
					int insert = M[i][j - 1] + 1;
					M[i][j] = Math.min(Math.min(replace, delete), insert);
				}
			}
		}
		
		return M[word1.length()][word2.length()];
	}
	
	/*
	 ?????????
	 space can be optimized to O(min(m, n)), since for each step we only look up 3 elements in the table (two in last row and one in current row), 
	 so we only need to keep two rows
	 */
	public int editDistance2(String word1, String word2) {
		// let word1 always has smaller length
		if (word1.length() > word2.length()) {
			editDistance2(word2, word1);
		}
		
		if (word1.length() == 0 || word1.isEmpty() || word1 == null) {
			return word2.length();
		}

		int[] lastRow = new int[word1.length() + 1];
		int[] curRow = new int[word1.length() + 1];
		
		// handle base case (initialize)
		for (int j = 1; j <= word1.length(); j++) {
			lastRow[j] = j;   // !!!!
		}
		
				
		for (int i = 1; i <= word2.length(); i++) {  // !!! word2
			curRow[0] = i;  // also base case
			for (int j = 1; j <= word1.length(); j++) {  // !!! word1
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					curRow[j] = lastRow[j - 1];
				} else {
					int replace = lastRow[j - 1] + 1;
					int delete = lastRow[j] + 1;
					int insert = curRow[j - 1] + 1;
					curRow[j] = Math.min(Math.min(replace, delete), insert);
				}
				lastRow = curRow;
			}
		}
		
		return curRow[word1.length()];
	}
	
	// ********************************************
	public int minDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();
		
		int[][] D = new int[m + 1][n + 1];
		for(int i = 0; i <= m; i++) {  // !! = 
			D[i][0] = i;
		}
		for(int j = 1; j <= n; j++) {
			D[0][j] = j;
		}
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(word1.charAt(i) == word2.charAt(j))
					D[i + 1][j + 1] = D[i][j];
				else {
					int replace = D[i][j];
					int delete = D[i][j + 1];
					int insert = D[i + 1][j];
					D[i + 1][j + 1] = Math.min(replace, Math.min(delete, insert)) + 1;
				}
			}
		}
		return D[m][n];
	}
	
	
	public int minDistance2(String word1, String word2) {
	    int[][] dp = new int[word2.length()+1][word1.length()+1];
	    for(int i = 0; i <= word2.length(); i++) {
	        for(int j = 0; j <= word1.length(); j++) {
	            if(i == 0 && j == 0) dp[i][j] = 0; // no strings given
	            else if(i == 0 && j != 0) {
	                dp[i][j] = j; // word2 is empty
	            } else if(i != 0 && j == 0) {
	                dp[i][j] = i; // word1 is empty
	            } else if(word2.charAt(i-1) != word1.charAt(j-1)) {
	                dp[i][j] = Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1])) + 1;
	            }else {
	                dp[i][j] = dp[i-1][j-1]; // same characters just carry over previous chars from both
	            }
	        }
	    }
	    return dp[word2.length()][word1.length()];
	}
	
	/**
     * DP, O(nm) Time, O(nm) Space
     * Searching for a path (sequence of edits) from the start string to the
     * final string
     * For two strings, X of length n, Y of length m
     * Define D(i,j): the edit distance between X[1..i] and Y[1..j]
     * the first i characters of X and the first j characters of Y
     * The edit distance between X and Y is thus D(n,m)
     * 
     * Bottom-up:
     * Initialization: D(i,0) = i, D(0,j) = j
     * 1. D(i, j) = min(D(i - 1, j) + 1, D(i, j - 1) + 1, D(i - 1, j - 1) + 0
     * or 1), 0 is X(i) = Y(j), 1 if X(i) != Y(j)
     * D(N, M) is distance 
     * 
     * Note that f[i][j] only depends on f[i-1][j-1], f[i-1][j] and f[i][j-1],
     * therefore we can reduce the space to O(n) by using only the (i-1)th
     * array and previous updated element(f[i][j-1]).
     */
    public static int minDistance3(String word1, String word2) {
        if (word1.equals(word2)) return 0;
        int m = word1.length();
        int n = word2.length();
        int[][] d = new int[m + 1][n + 1];
        d[0][0] = 0;
        for (int i = 1; i < m + 1; i++) d[i][0] = i;
        for (int j = 1; j < n + 1; j++) d[0][j] = j;
        
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                d[i][j] = Math.min(Math.min(d[i][j - 1] + 1, d[i - 1][j] + 1), word1.charAt(i - 1) == word2.charAt(j - 1) ? d[i - 1][j - 1] : d[i - 1][j - 1] + 1);
            }
        }
        
        return d[m][n];
    }
    
    /**
     * Optimal DP. Reduce table to a row. 
     */
    public static int minDistanceOptimal(String word1, String word2) {
        if (word1.equals(word2)) return 0;
        int m = word1.length();
        int n = word2.length();
        int[] d = new int[n + 1];
        d[0] = 0;
        for (int j = 1; j < n + 1; j++) d[j] = j;
        
        for (int i = 1; i < m + 1; i++) {
            int prev = d[0];
            d[0] += 1;
            for (int j = 1; j < n + 1; j++) {
                int temp = d[j];
                d[j] = Math.min(Math.min(d[j - 1] + 1, d[j] + 1), word1.charAt(i - 1) == word2.charAt(j - 1) ? prev : prev + 1);
                prev = temp;
            }
        }
        
        return d[n];
    }
    
	
	
}
