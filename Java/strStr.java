
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
	
	public static void main(String[] args) {
		strStr s = new strStr();
		int res = s.strStr("nnnneedle", "ee");
		System.out.println(res);
	}
}
