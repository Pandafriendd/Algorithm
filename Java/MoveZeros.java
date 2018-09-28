
public class MoveZeros {
	public int[] moveZero(int[] array) {
	    // Write your solution here
	    
	    if(array == null || array.length == 0) {
	      return array;
	    }
	    if(array.length == 1) {
	      return array;
	    }
	    int left = 0; 
	    int right = array.length - 1;
	    while(left <= right) {
	      if(array[left] != 0) {
	        left++;
	      }
	      else if(array[right] == 0) {
	        right--;
	      }
	      else { // array[left] == 0 && array[right] != 0
	        swap(array, left++, right--);
	      }
	    }
	    
	    return array;
	  }
	  
	 public void swap(int[] array, int i, int j) {
	    int t = array[i];
	    array[i] = array[j];
	    array[j] = t;
	  }
}
