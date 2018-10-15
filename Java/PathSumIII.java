/*
You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
 */


import java.util.*;
public class PathSumIII {
	public class TreeNode {
	      int val;
	      TreeNode left;
	      TreeNode right;
	      TreeNode(int x) { val = x; }
	  }
	
	
	// elegant dfs method, but very inefficient
	// time: O(n * height) space: O(n)
	public int pathSum(TreeNode root, int sum) { // including all root included and root non-included paths sum
		if(root == null) {
			return 0;
		}
		
		return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
	}
	
	private int pathSumFrom(TreeNode node, int remain) {  // how many satisfied paths that begins from node (including node) 
		if(node == null) {
			return 0;
		}
		
		return (node.val == remain ? 1 : 0) +
				pathSumFrom(node.left, remain - node.val) + pathSumFrom(node.right, remain - node.val);
	}
	
	
	// Prefix sum method O(n)
	// hashmap:  key ==> the prefix sum, value ==> how many ways get to this prefix sum
	public int pathSum1(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0,1);
        return helper(root, 0, sum, preSum);
    }
    
    public int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }
        
        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);
        
        res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1);
        return res;
    }
}
