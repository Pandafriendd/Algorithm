
import java.util.*;



public class BSTIterator {
	
    public class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode(int x){
	    val = x;
	}
    }
	
    Deque<Integer> queue;
    
    public BSTIterator(TreeNode root) {
        queue = new ArrayDeque<>();
        inorder(root, queue);
    }
    
    private void inorder(TreeNode root, Deque<Integer> queue) {
        if(root == null) {
            return;
        }
        
        inorder(root.left, queue);
        queue.add(root.val);
        inorder(root.right, queue);
    }

    /** @return whether we have a next smallest number */
    // O(1) time
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    /** @return the next smallest number */
    // O(1) time
    public int next() {
        if(hasNext()) {
            return queue.poll();
        }
        
        return -1;
    }
}
