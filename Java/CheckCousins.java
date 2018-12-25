/*
Given the binary Tree and the two nodes say a and b, determine whether the two nodes are cousins of each other or not.
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
	assume a, b are not null and both in the tree
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
			nodeCounter = 0;  // we can use a set or two boolean vars to check if a, b exist too
			while (size > 0) {
				TreeNode cur = queue.poll(); // expend
				// checking on next level
				if (cur.left != null && cur.right != null) {  // !!!!! both left and right should all not null
					if (cur.left == a && cur.right == b || cur.left == b && cur.right == a) {  // ??? how to compare tree node
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
				
				/* WRONG! Since it can have NPE 
				if (cur.left == a || cur.left == b || cur.right == a || cur.right == b) {
					nodeCounter++;
				}
				*/
				
				size--;
			}
			
			if (nodeCounter == 2) {  // both two nodes found in this level
				return true;
			}
			if (nodeCounter == 1) {  // only one node found in this level
				return false;
			}
		}
		
		return false;
	}
	
	/*
	 solution2: two helper functions
	 time: O(n) space: O(height)
	 */
	public boolean ifCousin2(TreeNode root, TreeNode a, TreeNode b) {
		if (root == null) {
			return false;
		}
		
		int levelOfA = getLevel(root, a, 1);  
		int levelOfB = getLevel(root, b, 1);
		TreeNode parentOfA = getParent(root, a);
		TreeNode parentOfB = getParent(root, b);
		
		return levelOfA == levelOfB && parentOfA != parentOfB;
	}
	
	// if node is absent in the tree, return 0; root level begins with 1
	private int getLevel(TreeNode root, TreeNode node, int level) {
		// base case 1
		if (root == null) {
			return 0;
		}
		// base case 2
		if (root == node) {
			return level;
		}
		
		int left = getLevel(root.left, node, level + 1);
		int right = getLevel(root.right, node, level + 1);
		
		return left == 0 ? right : left; // when both = 0, return 0
	}
	
	private TreeNode getParent(TreeNode root, TreeNode node) {
		if (root == null) {
			return null;
		}
		if (root.left == node || root.right == node) {
			return root;
		}
		
		TreeNode left = getParent(root.left, node);
		TreeNode right = getParent(root.right, node);
		
		return left == null ? right : left;
	}
	
	/*
	 solution3: optimized solution: one pass solution. LCA's solution
	 find the lca node, then if left return level == right return level && left return level - current level > 1, then a and b are cousins
	 time: O(n), space: O(height)
	 */
	public boolean ifCousin3(TreeNode root, TreeNode a, TreeNode b) {
		boolean[] res = new boolean[] {false};
		getLevelByLCA(root, a, b, 1, res); // root's level = 1
		return res[0];
	}
	
	private int getLevelByLCA(TreeNode root, TreeNode a, TreeNode b, int level, boolean[] res) {
		if (root == null) {
			return 0;
		}
		if (root == a || root == b) {
			return level;
		}
		
		int leftLevel = getLevelByLCA(root.left, a, b, level + 1, res);
		int rightLevel = getLevelByLCA(root.right, a, b, level + 1, res);
		
		if (leftLevel != 0 && leftLevel == rightLevel && leftLevel - level > 1) {
			res[0] = true;
		}
		
		return leftLevel == 0 ? rightLevel : leftLevel;
	}
	
}
