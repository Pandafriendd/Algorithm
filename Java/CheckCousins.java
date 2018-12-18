/*
Given the binary Tree and the two nodes say ¡®a¡¯ and ¡®b¡¯, determine whether the two nodes are cousins of each other or not.
Two nodes are cousins of each other if they are at same level and have different parents.

Example:

     6
   /   \
  3     5
 / \   / \
7   8 1   3
Say two node be 7 and 1, result is TRUE.
Say two nodes are 3 and 5, result is FALSE.
Say two nodes are 7 and 5, result is FALSE.

 */

import java.util.*;

public class CheckCousins {
	public class TreeNode {
		int key;
		TreeNode left;
		TreeNode right;
		public TreeNode(int k) {
			key = k;
		}
	}
	
	/*
	solution1: BFS
	time: O(n) space: O(n) since the queue at most can have n node
	 */
	public boolean ifCousin(TreeNode root, TreeNode a, TreeNode b) {
		if (root == null) {
			return false;
		}
		
		Queue<TreeNode> queue = new ArrayDeque<>();
		queue.offer(root);
		int size;
		int nodeCounter;
		
		while (!queue.isEmpty()) {
			size = queue.size();
			nodeCounter = 0;
			while (size > 0) {
				TreeNode cur = queue.poll(); // expend
				// checking on next level
				if (cur.left != null && cur.right != null) {  // !!!!! both left and right should all not null
					if (cur.left == a && cur.right == b || cur.left == b && cur.right == a) {
						return false;
					}
				}
				
				// generate left and right
				if (cur.left != null) {
					queue.offer(cur.left);
					if (cur.left == a || cur.left == b) {
						nodeCounter++;
					}
				}
				if (cur.right != null) {
					queue.offer(cur.right);
					if (cur.right == a || cur.right == b) {
						nodeCounter++;
					}
				}
				
				/* WRONG! Since it has NPE 
				if (cur.left == a || cur.left == b || cur.right == a || cur.right == b) {
					nodeCounter++;
				}
				*/
				
				size--;
			}
			
			if (nodeCounter == 2) {
				return true;
			}
		}
		
		return false;
	}
	
}
