
public class SolutionOfLongestPalindrome {
	String res = "";
	public String longestPalindrome(String s) {
		if(s.length() == 0 || s == null || s.length() < 2)
            return s;
        
        for(int i = 0; i < s.length() - 1; i++){
            extend(s, i, i);
            extend(s, i, i + 1);
        }
        return res;
    }
    
    public void extend(String s, int left, int right){
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }
        String cur = s.substring(left + 1, right);
        if (cur.length() > res.length())
            res = cur;
        
    }
    
    public static void main(String args[]) {
    	SolutionOfLongestPalindrome s = new SolutionOfLongestPalindrome();
    	String a = "babad";
    	System.out.println(s.longestPalindrome(a));
    }
}
