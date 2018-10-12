
import java.util.*;

public class StackWithmin {
	  private Deque<Integer> stack1;
	  private Deque<Integer> stack2;
	  private int size;
	  private int min;
	  
	  public StackWithmin() {
	    stack1 = new ArrayDeque<>();
	    stack2 = new ArrayDeque<>();
	    size = 0;
	    min = Integer.MAX_VALUE; // !!!
	    
	  }
	  
	  public Integer pop() {
	    if(size == 0) {
	      return -1;
	    }
	    else {
	      stack2.pop();
	      min = stack2.isEmpty() ? Integer.MAX_VALUE : stack2.peek();  // !!
	      size--;
	      return stack1.pop();
	    }
	  }
	  
	  public void push(int element) {
	    stack1.push(element);
	    if(element < min) {
	      min = element;
	    }
	    stack2.push(min);
	    size++;
	  }
	  
	  public Integer top() {
	    if(size == 0) {
	      return -1;
	    }
	    else {
	      return stack1.peek();
	    }
	  }
	  
	  public Integer min() {
	    if(size == 0) {
	      return -1;
	    }
	    else {
	      return stack2.peek();
	    }
	  }
}
