
/*
how to calculate how many nodes in each node's left subtree
 */

public class TotalNodesInLeftsubtree {
	
	public class TreeNode { 
		public int key; 
		public TreeNode left; 
		public TreeNode right;
		public int leftTotal;
		public TreeNode(int key) { 
			this.key = key; 
		}
	}
	
	/*
	the 3-steps:
	1. 
		a = the total number of nodes in my left subtree
		b = the total number of nodes in my right subtree
	2.
		update leftTotal based on a
	3. 
		return to parent: a + b + 1 (the total number of nodes of myself)
		
	 */
	
	public int getTotal(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		int left = getTotal(root.left);
		int right = getTotal(root.right);
		root.leftTotal = left;
		
		return left + right + 1;
	}
}
