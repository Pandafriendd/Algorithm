/*
 * Note:

You may only use constant extra space.
Recursive approach is fine, implicit stack space does not count as extra space for this problem.
Example:

Given the following binary tree,

     1
   /  \
  2    3
 / \    \
4   5    7
After calling your function, the tree should look like:

     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \    \
4-> 5 -> 7 -> NULL
 */


import java.util.*;
public class PopulatingNextRightPointersinEachNodeII {
	
	
	//Definition for binary tree with next pointer.
	public class TreeLinkNode{
		int val;
		TreeLinkNode left;
		TreeLinkNode right;
		TreeLinkNode next;
		TreeLinkNode(int x){
			val = x;
		}
	}
	
	 /**
	 * dfs
     * Store the head of next level
     * Store previous node 
     * Do level order traversal with a pointer
     */
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        
        TreeLinkNode cur = root;  // current node of current level
        TreeLinkNode prev; // previous node
        TreeLinkNode nextHead; // Head of the next level

        
        while (cur != null) {
            nextHead = null;
            prev = null;
            
            while (cur != null) {
                if (cur.left != null) { // left child
                    if (prev != null) 
                    	prev.next = cur.left;
                    else 
                    	nextHead = cur.left; // set nextHead
                    prev = cur.left; // move right
                }
                if (cur.right != null) { // right child
                    if (prev != null) 
                    	prev.next = cur.right;
                    else 
                    	nextHead = cur.right; // set nextHead
                    prev = cur.right; // move right
                }
                cur = cur.next; // move right to next node in same level
            }
            
            // move to next level
            cur = nextHead;
        }
    }
}
