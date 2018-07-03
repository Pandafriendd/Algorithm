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
	
	public static void main(String[] args) {
		GroupAnagrams g = new GroupAnagrams();
		String[] s = {"aab", "baa", "acc"};
		System.out.println(g.Solution(s));
	}
}
