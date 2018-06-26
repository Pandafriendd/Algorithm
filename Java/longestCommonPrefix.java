import java.util.*;


public class longestCommonPrefix {
	public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0 && strs == null)
            return "";
        if(strs.length == 1)
        	return strs[0];
        
        String prefix = strs[0];
        int len = prefix.length();
        
        for(int i = 1; i < strs.length; i++){
        	len = Math.min(prefix.length(), strs[i].length());
        	int j = 0;
        	for(j = 0; j < len; j++){
        		if(prefix.charAt(j) != strs[i].charAt(j))
        			break;
        	}
        	prefix = prefix.substring(0,j);
        }
        return prefix;
    }
	
	public static void main(String agrs[]){
		longestCommonPrefix lcp = new longestCommonPrefix();
		String[] strs = {"abcdef", "abce", "abcd"};
		System.out.println(lcp.longestCommonPrefix(strs));
	}
}

public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) return "";
    String pre = strs[0];
    for (int i = 1; i < strs.length; i++)
        while(strs[i].indexOf(pre) != 0)
            pre = pre.substring(0,pre.length()-1);
    return pre;
}