
import java.util.*;
public class BinaryTreePostorderTraversal {
	
	public class TreeNode {
		     int val;
		      TreeNode left;
		     TreeNode right;
		     TreeNode(int x) { val = x; }
		  }
	
	public List<Integer> postorderTraversal(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root != null){
            res.addAll(postorderTraversal(root.left));
            res.addAll(postorderTraversal(root.right));
            res.add(root.val);
        }
        return res;
    }
	
	 /**
     * post order: left - right - root
     * modify pre order: root - left - right to root - right - left
     * reverse the result
     */
    public List<Integer> postorderTraversalA(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode curNode = s.pop();
            res.add(curNode.val); // visit
            if (curNode.left != null) s.push(curNode.left);
            if (curNode.right != null) s.push(curNode.right);
        }
        Collections.reverse(res);
        return res;
    }

	
	//modify preorder from root - left - right to root - right - left
	//reversing the res of preorder
	public List<Integer> postorderTraversal2(TreeNode root){
		List<Integer> res = new ArrayList<>();
		if(root == null)
			return res;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while(!stack.isEmpty()) {
			TreeNode curNode = stack.pop();
			res.add(curNode.val);  // visit
			if(curNode.left != null)
				stack.push(curNode.left);
			if(curNode.right != null)
				stack.push(curNode.right);
		}
		Collections.reverse(res);
		return res;
	}
	
	/**
     * Use two pointers. 1 for current node, 1 for previous traversed node
     * 3 situations:
     * 1. Traversing down, prev is not set or current is prev's child
     *      Push left child to stack if not null, then push rigth child 
     * 2. Traversing up from left, prev is current's left child
     *      Push right child to stack if not null
     * 3. Traversing up from right
     *      Visit, and pop
     */
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        TreeNode prev = null; // previously traversed node
        TreeNode cur = root;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        while (!s.isEmpty()) {
            cur = s.peek();
            if (prev == null || prev.left == cur || prev.right == cur) { // traverse down
                if (cur.left != null) s.push(cur.left); // put left first
                else if (cur.right != null) s.push(cur.right);
            } else if (cur.left == prev) { // traverse up from the left
                if (cur.right != null) s.push(cur.right);
            } else { // traverse up from the right
                res.add(cur.val); // visit
                s.pop();
            }
            prev = cur;
        }
        return res;
    }
}
