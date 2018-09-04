/*
 * Inorder (Left, Root, Right)
 * Preorder (Root, Left, Right)
 * Postorder (Left, Right, Root)
 */


/*
 * Uses of Inorder
 * In case of binary search trees (BST), Inorder traversal gives nodes in non-decreasing (ascending order) order. 
 * To get nodes of BST in non-increasing order, a variation of Inorder traversal where Inorder traversal's reversed can be used.
 * 
 * Uses of Preorder
 * Preorder traversal is used to create a copy of the tree. 
 * Preorder traversal is also used to get prefix expression on of an expression tree. 
 * Please see http://en.wikipedia.org/wiki/Polish_notation to know why prefix expressions are useful.
 * 
 * Uses of Postorder
 * Postorder traversal is used to delete the tree. 
 * Postorder traversal is also useful to get the postfix expression of an expression tree. 
 * Please see http://en.wikipedia.org/wiki/Reverse_Polish_notation to for the usage of postfix expression.
 */
import java.util.*;



public class BinaryTreeTraversalSummarization {
	
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;
		}
	}
	
	/*
	 * Iteratively
	 */
	
	// Pre Order Traverse
	public List<Integer> preorderTraversal(TreeNode root) {
	    List<Integer> result = new ArrayList<>();
	    Deque<TreeNode> stack = new ArrayDeque<>();
	    TreeNode p = root;
	    while(!stack.isEmpty() || p != null) {
	        if(p != null) {
	            stack.push(p);
	            result.add(p.val);  // Add before going to children
	            p = p.left;
	        } else {
	            TreeNode node = stack.pop();
	            p = node.right;   
	        }
	    }
	    return result;
	}
	public List<Integer> preorderTraversal2222(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        
        while(!s.isEmpty() || cur != null) {
            while(cur != null) {
                s.push(cur);
                res.add(cur.val);
                cur = cur.left;
            }
            TreeNode node = s.pop();
            cur = node.right;
        }
        
        return res;
	}
	
	// In Order Traverse
	public List<Integer> inorderTraversal(TreeNode root) {
	    List<Integer> result = new ArrayList<>();
	    Deque<TreeNode> stack = new ArrayDeque<>();
	    TreeNode p = root;
	    while(!stack.isEmpty() || p != null) {
	        if(p != null) {
	            stack.push(p);
	            p = p.left;
	        } else {
	            TreeNode node = stack.pop();
	            result.add(node.val);  // Add after all left children
	            p = node.right;   
	        }
	    }
	    return result;
	}
	public List<Integer> inorderTraversal2222(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		Stack<TreeNode> s = new Stack<>();
		TreeNode cur = root;
				
		while(cur != null || !s.isEmpty()) {
			while(cur != null) {
				s.push(cur);
				cur = cur.left;				
			}
			TreeNode node = s.pop();
			res.add(node.val);
			cur = node.right;
		}
		
		return res;
	}
	
	
	
	// Post Order Traverse
	public List<Integer> postorderTraversal(TreeNode root) {
	    LinkedList<Integer> result = new LinkedList<>(); //!!!!!
	    Deque<TreeNode> stack = new ArrayDeque<>();
	    TreeNode p = root;
	    while(!stack.isEmpty() || p != null) {
	        if(p != null) {
	            stack.push(p);
	            result.addFirst(p.val);  // Reverse the process of preorder
	            p = p.right;             // Reverse the process of preorder
	        } else {
	            TreeNode node = stack.pop();
	            p = node.left;           // Reverse the process of preorder
	        }
	    }
	    return result;
	}
	public List<Integer> postorderTraversal22222(TreeNode root){
		LinkedList<Integer> res = new LinkedList<>();
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        
        while(!s.isEmpty() || cur != null) {
            while(cur != null) {
                s.push(cur);
                res.addFirst(cur.val);
                cur = cur.right;
            }
            TreeNode node = s.pop();
            cur = node.left;
        }
        
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
	
	 
    
    /**
     * Recursive
     */
    public List<Integer> preorderTraversalB(TreeNode root) {
    	List<Integer> res = new ArrayList<>();
		if(root != null) {
			res.add(root.val);
			res.addAll(preorderTraversal(root.left));
			res.addAll(preorderTraversal(root.right));
		}
		return res;
	}
    
    /**
     * Iterative
     * Use a stack
     * Pop current top, and push right first then push left
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) return res;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode curNode = s.pop();
            res.add(curNode.val); // visit
            if (curNode.right != null) s.push(curNode.right);
            if (curNode.left != null) s.push(curNode.left); // left pop first
        }
        return res;
    }

}
