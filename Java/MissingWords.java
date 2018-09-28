


import java.util.*;
public class MissingWords {
	/*
     * Complete the 'missingWords' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. STRING t
     */
	
	// something wrong
    public static List<String> missingWords0(String s, String t) {
    // Write your code here
        if(s == null || s.length() == 0)
            return new ArrayList<String>();
        
        
        String[] s1 = s.trim().split("\\s+");
        String[] s2 = t.trim().split("\\s+");
        if(s2.length > s1.length) {
            missingWords(t, s);
        }
        
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < s2.length; i++) {
            map.put(s2[i], map.getOrDefault(s2[i], 0) + 1);
        }
        
        List<String> res = new ArrayList<>();
        for(int i = 0; i < s1.length; i++) {
            if(!map.containsKey(s1[i]) || map.containsKey(s1[i]) && map.get(s1[i]) == 0) {  // meaning 1. map not contains key s[i] or 2. contains key but its value == 0
                res.add(s1[i]);
            }
            else if(map.containsKey(s1[i]) && map.get(s1[i]) > 0) {
                map.put(s1[i], map.get(s1[i]) - 1);
            }
        }
        
        return res;
    }

    
    // something right
    public static List<String> missingWords(String s, String t) {
    // Write your code here
        List<String> res = new ArrayList<>();

        String[] s1 = s.split("\\s+");
        String[] s2 = t.split("\\s+");

        for(int i = 0, j = 0; i < s1.length; i++) {
            if(!s1[i].equals(s2[j])) {
                res.add(s1[i]);
            } 
            else {
                if(j >= s2.length - 1) {
                    continue;
                } 
                else {
                    j++;
                }
            }
        }

        return res;
    }
	
	public static void main(String[] args) {
		missingWords("aa aa bb bb cc", "bb bb");
	}
}
