/*
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.
 */



import java.util.*;

public class NQueens {
	
	public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                board[i][j] = '.';
        List<List<String>> res = new ArrayList<List<String>>();
        dfs(board, 0, res);
        return res;
    }
    
    private void dfs(char[][] board, int colIndex, List<List<String>> res) {
        if(colIndex == board.length) {
            res.add(construct(board));
            return;
        }
        
        for(int i = 0; i < board.length; i++) {
            if(validate(board, i, colIndex)) {
                board[i][colIndex] = 'Q';
                dfs(board, colIndex + 1, res);
                board[i][colIndex] = '.'; //recover
            }
        }
    }
    
    private boolean validate(char[][] board, int x, int y) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < y; j++) {    // j is always less than y  because in dfs() method on every loop it is using colIndex + 1, this means in each iteration it is increasing the colIndex. This new colIndex is not explored yet, thus don't need to check the vertical alignment of Q.
            	//same as if (board[i][j] == 'Q' && (Math.abs(row - i) == Math.abs(col - j) || row == i))
            	if(board[i][j] == 'Q' && (x - y == i - j || x + y == i + j || x == i))   // x - y == i - j --> same \ diagonal; x + y == i + j  --> same / diagonal; x==i --> same row
                    return false;
            }
        }
        
        return true;
    }
    
    private List<String> construct(char[][] board) {
        List<String> res = new LinkedList<String>();
        for(int i = 0; i < board.length; i++) {
            String s = new String(board[i]); //!!
            res.add(s);
        }
        return res;
    }
    /*
     * validate:
     * The main idea here is to put Q in each column from left to right, and when we put Q in each column we check the validity row by row. 
     * Since we are traversing from left to right column, we only need to check whether the current position is in conflict with its left column elements. 
     * There are only three possible positions in the left column that might be in conflict with the current Q. 
     * Respectively, they are the 135 degree, horizontally left and 45 degree ones. For 135 degree, they are in a line whose slope is -1. 
     * So (y-j)/(x-i) = -1 -> y + x = i + j. For the horizontally one, x = i. And for the 45 degree one, the line slope is 1, so (y-j)/(x-i) = 1 -> y + i = x + j.
     */
    
    
    
    
    public static void main(String[] args) {
    	NQueens n = new NQueens();
    	List<List<String>> result = n.solveNQueens(5);
    	for (List<String> board : result) {
    	    for (String line : board)
    	        System.out.println(line);
    	    System.out.println();
    	}
    }
	
	
	
	
	
	
	
	
	//using bit manipulation
	  	int limit, total; // limit is all ones, total is # of rows
	    String[] strings; // for a solution
	    List<String[]> res; // solutions
	    StringBuilder sb; // for a row
	    List<Integer> indices; // store solution
	    
	    public List<String[]> solveNQueens1(int n) {
	        res = new ArrayList<String[]>();
	        if (n <= 0) return res;
	        total = n;
	        strings = new String[n];
	        sb = new StringBuilder();
	        for (int i = 0; i < n; i++) sb.append(".");
	        indices = new ArrayList<Integer>();
	        limit = (1 << n) - 1;
	        dfs(0, 0, 0);
	        return res;
	    }
	    
	    /**
	     * Save indices of each line in a list
	     * Retrieve the indices of each line when there is a solution
	     */
	    public void dfs(int h, int r, int l) {
	        if (h == limit) {
	            for (int i = indices.size() - 1; i >= 0; i--) {
	                int gap = h - indices.get(i); // last position
	                h = indices.get(i);
	                int n = 0;
	                while (gap > 0) {
	                    n++;
	                    gap >>= 1;
	                }
	                StringBuilder ans = new StringBuilder(sb);
	                ans.setCharAt(n - 1, 'Q'); // note n - 1
	                strings[i] = ans.toString();
	            }
	            res.add(strings); // add to result
	            strings = new String[total]; // reset strings
	            return;
	        }
	        indices.add(h); // add then remove
	        int pos = limit & (~(h|r|l)); // set unsaved pos to zero, note ~
	        while (pos != 0) {
	            int p = pos & (-pos); // rightmost 1
	            pos -= p; // note how to place a queen
	            dfs(h + p, (r + p) << 1, (l + p) >> 1);
	        }
	        indices.remove(indices.size() - 1); // remove added h
	    }
	   
	   
	   
}
