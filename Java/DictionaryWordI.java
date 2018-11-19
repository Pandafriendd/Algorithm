/*
Given a word and a dictionary, determine if it can be composed by concatenating words from the given dictionary.

Assumptions

The given word is not null and is not empty
The given dictionary is not null and is not empty and all the words in the dictionary are not null or empty
Examples

Dictionary: {¡°bob¡±, ¡°cat¡±, ¡°rob¡±}

Word: ¡°robob¡± return false

Word: ¡°robcatbob¡± return true since it can be composed by "rob", "cat", "bob"
 */

import java.util.*;
public class DictionaryWordI {
	/*
	 DP: M[i] if from 0-th to i-1-th elements can composed by concatenating words from the dict
	 base case: M[0]
	 indiuction rule: M[i] = true, if there are exist at least one j (j < i), such that M[j] is true && substring(j + 1, i) is also true
	 					   = false, otherwise
	 */
	public boolean canBreak(String input, String[] dict) {
		HashSet
		
	}

}
