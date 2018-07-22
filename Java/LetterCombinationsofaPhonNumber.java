import java.util.*;

public class LetterCombinationsofaPhonNumber {
	private static final String[] LETTERS = {
	        "", //0
	        "", //1
	        "abc", //2
	        "def", //3;
	        "ghi", //4
	        "jkl", //5
	        "mno", //6
	        "pqrs", //7
	        "tuv", //8
	        "wxyz", //9
	    };
	    
	public List<String> solution(String digits) {
	        List<String> res = new ArrayList<String>();
	        if(digits == null || digits.length() == 0)
	            return res;
	        helper(digits, 0, "", res); //backtracking!!!
	        return res;
	}
	
	/**
	 * @param s len of digit already added
	 * @param comb temp string (current result)
	 * @param final result
	 */
	private void helper(String digits, int s, String comb, List<String> res){
	        if(s == digits.length()){ // all digits done, return
	            res.add(comb);
	            return;
	        }
	        String c = LETTERS[digits.charAt(s) - '0']; //get int index of digits !!!! Since digits is a string!!!
	        for(int i = 0; i < c.length(); i++){ // i starts from 0
	            helper(digits, s + 1, comb + c.charAt(i), res); // backtracking!!!
	        }
	    }
	    
	    public static void main(String[] args) {
	    	LetterCombinationsofaPhonNumber l = new LetterCombinationsofaPhonNumber();
	    	List<String> list = l.solution("222");
	    	System.out.println(list);
	    }
}
