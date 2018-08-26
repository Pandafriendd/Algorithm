/*
Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples 1:

Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7 

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]
Examples 2:

Input: [3,9,8,4,0,1,7]

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7 

Output:

[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Examples 3:

Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]
 */


import java.util.*;
public class BinaryTreeVerticalOrderTraversal {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x){
			val = x;
		}
	}
	
	
	/*
The following solution takes 5ms.

1. BFS, put node, col into queue at the same time
2. Every left child access col - 1 while right child col + 1
3. This maps node into different col buckets
4. Get col boundary min and max on the fly
5. Retrieve result from cols

	 */
	public List<List<Integer>> verticalOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
	    if (root == null) {
	        return res;
	    }
	    
	    Map<Integer, ArrayList<Integer>> map = new HashMap<>(); // col ==> nodes' list
	    Queue<TreeNode> q = new LinkedList<>(); // for bfs, stored nodes
	    Queue<Integer> cols = new LinkedList<>(); // for bfs, stored columns

	    q.add(root); 
	    cols.add(0);  // we set the root at col 0

	    int min = 0;
	    int max = 0;
	    
	    while (!q.isEmpty()) {
	        TreeNode node = q.poll();
	        int col = cols.poll();
	        
	        if (!map.containsKey(col)) {
	            map.put(col, new ArrayList<Integer>());
	        }
	        map.get(col).add(node.val);

	        if (node.left != null) {
	            q.add(node.left); 
	            cols.add(col - 1);
	            min = Math.min(min, col - 1);                                                                                                                          
	        }
	        
	        if (node.right != null) {
	            q.add(node.right);
	            cols.add(col + 1);
	            max = Math.max(max, col + 1);
	        }
	    }

	    for (int i = min; i <= max; i++) {
	        res.add(map.get(i));
	    }

	    return res;
    }
	
	/*
	 Solution with TreeMap instead of max/min
	 */
	public List<List<Integer>> verticalOrder3(TreeNode root) {
		if (root == null) {
			return new ArrayList<>();
		}
		
		Map<Integer, List<Integer>> map = new TreeMap<>();  // TreeMap, col ==> nodes' list, and col from small to big
	    Queue<TreeNode> q = new LinkedList<>();
	    Queue<Integer> cols = new LinkedList<>();

	    q.add(root); 
	    cols.add(0);

	    while (!q.isEmpty()) {
	        TreeNode node = q.poll();
	        int col = cols.poll();

	        if (!map.containsKey(col)) {
	            map.put(col, new ArrayList<Integer>());
	        }
	        map.get(col).add(node.val);

	        if (node.left != null) {
	            q.add(node.left); 
	            cols.add(col - 1);
	        }

	        if (node.right != null) {
	            q.add(node.right);
	            cols.add(col + 1);
	        }
	    }
	    
	    return new ArrayList<>(map.values());
	}
	
	
	/*
	Alternatively, we can calculate the range first, then insert into buckets.
	 */
	public List<List<Integer>> verticalOrder2(TreeNode root) {
	    List<List<Integer>> cols = new ArrayList<>();
	    if (root == null) {
	        return cols;
	    }
	    
	    int[] range = new int[] {0, 0}; // [minIndex, maxIndex]
	    getRange(root, range, 0);
	    
	    for (int i = range[0]; i <= range[1]; i++) {
	        cols.add(new ArrayList<Integer>());
	    }
	    
	    Queue<TreeNode> queue = new LinkedList<>();
	    Queue<Integer> colQueue = new LinkedList<>();
	    
	    queue.add(root);
	    colQueue.add(-range[0]);  // since we're using ArrayList to store result, the index should >= 0
	    
	    while (!queue.isEmpty()) {
	        TreeNode node = queue.poll();
	        int col = colQueue.poll();
	        
	        cols.get(col).add(node.val);
	        
	        if (node.left != null) {
	            queue.add(node.left);   
	            colQueue.add(col - 1);
	        } 
	        if (node.right != null) {
	            queue.add(node.right);
	            colQueue.add(col + 1);
	        }
	    }
	    
	    return cols;
	}

	public void getRange(TreeNode root, int[] range, int col) {
	    if (root == null) {
	        return;
	    }
	    range[0] = Math.min(range[0], col);
	    range[1] = Math.max(range[1], col);
	    
	    getRange(root.left, range, col - 1);
	    getRange(root.right, range, col + 1);
	}
}
