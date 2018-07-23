/*
 * Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:
	Each row must contain the digits 1-9 without repetition.
	Each column must contain the digits 1-9 without repetition.
	Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 */

import java.util.*;


public class isValidSudoku {
	public boolean isValidSudoku(char[][] board) { //93%
        int[] row = new int[9];
        int[] col = new int[9];
        int[] sqr = new int[9];

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0'; //get the int 
                    if ((row[i] & 1 << num) > 0) 
                        return false; // already in row
                    else 
                        row[i] |= 1 << num;

                    if ((col[j] & 1 << num) > 0) 
                        return false;// already in col
                    else 
                        col[j] |= 1 << num;

                    int sqrIdx = (i - i % 3) + j / 3; // note the square idx
                    if ((sqr[sqrIdx] & 1 << num) > 0) 
                        return false; // already
                    else sqr[sqrIdx] |= 1 << num;

                }
            }
        }
        return true;
    }
    /**
     * hashtable, index as key, mask as value
     */
    public boolean isValidSudoku22(char[][] board) { //14.19%
        Map<Integer, Integer> row = new HashMap<Integer, Integer>();
        Map<Integer, Integer> col = new HashMap<Integer, Integer>();
        Map<Integer, Integer> sqr = new HashMap<Integer, Integer>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    int rowMask = row.containsKey(i) ? row.get(i) : 0;
                    if ((rowMask & 1 << num) > 0) {
                        return false;
                    } else {
                        row.put(i, rowMask | 1 << num);
                    }

                    int colMask = col.containsKey(j) ? col.get(j) : 0;
                    if ((colMask & 1 << num) > 0) {
                        return false;
                    } else {
                        col.put(j, colMask | 1 << num);
                    }

                    int sqrIdx = (i - i % 3) + j / 3;
                    int sqrMask = sqr.containsKey(sqrIdx) ? sqr.get(sqrIdx) : 0;
                    if ((sqrMask & 1 << num) > 0) {
                        return false;
                    } else {
                        sqr.put(sqrIdx, sqrMask | 1 << num);
                    }
                }
            }
        }

        return true;
    }
    
    public boolean isValidSudoku33333(char[][] board){ //43%
        //int rowIndex, colIndex;
        for(int i = 0; i < 9; i++){
            HashSet<Character> row = new HashSet<Character>();
            HashSet<Character> col = new HashSet<Character>();
            HashSet<Character> sqr = new HashSet<Character>();
            for(int j = 0; j < 9; j++){
                if(board[i][j] != '.' && !row.add(board[i][j])) //!!! board[i][j]
                    return false;
                if(board[j][i] != '.' && !col.add(board[j][i]))  //!!! board[j][i]
                    return false;
                int rowIndex = 3 * (i / 3);
                int colIndex = 3 * (i % 3);
                if(board[rowIndex + j / 3][colIndex + j % 3] != '.' && !sqr.add(board[rowIndex + j / 3][colIndex + j % 3]))
                    return false;
                
            }
        }
        return true;
    }

}
