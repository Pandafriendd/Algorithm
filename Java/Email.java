import java.util.*;
public class Email {
	public int solution(String[] L) {
        // write your code in Java SE 8
        if(L == null || L.length <= 1) {
            return 0;
        }
        
        HashMap<String, Integer> map = new HashMap<>();
        for(String s : L) {
            s = clear(s);
            map.put(s, map.getOrDefault(s, 0) + 1);
            
        }
        
        int res = 0;
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue() >= 2) {
                res++;
            }
        }
        
        return res;
    }
    
    private String clear(String s) {
        String[] a = s.split("@");
        
        int index = a[0].indexOf("+");
        if(index != -1) {
            a[0] = a[0].substring(0, index);
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < a[0].length(); i++) {
            if(a[0].charAt(i) != '.') {
                sb.append(a[0].charAt(i));
            }
        }
        a[0] = sb.toString() + a[1];
        return a[0];
    }
}
