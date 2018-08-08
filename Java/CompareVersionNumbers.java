
public class CompareVersionNumbers {
	
	//This code assumes that next level is zero if no mo levels in shorter version number. And than compare levels.
	public int compareVersion11(String version1, String version2) {
	    String[] levels1 = version1.split("\\.");
	    String[] levels2 = version2.split("\\.");
	    
	    int length = Math.max(levels1.length, levels2.length);
	    for (int i=0; i<length; i++) {
	    	Integer v1 = i < levels1.length ? Integer.parseInt(levels1[i]) : 0;
	    	Integer v2 = i < levels2.length ? Integer.parseInt(levels2[i]) : 0;
	    	int compare = v1.compareTo(v2);
	    	if (compare != 0) {
	    		return compare;
	    	}
	    }
	    
	    return 0;
	}
	
	
	/*
	 wrong:
	 Input:
"01"
"1"
Output:
-1
Expected:
0
	 */
	public int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");  // !!!!
        String[] s2 = version2.split("\\.");
        int len = Math.min(s1.length, s2.length);
        for(int i = 0; i < len; i++){
            if(s1[i].compareTo(s2[i]) > 0){
                return 1;
            }
            else if(s1[i].compareTo(s2[i]) < 0){
                return -1;
            }
        }
        return 0;
    }
}
