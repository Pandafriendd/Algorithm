// Recursive Java program for level order traversal of Binary Tree

import java.util.Queue;
import java.util.LinkedList;

class Node{
	int data;
	Node left, right;
	public Node(int x){
		data = x;
		left = right = null;
	}
}

class LevelOrderTreeTraveral{
	Node root;


	public LevelOrderTreeTraveral(){
		root = null;
	}

	// method 1
	//function to print level order traveral of tree
	void printLevelOrder(){
		int h = height(root);
		for(int i = 1; i <= h; i++){
			printGivenLevel(root, i);
		}
	}

	//Compute the "height" of a tree -- the number of nodes along the longest path from the root node down to the farthest leaf node.
	int height(Node root){
		if(root == null)
			return 0;
		else{
			int lheight = height(root.left);
			int rheight = height(root.right);
			return lheight >= rheight ? lheight + 1 : rheight + 1;   // height beginning form 1
		}
	}
	void printGivenLevel(Node root, int level){
		if(root == null)
			return;
		if(level == 1)
			System.out.print(root.data + " ");
		else if(level > 1){
			printGivenLevel(root.left, level - 1);
			printGivenLevel(root.right, level - 1);
		}
	}

	public static void main(String args[]){
		LevelOrderTreeTraveral tree = new LevelOrderTreeTraveral();
		tree.root = new Node(1);
		tree.root.left= new Node(2);
        tree.root.right= new Node(3);
        tree.root.left.left= new Node(4);
        tree.root.left.right= new Node(5);

        tree.printLevelOrder();
	}


	//method 2
	// For each node, first the node is visited and then it’s child nodes are put in a FIFO queue.
	// printLevelorder(tree)
	// 1) Create an empty queue q
	// 2) temp_node = root /*start from root*/
	// 3) Loop while temp_node is not NULL
    //   a) print temp_node->data.
    //   b) Enqueue temp_node’s children (first left then right children) to q
    //   c) Dequeue a node from q and assign it’s value to temp_node

    /* Given a binary tree. Print its nodes in level order
     using array for implementing queue  */
    void printLevelOrder2(){
    	Queue<Node> queue = new ListedList<Node>();
    	queue.add(root);

    	while(!queue.isEmpty()){
    		// poll() removes the present head.
    		Node tempNode = queue.poll();
    		System.out.print(tempNode.data + " ");

    		//enqueue left child
    		if(tempNode != null)
    			queue.add(tempNode.left);

    		//enqueue right child
    		if(tempNode != null)
    			queue.add(tempNode.right);
    	}
    }


}