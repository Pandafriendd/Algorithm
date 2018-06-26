import java.util.*;


public class StringReoder {
	public String Reoder(String s) {
		if(s.length() == 0 || s == null)
			return null;
		String res;
		int max = 0;
		Map<Character, Integer> map = new HashMap();
		for(int i = 0; i < s.length(); i++) {
			if(!map.containsKey(s.charAt(i)))
				map.put(s.charAt(i), 1);
			else {
				map.put(s.charAt(i), map.get(i) + 1);
				max = Math.max(max, map.get(i));
			}
		}
		StringBuilder sb = new StringBuilder();
		
	}
}
