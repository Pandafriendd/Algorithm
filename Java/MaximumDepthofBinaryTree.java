

import java.util.*;
public class MaximumDepthofBinaryTree {
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;
		}
	}
	
	
	// recursion approach
	public int maxDepth(TreeNode root) {
		if(root == null)
			return 0;
		else {
			int lDepth = maxDepth(root.left);
			int rDepth = maxDepth(root.right);
			return lDepth >= rDepth ? lDepth + 1 : rDepth + 1;
		}
	}
	
	
	/*
	 *  DFS and BFS
	 *  used two stacks for the dfs one 
	 *  and a queue for the level-order traversal one. 
	 *  Level order one is faster.
	 *  
	 */
	//BFS
	public int maxDepth2(TreeNode root) {
	    if(root == null) {
	        return 0;
	    }
	    Queue<TreeNode> queue = new LinkedList<>();  //!
	    queue.offer(root);
	    int count = 0;
	    while(!queue.isEmpty()) {
	        int size = queue.size();
	        
	        //it counts each level once. for example this part of the code gets rid of the nodes which are on the same level by popping them after adding +1 to height
	        while(size-- > 0) {
	            TreeNode node = queue.poll();
	            if(node.left != null) {
	                queue.offer(node.left);
	            }
	            if(node.right != null) {
	                queue.offer(node.right);
	            }
	        }
	        count++;
	    }
	    return count;
	}
	// 3ms
	
	
	//DFS in pre order -- root -> right -> left
	//The dfs one seems to be bfs, but it actually is dfs.Because when I look at the order of nodes that pop out of stack, the order indeed confirms to dfs.
	public int maxDepth3(TreeNode root) {
	    if(root == null) {
	        return 0;
	    }
	    
	    Stack<TreeNode> stack = new Stack<>(); // !!
	    Stack<Integer> value = new Stack<>();
	    stack.push(root);
	    value.push(1);
	    int max = 0;
	    while(!stack.isEmpty()) {
	        TreeNode node = stack.pop();
	        int temp = value.pop();
	        max = Math.max(temp, max);
	        if(node.left != null) {
	            stack.push(node.left);
	            value.push(temp+1);
	        }
	        if(node.right != null) {
	            stack.push(node.right);
	            value.push(temp+1);
	        }
	    }
	    return max;
	}
	// 7ms
}
