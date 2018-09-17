/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn¡¯t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. 
Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. 
Two cells are connected if they are adjacent cells connected horizontally or vertically.
 */



import java.util.*;
public class SurroundedRegions {
	
	
	// O(n^2)  run time: 41ms
	public void solve(char[][] board) {
		if(board == null || board.length <= 2 || board[0].length <= 2)  // !! <= 2, Last executed input: [["X"]]
			return;
		
		for(int row = 0; row <= board.length - 1; row = row + board.length - 1) {
			for(int col = 0; col <= board[0].length - 1; col++) {
				if(board[row][col] == 'O') {
					dfs1(board, row, col);
				}
			}
		}
		for(int col = 0; col <= board[0].length - 1; col = col + board[0].length - 1) {
			for(int row = 0; row <= board.length - 1; row++) {
				if(board[row][col] == 'O') {
					dfs1(board, row, col);
				}
			}
		}
        
        System.out.println(Arrays.deepToString(board));   // GOOD EASY way to print 2d array 
		
		for(int row = 1; row <= board.length - 2; row++) {
			for(int col = 1; col <= board[0].length - 2; col++) {
				if(board[row][col] == 'O') {
					dfs2(board, row, col);
				}
			}
		}
        
		System.out.println(Arrays.deepToString(board));
        
		for(int row = 0; row <= board.length - 1; row = row + board.length - 1) {
			for(int col = 0; col <= board[0].length - 1; col++) {
				if(board[row][col] == '*') {
					dfs3(board, row, col);
				}
			}
		}
		for(int col = 0; col <= board[0].length - 1; col = col + board[0].length - 1) {
			for(int row = 0; row <= board.length - 1; row++) {
				if(board[row][col] == '*') {
					dfs3(board, row, col);
				}
			}
		}
        
        System.out.println(Arrays.deepToString(board));
		
		return;
    }
	private void dfs1(char[][] b, int row, int col) {  // change 'O' to '*'
		if(b[row][col] != 'O' || row < 0 || row >= b.length || col < 0 || col >= b[0].length)
			return;
		if(b[row][col] == 'O') {
			b[row][col] = '*';
			
			if(row + 1 <= b.length - 1 && b[row + 1][col] == 'O') {  // up
				dfs1(b, row + 1, col);
			}
			if(row - 1 >= 0 && b[row - 1][col] == 'O') {  // down
				dfs1(b, row - 1, col);
			}
			if(col + 1 <= b[0].length - 1 && b[row][col + 1] == 'O') { //right
				dfs1(b, row, col + 1);
			}
			if(col - 1 >= 0 && b[row][col - 1] == 'O') { // left
				dfs1(b, row, col - 1);
			}
		}
	}
	
	private void dfs2(char[][] b, int row, int col) { // change 'O' to 'X'
		if(b[row][col] != 'O' || row < 0 || row >= b.length || col < 0 || col >= b[0].length)
			return;
		if(b[row][col] == 'O') {
			b[row][col] = 'X';
			
			if(row + 1 <= b.length - 1 && b[row + 1][col] == 'O') {  // up
				dfs2(b, row + 1, col);
			}
			if(row - 1 >= 0 && b[row - 1][col] == 'O') {  // down
				dfs2(b, row - 1, col);
			}
			if(col + 1 <= b[0].length - 1 && b[row][col + 1] == 'O') { //right
				dfs2(b, row, col + 1);
			}
			if(col - 1 >= 0 && b[row][col - 1] == 'O') { // left
				dfs2(b, row, col - 1);
			}
		}
	}
	
	private void dfs3(char[][] b, int row, int col) {  // change '*' to 'O'
		if(b[row][col] != '*' || row < 0 || row >= b.length || col < 0 || col >= b[0].length)
			return;
		if(b[row][col] == '*') {
			b[row][col] = 'O';
			
			if(row + 1 <= b.length - 1 && b[row + 1][col] == '*') {  // up
				dfs3(b, row + 1, col);
			}
			if(row - 1 >= 0 && b[row - 1][col] == '*') {  // down
				dfs3(b, row - 1, col);
			}
			if(col + 1 <= b[0].length - 1 && b[row][col + 1] == '*') { //right
				dfs3(b, row, col + 1);
			}
			if(col - 1 >= 0 && b[row][col - 1] == '*') { // left
				dfs3(b, row, col - 1);
			}
		}
	}
	
	
	// 4ms optimal solution DFS
	// traverse border first, when meets 'O', change it to '*'
	// then traverse whole board, when meets O, change it to X, when meet *, change it to O
	public void solve222(char[][] board) {
        int rows = board.length;
        if(rows == 0) return;
        int cols = board[0].length;
        
        for(int i=0; i<cols; i++) {
            if(board[0][i] == 'O') {
                mark(board, 0, i);
            }
        }
        for(int i=0; i<cols; i++) {
            if(board[rows-1][i] == 'O') {
                mark(board, rows-1, i);
            }
        }
        for(int i=0; i<rows; i++) {
            if(board[i][0] == 'O') {
                mark(board, i, 0);
            }
        }
        for(int i=0; i<rows; i++) {
            if(board[i][cols-1] == 'O') {
                mark(board, i, cols-1);
            }
        }
        
        for(int i=0; i<rows; i++) {
            for(int j=0; j<cols; j++) {
                if(board[i][j] == 'O') 
                	board[i][j] = 'X';
                else if(board[i][j] != 'X') 
                	board[i][j] = 'O';
            }
        }
    }
    
    private void mark(char[][] board, int x, int y) {
        if(x<0 || x>=board.length || y<0 || y>=board[0].length || board[x][y]!='O') return;
        board[x][y] = '*';
        mark(board, x-1, y);
        mark(board, x+1, y);
        mark(board, x, y-1);
        mark(board, x, y+1);
    }
	
	
	 	
}
