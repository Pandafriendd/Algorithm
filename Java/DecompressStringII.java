/*
Given a string in compressed form, decompress it to the original string. The adjacent repeated characters in the original string are compressed to have the character followed by the number of repeated occurrences.

Assumptions

The string is not null

The characters used in the original string are guaranteed to be ¡®a¡¯ - ¡®z¡¯

There are no adjacent repeated characters with length > 9

Examples

¡°a1c0b2c4¡± ¡ú ¡°abbcccc¡±
 */
public class DecompressStringII {
	
	// more elegant method
	public String dc(String input) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i++);
			int count = input.charAt(i) - '0';  // !!
			while (count > 0) {
				sb.append(c);
				count--;
			}
		}
		
		return sb.toString();
	}
	
	
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
		        // int count = Integer.parseInt(c); // Wrong!! char cannot be converted to String
		        int count = c - '0';
	            if (count == 0) {
	              sb.deleteCharAt(sb.length() - 1);  // !!!
	              continue;
	            }
		        while (count > 1) {  // since we have append once when we first meet the char
		          sb.append(last);
		          count--;
		        }
		    }
		}
		    
		return sb.toString();
	}


}
