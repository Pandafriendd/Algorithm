
public class DifferentTeams {
	static int differentTeams(String skills) {
        int[] buckets = new int[5];  // initialize as all 0
        int len = skills.length();
        
        for(int i = 0; i < len; i++) {
            char c = skills.charAt(i);
            //p c m b z
            if(c == 'p') {
                buckets[0]++; 
            }
            else if(c == 'c') {
                buckets[1]++;
            }
            else if(c == 'm') {
                buckets[2]++;
            }
            else if(c == 'b') {
                buckets[3]++;
            }
            else if(c == 'z') {
                buckets[4]++;
            }
        }
        
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < buckets.length; i++) {
            min = Math.min(min, buckets[i]);
        }
        
        return min;
        
    }
}
