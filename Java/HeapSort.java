import java.util.Arrays;

/*
Heap Sort is a comparison based sorting algorithm with O(nlogn) time and O(1) space.

Requirements:

You have to do it in place, extra space used is no more than O(1).
Time complexity is O(nlogn).
 */
public class HeapSort {
	/* 
	  1. heapify the input to a max heap, since we want a ascending sorted output, it takes O(n) time and O(1) space
	  2. swap the max element to last position, and then percolate the root element down, which takes O(logn) per operation.
	  3. then swap the second max elemnt to the second last position, percolate the root down ...
	  4. there are n times percolate down in total, it takes O(nlogn). Overall time: O(n + nlogn) = O(nlogn), space O(1) 
	 */ 
	  public static int[] heapsort(int[] array) {
	    if (array == null || array.length == 0 || array.length == 1) {
	      return array;
	    }
	    heapify(array);

	    int end = array.length - 1;
	    while (end > 0) {  // end = 0 means process completed
	      swap(array, 0, end);
	      percolateDown(array, 0, --end); // ! make sure to rule out the just-swap-to-end element in the percolate down process
	    }

	    return array;
	  }

	  private static void heapify(int[] array) {
	    int lastParent = array.length / 2 - 1;  // index of last parent
			for(int parent = lastParent; parent >= 0; parent--) {
				percolateDown(array, parent, array.length - 1);
			}
	  }

	  private static void percolateDown(int[] array, int downIndex, int endIndex) {
		if (downIndex == endIndex) {
			return;
		}
	    // node parent's index = (node's index - 1) / 2
		// [0, (endIndex - 1) / 2] is the range that has children
		while(downIndex <= ((endIndex - 1) / 2)) { // check if it has children  !! should be size / 2 - 1 rather than (size - 2) / 2, since when size = 1 later one is wrong. Or make sure size > 1
			int lChild = downIndex * 2 + 1;
			int rChild = downIndex * 2 + 2;
			int swapCandidate = lChild;  // !!! choose the max child as swap candidate
			if(rChild <= endIndex && array[rChild] > array[lChild]) {
				swapCandidate = rChild;
			}
			if(array[downIndex] < array[swapCandidate]) {
				swap(array, downIndex, swapCandidate);
			} 
			else {
				break;
			}

			downIndex = swapCandidate;
		}
	  }

	  private static void swap(int[] array, int index1, int index2) {
	    int temp = array[index1];
	    array[index1] = array[index2];
	    array[index2] = temp;
	  }
	  
	  public static void main(String[] args) {
		  int[] array = new int[] {1, 2, 3, 4, 5, 6, 7};
		  System.out.println(Arrays.toString(heapsort(array)));
	  }
}
