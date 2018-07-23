/*
 * Given a string that contains only digits 0-9 and a target value, 
 * return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
 * 
 * Example 1:
	Input: num = "123", target = 6
	Output: ["1+2+3", "1*2*3"] 
 */

import java.util.*;

public class ExpressionAddOperators282 {
	
	/*
	 * This problem has a lot of edge cases to be considered:
		overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
		0 sequence: because we can't have numbers with multiple digits started with zero, we have to deal with it too.
		a little trick is that we should save the value that is to be multiplied in the next recursion.
		link: https://leetcode.com/problems/expression-add-operators/discuss/71895/Java-Standard-Backtrace-AC-Solutoin-short-and-clear
		https://leetcode.com/problems/expression-add-operators/discuss/140390/A-recursive-algorithm-with-detailed-explanation-and-proof-of-correctness
	 */
	public List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<String>();
        if(num == null || num.length() == 0) return rst;
        helper(rst, "", num, target, 0, 0, 0);
        return rst;
    }
	
	/**
	 * 
	 * @param rst
	 * @param path the path to current
	 * @param num
	 * @param target
	 * @param pos The current start position in this recursion
	 * @param eval Keep the current value we have got
	 * @param multed the previous number before this current position(either negative or positive)
	 */
    public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long multed){
        if(pos == num.length()){
            if(target == eval)
                rst.add(path);
            return;
        }
        for(int i = pos; i < num.length(); i++){
            if(i != pos && num.charAt(pos) == '0') break;   // this condition it to exclude numbers with leading zeros
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if(pos == 0){
                helper(rst, path + cur, num, target, i + 1, cur, cur);
            }
            else{
                helper(rst, path + "+" + cur, num, target, i + 1, eval + cur , cur);
                
                helper(rst, path + "-" + cur, num, target, i + 1, eval -cur, -cur);
                
                helper(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur );
            }
        }
    }
}
