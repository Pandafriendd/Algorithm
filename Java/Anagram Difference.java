public class solution{
	statuc int[] getMininumDifference(String[] a, String[] b){
		int alength = a.length;
		int blength = b.length;
		//int n = alength > blength ? alength : blength;
		int n = Math.max(alength, blength);
		int[] res = new int[n];
		for(int i = 0; i < n; i++) {
			if(a[i].length() != b[i].length() || a[i] == null || b[i] ==  null)
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