// brute force
// time: O(n * height), since prefixPath's max size will be the length of tree, so O(n^2) in worst case
// space: O(height)?
public boolean exist(TreeNode root, int target) {
    if (root == null) {
    	return false;
    }

    List<Integer> prefixPath = new ArrayList<>();
    return helper(root, prefixPath, target);
}

private boolean helper(TreeNode root, List<Integer> prefixPath, int target) {
	if (root == null) {
		return false;
	}

	int currentSum = root.key;
	if (currentSum == target) {
		return true;
	}
	for (int i = prefixPath.size() - 1; i >= 0; i--) {
		currentSum += prefixPath.get(i);
		if (currentSum == target) {
			return true;
		}
	}
	prefixPath.add(root.key);
	boolean result = helper(root.left, prefixPath, target) || helper(root.right, prefixPath, target);
	prefixPath.remove(prefixPath.size() - 1);
	return result;
}
