public List<List<Integer>> layerByLayer(TreeNode root) {
    // Write your solution here
    List<List<Integer>> res = new ArrayList<>();
    if(root == null) {
      return res;
    }
    
    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);
    int size = 0;
    while(!queue.isEmpty()) {
      size = queue.size();
      List<Integer> list = new ArrayList<>();
      while(size > 0) {
        TreeNode node = queue.poll();
        list.add(node.key);
        if(node.left != null) {
          queue.offer(node.left);
        }
        if(node.right != null) {
          queue.offer(node.right);
        }
        size--;
      }
      res.add(list);
    }
    
    return res;
}