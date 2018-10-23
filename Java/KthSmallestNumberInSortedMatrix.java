/*
Given a matrix of size N x M. For each row the elements are sorted in ascending order, and for each column the elements are also sorted in ascending order. Find the Kth smallest number in it.

Assumptions

the matrix is not null, N > 0 and M > 0
K > 0 and K <= N * M
Examples

{ {1,  3,   5,  7},

  {2,  4,   8,   9},

  {3,  5, 11, 15},

  {6,  8, 13, 18} }

the 5th smallest number is 4
the 8th smallest number is 6
 */


import java.util.*;
public class KthSmallestNumberInSortedMatrix {
	public class Cell {
	    int x;
	    int y;
	    int val;
	    
	    public Cell(int x, int y, int val) {
	      this.x = x;
	      this.y = y;
	      this.val = val;
	    }
	  }
	  
	
	// time: O(klongk) space: O(n^2 + k), using hashset could be O(n)
	  public int kthSmallest(int[][] matrix, int k) {
	    // for expension and generation
	    PriorityQueue<Cell> minHeap = new PriorityQueue<>(k, new Comparator<Cell>(){  // !!!!
	      @Override
	      public int compare(Cell c1, Cell c2) {
	        if (c1.val == c2.val) {
	          return 0;
	        }
	        
	        return c1.val < c2.val ? -1 : 1;
	      }
	    });  
	    boolean[][] visited = new boolean[matrix.length][matrix[0].length]; 
	    minHeap.offer(new Cell(0, 0, matrix[0][0]));
	    visited[0][0] = true;
	    
	    for (int i = 0; i < k; i++) {  // expend k times
	      if (!minHeap.isEmpty()) {
	        Cell cur = minHeap.poll();   // expend
	        int x = cur.x;
	        int y = cur.y;
	        
	        if (i == k - 1) {  // when we expend k times
	          return matrix[x][y];  
	        }
	        
	        if (x + 1 < matrix.length && !visited[x + 1][y]) {
	          minHeap.offer(new Cell(x + 1, y, matrix[x + 1][y]));
	          visited[x + 1][y] = true;
	        }
	        if (y + 1 < matrix[0].length && !visited[x][y + 1]) {
	          minHeap.offer(new Cell(x, y + 1, matrix[x][y + 1]));
	          visited[x][y + 1] = true;
	        }
	      }
	    }
	    
	    return -1; // if k is not valid
	  }
}
