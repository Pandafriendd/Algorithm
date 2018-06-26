import java.util.*;

public class generateParen {
	public List<String> generateParenthesis(int n){
		List<String> list = new ArrayList<String>();
		backtrack(list, "", 0, 0, n);
		return list;
	}
	
	public void backtrack(List<String> list, String s, int left, int right, int n){
		//meaning that the number of parenthesis meet the requirement
		if(s.length() == 2 * n){
			list.add(s);
			return;
		}
		
		//the number of left parenthesis less than n, add "(" to s 
		if(left < n)
			backtrack(list, s+"(", left + 1, right, n);
		//the number of right parenthesis less than left parenthesis, add ")" to s
		if(right < left)
			backtrack(list, s+")", left, right+1, n);
	}
	
	public static void main(String args[]){
		generateParen gp = new generateParen();
		System.out.println(gp.generateParenthesis(3));
	}
}
