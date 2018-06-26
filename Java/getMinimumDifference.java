import java.util.*;

public class getMinimumDifference {
	/**
	//this is the paypal OA questions
	static int[] getMinimumDifference(String[] a, String[] b) {
		int alength = a.length;
		int blength = b.length;
		//int n = alength > blength ? alength : blength;
		int n = Math.max(alength, blength);
		int[] res = new int[n];
		for(int i = 0; i < n; i++) {
			if(a[i].length() != b[i].length()|| a[i] == null || b[i] ==  null)
				res[i] = -1;
			else {
				HashMap<Character, Integer> map = new HashMap<>();
				for(int j = 0; j < a[i].length(); j++) {
					if(!map.containsKey(a[i].charAt(j))) {
						map.put(a[i].charAt(j), 1);
					}
					else {
						map.put(a[i].charAt(j), map.get(a[i].charAt(j))+1);
					}
				}
				for(int k = 0; k < b[k].length(); k++) {
					if(map.containsKey(b[i].charAt(k)) && map.get(b[i].charAt(k)) > 0)
						map.put(b[i].charAt(k), map.get(b[i].charAt(k)) - 1);
					else
						res[i]++;
				}
			}
		}
		return res;
	}
	**/
	
	
	static int anagram(String s){
        // Complete this function
        int res = 0;
        if(s.length() % 2 == 1)
        		res = -1;
        else{
            HashMap<Character, Integer> map = new HashMap<>();
            for(int i = 0; i < (s.length() / 2); i++){
                if(!map.containsKey(s.charAt(i)))
                    map.put(s.charAt(i), 1);
                else
                    map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            }
            for(int i = s.length() / 2; i < s.length(); i++){
                if(map.containsKey(s.charAt(i)) && map.get(s.charAt(i)) > 0)
                    map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
                else
                    res++;
            }
        }
        return res;
            
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            String s = in.next();
            int result = anagram(s);
            //System.out.println(s.length() / 2);
            System.out.println(result);
        }
    }
}
