/*
Given two sorted arrays A and B, of sizes m and n respectively. 
Define s = a + b, where a is one element from A and b is one element from B. Find the Kth smallest s out of all possible s'.

Assumptions

A is not null and A is not of zero length, so as B
K > 0 and K <= m * n
Examples

A = {1, 3, 5}, B = {4, 8}

1st smallest s is 1 + 4 = 5
2nd smallest s is 3 + 4 = 7
3rd, 4th smallest s are 9 (1 + 8, 4 + 5) 
5th smallest s is 3 + 8 = 11
 */

import java.util.*;
public class KthSmallestSumInTwoSortedArrays {
	
	// time: O(klogk)  space: O(n * m)
	  public int kthSum(int[] A, int[] B, int k) {
	    PriorityQueue<Cell> minHeap = new PriorityQueue<>(k, new Comparator<Cell>() {
	      @Override
	      public int compare(Cell c1, Cell c2) {
	        if (c1.sum == c2.sum) {
	          return 0;
	        }
	        
	        return c1.sum < c2.sum ? -1 : 1;
	      }
	    }); 
	    boolean[][] visited = new boolean[A.length][B.length];
	    minHeap.offer(new Cell(0, 0, A, B));
	    visited[0][0] = true;
	    
	    for (int i = 0; i < k; i++) {
	      if (!minHeap.isEmpty()) {
	        Cell cur = minHeap.poll(); // expend
	        int x = cur.x;
	        int y = cur.y;
	        
	        if (i == k - 1) {  // when generate the kth times, return
	          return cur.sum;
	        }
	        
	        if (x + 1 < A.length && !visited[x + 1][y]) {
	          minHeap.offer(new Cell(x + 1, y, A, B));  // generate
	          visited[x + 1][y] = true;
	        }
	        if (y + 1 < B.length && !visited[x][y + 1]) {
	          minHeap.offer(new Cell(x, y + 1, A, B));  // generate
	          visited[x][y + 1] = true;
	        }
	      }
	    }
	    
	    return -1; // is k is not valid
	  }
	  
	  public class Cell {
	    int x;
	    int y;
	    int sum;
	    
	    public Cell(int x, int y, int[] A, int[] B) {
	      this.x = x;
	      this.y = y;
	      this.sum = A[x] + B[y];
	    }
	  }
}
