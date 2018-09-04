import java.util.*;

public class BinaryTreeInorderTraversal {
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			x = val;
		}
	}
	
	public List<Integer> inorder(TreeNode root){
		List<Integer> res = new ArrayList<>();
		Stack<TreeNode> s = new Stack<>();
		s.push(root);
		TreeNode cur = root;
		
		while(!s.isEmpty() || cur != null) {
			if(cur != null) {
				s.push(cur.left);
				cur = cur.left;
			}
			else {
				TreeNode node = s.pop();
				res.add(node.val);
				cur = node.right;
			}
		}
		
		return res;
	}
	
	public List<Integer> inorder2(TreeNode root){
		List<Integer> res = new ArrayList<>();
		if(root != null) {
			res.addAll(inorder2(root.left));
			res.add(root.val);
			res.addAll(inorder2(root.right));
		}
		return res;
	}
	
	/*
	how to tackle various tree questions using iterative inorder traversal. First one is the standard iterative inorder traversal using stack.
	 */
	public List<Integer> inorderr(TreeNode root) {
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
	
	/*
	 * Validate Binary Search Tree
	 */
	public boolean isValidBST(TreeNode root) {
		if(root == null)
			return true;
		 
		Stack<TreeNode> s = new Stack<>();
		TreeNode pre = null;
		TreeNode cur = root;
		
		while(!s.isEmpty() || cur != null) {
			while(cur != null) {
				s.push(cur);
				cur = cur.left;
			}
			
			TreeNode node = s.pop();
			if(pre != null && node.val <= pre.val)
				return false;
			pre = node;
			cur = node.right;
		}
		
		return true;
	}
	
	/*
	 * Kth Smallest Element in a BST
	 */
	public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> s = new Stack<>();
        TreeNode cur = root;
        
        while(!s.isEmpty() || cur != null) {
        	while(cur != null) {
        		s.push(cur);
        		cur = cur.left;
        	}
        	
        	TreeNode node = s.pop();
        	if(--k == 0) {
        		cur = node;
        		break;
        	}
        	cur = node.right;
        }
        
        return cur.val;
    }
	
	
	
	/*
	 * Approach 3: Morris Traversal
In this method, we have to use a new data structure-Threaded Binary Tree, and the strategy is as follows:

Step 1: Initialize current as root

Step 2: While current is not NULL,

If current does not have left child

    a. Add current¡¯s value

    b. Go to the right, i.e., current = current.right

Else

    a. In current's left subtree, make current the right child of the rightmost node

    b. Go to this left child, i.e., current = current.left
For example:

          1
        /   \
       2     3
      / \   /
     4   5 6
First, 1 is the root, so initialize 1 as current, 1 has left child which is 2, the current's left subtree is

         2
        / \
       4   5
So in this subtree, the rightmost node is 5, then make the current(1) as the right child of 5. Set current = cuurent.left (current = 2). The tree now looks like:

         2
        / \
       4   5
            \
             1
              \
               3
              /
             6
For current 2, which has left child 4, we can continue with the same process as we did above

        4
         \
          2
           \
            5
             \
              1
               \
                3
               /
              6
then add 4 because it has no left child, then add 2, 5, 1, 3 one by one, for node 3 which has left child 6, do the same as above. Finally, the inorder taversal is [4,2,5,1,6,3].
	 */
	public List < Integer > inorderTraversal(TreeNode root) {
        List < Integer > res = new ArrayList < > ();
        TreeNode curr = root;
        TreeNode pre;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right; // move to next right node
            } else { // has a left subtree
                pre = curr.left;
                while (pre.right != null) { // find rightmost
                    pre = pre.right;
                }
                pre.right = curr; // put cur after the pre node
                TreeNode temp = curr; // store cur node
                curr = curr.left; // move cur to the top of the new tree
                temp.left = null; // original cur left be null, avoid infinite loops
            }
        }
        return res;
    }
	
	//The Morris solution above modifies the tree. The following one recovers it
	public List<Integer> inorderTraversal4(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode pre = cur.left;
                while ((pre.right != null) && (pre.right != cur)) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    nodes.add(cur.val);
                    cur = cur.right;
                }
            } else {
                nodes.add(cur.val);
                cur = cur.right;
            }
        }
        return nodes;
    }
}
