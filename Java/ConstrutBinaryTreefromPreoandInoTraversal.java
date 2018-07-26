/*
* Given preorder and inorder traversal of a tree, construct the binary tree.
* Note: You may assume that duplicates do not exist in the tree.
*
* my high level solution: 
* 1. choose first node from preorder, and then check inorder
* eg: pre: 3 9 20 15 7   in: 9 3 15 20 7    choose 3 and based on in we have:
*         3
*     9 /   \(20 15 7)
*  2. choose second node from pre, and then check inorder
* choose 9, since 9.left == null 9.right == 3, which is already constructed, we skip
* 3. choose third node, and check in.
* choose 20, 20.l = 15, 20.r = 7
*          3
*      9/    \20
           15/ \7
*/


//recursive
/*
Space: O(N)
Because each node we create a helper(), the recursion stack will cost O(N)

Time: O(N log N) for a balanced tree, O(N^2) for a skew tree
As mentioned above, the helper() runs O(N) time, and for each helper(), there is a for-loop to search the inorder index.

For a balanced tree, the range of the search will be reduced by half each time, so the search costs O(log n)
Therefore the time is O(N) * O(log N) = O(N log N)

For a skew tree, the range of the search will only be reduced by 1, so the search still costs O(N)
Therefore the time is O(N) * O(N) = O(N^2)
*/
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    private TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder){
        //it is quite easy to omit the "preStart > preorder.length - 1" condition , as that occurs only when the rightmost subproblem in pre-order array happens to have no right subtree.
        //Because we use preStart to only create a new root for the given sub-tree and not divide the array. Hence as long as we check if preStart < preorder.length it's sufficient.
        if(preStart > preorder.length - 1 || inStart > inEnd){  
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0; // index of current root in inorder
        for(int i = inStart; i <= inEnd; i++){
            if(inorder[i] == root.val)
                inIndex = i;    // already found current root in inorder
        }
        root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);  //(inIndex - inStart) is the size of root's left subtree

        return root;
    }


    //using hashmap. Solution With Hashmap to avoid inorder iteration in every recursive call
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if(preorder == null ||inorder == null || preorder.length ==0 || inorder.length==0)
            return null;
        Map<Integer, Integer> indexes = new HashMap();
        for(int i =0; i<inorder.length; i++){
            indexes.put(inorder[i],i);     
        }
        return helper(preorder,inorder,0, preorder.length-1, indexes,0);
        
    }
    private TreeNode helper(int[] preorder, int[] inorder, int pstart,int pend,  Map<Integer, Integer> indexes, int iStart ){
        if(pstart>pend || iStart>= preorder.length)
            return null;
        int idx = indexes.get(preorder[iStart]);
        TreeNode root = new TreeNode(preorder[iStart]);
        root.left =  helper(preorder, inorder, pstart, idx-1, indexes, iStart+1);
        root.right = helper(preorder, inorder,  idx+1, pend, indexes, iStart+idx-pstart+1);  
        return root;
    }



    // iterative solution
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // deal with edge case(s)
        if (preorder.length == 0) {
            return null;
        }
        
        // build a map of the indices of the values as they appear in the inorder array
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        
        // initialize the stack of tree nodes
        Stack<TreeNode> stack = new Stack<>();
        int value = preorder[0];
        TreeNode root = new TreeNode(value);
        stack.push(root);
        
        // for all remaining values...
        for (int i = 1; i < preorder.length; i ++) {
            // create a node
            value = preorder[i];
            TreeNode node = new TreeNode(value);
            
            if (map.get(value) < map.get(stack.peek().val)) {
                // the new node is on the left of the last node,
                // so it must be its left child (that's the way preorder works)
                stack.peek().left = node;
            } else {
                // the new node is on the right of the last node,
                // so it must be the right child of either the last node
                // or one of the last node's ancestors.
                // pop the stack until we either run out of ancestors
                // or the node at the top of the stack is to the right of the new node
                TreeNode parent = null;
                while(!stack.isEmpty() && map.get(value) > map.get(stack.peek().val)) {
                    parent = stack.pop();
                }
                parent.right = node;
            }
            stack.push(node);
        }
        
        return root;
    } 
}