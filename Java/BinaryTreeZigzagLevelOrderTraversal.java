/*
Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]
 */

import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) {
			val = x;
		}
	}
	
	/*
	 level from 0
	 Initialize:
	 deque.add(root)  Deque: head --- tail
	 
	 For each step:
	 Case 1 even levels: Expend node from head to tail. Generate node.left to the tail, and then generate node.right to the tail !!!
	 Case 2 odd levels: Expend node from tail to head. Generate node.right to the head, and then generate node.left to the head !!!
	 */
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		
		Deque<TreeNode> deque = new ArrayDeque<>();
		deque.offerFirst(root);
		int level = 0;
		
		while (!deque.isEmpty()) {
			int size = deque.size();
			List<Integer> list = new ArrayList<>();
			while (size > 0) {
				if (level % 2 == 0) { // even levels
					TreeNode node = deque.pollFirst();
					list.add(node.val);
					if (node.left != null) {
						deque.offerLast(node.left);
					}
					if (node.right != null) {
						deque.offerLast(node.right);
					}
				} else { // odd levels
					TreeNode node = deque.pollLast();
					list.add(node.val);
					if (node.right != null) {
						deque.offerFirst(node.right);
					}
					if (node.left != null) {
						deque.offerFirst(node.left);
					}
				}
				size--;
			}
			res.add(list);
			level++;  // !!
		}
		
		return res;
    }
}
