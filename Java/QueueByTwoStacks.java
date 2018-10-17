

import java.util.*;

public class QueueByTwoStacks {
	
	  private Deque<Integer> offerStack;  // for offer elements
	  private Deque<Integer> pollStack; // for poll elements
	  private int size;
	  
	  public QueueByTwoStacks() {
	    offerStack = new ArrayDeque<>();
	    pollStack = new ArrayDeque<>();
	    size = 0;
	  }
	  
	  public Integer poll() {
	    if(size == 0) {  // !!
	      return null;
	    }
	    
	    if(!pollStack.isEmpty()) {
	      size--;
	      return pollStack.pop();
	    }
	    else {
	      while(!offerStack.isEmpty()) {
	        pollStack.push(offerStack.pop());
	      }
	      size--;
	      return pollStack.pop();
	    }
	  }
	  
	  public void offer(int value) {
	    offerStack.push(value);
	    size++;
	  }
	  
	  public Integer peek() {
	    if(size == 0) {
	      return null;
	    }
	    
	    if(!pollStack.isEmpty()) {
	      return pollStack.peek();
	    }
	    else {
	      while(!offerStack.isEmpty()) {
	        pollStack.push(offerStack.pop());
	      }
	      return pollStack.peek();
	    }
	  }
	  
	  public boolean isEmpty() {
	    return size == 0;
	  }
	  
	  public int size() {
	    return size;
	  }
}
