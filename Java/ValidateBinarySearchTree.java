/*
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.

Example 1:
Input:
    2
   / \
  1   3
Output: true

Example 2:

    5
   / \
  1   4
     / \
    3   6
Output: false
Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
             is 5 but its right child's value is 4.
 */
import java.util.*;
public class ValidateBinarySearchTree {
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
	}
	
	
	/*
	 * recursive
	 */
	TreeNode prev = null;
    
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        
        if(!isValidBST(root.left))
            return false;
        
        if (prev != null && prev.val >= root.val)
            return false;
        
        prev = root;
        
        if (!isValidBST(root.right))
            return false;
        
        return true;        
    }
    
    /*
     * another recursive
     * handles the min and max value using Long type, which can handle the input [Integer.MIN_VALUE] or [Integer.MAX_VALUE]
     */
    public boolean isValidBST000(TreeNode root) {
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private boolean valid(TreeNode node, long min, long max) {
        if(node == null)
            return true;
        if(node.val <= min || node.val >= max)
            return false;
        
        boolean left = valid(node.left, min, node.val);
        boolean right = valid(node.right, node.val, max);
        
        return left && right;
    }
    
    /*
	 * Validate Binary Search Tree
	 */
	public boolean isValidBST222(TreeNode root) {
		if(root == null)
			return true;
		 
		Stack<TreeNode> s = new Stack<>();
		TreeNode pre = null;
		TreeNode cur = root;
		
		while(!s.isEmpty() || cur != null) {
			while(cur != null) {
				s.push(cur);
				cur = cur.left;
			}
			
			TreeNode node = s.pop();
			if(pre != null && pre.val >= node.val)  // !!! pre != null means first node (most left node) has not previous node
				return false;
			pre = node;  // !!!
			cur = node.right;  // !!
		}
		
		return true;
	}
	
	/*
	how to tackle various tree questions using iterative inorder traversal. First one is the standard iterative inorder traversal using stack.
	 */
	public List<Integer> inorder(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		Stack<TreeNode> s = new Stack<>();
		TreeNode cur = root;
				
		while(cur != null || !s.isEmpty()) {
			while(cur != null) {
				s.push(cur);
				cur = cur.left;				
			}
			TreeNode node = s.pop();
			res.add(node.val);
			cur = node.right;
		}
		
		return res;
	}
	
	
	/*
	 * Kth Smallest Element in a BST
	 */
	public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        
        while(!s.isEmpty() || cur != null) {
        	while(cur != null) {
        		s.push(cur);
        		cur = cur.left;
        	}
        	
        	TreeNode node = s.pop();
        	if(--k == 0) {
        		cur = node;
        		break;
        	}
        	cur = node.right;
        }
        
        return cur.val;
    }
}
