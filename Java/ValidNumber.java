/*
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true

Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
 */

/**
 * 
 * @author lizhi
 * the real task would be to ask for more specification (to see how a candidate might handle vague wishes from a customer),
 * it requires heavy clarification through interaction
 * you need to ask the interviewer what the definition is
 * 
 * The question to ask:
 * 1. whether we can have digit number after "e"? 
 * e.g. "e" and "e9" are supposed to be false from the test cases but they are totally legitimate hex numbers, so the problem better be decimal reasonable numbers
 * 2. whether we can let "." in both sides?
 * 3. 1e12 is valid, then how about e12?
 * 4. whether 2.334e2.0 is valid?
 * 5. ++1, +1, 1+ the +/- must be at the beginning? must be the first non-space char?
 * 6. " ", must appear at two ends?
 * 
 * 
 * testcases: 
 * 3.e3 is 3.0e3 = 3000
 * 48.
 * .1
 * 1e12 and e12
 * 2.334e2.0 this is not a "valid" number in scientific notation. The exponent must be an integer.
 *
 */
public class ValidNumber {
	
	/*
We start with trimming.

If we see [0-9] we reset the number flags.
We can only see . if we didn't see "e" or "."
We can only see e if we didn't see e but we did see a number. We reset numberAfterE flag.
We can only see + and - in the beginning and after an e
any other character break the validation.

At the end it is only valid if there was at least 1 number and if we did see an e then a number after it as well.
So basically the number should match this regular expression:

[-+]?(([0-9]+(.[0-9]*)?)|.[0-9]+)(e[-+]?[0-9]+)?
	 */
	public boolean isNumber(String s) {
        s = s.trim();

        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = false;
        
        for(int i = 0; i < s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
                if (eSeen) {
                    numberAfterE = true;
                }
            } 
            else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } 
            else if(s.charAt(i) == 'e') {
                if(eSeen || !numberSeen) {
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            } 
            else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i != 0 && s.charAt(i-1) != 'e') {
                    return false;
                }
            } 
            else {
                return false;
            }
        }

        return numberSeen && (eSeen && numberAfterE || !eSeen && !numberAfterE);
    }
	
	public static void main(String[] args) {
		ValidNumber v = new ValidNumber();
		System.out.println(v.isNumber("1  2"));
	}
	
	//regular expressions
	public boolean isNumber2(String s) {
        return s.matches("^\\s*[-+]?((\\d+(\\.\\d*)?)|(\\.\\d+))(e[-+]?\\d+)?\\s*$");
    }
}
