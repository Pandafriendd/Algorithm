/*
Determine whether two given binary trees are identical assuming any number of ¡®tweak¡¯s are allowed. A tweak is defined as a swap of the children of one node in the tree.

Examples

        5

      /    \

    3        8

  /   \

1      4

and

        5

      /    \

    8        3

           /   \

          1     4

the two binary trees are tweaked identical.

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
public class TweakedIdenticalBinaryTrees {
	public boolean isTweakedIdentical(TreeNode r1, TreeNode r2) {
	    // Write your solution here
	    if(r1 == null && r2 == null) {
	      return true;
	    }
	    else if(r1 == null || r2 == null) {
	      return false;
	    }
	    else if(r1.key != r2.key) {
	      return false;
	    }
	    // both != null
	    // (r1.key == r2.key) &&
	    return (isTweakedIdentical(r1.left, r2.left) && isTweakedIdentical(r1.right, r2.right)) ||
	      (isTweakedIdentical(r1.left, r2.right) && isTweakedIdentical(r1.right, r2.left));
	  }
}
