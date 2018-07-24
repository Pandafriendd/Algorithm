/*
 * Inorder (Left, Root, Right)
 * Preorder (Root, Left, Right)
 * Postorder (Left, Right, Root)
 */

import java.util.*;
public class BinaryTreeTraversalSummarization {
	
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;
		}
	}
	
	/*
	 * Iteratively
	 */
	
	// Pre Order Traverse
	public List<Integer> preorderTraversal(TreeNode root) {
	    List<Integer> result = new ArrayList<>();
	    Deque<TreeNode> stack = new ArrayDeque<>();
	    TreeNode p = root;
	    while(!stack.isEmpty() || p != null) {
	        if(p != null) {
	            stack.push(p);
	            result.add(p.val);  // Add before going to children
	            p = p.left;
	        } else {
	            TreeNode node = stack.pop();
	            p = node.right;   
	        }
	    }
	    return result;
	}
	
	
	// In Order Traverse
	public List<Integer> inorderTraversal(TreeNode root) {
	    List<Integer> result = new ArrayList<>();
	    Deque<TreeNode> stack = new ArrayDeque<>();
	    TreeNode p = root;
	    while(!stack.isEmpty() || p != null) {
	        if(p != null) {
	            stack.push(p);
	            p = p.left;
	        } else {
	            TreeNode node = stack.pop();
	            result.add(node.val);  // Add after all left children
	            p = node.right;   
	        }
	    }
	    return result;
	}
	
	// Post Order Traverse
	public List<Integer> postorderTraversal(TreeNode root) {
	    LinkedList<Integer> result = new LinkedList<>();
	    Deque<TreeNode> stack = new ArrayDeque<>();
	    TreeNode p = root;
	    while(!stack.isEmpty() || p != null) {
	        if(p != null) {
	            stack.push(p);
	            result.addFirst(p.val);  // Reverse the process of preorder
	            p = p.right;             // Reverse the process of preorder
	        } else {
	            TreeNode node = stack.pop();
	            p = node.left;           // Reverse the process of preorder
	        }
	    }
	    return result;
	}
}
