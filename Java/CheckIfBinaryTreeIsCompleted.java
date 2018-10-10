
public class CheckIfBinaryTreeIsCompleted {
	
	public boolean isCompleted(TreeNode root) {
	    // Write your solution here
	    if(root == null) {
	      return true;
	    }
	    
	    Queue<TreeNode> q = new ArrayDeque<>();
	    q.offer(root);
	    boolean nullFlag = false;
	    while(!q.isEmpty()) {
	      TreeNode node = q.poll();
	      if(node.left != null) {
	        if(nullFlag) {
	          return false;
	        }
	        q.offer(node.left);
	      }
	      else {
	        nullFlag = true;
	      }
	      if(node.right != null) {
	        if(nullFlag) {
	          return false;
	        }
	        q.offer(node.right);
	      }
	      else {
	        nullFlag = true;
	      }
	    }
	    
	    return true;
	  }
}
