
import java.util.*;



/*
	Get all valid permutations of l pairs of (), m pairs of <> and n pairs of {}.

	Assumptions

	l, m, n >= 0
	Examples

	l = 1, m = 1, n = 0, all the valid permutations are ["()<>", "(<>)", "<()>", "<>()"]
*/


public class GenerateParenthesesII {
	
	
	/*
	[2,0,0]
	Expected: [["(())","()()"]]
	Your Solution: [["(())"]]
	????
	How to initialize the stack????? don't need to worry, just remember removing after adding
	
	brief of solution:
	case 1: whenever we add a left p, check pRemain, push to stack, add it to sb
	case 2: ....              right p, only need to check if it matches the top of stack
		2.1: if matches: stack.pop, add it to sb
		2.2 not matches: skip this branch, do not call recursion function
	*/ 
	public static List<String> validParentheses(int l, int m, int n) {
		int[] pRemain = {l, m, n};
		int length = 2 * (l + m + n);
		Deque<Character> stack = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		List<String> res = new ArrayList<>();
		helper(pRemain, stack, sb, res, length);
		
		return res;
	}

	/**
	* @param pRemain remian # of ps still needed
	* add an element into stack or sb, it must be removed!!!!!!!!!!!!!!!
	*/
	private static void helper(int[] pRemain, Deque<Character> stack, StringBuilder sb, List<String> res, int length) {
		// base case
		if (pRemain[0] == 0 && pRemain[1] == 0 && pRemain[2] == 0 && sb.length() == length) {
			res.add(sb.toString());
			return;
		}

		if (pRemain[0] > 0) {
			stack.push('(');
			sb.append('(');
			pRemain[0]--;
			helper(pRemain, stack, sb, res, length);
			sb.deleteCharAt(sb.length() - 1);
			pRemain[0]++;
			stack.pop();  // !!
		}
		if (pRemain[1] > 0) {
			stack.push('<');
			sb.append('<');
			pRemain[1]--;
			helper(pRemain, stack, sb, res, length);
			sb.deleteCharAt(sb.length() - 1);
			pRemain[1]++;
			stack.pop();
		}
		if (pRemain[2] > 0) {
			stack.push('{');
			sb.append('{');
			pRemain[2]--;
			helper(pRemain, stack, sb, res, length);
			sb.deleteCharAt(sb.length() - 1);
			pRemain[2]++;	
			stack.pop();
		}

		if (!stack.isEmpty() && stack.peek() == '(') {
			sb.append(')');
			stack.pop();
			helper(pRemain, stack, sb, res, length);
			sb.deleteCharAt(sb.length() - 1);
			stack.push('(');  // !!!
		}
		if (!stack.isEmpty() && stack.peek() == '<') {
			sb.append('>');
			stack.pop();
			helper(pRemain, stack, sb, res, length);
			sb.deleteCharAt(sb.length() - 1);
			stack.push('<');
		}
		if (!stack.isEmpty() && stack.peek() == '{') {
			sb.append('}');
			stack.pop();
			helper(pRemain, stack, sb, res, length);
			sb.deleteCharAt(sb.length() - 1);
			stack.push('{');
		}
	}
	
	/*
	following up: we have an additional priority
	
	brief of solution:
	case 1: whenever we add a left p, first check the priority of curr against (=<) the top of stack and pRmain, if valid, then push to stack, add it to sb
	(same) case 2: ....      right p, only need to check if it matches the top of stack
		2.1: if matches: stack.pop, add it to sb
		2.2 not matches: skip this branch, do not call recursion function
	 */
	
	public static void main(String[] args) {
		System.out.println(validParentheses(2, 0 ,0));
	}
}
