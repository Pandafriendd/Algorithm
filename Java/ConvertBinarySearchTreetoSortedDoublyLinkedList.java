/*
transform a BST into a circular doubly linked list. (in-place)  
Each node in a doubly linked list has a predecessor and successor. 
For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.
Specifically, we want to do the transformation in place. 
After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. 
We should return the pointer to the first element of the linked list.
 */
import java.util.*;
public class ConvertBinarySearchTreetoSortedDoublyLinkedList {
	
	public class Node{
		public int val;
		public Node left;
		public Node right;
		
		public Node() {}
		
		public Node(int x, Node l, Node r) {
			val = x;
			left = l;
			right = r;
		}
	}
	
	/*
step1: inorder tranversal by recursion to connect the original BST
step2: connect the head and tail to make it circular
Tips: Using dummy node to handle corner case
	 */
	Node prev = null;
	public Node treeToDoublyList(Node root) {
		if (root == null) 
			return null;
		Node dummy = new Node(0, null, null);
		prev = dummy;
		helper(root);
		
		/*Connect head and tail
		 * After inorder tranversal, the "prev" will point to the cur tail of DLL. And "dummy.right" still point to the head of DLL
		 * Since the DLL is circular, we need connect the tail.next -> head, which is "prev.right = dummy.right"
		 */
		prev.right = dummy.right;
		dummy.right.left = prev;
		return dummy.right;
	}

	private void helper (Node cur) {
		if (cur == null) 
			return;
		
		helper(cur.left);
		
		prev.right = cur;
		cur.left = prev;
		prev = cur;
		
		helper(cur.right);
	}
	
	/*
iterative solution using stack
	 */
	public Node treeToDoublyLis2t(Node root) {
        if (root == null) {
            return null;
        }
        Node cur = root;
        Node start = root;
        while (start.left != null) {
            start = start.left;
        }
        Node prev = null;
        Stack<Node> stack = new Stack<Node>();
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (prev != null) {
                prev.right = cur;
                cur.left = prev;
            }
            prev = cur;
            cur = cur.right;
        }
        start.left = prev;
        prev.right = start;
        return start;
    }
}
