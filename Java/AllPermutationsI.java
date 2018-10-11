/*
Given a string with no duplicate characters, return a list with all permutations of the characters.

Examples

Set = ¡°abc¡±, all permutations are [¡°abc¡±, ¡°acb¡±, ¡°bac¡±, ¡°bca¡±, ¡°cab¡±, ¡°cba¡±]
Set = "", all permutations are [""]
Set = null, all permutations are []
 */

import java.util.*;
public class AllPermutationsI {
	
	// my dummy solution
	public List<String> permutations(String set) {
	    // Write your solution here
	    List<String> res = new ArrayList<>();
	    char[] array = set.toCharArray(); 
	    permutationHelper(array, "", res);
	    
	    return res;
	  }
	  
	  private void permutationHelper(char[] array, String s, List<String> res) {
	    
	    // base case
	    if(s.length() == array.length) {
	      res.add(s); //!!!   ????????????????????
	      return;
	    }
	    
	    // recursive rules
	    for(int i = 0; i <= array.length - 1; i++) {
	      if(s.indexOf(array[i]) == -1) {  // s not contain array[i]   // !!!!
	        s = s + array[i];  // choose
	        permutationHelper(array, s, res); // explore
	        s = s.substring(0, s.length() - 1);  // unchoose
	      }
	    }
	  }
	

}
