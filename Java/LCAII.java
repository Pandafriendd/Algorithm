

/*
given two nodes in a binary tree (with parent pointer available), find their lowest common ancestor.

Assumptions

There is parent pointer for the nodes in the binary tree

The given two nodes are not guaranteed to be in the binary tree

Examples

        5

      /   \

     9     12

   /  \      \

  2    3      14

The lowest common ancestor of 2 and 14 is 5

The lowest common ancestor of 2 and 9 is 9

The lowest common ancestor of 2 and 8 is null (8 is not in the tree)
 */

public class LCAII {
	public class TreeNodeP {
		int key;
		TreeNodeP left;
		TreeNodeP right;
		TreeNodeP parent;
		TreeNodeP(int k, TreeNodeP p) {
			key = k;
			parent = p;
		}
	}
	
	public TreeNodeP lowestCommonAncestor(TreeNodeP one, TreeNodeP two) {
		int oneHeight = getHeight(one);
		int twoHeight = getHeight(two);
		int diff = Math.abs(oneHeight - twoHeight);
		if (oneHeight > twoHeight) {
			while (diff > 0) {
				one = one.parent;
				diff--;
			}
		} else if (oneHeight < twoHeight) {
			while (diff > 0) {
				two = two.parent;
				diff--;
			}
		}
		
		while (one != two) {
			if (one != null) {   // check to avoid NPE
				one = one.parent;
			} else {
				return null;   // maybe not in the tree
			}
			if (two != null) {   // check to avoid NPE
				two = two.parent;
			} else {   
				return null;   // maybe not in the tree
			}
		}
		
		return one;
	}
	
	private int getHeight(TreeNodeP node) {
		int height = 0;
		while (node != null) {
			height++;
			node = node.parent;
		}
		return height;
	}
}
