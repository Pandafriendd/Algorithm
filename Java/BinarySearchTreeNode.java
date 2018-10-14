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
}
