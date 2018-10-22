/*
Right shift a given string by n characters.

Assumptions

The given string is not null.
n >= 0.
 */
public class RightShiftByNCharacters {
	public String rightShift(String input, int n) {
	    // Write your solution here
	    if (input == "") {
	      return input;
	    }
	    
	    char[] array = input.toCharArray();
	    if(n >= array.length) {
	      n = n % array.length;
	    }
	    
	    reverse(array, 0, array.length - 1);
	    reverse(array, 0, n - 1);
	    reverse(array, n, array.length - 1);
	    
	    return new String(array);
	  }
	  
	  private void reverse(char[] array, int start, int end) {
		    if (array == null || array.length == 0) {
		      return;
		    }
		    
		    while (start < end) {
		      swap(array, start, end);
		      start++;
		      end--;
		    }
		  }
	  
	  private void swap(char[] array, int i, int j) {
		    char t = array[i];
		    array[i] = array[j];
		    array[j] = t;
		  }
}
