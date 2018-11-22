/*
Given two nodes in a binary tree, find their lowest common ancestor.

Assumptions

There is no parent pointer for the nodes in the binary tree
The given two nodes are guaranteed to be in the binary tree

Examples

        5

      /   \

     9     12

   /  \      \

  2    3      14

The lowest common ancestor of 2 and 14 is 5
The lowest common ancestor of 2 and 9 is 9
 */

/*
way of thinking:
	1. what do you expect from your lchild and rchild?
	2. what do you want to do in the current layer?
	3. what do you want to return to your parent?
	
analysis: 
case 1: the two nodes in the same branch:
	1.1: if lchild return null && rchild return null, return null to parent 
	1.2: if only one of lchild and rchild return a non-null node, return this node to parent
case 2: the two nodes not in the same branch:
	2.1: if lchild return null && rchild return null, return null to parent
	2.2: if only one of lchild and rchild return a non-null node, return this node to parent
	2.3: if both lchild and rchild return non-null node, return the current root to parent
	
discussion:
if return is from case 2.3, we know:
	1. both one and two must be in the tree
	2. one and two are in the same branch (not directly ancestor)
if return is from 1.2 or 2.2, we know:
	1. either one of one or two is directly ancestor
	2. or one of node maybe not in the tree. HOW DO WE KNOW?
		say return one, maybe two not in the tree, just call LCA(root = one, two, two), if return null, two must not in the tree. if return two, in the tree
 */
public class LCAI {
	public class TreeNode {
		public int key;
		public TreeNode left;
		public TreeNode right;
		public TreeNode(int k) {
			key = k;
		}
	}
	
	public TreeNode LCA(TreeNode root, TreeNode one, TreeNode two) {
		// base case
		if (root == null || root == one || root == two) {
			return root;
		}
		
		// recursion rules
		TreeNode lchild = LCA(root.left, one, two);
		TreeNode rchild = LCA(root.right, one, two);
		
		if(lchild == null && rchild == null) {  // case 1.1 and 2.1
			return null;
		} else if (lchild != null && rchild != null) { // case 2.3
			return root;
		}
		
		return lchild == null ? rchild : lchild; // case 1.2 and 2.2
	}
	
}
