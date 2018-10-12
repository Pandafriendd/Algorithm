/*
Sort With 3 Stacks
Given one stack with integers, sort it with two additional stacks (total 3 stacks). 
After sorting the original stack should contain the sorted integers and from top to bottom the integers are sorted in ascending order.

Assumptions:
The given stack is not null.
Requirements:

No additional memory, time complexity = O(nlog(n)).
 */


import java.util.*;
public class SortWith3Stacks {
	
	// s1: store unsorted elements
	// s2: buffer
	// s3: store sorted elements
	public void sort(Deque<Integer> s1) {
	    Deque<Integer> s2 = new ArrayDeque<Integer>();
	    Deque<Integer> s3 = new ArrayDeque<Integer>();
	    
	    while(!s1.isEmpty()) {
	      int min = Integer.MAX_VALUE;
	      // from s1 to s2
	      while(!s1.isEmpty()) {
	        if(s1.peek() < min) {
	          min = s1.peek();
	        }
	        s2.push(s1.pop());
	      }
	      
	      // from s2 to s1 except min
	      while(!s2.isEmpty()) {
	        if(s2.peek() != min) {
	          s1.push(s2.pop());
	        }
	        else {
	          s3.push(s2.pop());
	        }
	      }
	    }
	    
	    // from s3 to s1
	    while(!s3.isEmpty()) {
	      s1.push(s3.pop());
	    }
	}
}
