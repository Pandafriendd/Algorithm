import java.util.*;

public class GenerateParenthesesII {
	
	/*
	Get all valid permutations of l pairs of (), m pairs of <> and n pairs of {}.

	Assumptions

	l, m, n >= 0
	Examples

	l = 1, m = 1, n = 0, all the valid permutations are ["()<>", "(<>)", "<()>", "<>()"]
	*/

	/*
	[2,0,0]
	Expected: [["(())","()()"]]
	Your Solution: [["(())"]]
	????
	How to initialize the stack????? don't need to worry, just remember removing after adding
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
	
	public static void main(String[] args) {
		System.out.println(validParentheses(2, 0 ,0));
	}
}
