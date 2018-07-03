import java.util.*;

public class GroupAnagrams {
	
	//Categorize by Sorted String. Two strings are anagrams if and only if their sorted strings are equal.
	public List<List<String>> Solution(String[] strs) {
		//List<List<String>> res = new ArrayList<List<String>>();
		if(strs == null || strs.length == 0)
			return new ArrayList();
		
		Map<String, List> ans = new HashMap<String, List>();
		for(String s : strs) {
			char[] c = s.toCharArray();
			Arrays.sort(c);
			String key = String.valueOf(c);
			if(!ans.containsKey(key))
				ans.put(key, new ArrayList());
			ans.get(key).add(s);
		}
		
		
		return new ArrayList(ans.values()); //!!!
	}
	
	/**
     * Use map<String, Integer>
     * Integer is initialized as the index, updated to -1 when the word is 
     * added to map to make sure that no duplicate situation happens
     */
	public List<String> Solution2(String[] strs){
		List<String> res = new ArrayList<String>(); //!!
		if(strs == null || strs.length == 0)
			return new ArrayList();
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		for(int i = 0; i < strs.length; i++) {
			char[] c = strs[i].toCharArray();
			Arrays.sort(c);
			String key = String.valueOf(c);
			if(map.containsKey(key)) {
				res.add(strs[i]); //add this string
				if(map.get(key) >= 0) { //key string not added
					res.add(strs[map.get(key)]);
					map.put(key, -1); //mark already added as -1
				}
			}
			else
				map.put(key, i); //first put sorted string and index
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		GroupAnagrams g = new GroupAnagrams();
		String[] s = {"aab", "baa", "acc", "cca"};
		//System.out.println(g.Solution(s).toString());
		System.out.println(g.Solution(s));
	}
}
