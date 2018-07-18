/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // my dmmy method, 2%
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>(); // !!
        if(root == null)
            return res;
        int height = height(root);
        for(int level = 1; level <= height; level++){
            ArrayList<Integer> list = new ArrayList<Integer>();
            givenLevelNode(list, root, level);
            res.add(list);
        }
        return res;
    }
    
    private void givenLevelNode(ArrayList<Integer> list, TreeNode root, int level) {
        if(root == null)
            return;
        if(level == 1){
            list.add(root.val);
            return;
        }
        else if(level > 1){
            givenLevelNode(list, root.left, level - 1);
            givenLevelNode(list, root.right, level - 1);
        }
    }
    
    private int height(TreeNode root) {
        if(root == null)
            return 0;
        else{
            int lheight = height(root.left);
            int rheight = height(root.right);
            return lheight >= rheight ? lheight + 1 : rheight + 1;
        }
    }


    // queque used
    public List<List<Integer>> levelOrder2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
        
        if(root == null) return wrapList;
        
        queue.offer(root);
        while(!queue.isEmpty()){
            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<Integer>();
            for(int i=0; i<levelNum; i++) {
                if(queue.peek().left != null) queue.offer(queue.peek().left);
                if(queue.peek().right != null) queue.offer(queue.peek().right);
                subList.add(queue.poll().val);
            }
            wrapList.add(subList);
        }
        return wrapList;
    }


    // iterative
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        
        if (root == null) return list;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            List<TreeNode> nodes = new ArrayList<>();
            while(!queue.isEmpty())
                nodes.add(queue.remove());
            
            List<Integer> innerList = new ArrayList<>();
            for(TreeNode node: nodes) {
                innerList.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            list.add(innerList);
        }
        
        return list;
    }


    // recursive
    public List<List<Integer>> levelOrder4(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        levelOrder(list, root, 0);
        return list;
    }
    
    public void levelOrder(List<List<Integer>> list, TreeNode node, int level) {
        if (node == null) return;
        
        if (list.size() == level) 
            list.add(new ArrayList<>());
        
        list.get(level).add(node.val);        
        levelOrder(list, node.left, level + 1);
        levelOrder(list, node.right, level + 1);
    }
}