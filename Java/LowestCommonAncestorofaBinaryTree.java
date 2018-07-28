/*
 * Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: 
¡°The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).¡±

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
 */
public class LowestCommonAncestorofaBinaryTree {
	
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;
		}
	}
	
	/*
	 * It's recursive and expands the meaning of the function. If the current (sub)tree contains both p and q, then the function result is their LCA. If only one of them is in that subtree, then the result is that one of them. If neither are in that subtree, the result is null/None/nil.

Update: I also wrote two iterative solutions now, one of them being a version of the solution here. They're more complicated than this simple recursive solution, but I do find them interesting.
	 */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	    if (root == null || root == p || root == q) return root;
	    TreeNode left = lowestCommonAncestor(root.left, p, q);
	    TreeNode right = lowestCommonAncestor(root.right, p, q);
	    return left == null ? right : right == null ? left : root;
	}
	
	/*
	 * Share my understanding of what lowestCommonAncestor() does:

if both p and q exist in Tree rooted at root, then return their LCA
if neither p and q exist in Tree rooted at root, then return null
if only one of p or q (NOT both of them), exists in Tree rooted at root, return it
	 */
	 public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
	        if(root == null || root == p || root == q)  return root;
	        TreeNode left = lowestCommonAncestor(root.left, p, q);
	        TreeNode right = lowestCommonAncestor(root.right, p, q);
	        if(left != null && right != null)   return root;
	        return left != null ? left : right;
	    }
}
