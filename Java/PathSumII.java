/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
 */

import java.util.*;
public class PathSumII {
	public class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
		  }
	
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        
        pathSumHelper(root, sum, new ArrayList<>(), res);
        return res;
    }
    
    private void pathSumHelper(TreeNode node, int remainSum, List<Integer> tempList, List<List<Integer>> res) {
        // base case
        if(node == null) {
            return;
        }
        if(node.left == null && node.right == null && remainSum == node.val) {
            tempList.add(node.val);
            res.add(new ArrayList<>(tempList));
            tempList.remove(tempList.size() - 1);  // !!!!!!! since I add, I need to remove after that!!!! //don't forget to remove the last integer!!!!!!!!!!
            return; 
        }
        
        
        /*
        This base case is WRONG!!!!!! since every leaf has two null in left and right, it will lead to duplicate results
        if(node == null) {
            if(AremainSum == 0) {
                res.add(new ArrayList<>(tempList));
            }
            return;
        }
         */
        
        
        // recursive rules
        tempList.add(node.val);
        pathSumHelper(node.left, remainSum - node.val, tempList, res);
        tempList.remove(tempList.size() - 1);
        
        tempList.add(node.val);
        pathSumHelper(node.right, remainSum - node.val, tempList, res);
        tempList.remove(tempList.size() - 1);
    }
}
