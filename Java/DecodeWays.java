/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:
Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).

Example 2:
Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
public class DecodeWays {
	
	/*
	 * time: O(n) space: O(n)
	 * used a dp (ways[]) array of size len + 1 to save subproblem solutions
	 * ways[0] means an empty string will have one way to decode, 
	 * ways[1] means the way to decode a string of size 1. 
	 * then check one digit and two digit combination and save the results along the way. 
	 * In the end, ways[len] will be the end result.
	 * note that there can be zeros in s 
	 */
	public static int numDecodings(String s) {
		if(s == null || s.length() == 0)
			return 0;
		int len = s.length();
		int[] ways = new int[len + 1];
		ways[0] = 1;
		ways[1] = s.charAt(0) == '0' ? 0 : 1;
		
		for(int i = 2; i <= len; i++) {
			int code1 = Integer.valueOf(s.substring(i - 1, i)); // 1 digit
			//System.out.println("c1: " + code1);
			int code2 = Integer.valueOf(s.substring(i - 2, i)); // 2 digit
			//System.out.println("c2: " + code2);
			
			//ways[i] = (code1 != 0 ? ways[i - 1] : 0) + (code2 <= 26 && code2 > 9 ? ways[i - 2] : 0);
			if(code1 >= 1 && code1 <= 9)
				ways[i] = ways[i - 1];
			if(code2 >= 10 && code2 <= 26)
				ways[i] += ways[i - 2];
			//System.out.println("ways[" + i + "]: " + ways[i]);
		}
		
		return ways[len];
	}
	/*
	 * optimal DP, space: O(1)
	 */
	
	public static int numDecodings2(String s) {
		if(s == null || s.length() == 0)
			return 0;
		int len = s.length();
		int prev1 = 1;
		int prev2 = s.charAt(0) == '0' ? 0 : 1;
		
		for(int i = 2; i <= len; i++) {
			int code1 = Integer.valueOf(s.substring(i - 1, i)); // 1 digit
			int code2 = Integer.valueOf(s.substring(i - 2, i)); // 2 digit
			int temp = prev2;
			prev2 = (code1 != 0 ? prev2 : 0) + (code2 <= 26 && code2 > 9 ? prev1 : 0);
			prev1 = temp;
		}
		
		return prev2;
	}
	
	
	
	public static void main(String[] args)
    {
		DecodeWays d = new DecodeWays();
        //System.out.println(numDecodings("2304"));  // should return 0
        /*
        String             "2     3      0      4"
  		  i          0      1     2      3      4
        dp[i]        1      1     2      0      0
         */
		//System.out.println(numDecodingsRecursion("123"));
		System.out.println(numDecodings2("123"));
		System.out.println(numDecodings("123"));
    }
	
	
	// recursion O(2^n)
	static int numDecodingsRecursion(String s) {
        return s.isEmpty() ? 0: helper(0,s);    
    }
    static int helper(int p, String s) {
        int n = s.length();
        if(p == n) return 1;
        if(s.charAt(p) == '0') return 0;
        int res = helper(p+1,s);
        if( p+1 < n && (s.charAt(p)=='1'|| (s.charAt(p)=='2'&& s.charAt(p + 1)<'7'))) 
        	res += helper(p+2,s);
        return res;
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	// my stupid thought!!! never works!!! WTF!!!
	public static void SubString(String str, int n)
    {
		int count = 0;
		StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) { 
           for (int j = i+1; j <= n; j++) {
        	   String subs = str.substring(i, j);
        	   
        	   if((Integer.valueOf(subs)) <= 26) {
        		   System.out.println(subs);
        		   sb.append(subs);
        		   
        	   }
           }
        }
        
        for(int i = 0; i < sb.length() - 2; i++) {
    	   for(int j = i + 1; j < sb.length() - 1; j++) {
    		   for(int k = j + 1; k < sb.length(); k++) {
    			   StringBuilder temp = new StringBuilder();
    			   temp.append(sb.charAt(i));
    			   temp.append(sb.charAt(j));
    			   temp.append(sb.charAt(k));
    			   //System.out.println(temp.toString());
    			   if(temp.toString().equals(str)) {  // !!! have to use equals!!!!
    				   count++;
    			   }
    		   }
    	   }   
    	}
        System.out.println("count " + count);
    }
 
    
}
