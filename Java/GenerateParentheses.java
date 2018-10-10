import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
	
	// my solution
	public List<String> generateParenthesis0000(int n) {
        List<String> res = new ArrayList<>();
        if(n < 1) {
            return res;
        }
        StringBuilder sb = new StringBuilder();
        generateHelper(n, 0, 0, res, sb);
        
        return res;
    }
    
    private void generateHelper(int n, int l, int r, List<String> res, StringBuilder sb) {
        //base case
        if(sb.length() == 2 * n) {
            res.add(sb.toString());
            return;
        }
        
        // recursive rule
        if(l < n) {
            sb.append("(");
            generateHelper(n, l + 1, r, res, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if(r < l) {
            sb.append(")");
            generateHelper(n, l, r + 1, res, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        
    }
    
    
    
	
	
	public List<String> generateParenthesis(int n){
		List<String> list = new ArrayList<String>();
		backtrack(list, "", 0, 0, n);
		return list;
	}
	
	/**
     * @param left left parentheses already added
     * @param right right parentheses already added
     */
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
		if(right < n) //if(right < n) will be wrong answer, if u look at the result, all results have left >= right till last iteration 
			backtrack(list, s+")", left, right+1, n);
	}
	

    /**
     * Backtracking
     * Helper function use left and right to represent available parentheses
     * Initialize left as n, right as 0
     */
    public List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList<String>();
        if (n <= 0) return ans;
        dfs(n, 0, "", ans);
        return ans;
    }

    /**
     * @param left available left parentheses
     * @param right available right parentheses
     * @param res current result
     * @param ans the answer list of the problem
     */
    public void dfs(int left, int right, String res, List<String> ans) {
        if (left == 0 && right == 0) {
            ans.add(res);
            return;
        }
        if (left > 0) dfs(left - 1, right + 1, res + "(", ans); // add (, left -1, right + 1
        if (right > 0) dfs(left, right - 1, res + ")", ans); // add ), right - 1
    }
	
	public static void main(String args[]){
		GenerateParentheses gp = new GenerateParentheses();
		//System.out.println(gp.generateParenthesis(3));
		System.out.println(gp.generateParenthesis(2));
	}
}
