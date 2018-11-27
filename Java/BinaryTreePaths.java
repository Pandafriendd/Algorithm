/*
Given a binary tree, return all root-to-leaf paths from left to right.

For example, given the following binary tree:

   1
 /   \
2     3
 \
  5
All root-to-leaf paths are:

["1->2->5", "1->3"]


*/


// this code only handle root's key < 10
public String[] binaryTreePaths(TreeNode root) {
	List<String> allPaths = new ArrayList<>();
	StringBuilder curPath = new StringBuilder();

	traverse(root, allPaths, curPath);

	String[] res = new String[allPaths.size()];
	int i = 0;
	for (String path : allPaths) {
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < path.length(); j++) {
			if (j != path.length() - 1) {
				sb.append(path.charAt(j));
				sb.append("->");
      		} else {
				sb.append(path.charAt(j));
      		}
		}
		curPath.setLength(Math.max(curPath.length() - 2, 0));
		res[i] = sb.toString();
		i++;
	}

	return res;
}

private void traverse(TreeNode root, List<String> allPaths, StringBuilder curPath) {
	// base case 1
	if (root == null) {
		return;
	}
	// base case 2: leaf
	if (root.left == null && root.right == null) {
		curPath.append(Integer.toString(root.key));
		allPaths.add(curPath.toString());
		curPath.setLength(Math.max(curPath.length() - 1, 0));
		return;
	}

	// recursive rules
	curPath.append(Integer.toString(root.key));
	traverse(root.left, allPaths, curPath);
	traverse(root.right, allPaths, curPath);
	curPath.setLength(Math.max(curPath.length() - 1, 0));
}
