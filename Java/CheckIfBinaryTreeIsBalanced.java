
/*Check if a given binary tree is balanced. A balanced binary tree is one in which the depths of every node¡¯s left and right subtree differ by at most 1.

Examples

        5

      /    \

    3        8

  /   \        \

1      4        11

is balanced binary tree,

        5

      /

    3

  /   \

1      4

is not balanced binary tree.

Corner Cases

What if the binary tree is null? Return true in this case.
How is the binary tree represented?

We use the level order traversal sequence with a special symbol "#" denoting the null node.

For Example:

The sequence [1, 2, 3, #, #, 4] represents the following binary tree:

    1

  /   \

 2     3

      /

    4
 */


public class CheckIfBinaryTreeIsBalanced {
	
	public class TreeNode { 
		public int key; 
		public TreeNode left; 
		public TreeNode right; 
		public TreeNode(int key) { 
			this.key = key; 
		}
	}
	
	// old solution: time: O(nlogn)
	public boolean isBalanced(TreeNode root) {
	    if(root == null) {
	      return true;
	    }
	    
	    int left = getHeight(root.left);
	    int right = getHeight(root.right);
	    
	    if(Math.abs(left - right) > 1) {
	      return false;
	    }
	    
	    return isBalanced(root.left) && isBalanced(root.right);
	    
	  }
	  
	  private int getHeight(TreeNode root) {
	    if(root == null) {
	      return 0;
	    }
	    
	    int l = getHeight(root.left);
	    int r = getHeight(root.right);
	    
	    return Math.max(l, r) + 1;
	  }
    
    // optimized solution, change the meaning of the return value of getHeight
    /* The 3-steps:
    1. what do you expect from your lchild and rchild? (usually it is the return type of recursion function)
    	height of my lsubtree and rsubtree (>= 0: real height; -1: unbalanced)
    2. what do you do in the current layer?
    	do some checking
    3. what do you want to report to your parent?
	*/
	  // time: O(n), space: O(height)
    public boolean isBalanced2(TreeNode root) {
    	if (root == null) {
    		return true;
    	}
    	
    	int left = getHeightNew(root.left);
    	int right = getHeightNew(root.right);
    	
    	if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
    		return false;
    	} else {
    		return true;	
    	}
    }
    
    public int getHeightNew(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	
    	int l = getHeightNew(root.left);
    	int r = getHeightNew(root.right);
    	
    	if (l == -1 || r == -1 || Math.abs(l - r) > 1) {
    		return -1;
    	} else {
    		return Math.max(l, r) + 1;
    	}
    }
}
