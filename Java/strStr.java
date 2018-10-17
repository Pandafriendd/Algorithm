
public class strStr {
	public int strStr(String haystack, String needle) {
        if(needle.length() == 0)
        	return 0;
        if(needle.length() > haystack.length() || haystack.length() == 0)
        	return -1;
        
        for(int i = 0; i <= haystack.length() - needle.length(); i++) {
        	String s = haystack.substring(i, i + needle.length()); //! substring includes start index, exclude end index
        	if(s.equals(needle))
        		return i;
        }
        return -1;
    }
	
	
	// O(len1 * len2)  KMP can do O(len1 + len2)
	public static int strstr(String large, String small) {
	  
	    if (small.isEmpty()) {
	      return 0;
	    }
	    
	    int len1 = large.length();
	    int len2 = small.length();
	    
	    for (int i = 0; i <= len1 -len2; i++) {
	      for (int j = 0; j < len2; j++) {
	        if (large.charAt(i + j) != small.charAt(j)) {
	          break;
	        }      
	        if (j == len2 - 1) {
	          return i;
	        }
	      }
	    }
	    
	    return -1;
	  }
	
	
	
	
	//!!!! KMP !!!!
	/**
     * Compute temporary array to maintain size of suffix which is same as prefix
     * Time/space complexity is O(size of pattern)
     */
    private int[] computeTemporaryArray(char pattern[]){
        int [] lps = new int[pattern.length];
        int index =0;
        for(int i=1; i < pattern.length;){
            if(pattern[i] == pattern[index]){
                lps[i] = index + 1;
                index++;
                i++;
            }else{
                if(index != 0){
                    index = lps[index-1];
                }else{
                    lps[i] =0;
                    i++;
                }
            }
        }
        return lps;
    }
    
    /**
     * KMP algorithm of pattern matching.
     */
    public boolean KMP(char []text, char []pattern){
        
        int lps[] = computeTemporaryArray(pattern);
        int i=0;
        int j=0;
        while(i < text.length && j < pattern.length){
            if(text[i] == pattern[j]){
                i++;
                j++;
            }else{
                if(j!=0){
                    j = lps[j-1];
                }else{
                    i++;
                }
            }
        }
        if(j == pattern.length){
            return true;
        }
        return false;
    }
	
	public static void main(String[] args) {
		strStr s = new strStr();
		int res = s.strStr("nnnneedle", "ee");
		System.out.println(strstr("mississippi","issip"));
	}
}
