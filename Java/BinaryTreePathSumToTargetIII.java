/*
Given a binary tree in which each node contains an integer number. 
Determine if there exists a path (the path can only be from one node to itself or to any of its descendants), 
the sum of the numbers on the path is the given target number.

Examples

    5

  /    \

2      11

     /    \

    6     14

  /

 3

If target = 17, There exists a path 11 + 6, the sum of the path is target.

If target = 20, There exists a path 11 + 6 + 3, the sum of the path is target.

If target = 10, There does not exist any paths sum of which is target.

If target = 11, There exists a path only containing the node 11.
 */


import java.util.*;
public class BinaryTreePathSumToTargetIII {
	
	public class TreeNode { 
		public int key; 
		public TreeNode left; 
		public TreeNode right; 
		public TreeNode(int key) { 
			this.key = key; 
		}
	}
	
	// brute force
	// time: O(n * height), since prefixPath's max size will be the length of tree, so O(n^2) in worst case
	// space: O(height)?
	public boolean exist(TreeNode root, int target) {
	    if (root == null) {
	    	return false;
	    }

	    List<Integer> prefixPath = new ArrayList<>();
	    return helper(root, prefixPath, target);
	}

	private boolean helper(TreeNode root, List<Integer> prefixPath, int target) {
		if (root == null) {
			return false;
		}

		int currentSum = root.key;
		if (currentSum == target) {
			return true;
		}
		for (int i = prefixPath.size() - 1; i >= 0; i--) {
			currentSum += prefixPath.get(i);
			if (currentSum == target) {
				return true;
			}
		}
		prefixPath.add(root.key);
		boolean result = helper(root.left, prefixPath, target) || helper(root.right, prefixPath, target);
		prefixPath.remove(prefixPath.size() - 1);
		return result;
	}
	
	
	// Time: O(n) since we optimized the for loop to O(1) by using hashset
	public boolean exist2(TreeNode root, int target) {
	    if (root == null) {
	    	return false;
	    }
	    
	    // hashset maybe not very good, since we need to maintain the order and remove last element, still O(1)
	    // actually not, remove() api in Set pass the removed value as parameter, which we already known
	    // LinkedHashSet<Integer> prefixSum = new LinkedHashSet<>();
	    Set<Integer> prefixSum = new HashSet<>();
	    return helper(root, prefixSum, target, 0);
	}
	
	private boolean helper(TreeNode root, Set<Integer> prefixSum, int target, int currentPrefixSum) {
		if (root == null) {
			return false;
		}
		currentPrefixSum += root.key;
		prefixSum.add(currentPrefixSum);
		
		if (prefixSum.contains(target)) {
			return true;
		}
		if (prefixSum.contains(currentPrefixSum - target)) {
			return true;
		}
		
		boolean result = helper(root.left, prefixSum, target, currentPrefixSum) || helper(root.right, prefixSum, target, currentPrefixSum);
		prefixSum.remove(currentPrefixSum);
		return result;
	}
}
