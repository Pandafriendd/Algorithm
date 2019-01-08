

/*
Given two keys in a binary search tree, find their lowest common ancestor.

Assumptions

There is no parent pointer for the nodes in the binary search tree

There are no duplicate keys in the binary search tree

The given two nodes are guaranteed to be in the binary search tree

Examples

        5

      /   \

     2     12

   /  \      \

  1    3      14

The lowest common ancestor of 1 and 14 is 5

The lowest common ancestor of 1 and 3 is 2

 */
public class LCAinBST {
	public class TreeNode {
		public int key;
		public TreeNode left;
		public TreeNode right;
		public TreeNode(int k) {
			key = k;
		}
	}
	
	public TreeNode LCA(TreeNode root, TreeNode one, TreeNode two) {
		// always ensure one.key <= two.key
		if (one.key > two.key) {
			return LCA(root, two, one);
		}
		if (root == null) {
			return root;
		}
		if (root.key > one.key && root.key > two.key) { // go to the left
			return LCA(root.left, one, two);
		} else if (root.key < one.key && root.key < two.key) {
			return LCA(root.right, one, two);
		} else if (root.key >= one.key && root.key <= two.key) {
			return root;
		} else {
			return null;
		}
	}
}
