//Reverse a given string. Assumptions The given string is not null.

public class ReverseString {
	public String reverse(String input) {
	    // Write your solution here
	    char[] array = input.toCharArray();
	    int left = 0;
	    int right = array.length - 1;
	    
	    while (left < right) {
	      swap(array, left, right);
	      left++;
	      right--;
	    }
	    
	    return new String(array);
	  }
	  
	  private void swap(char[] array, int i, int j) {
		    char t = array[i];
		    array[i] = array[j];
		    array[j] = t;
	}
	  
	  
	  // revursion
	  public String reverse2(String input) {
		  char[] array = input.toCharArray();
		  array = reverseHelper(array, 0, array.length);
		  return new String(array);
	  }
	  
	  private char[] reverseHelper(char[] array, int left, int right) {
		  // base case
		  if (left >= right) {  // >= !!!  == is WRONG !!! think about reverse only 2 chars
			  return array;
		  }
		  
		  swap(array, left, right);
		  return reverseHelper(array, left + 1, right - 1);
	  }
	  
	  
	  
}
