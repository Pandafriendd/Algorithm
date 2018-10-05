
public class MaxDifference {
	static int maxDifference(int[] a) {
        if(a == null || a.length <=1 ) 
        	return -1;
        int min = a[0], res = -1;
        for(int i = 1; i < a.length; i++) {
            res = Math.max(res, a[i] - min > 0 ? a[i] - min : -1);
            min = Math.min(min, a[i]);
        }
        return res;
    }
}
