

import java.util.*;

public class QueueByTwoStacks {
	
	  private Deque<Integer> stack1;  // for offer elements
	  private Deque<Integer> stack2; // for poll elements
	  private int size;
	  
	  public QueueByTwoStacks() {
	    stack1 = new ArrayDeque<>();
	    stack2 = new ArrayDeque<>();
	    size = 0;
	  }
	  
	  public Integer poll() {
	    if(size == 0) {
	      return null;
	    }
	    
	    if(!stack2.isEmpty()) {
	      size--;
	      return stack2.pop();
	    }
	    else {
	      while(!stack1.isEmpty()) {
	        stack2.push(stack1.pop());
	      }
	      size--;
	      return stack2.pop();
	    }
	  }
	  
	  public void offer(int value) {
	    stack1.push(value);
	    size++;
	  }
	  
	  public Integer peek() {
	    if(size == 0) {
	      return null;
	    }
	    
	    if(!stack2.isEmpty()) {
	      return stack2.peek();
	    }
	    else {
	      while(!stack1.isEmpty()) {
	        stack2.push(stack1.pop());
	      }
	      return stack2.peek();
	    }
	  }
	  
	  public boolean isEmpty() {
	    return size == 0;
	  }
	  
	  public int size() {
	    return size;
	  }
}
