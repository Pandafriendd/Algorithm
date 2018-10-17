
import java.util.*;

public class UniquePrefixTrie {
	
	
	static class TrieNode {
		TrieNode[] children = new TrieNode[26];  // default as null
		int freq = 0;
		String word;
	}
	
	private static TrieNode buildTrie(String[] words) {
		TrieNode root = new TrieNode();
		for (String word : words) {
			TrieNode cur = root;
			for (int i = 0; i < word.length(); i++) {
				int index = word.charAt(i) - 'a';
				if (cur.children[index] == null) {  // meaning it still not been added yet
					cur.children[index] = new TrieNode();
				}
				cur = cur.children[index];
				cur.freq++;
				cur.word = word.substring(0, i + 1);
			}
		}
		
		return root;
	}
	
	public static List<String> findPrefix(String[] words) {
		TrieNode root = buildTrie(words);
		List<String> res = new ArrayList<>();
		for (String word : words) {
			TrieNode cur = root;
			for (char c : word.toCharArray()) {
				cur = cur.children[c - 'a'];
				if(cur.freq == 1) {
					res.add(cur.word);
					break;
				}
			}
		}
		
		return res;
	}
	
	
	
	public static void main(String[] args) {
		String[] words = {"zebra", "dog", "duck", "dove"};
		System.out.println(findPrefix(words));
	}
}
