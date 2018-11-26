/*
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * a solution requires that no two queens share the same row, column, or diagonal
 * 
 * Example:

Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
 */


import java.util.*;
public class NQueensII {
	
	
	public static void main(String[] args) {
//        System.out.println(totalNQueens(2));
//        System.out.println(totalNQueens(3));
//        System.out.println(totalNQueens(4));
//        System.out.println(totalNQueens(8));
		
		NQueensII n = new NQueensII();
		System.out.println(n.totalNQueens0(4));
	}
	
	
	
	/*
my solution based on choosing, exploring, unchoosing
we also can do without global variable
	 */
	
    //int cnt = 0;
	public int totalNQueens0(int n) {

	HashSet<Integer> colSet = new HashSet<>();  // column
	HashSet<Integer> d1Set = new HashSet<>();  // 45 diagonal
	HashSet<Integer> d2Set = new HashSet<>(); // 135 diagonal
	    
        //nQueensHelper(0, n, colSet, d1Set, d2Set);
        int cnt = nQueensHelper(0, n, 0, colSet, d1Set, d2Set);
        
        return cnt;
    }
    
    public int nQueensHelper(int row, int n, int count, HashSet<Integer> colSet, HashSet<Integer> d1Set, HashSet<Integer> d2Set) {
    	// base cases
        if(row == n) {  // !!!!! should == n, if (row == n - 1) is wrong since n - 1 still not being try
        	count++;
        }
        
        // recursive cases
        else {
            for(int col = 0; col < n; col++) {                    	
                // constraints
            	if(!colSet.contains(col) && !d1Set.contains(row + col) && !d2Set.contains(row - col)) {
            		
            		// choose
                    colSet.add(col);
                    d1Set.add(row + col);
                    d2Set.add(row - col);
                    
                    // explore
                    count = nQueensHelper(row + 1, n, count, colSet, d1Set, d2Set);
                    
                    // unchoose
                    colSet.remove(col);
                    d1Set.remove(row + col);
                    d2Set.remove(row - col);
            	}
            }
        }
        
        return count;
    }
    
	/**
     * don't need to actually place the queen,
     * instead, for each row, try to place without violation on col/ diagonal1/ diagnol2.
     * trick: to detect whether 2 positions sit on the same diagonal:
     * if sub(col, row) equals, same diagnol1;  \
     * if sum(col, row) equals, same diagnal2.   /
     */
    //in the 45 diag lines: col - row are always same , and in 135 drag lines: col + row are always same
    private final Set<Integer> occupiedCols = new HashSet<Integer>();
    private final Set<Integer> occupiedDiag1s = new HashSet<Integer>();
    private final Set<Integer> occupiedDiag2s = new HashSet<Integer>();
    public int totalNQueens4(int n) {
        return totalNQueensHelper(0, 0, n);
    }

    private int totalNQueensHelper(int row, int count, int n) {
        for (int col = 0; col < n; col++) {
            if (occupiedCols.contains(col))
                continue;
            int diag1 = row - col;
            if (occupiedDiag1s.contains(diag1))
                continue;
            int diag2 = row + col;
            if (occupiedDiag2s.contains(diag2))
                continue;
            
            // we can now place a queen here
            if (row == n-1)
                count++;
            else {
                occupiedCols.add(col);
                occupiedDiag1s.add(diag1);
                occupiedDiag2s.add(diag2);
                count = totalNQueensHelper(row+1, count, n);
                
                // recover
                occupiedCols.remove(col);
                occupiedDiag1s.remove(diag1);
                occupiedDiag2s.remove(diag2);
            }
        }
        
        return count;
    }
    
    //using global varibale
    /*
     * Start row by row, and loop through columns. At each decision point, skip unsafe positions by using three boolean arrays.
     * Start going back when we reach row n.
     * Just FYI, if using HashSet, running time will be at least 3 times slower!
     */
    int count = 0;
    public int totalNQueens2(int n) {
        boolean[] cols = new boolean[n];     // columns   |
        boolean[] d1 = new boolean[2 * n];   // diagonals \  top left -> bottom right
        boolean[] d2 = new boolean[2 * n];   // diagonals /  top right -> bottom left
        backtracking(0, cols, d1, d2, n);
        return count;
    }
    
    public void backtracking(int row, boolean[] cols, boolean[] d1, boolean []d2, int n) {
        if(row == n) count++;
        
        /*
         * 45 degree line is y = x + b
         * 135 degree line is y = -x + b
         * in another word, 45 degree y - x is constant, and 135 degree y + x is constant.
         * Here b is shifted to [0~2n), so y-x and y+x can be trackable.
         */
        for(int col = 0; col < n; col++) {
            int id1 = col - row + n; 
            int id2 = col + row;
            if(cols[col] || d1[id1] || d2[id2]) 
            	continue;
            
            cols[col] = true; 
            d1[id1] = true; 
            d2[id2] = true;
            
            backtracking(row + 1, cols, d1, d2, n);
            cols[col] = false; d1[id1] = false; d2[id2] = false;
        }
    }
    
    
    /*
    常规n-queens解法, 数答案个数.
    用column标记此行之前的哪些column已经放置了queen. 棋盘坐标(row, col)对应column的第col位(LSB --> MSB, 下同).
    用diag标记此位置之前的哪些主对角线已经放置了queen. 棋盘坐标(row, col)对应diag的第(n - 1 + row - col)位.
    用antiDiag标记此位置之前的哪些副对角线已经放置了queen. 棋盘坐标(row, col)对应antiDiag的第(row + col)位.
     */
    int count1 = 0;
    
    public int totalNQueens3(int n) {
        dfs(0, n, 0, 0, 0);
        return count1;
    }
    
    private void dfs(int row, int n, int column, int diag, int antiDiag) {
        if (row == n) {
            ++count1;
            return;
        }
        for (int i = 0; i < n; ++i) {
            boolean isColSafe = ((1 << i) & column) == 0;
            boolean isDiagSafe = ((1 << (n - 1 + row - i)) & diag) == 0;
            boolean isAntiDiagSafe = ((1 << (row + i)) & antiDiag) == 0;
            if (isColSafe && isDiagSafe && isAntiDiagSafe) {
                dfs(row + 1, n, (1 << i) | column, (1 << (n - 1 + row - i)) | diag, (1 << (row + i)) | antiDiag);
            }
        }
    }
    
    
    
    
    
    
    /**
     * backtrace program using bit-wise operation to speed up calculation.
     * 'limit'is all '1's.
     * 'h'    is the bits all the queens vertically projected on a row. If
     *        h==limit, then it's done, answer++.
     * 'r'    is the bits all the queens anti-diagonally projected on a row.
     * 'l'    is the bits all the queens diagonally projected on a row.
     * h|r|l  is all the occupied bits. Then pos = limit & (~(h|r|l)) is all
     *        the free positions.
     * p = pos & (-pos)
     *        gives the right most '1'. pos -= p means we will place
     *        a queen on this bit represented by p.
     * 'h+p'  means one more queue vertically projected on next row.
     * '(r+p) << 1'
     *        means one more queue anti-diagonally projected on next row.
     *        Because we are moving to next row and the projection is skew from
     *        right to left, we have to shift left one position after moved to
     *        next row.
     * '(l+p) >> 1'
     *        means one more queue diagonally projected on next row. Because we
     *        are moving to next row and the projection is skew from left to
     *        right, we have to shift right one position after moved to next
     *        row.
     * https://oj.leetcode.com/discuss/743/whats-your-solution
     */
    public static int totalNQueens(int n) {
        ans = 0;
        limit = (1 << n) - 1; // note that parentheses can't be ignored
        dfs(0, 0, 0);
        return ans;
    }
    
    static int ans, limit;
    
    /**
     * Backtracking
     */
    public static void dfs(int h, int r, int l) {
        if (h == limit) { 
            ans++;
            return;
        }
        int pos = limit & (~(h|r|l));
        while (pos != 0) { // has position
            int p = pos & (-pos); // right most 1
            pos -= p; // place a queen, 1 -> 0 on that position
            dfs(h + p, (r + p) << 1, (l + p) >> 1);
        }
    }
}
