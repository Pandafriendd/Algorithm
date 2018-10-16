/*
 * binary search tree implementation
 */
public class BinarySearchTreeNode {
	int key;
	int value;
	int size;
	BinarySearchTreeNode left;
	BinarySearchTreeNode right;

	public BinarySearchTreeNode(int k) {
		key = k;
		value = 1000; // default value;
		left = null;
		right = null;
	}


	// return the TreeNode have found
	// return null if we find nothing
	public BinarySearchTreeNode search0(BinarySearchTreeNode root, int target) {
		if(root == null) {  // not necessary
			return root;
		}

		BinarySearchTreeNode cur = root;
		while(cur != null) {
			if(cur.key == target) {
				return cur;
			}
			else if(cur.key > target) {
				cur = cur.left;
			}
			else {  // cur.key < target
				cur = cur.right;
			}
		}

		return cur;
	}

	// recursive search using tail recursion
	public BinarySearchTreeNode search(BinarySearchTreeNode root, int target) {
		if(root == null) {
			return root;
		}

		if(root.key == target) {
			return root;
		}
		else if(root.key > target) {
			return search(root.left, target);
		}
		else {
			return search(root.right, target);
		}
	}



	// insert a specific key into the BST, return the root of the BST
	// my first implementation is WRONG!!!!!!!!!!!
	public BinarySearchTreeNode insertWrong(BinarySearchTreeNode root, int key) {
		while(root != null) {
			if(root.key == key) {
				return root;
			}
			else if(root.key > key) {
				root = root.left;
			}
			else {
				root = root.right;
			}
		}

		root = new BinarySearchTreeNode(key); // !!! it's wrong!!!!! since it not enough, didn't dereference
		return root;
	}


	// This is the RIGHT version!!!!
	public BinarySearchTreeNode insert0(BinarySearchTreeNode root, int key) {
		if(root == null) {
			root = new BinarySearchTreeNode(key);
			return root; 
		}

		BinarySearchTreeNode cur = root;
		BinarySearchTreeNode pre = null;
		while(cur != null) {
			if(cur.key == key) {   // already exist, just return root
				return root;
			}
			else if(cur.key > key) {
				pre = cur;
				cur = cur.left;
			}
			else {   // cur.key < key
				pre = cur;
				cur = cur.right;
			}
		}

		// now cur == null, pre is the parent of null
		if(pre.key > key) {
			pre.left = new BinarySearchTreeNode(key);
		}
		else {  // pre.key < key
			pre.right = new BinarySearchTreeNode(key);
		}

		return root;
	}

	// insert using recursion. Elegant!!
	// return the root after insertion
	public BinarySearchTreeNode insert(BinarySearchTreeNode root, int key) {
		if(root == null) {
			return new BinarySearchTreeNode(key);
		}

		if(root.key == key) {
			return root;
		}
		else if(root.key > key) {
			root.left = insert(root.left, key);
		}
		else {
			root.right = insert(root.right, key);
		}

		return root;
	}
	
	// delete the node in the BST, return the root after deletion
	public BinarySearchTreeNode delete(BinarySearchTreeNode root, int key) {
		if(root == null) {
			return root;
		}
		
		// first, find target node
		if(root.key > key) {
			root.left = delete(root.left, key);
			return root;
		}
		else if(root.key < key) {
			root.right = delete(root.right, key);
			return root;
		}
		
		// now root != null && root.key == key
		if(root.left == null) {
			return root.right;
		}
		else if(root.right == null) {
			return root.left;
		}
		
		// now root has both left and right child, find the smallest element in right child and set it to replace the deleted position
		if(root.right.left == null) { // !!!
			root.right.left = root.left;
			return root.right;
		}
		// now right child has both left and right child
		BinarySearchTreeNode smallest = findandDeleteSmallest(root.right);
		smallest.left = root.left;
		smallest.right = root.right;
		return smallest;
	}
	
	private BinarySearchTreeNode findandDeleteSmallest(BinarySearchTreeNode root) {
		BinarySearchTreeNode cur = root;
		BinarySearchTreeNode pre = null;
		while(cur.left != null) {
			pre = cur;
			cur = cur.left;
		}
		
		// now cur is the left most node, pre is parent of cur
		pre.left = cur.right;
		return cur;
		
	}
}
