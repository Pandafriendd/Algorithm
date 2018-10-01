
import java.util.*;
public class leetcode3 {
	public static int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        
        HashMap<Character, Integer> map = new HashMap<>();  // char ==> index
        int i = 0;
        int res = 0;     
        for(int j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if(map.containsKey(c)) {
                i = Math.max(map.get(c), i);
            }
            res = Math.max(res, j - i + 1);
            map.put(c, j + 1);
        }
        
        return res;
    }
	
	public static void main(String[] args) {
		int a = lengthOfLongestSubstring("abcabcbb");
	}
}
