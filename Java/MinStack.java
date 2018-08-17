/*
 Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.
 */

import java.util.*;
/*
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * without using java Stack lib, by a linkedlist
 */
class MinStack666 {
    private Node head;
    
    public void push(int x) {
        if(head == null) 
            head = new Node(x, x);
        else 
            head = new Node(x, Math.min(x, head.min), head);
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }
    
    private class Node {
        int val;
        int min;
        Node next;
        
        private Node(int val, int min) {
            this(val, min, null);
        }
        
        private Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }
}


class MinStack {
	/*
	 * standard solution, two ss, a minStack to store minimums
	 */
	private Stack<Integer> s = new Stack<>();
	private Stack<Integer> minS = new Stack<>();
	
    public MinStack() {
        
    }
    
    public void push(int x) {
        s.push(x);
        if(minS.isEmpty() || x <= minS.peek())
        	minS.push(x);
    }
    
    public void pop() {
        if(s.pop().equals(minS.peek()))
        	minS.pop();
    }
    
    public int top() {
        return s.peek();
    }
    
    public int getMin() {
        return minS.peek();
    }
}



/**
 * one stack
 * build a static wrapper class for items in stack
 * including its value and current min
 */
class MinStack2 {
    
    /**
     * Wrapper class for element in stack
     */
    static class Element {
        
        final int value;
        final int min;
        
        public Element(int x, int min) {
            this.value = x;
            this.min = min;
        }
    }

    Stack<Element> s;

    public void push(int x) {
        if (s == null) s = new Stack<Element>();
        int min = s.isEmpty() ? x : Math.min(x, s.peek().min);
        s.push(new Element(x, min));
    }

    public void pop() {
        s.pop();
    }

    public int top() {
        return s.peek().value;
    }

    public int getMin() {
        return s.peek().min;
    }
}



/*
 The idea is to store the gap between the min value and the current value;

The problem for my solution is the cast. 
I have no idea to avoid the cast. 
Since the possible gap between the current value and the min value could be Integer.MAX_VALUE-Integer.MIN_VALUE;
 */
public class MinStack333 {
    long min;
    Stack<Long> stack;

    public MinStack333(){
        stack=new Stack<>();
    }
    
    public void push(int x) {
        if (stack.isEmpty()){
            stack.push(0L); //??
            min=x;
        }else{
            stack.push(x-min);//Could be negative if min value needs to change
            if (x<min) min=x;
        }
    }

    public void pop() {
        if (stack.isEmpty()) return;
        
        long pop=stack.pop();
        
        if (pop<0)  min=min-pop;//If negative, increase the min value
        
    }

    public int top() {
        long top=stack.peek();
        if (top>0){
            return (int)(top+min);
        }else{
           return (int)(min);
        }
    }

    public int getMin() {
        return (int)min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
