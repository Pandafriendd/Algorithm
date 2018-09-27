
import java.util.*;
public class MergerSort {
	
	// my solution
	public int[] mergeSort(int[] array) {
	    // Write your solution here
	    if(array == null || array.length == 0)
	      return array;
	    
	    return mSort(array, 0, array.length - 1);
	  }
	  
	  private int[] mSort(int[] array, int left, int right) {
	    if(left == right) {  // base cases
	      return new int[] {array[left]};
	    }
	    else {  // recursive bases
	      int mid = left + (right - left) / 2;
	      int[] lHalf = mSort(array, left, mid);
	      int[] rHalf = mSort(array, mid + 1, right);
	      
	      return merge(lHalf, rHalf);
	    }
	  }
	  
	  private int[] merge(int[] l, int[] r) {
	    int[] res = new int[l.length + r.length];
	    
	    int i = 0, j = 0, k = 0;
	    while(i < l.length && j < r.length) {
	      if(l[i] < r[j]) {
	        res[k++] = l[i++];
	      }
	      else {  // l[i] >= r[j]
	        res[k++] = r[j++];
	      }
	    }
	    
	    while(i < l.length) {
	      res[k++] = l[i++];
	    }
	    while(j < r.length) {
	      res[k++] = r[j++];
	    }
	    
	    return res;
	  }
	
	
	
	public static void main(String[] args)
	{
		Integer[] a = {2, 6, 3, 5, 1};
		mergeSort(a);
		System.out.println(Arrays.toString(a));
	}

	public static void mergeSort(Comparable [ ] a)
	{
		Comparable[] tmp = new Comparable[a.length];
		mergeSort(a, tmp,  0,  a.length - 1);
	}


	private static void mergeSort(Comparable [ ] a, Comparable [ ] tmp, int left, int right)
	{
		if( left < right )
		{
			int center = (left + right) / 2;
			mergeSort(a, tmp, left, center);
			mergeSort(a, tmp, center + 1, right);
			merge(a, tmp, left, center + 1, right);
		}
	}

	// merge 2 arrays: array1[left .. right - 1], array2[right .. rightEnd]
    private static void merge(Comparable[ ] a, Comparable[ ] tmp, int left, int right, int rightEnd )
    {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;

        while(left <= leftEnd && right <= rightEnd)
            if(a[left].compareTo(a[right]) <= 0)
                tmp[k++] = a[left++];
            else
                tmp[k++] = a[right++];

        while(left <= leftEnd)    // Copy rest of first half
            tmp[k++] = a[left++];

        while(right <= rightEnd)  // Copy rest of right half
            tmp[k++] = a[right++];

        // Copy tmp back
        for(int i = 0; i < num; i++, rightEnd--)
            a[rightEnd] = tmp[rightEnd];
    }
}
