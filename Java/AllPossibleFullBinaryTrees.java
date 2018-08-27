/*
A full binary tree is a binary tree where each node has exactly 0 or 2 children.

Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.

Each node of each tree in the answer must have node.val = 0.

You may return the final list of trees in any order.

Example 1:

Input: 7
Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]

 */
import java.util.*;
public class AllPossibleFullBinaryTrees {
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;
		}
	}
	
	
	/*
	I recursively found the possible children of each node at each level. 
	At every level I subtracted 1 from N since this node counts as a node. (N is given as 1 to 50) 
	If N==1 I returned a node with no children. 
	for every i where i is the number of left children I called allPossibleFBT(i) and allPossibleFBT(N-i) for the remaining children on the opposite side. 
	Then I iterate through all possible combinations of children setting the current node's left and right children, and add it to the result list.
	
	For all combinations of left and right number of children
	1. Recurse into left and right subtree;
	2. Combine results
	Note that there is no valid answer for even number of nodes, so we may add an early exit there.
	 */
	public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> res = new ArrayList<>();
        if(N==1){
            res.add(new TreeNode(0));
            return res;
        }
        N=N-1;
        for(int i=1; i<N;i+=2){
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(N-i);
            for(TreeNode nl: left){
                for(TreeNode nr:right){
                    TreeNode cur = new TreeNode(0);
                    cur.left=nl;
                    cur.right=nr;
                    res.add(cur);
                }
            }
        }
        return res;
    }
}
