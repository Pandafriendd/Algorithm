
import java.util.*;

public class KthSmallestSumInTwoSortedArraysUisngHashSet {
	// time: O(klogk)  space: O(n) n is the # of unique sum of A and B
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
	    HashSet<Cell> visited = new HashSet<>();
	    minHeap.offer(new Cell(0, 0, A, B));
	    visited.add(new Cell(0, 0, A, B));
	    
	    for (int i = 0; i < k; i++) {
	      if (!minHeap.isEmpty()) {
	        Cell cur = minHeap.poll(); // expend
	        int x = cur.x;
	        int y = cur.y;
	        
	        if (i == k - 1) {  // when generate the kth times, return
	          return cur.sum;
	        }
	        
	        if (x + 1 < A.length && visited.add(new Cell(x + 1, y, A, B))) {
	          minHeap.offer(new Cell(x + 1, y, A, B));  // generate
	        }
	        if (y + 1 < B.length && visited.add(new Cell(x, y + 1, A, B))) {
	          minHeap.offer(new Cell(x, y + 1, A, B));  // generate
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
	    
	    @Override
	    public boolean equals(Object obj) {
	      if (this == obj) {
	        return true;
	      }
	      if (!(obj instanceof Cell)) {
	        return false;
	      }
	      
	      Cell other = (Cell) obj;
	      return this.sum == other.sum;
	    }
	    
	    @Override
	    public int hashCode() {
	      return this.x * 31 * 31 + this.y * 31 + this.sum;
	    }
	  }
}
