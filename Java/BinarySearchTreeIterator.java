/*
Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */
import java.util.*;
public class BinarySearchTreeIterator {
	
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;
		}
	}
	
	/*
I use Stack to store directed left children from root.
When next() be called, I just pop one element and process its right child as new root.
The code is pretty straightforward.
So this can satisfy O(h) memory, hasNext() in O(1) time, next() is O(1) time.
	 */
	private Stack<TreeNode> stack = new Stack<TreeNode>();
    
    public BinarySearchTreeIterator(TreeNode root) {
        pushAll(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode tmpNode = stack.pop();
        pushAll(tmpNode.right);
        return tmpNode.val;
    }
    
    private void pushAll(TreeNode node) {
        //for (; node != null; stack.push(node), node = node.left);
        while(node != null) {
        	stack.push(node);
        	node = node.left;
        }
    }
}

/*
The implementation of BST iterator is very similar to binary search tree Inorder traversal. 
Inorder using stack and two while loop. However, in the iterator, the first while condition become hasNext() and the code inside the first while condition become next().
 */
public List<Integer> inorderTraversal(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    List<Integer> res = new ArrayList<>();
    while (root != null || !stack.isEmpty()) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        root = stack.pop();
        res.add(root.val);
        root = root.right;
    }
    return res;
}
//BSTIterator
public class BSTIterator {

    Stack<TreeNode> stack;
    TreeNode node;
    public BSTIterator(TreeNode root) {
        stack = new Stack();
        node = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty() || node != null;
    }

    /** @return the next smallest number */
    public int next() {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        node = stack.pop();
        int res = node.val;
        node = node.right;
        return res;
    }
}