/*
Given an array that is initially stored in one stack, sort it with one additional stacks (total 2 stacks).

After sorting the original stack should contain the sorted integers and from top to bottom the integers are sorted in ascending order.

Assumptions:

The given stack is not null.
Requirements:

No additional memory, time complexity = O(n ^ 2).
 */

import java.util.*;

public class SortWith2Stacks {
	
	// could have dulplicates
	
	// s1 input, s1  left | right, left is sorted, right is buffer
	public void sort(Deque<Integer> s1) {
	    
	    Deque<Integer> s2 = new ArrayDeque<Integer>();
	    
	    while(!s1.isEmpty()) {
	      int min = Integer.MAX_VALUE;
	      int minCount = 0;  // deal with dul elements
	      
	      // from s1 to s2
	      while(!s1.isEmpty()) {
	        if(s1.peek() < min) {
	          min = s1.peek();
	          minCount = 0;
	        }
	        if(s1.peek() == min) {
	          minCount++;
	        }
	        s2.push(s1.pop());
	      }
	      
	      // from s2 to s1 except min, finally push min to s2
	      while(!s2.isEmpty() && s2.peek() >= min) {  // !!!
	        if(s2.peek() != min) {
	          s1.push(s2.pop());
	        }
	        else {
	          min = s2.pop();
	        }
	      }
	      while(minCount > 0) {
            s2.push(min);
            minCount--;
          }
	    }
	    
	    // from s3 to s1
	    while(!s2.isEmpty()) {
	      s1.push(s2.pop());
	    }
	}
}
