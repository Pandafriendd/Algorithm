/*
Given a 2D board and a word, find if the word exists in the grid.
The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once.

Example:
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 */
public class WordSearch {
	
	/**
     * Remember position in board
     * Remember position in matched word
     */
    public static boolean dfs(char[][] board, int i, int j, String word, int n) {
        if (word.length() == n) 
        	return true;
        // outside board or doesn't match
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(n)) 
        	return false;
        board[i][j] = '#'; // mark 
        // search 4 connectivity
        boolean res = dfs(board, i - 1, j, word, n + 1) || dfs(board, i + 1, j, word, n + 1) || dfs(board, i, j - 1, word, n + 1) || dfs(board, i, j + 1, word, n + 1);
        board[i][j] = word.charAt(n);// reset mark
        return res;
    }
	 /**
     * Use boolean array to remember whether a word is used
     * Traverse each position and do DFS
     */
    public static boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null) 
        	return false;
        if (word.length() == 0) 
        	return true;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++)
                if (board[i][j] == word.charAt(0)) { // match the first char
                    if (dfs(board, i, j, word, 0)) 
                    	return true;
                }
        }
        
        return false;
    }
    
    // my code
    public boolean exist1(char[][] board, String word) {
        if(word == null || word.length() == 0 || board == null) {
            return true;
        }
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(0) && dfss(board, i, j, word, 0))  // !!!!
                    return true;
            }
        }
        
        return false;
    }
    
    private boolean dfss(char[][] board, int row, int col, String word, int pos) {
        if(pos == word.length()) {
            return true;
        }
        if(row < 0 || row > board.length - 1 || col < 0 || col > board[0].length - 1 || board[row][col] != word.charAt(pos)) {
            return false;
        }
        
        char temp = board[row][col];
        board[row][col] = '*';
        
        boolean res = dfss(board, row + 1, col, word, pos + 1) || dfss(board, row - 1, col, word, pos + 1) 
            || dfss(board, row, col + 1, word, pos + 1) || dfss(board, row, col - 1, word, pos + 1);
        
        board[row][col] = temp;
        
        return res;
    }
}
