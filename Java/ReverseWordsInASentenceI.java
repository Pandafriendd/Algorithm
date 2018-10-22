/*
Reverse the words in a sentence.

Assumptions

Words are separated by single space

There are no heading or tailing white spaces

Examples

¡°I love Google¡± ¡ú ¡°Google love I¡±

Corner Cases

If the given string is null, we do not need to do anything.
 */


public class ReverseWordsInASentenceI {
	public String reverseWords(String input) {
	    // Write your solution here
	    if (input == null) {
	      return input;
	    }
	    
	    // 1. reverse the whole sentence
	    char[] array = input.toCharArray();
	    reverse(array, 0, array.length - 1);  // !!! careful about the bound !!!!
	    
	    // 2. reverse each world
	    int startOfWorld = 0;;
	    for (int i = 0; i < array.length; i++) {
	    	// when i encounter a space
	      if (array[i] == ' ') {
	        reverse(array, startOfWorld, i - 1);
	        startOfWorld = i + 1;
	      }
	      
	      // i reach the end of the sentence
	      if (i == array.length - 1) {
	        reverse(array, startOfWorld, i);
	      }
	    }
	    
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
