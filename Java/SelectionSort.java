
public class SelectionSort {
	public static int[] solve(int[] array) {
	    // Write your solution here
	    
	    if(array == null || array.length == 0)
	      return array;
	    
	    for(int i = 0; i <= array.length - 2; i++) {
	      int min = i;
	      for(int j = i + 1; j <= array.length - 1; j++) {
	        if(array[j] < array[min]) {
	          min = j;
	        }    
	      }
	      swap(array, i, min);  // !!
	    }
	    
	    return array;
	  }
	private static void swap(int[] a, int i, int j) {
	    int temp = a[i];
	    a[i] = a[j];
	    a[j] = temp;
	}
	
	public static void main(String[] args) {
		solve(new int[] {2,1,3,5,4,8});
		
	}
	  
	 
}
