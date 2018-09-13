/*
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
Note: A leaf is a node with no children.   !!!!!!

Example:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its minimum depth = 2.
 */


import java.util.*;
public class MinimumDepthofBinaryTree {
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		public TreeNode(int x) {
			val = x;
		}
	}
	
	
	// recursive solution
	public int minDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        int l = minDepth(root.left);
        int r = minDepth(root.right);
              
        if(r == 0)  // no right child, return l + 1, should check this first, since {1,2} return 2 rather than 1
            return l + 1;
        if(l == 0)  // no left child, return r + 1
            return r + 1;
        if(l > r)  // left height > right height
            return r + 1;
        if(l <= r)
            return l + 1;
        
        return -1;
    }
	
	
	// BFS, find the first level that has leaf nodes, and then return level
	public int minDepthBFS(TreeNode root ) {
		if(root == null) {
			return 0;
		}
		
		Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int level = 1;
        while(!q.isEmpty()) {
            int size = q.size();
            while(size > 0) {
                TreeNode node = q.remove();
                size--;
                if(node.left == null && node.right == null) {
                    return level;
                }
                if(node.left != null) {
                    q.add(node.left);
                }
                if(node.right != null) {
                    q.add(node.right);
                }
            }
            level++; // size = 0, go next level
        }
        
        return -1;
	}
}
