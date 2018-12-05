import java.util.Arrays;

public class QuickSort {
	public int[] quickSort(int[] array) {
	    // Write your solution here
	    if(array == null || array.length == 0) {
	      return array;
	    }
	    
	   quickSort(array, 0, array.length - 1);
	   
	   return array;
	  }
	
	public void quickSort(int[] array, int left, int right) {
	    if(left >= right) { // base cases  !!!!!  if only one element, return. Or if right < left, return
	        return;
      	    }
		
	    int mid = left + (right - left) / 2;
	    swap(array, mid, right);
	    
	    int index = partition(array, left, right);
	    swap(array, index, right);
	    
	    quickSort(array, left, index - 1);
	    quickSort(array, index + 1, right);
	  }
	  
	public int partition(int[] array, int left, int right) { 
	    int l = left;
	    int r = right;
	    int pivot = array[right];
	    
	    while(l <= r) {
	      if(array[l] < pivot) {
	        l++;
	      }
	      else if(array[r] >= pivot) {
	        r--;
	      }
	      else {  // array[l] >= pivot && array[r] < pivot
	        swap(array, l++, r--);
	      }
	    }
	    
	    return l;  // the first one bigger than pivot, since l = r + 1 when while loop finished
	  }
	  
	public void swap(int[] array, int i, int j) {
	    int t = array[i];
	    array[i] = array[j];
	    array[j] = t;
	  }
	  
	  public static void main(String[] args) {
		  QuickSort q = new QuickSort();
		  int[] a = new int[] {3,5,1,2,4,8};
		  q.quickSort(a);
		  System.out.println(Arrays.toString(a));
	  }
}
