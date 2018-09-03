


import java.util.*;

public class BinaryTreePreorderTraversal {
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;
		}
	}
	
	//recursive1 time O(n)  space O(nlogn)
	public List<Integer> preorderTraversal(TreeNode root){
		List<Integer> res = new ArrayList<>();
		if(root != null) {
			res.add(root.val);
			res.addAll(preorderTraversal(root.left));
			res.addAll(preorderTraversal(root.right));
		}
		return res;
	}
	
	//recursive2 time O(n)  space O(n)	
	public List<Integer> preorderTraversal2(TreeNode root) {
		List<Integer> res = new ArrayList<>();
        dfs(res, root);
        return res;
    }
	private void dfs(List<Integer> res, TreeNode node) {
		if(node == null)
			return;
		
		res.add(node.val);
		dfs(res, node.left);
		dfs(res, node.right);
	}
	
	// Pre Order Traverse
		public List<Integer> preorderTraversal3(TreeNode root) {
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
		
		 /**
	     * Iterative
	     * Use a stack
	     * Pop current top, and push right first then push left
	     */
	    public List<Integer> preorderTraversal4(TreeNode root) {
	        List<Integer> res = new ArrayList<Integer>();
	        if (root == null) return res;
	        Stack<TreeNode> s = new Stack<TreeNode>();
	        s.push(root);
	        while (!s.isEmpty()) {
	            TreeNode curNode = s.pop();
	            res.add(curNode.val); // visit
	            if (curNode.right != null) 
	            	s.push(curNode.right);
	            if (curNode.left != null) 
	            	s.push(curNode.left); // left pop first
	        }
	        return res;
	    }
	    
	    public static void main(String[] args) {
	    	
	    }
}
