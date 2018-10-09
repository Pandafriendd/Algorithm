
public class FirstOccurrenceString {
	public static int firstOccurrence(String s, String x) {
	        if(s == null || x == null) {
	            return 0;
	        }
	        
	        int len1 = s.length();
	        int len2 = x.length();
	        
	        for(int start = 0; start + len2 <= len1; start++) {
	            boolean flag = true;
	            int i = start;
	            for(int j = 0; j < len2; j++, i++) {
	                if(s.charAt(i) != x.charAt(j) && x.charAt(j) != '*') {
	                    flag = false;
	                    break;
	                }
	            }
	            if(flag) 
	                return start;
	        }
	        
	        return -1;   
	    }
}
