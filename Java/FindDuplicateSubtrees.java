/*
Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1:

        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:

      2
     /
    4
and

    4
Therefore, you need to return above trees' root in the form of a list.
 */

import java.util.*;
public class FindDuplicateSubtrees {
	
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;
		}
	}
	
	
	/*
Depth-First Search: 
We can serialize each subtree. For example, the tree

   1
  / \
 2   3
    / \
   4   5
can be represented as the serialization 1,2,#,#,3,4,#,#,5,#,#, which is a unique representation of the tree.
Perform a depth-first search, where the recursive function returns the serialization of the tree. 
At each node, record the result in a map, and analyze the map after to determine duplicate subtrees.

time: O(n^2), where n is the number of nodes in the tree. We visit each node once, but each creation of serial may take O(n) work.
space: O(n^2), the size of freqMap
	 */
	Map<String, Integer> freqMap;
	List<TreeNode> res;
	
	public List<TreeNode> findDuplicateSubtrees(TreeNode root){
		freqMap = new HashMap<>();
		res = new ArrayList<>();
		
		serializeTree(root);
		return res;
	}
	
	public String serializeTree(TreeNode node) {
		if(node == null)
			return "#";
		String serial = node.val + "," + serializeTree(node.left) + "," + serializeTree(node.right);
		freqMap.put(serial, freqMap.getOrDefault(serial, 0) + 1);
		
		if(freqMap.get(serial) == 2)
			res.add(node);
		
		return serial;
	}
	
}
/*
Unique Identifier:
Suppose we have a unique identifier for subtrees: two subtrees are the same if and only if they have the same id.
Then, for a node with left child id of x and right child id of y, (node.val, x, y) uniquely determines the tree.
If we have seen the triple (node.val, x, y) before, we can use the identifier we've remembered. Otherwise, we'll create a new one.

Time Complexity: O(N), where N is the number of nodes in the tree. We visit each node once.
Space Complexity: O(N). Every structure we use is using O(1) storage per node.
	 */
class Solution {
	public class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;
		}
	}
	
    int t;
    Map<String, Integer> trees;
    Map<Integer, Integer> count;
    List<TreeNode> ans;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        t = 1;
        trees = new HashMap<>();
        count = new HashMap<>();
        ans = new ArrayList<>();
        lookup(root);
        return ans;
    }

    public int lookup(TreeNode node) {
        if (node == null) 
        	return 0;
        String serial = node.val + "," + lookup(node.left) + "," + lookup(node.right);
        /*
        int uid = trees.computeIfAbsent(serial, x-> t++) means something like:
        if serial is not contained in trees, then trees.put(serial, t++) [make the value of this key serial equal to t, and then incremement t] and after, uid = trees.get(serial).
         */
        int uid = trees.computeIfAbsent(serial, x-> t++);
        count.put(uid, count.getOrDefault(uid, 0) + 1);
        if (count.get(uid) == 2)
            ans.add(node);
        return uid;
    }
}
