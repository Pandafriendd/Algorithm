/*
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.

Example 1:

Input: root = [2,1,3], p = 1

  2
 / \
1   3

Output: 2
Example 2:

Input: root = [5,3,6,2,4,null,null,1], p = 6

      5
     / \
    3   6
   / \
  2   4
 /   
1

Output: null
 */

import java.util.*;
public class InorderSuccessorinBST {
	
	public class TreeNode {
		      int val;
		      TreeNode left;
		      TreeNode right;
		      TreeNode(int x) { val = x; }
		  }
	
	public TreeNode inorderSuccessor0(TreeNode root, TreeNode p) {
        if(root == null) {
            return root;
        }
        
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        TreeNode pre = null;
        
        while(!stack.isEmpty() || cur != null) {
            while(cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            
            // now cur == null
            TreeNode node = stack.pop();
            if(pre == p) {
                return node;
            }
            pre = node;
            cur = node.right;
        }
        
        return null;
        
    }
	
	
	
	// recursive
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null) {
            return root;
        }
        
        if(p.val < root.val) {  // !!!!
            TreeNode l = inorderSuccessor(root.left, p);
            if(l == null) {
                return root;
            }
            else {
                return l;
            }
        }
        else {  // p.v >= root.v
            return inorderSuccessor(root.right, p);
        }
    }
	
	
	public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        if(root == null) {
            return root;
        }
        
        if(p.val > root.val) {  // !!!
            TreeNode r = inorderPredecessor(root.right, p);
            if(r == null) { 
                return root;
            }
            else {
                return r;
            }
        }
        else {
            return inorderPredecessor(root.left, p);
        }
    }
}
