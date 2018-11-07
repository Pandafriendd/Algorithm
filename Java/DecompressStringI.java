/*
Given a string in compressed form, decompress it to the original string. The adjacent repeated characters in the original string are compressed to have the character followed by the number of repeated occurrences. If the character does not have any adjacent repeated occurrences, it is not compressed.

Assumptions

The string is not null

The characters used in the original string are guaranteed to be ¡®a¡¯ - ¡®z¡¯

There are no adjacent repeated characters with length > 9

Examples

¡°acb2c4¡± ¡ú ¡°acbbcccc¡±
 */

import java.util.*;

public class DecompressStringI {
	
	// assume the digit is less than 10
	public String decompress(String input) {
	    StringBuilder sb = new StringBuilder();
	    char last = ' ';
	    for (int i = 0; i < input.length(); i++) {
	      char c = input.charAt(i);
	      
	      if (!Character.isDigit(c)) {
	        sb.append(c);
	        last = c;
	      } else {
	        int count = c - '0';
	        while (count > 1) {  // since we have append once when we first meet the char
	          sb.append(last);
	          count--;
	        }
	      }
	    }
	    
	    return sb.toString();
	}
}
