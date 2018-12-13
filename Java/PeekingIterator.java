/*
Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation 
-- it essentially peek() at the element that will be returned by the next call to next().

Example:
Assume that the iterator is initialized to the beginning of the list: [1,2,3].
Call next() gets you 1, the first element in the list.
Now you call peek() and it returns 2, the next element. Calling next() after that still return 2. 
You call next() the final time and it returns 3, the last element. 
Calling hasNext() after that should return false.

Follow up: How would you extend your design to be generic and work with all types, not just integer?
 */

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html


import java.util.*;

/*
my idea: cache the next value, when we call peek(), we cache it. Everytime when we call hasNext(), we check if nextvalue exist first, so do for next()
 */
public class PeekingIterator implements Iterator<Integer> {
	private Integer nextValue;
	private Iterator<Integer> iterator;
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
		nextValue = null;
		this.iterator = iterator;
	}
	
	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		int result = 0;
		if (nextValue != null) {
			result = nextValue;
		} else if (iterator.hasNext()) {
			nextValue = iterator.next();
			result = nextValue;
		}
		
		return result;
	}

	@Override
	public boolean hasNext() {
		if (nextValue != null) {	
			return true;
		} else {
			return iterator.hasNext();
		}
	}

	@Override
	public Integer next() {
		if (nextValue != null) {
			int oldNext = nextValue;
			nextValue = null;   // !!!!!
			return oldNext;
		} else {
			if (iterator.hasNext()) {
				return iterator.next();
			} else {
				return null;
			}
		}
	}
	
}
