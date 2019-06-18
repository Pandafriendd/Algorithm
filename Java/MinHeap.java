
/*
Implementation of a (capacity limited) min heap containing only int elements, 
with the basic operations of heap and the capability to do update at a specific index
*/
public class MinHeap {
	private int[] heapArray;
	private int size;

	public MinHeap(int capacity) {  // constructor 1
		if(capacity <= 0) {
			throw new IllegalArgumentException();
		}

		heapArray = new int[capacity];
		size = 0;
	}

	public MinHeap(int[] array) { // constructor 2
		if(array == null || array.length == 0) {
			throw new IllegalArgumentException();
		}

		heapArray = array;
		size = array.length;
		heapify();
	}

	private void heapify() {  // find the last node that has children, and do percolateDown. Then go through other parents
		int lastParent = size / 2 - 1;  // index of last parent
		for(int parent = lastParent; parent >= 0; parent--) {
			percolateDown(parent);
		}
	}

	public void offer(int value) {
		if(size < heapArray.length) {   // not out of capacity
			heapArray[size] = value;
			percolateUp(size);
			size++;
		}
		else { // size == heapArray.length. reach max capacity

			// TO DO: automatically bigger the capacity
			int[] newArray = new int[(int) (heapArray.length * 1.5)];
			copy(newArray, heapArray);  // !!! ???
			heapArray = newArray;

			heapArray[size] = value;
			percolateUp(size);
			size++;
		}
	}

	public Integer poll() {  // poll the peak
		if(size == 0) {
			throw new NoSuchElementException();
		}

		int peak = heapArray[0];
		swap(0, size - 1);
		percolateDown(0);
		size--;
		return peak;
	}

	public int update(int index, int newValue) {  // return old value
		if(index < 0 || index > size - 1) {
			throw new IllegalArgumentException();
		}

		int oldValue = heapArray[index];
		heapArray[index] = newValue;
		if(oldValue > newValue) {
			percolateUp(index);
		}
		else if(oldValue < newValue) {
			percolateDown(index);
		}

		return oldValue;
	}

	public Integer size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean isFull() {
		return size == heapArray.length;
	}

	private void percolateDown(int index) {
		//[0, size/2 - 1] is the range that has children
		while(index <= size / 2 - 1) { // check if it has children  !! should be size / 2 - 1 rather than (size - 2) / 2, since when size = 1 later one is wrong
			int lChild = index * 2 + 1;
			int rChild = index * 2 + 2;
			int swapCandidate = lChild;  // !!!choose the min child as the candidate
			if(rChild <= size - 1 && heapArray[rChild] < heapArray[lChild]) {
				swapCandidate = rChild;
			}
			if(heapArray[index] > heapArray[swapCandidate]) {
				swap(index, swapCandidate);
			} 
			else {
				break;
			}

			index = swapCandidate;
		}
	}

	private void percolateUp(int index) {
		while(index > 0) {
			int parent = (index - 1) / 2;
			if(parent >= 0 && heapArray[parent] > heapArray[index]) {
				swap(parent, index);
			}
			else {
				break;
			}

			index = parent;
		}
	}

	private void swap(int i, int j) {
		int temp = heapArray[i];
		heapArray[i] = heapArray[j];
		heapArray[j] = temp;
	}
}
