

import java.util.*;
public class SymmetricTree {
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode (int x){
			val = x;
		}
	}
	
	/*
	 * Approach 1: Recursive
	 * A tree is symmetric if the left subtree is a mirror reflection of the right subtree.Two trees are a mirror reflection of each other if:
	 * 1. Their two roots have the same value. 
	 * 2. The right subtree of each tree is a mirror reflection of the left subtree of the other tree.
	 */
	
	/*
	 * Time complexity : O(n). Because we traverse the entire input tree once, the total run time is O(n), where n is the total number of nodes in the tree.
	 * Space complexity : The number of recursive calls is bound by the height of the tree. In the worst case, the tree is linear and the height is in O(n).
	 */
	public boolean isSymmetric(TreeNode root) {
		return isMirror(root, root);
	}
	
	private boolean isMirror(TreeNode t1, TreeNode t2) {
		if(t1 == null && t2 == null)
			return true;
		if(t1 == null || t2 == null)
			return false;
		return (t1.val == t2.val) && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
	}
	
	private boolean isSymmetric(TreeNode r1, TreeNode r2) {
    		if(r1 == null && r2 == null) {
      			return true;
    		}
    		else if(r1 == null) {
      			return false;
    		}
    		else if(r2 == null) {
      			return false;
    		}
    
    		// both != null
    		return (r1.key == r2.key) &&
        		isSymmetric(r1.left, r2.right) &&
        		isSymmetric(r1.right, r2.left);
  	}
	
	
	
	
	/*
	 * Approach 2: Iterative
	 * Instead of recursion, we can also use iteration with the aid of a queue. 
	 * Each two consecutive nodes in the queue should be equal, and their subtrees a mirror of each other. 
	 * Initially, the queue contains root and root. 
	 * Then the algorithm works similarly to BFS, with some key differences. 
	 * Each time, two nodes are extracted and their values compared. Then, the right and left children of the two nodes are inserted in the queue in opposite order. 
	 * The algorithm is done when either the queue is empty, or we detect that the tree is not symmetric (i.e. we pull out two consecutive nodes from the queue that are unequal).
	 */
	
	/*
	 * Time complexity : O(n). Because we traverse the entire input tree once, the total run time is O(n), where n is the total number of nodes in the tree. 
	 * Space complexity : There is additional space required for the search queue. In the worst case, we have to insert O(n) nodes in the queue.
	 */
	public boolean isSymmetric2(TreeNode root) {
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		q.add(root);
		while(!q.isEmpty()) {
			TreeNode t1 = q.poll();
			TreeNode t2 = q.poll();
			if(t1 == null && t2 == null)
				continue;
			if(t1 == null || t2 == null)
				return false;
			if(t1.val != t2.val)
				return false;
			q.add(t1.left);
			q.add(t2.right);
			q.add(t1.right);
			q.add(t2.left);
		}
		return true;
	}
}
