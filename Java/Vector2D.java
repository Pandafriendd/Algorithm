import java.util.*;

/*
Implement an iterator to flatten a 2d vector.

Example:

Input: 2d vector =
[
  [1,2],
  [3],
  [4,5,6]
]
Output: [1,2,3,4,5,6]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,2,3,4,5,6].
Follow up:
As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 */


/*
I first hold the 2D List inside a Iterator of List this allows me to operate on the subsequent list once needed. 
I then remove the first list from the 2D List and store as row which is evaluated when next() & hasNext() are called. 
I then want to ensure row iterator is pointing to a none empty list so i call the getNextRow() method. 
next() and hashNext() are now very simple. next() returns the next element of the current list then ensures row isn't empty. 
hasNext() checks row isn't null and has a next value.
 */
public class Vector2D {
    Iterator<List<Integer>> itrs;
    Iterator<Integer> row;
    
    public Vector2D(List<List<Integer>> vec2d) {
        if(vec2d == null || vec2d.size() == 0) return;
        itrs = vec2d.iterator();
        row = itrs.next().iterator();
        getNextRow(); // !!
    }
    
    private void getNextRow(){
        while(!row.hasNext() && itrs.hasNext())  // !!! while
        	row = itrs.next().iterator();
    }

    public int next() {
        int next = row.next();
        getNextRow();
        return next;
    }

    public boolean hasNext() {
        return row != null && row.hasNext();
    }
}

/*
Put all iterator in a queue
Keep track of the current iterator
Check hasNext() and next() of current
 */
public class Vector2D1{
	Queue<Iterator<Integer>> queue;
	 Iterator<Integer> current = null;
	 
	 public Vector2D1(List<List<Integer>> vec2d) {
	     queue = new LinkedList<Iterator<Integer>>();
	     for (int i = 0; i < vec2d.size(); i++){
	         queue.add(vec2d.get(i).iterator());
	     }
	     current = queue.poll(); // first row
	 }

	 public int next() {  // ??
	     if (!current.hasNext()) 
	    	 return -1;
	     
	     return current.next();
	 }

	 public boolean hasNext() {
	     if (current == null) 
	    	 return false;
	     
	     while (!current.hasNext()) {
	         if (!queue.isEmpty())
	             current = queue.poll(); 
	         else 
	        	 return false;
	     }
	     
	     return true;
	 }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
