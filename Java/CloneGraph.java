/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.

OJ's undirected graph serialization:
Nodes are labeled uniquely.

We use # as a separator for each node, and , as a separator for node label and each neighbor of the node.
As an example, consider the serialized graph {0,1,2#1,2#2,2}.

The graph has a total of three nodes, and therefore contains three parts as separated by #.

First node is labeled as 0. Connect node 0 to both nodes 1 and 2.
Second node is labeled as 1. Connect node 1 to node 2.
Third node is labeled as 2. Connect node 2 to node 2 (itself), thus forming a self-cycle.
Visually, the graph looks like the following:

       1
      / \
     /   \
    0 --- 2
         / \
         \_/
 */

import java.util.*;
public class CloneGraph {
	
	class UndirectedGraphNode {
		int label;
		List<UndirectedGraphNode> neighbors;
		UndirectedGraphNode(int x) { 
			label = x; 
			neighbors = new ArrayList<UndirectedGraphNode>(); 
		}
	}
	
	/*
DFS
Follow up: If nodes are not labeled uniquely, the map can't use the label as the key because they might duplicate.
The map should be Map<UndirectedGraphNode, UndirectedGraphNode>, 
where the key is the original node and the value is the clone. 
The default Object.hashCode() method will use a integer, derived from the internal memory address, that will guarantee uniqueness.
	 */
	private HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();
	
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return clone(node);
    }

    private UndirectedGraphNode clone(UndirectedGraphNode node) {
        if (node == null) return null;
        
        if (map.containsKey(node.label)) {
            return map.get(node.label);
        }
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(clone.label, clone); // put this line before for loop makes sure cycle can be detected.
        for (UndirectedGraphNode neighbor : node.neighbors) {
            clone.neighbors.add(clone(neighbor));
        }
        return clone;
    }
    
    //Map is defined as local variable instead of global,
    public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
        Map<Integer, UndirectedGraphNode> map = new HashMap<>();
        return dfs(node, map);
    }

    private UndirectedGraphNode dfs(UndirectedGraphNode node, Map<Integer, UndirectedGraphNode> map) {
        if(node == null) {
            return null;
        }
        if(map.containsKey(node.label)) {
            return map.get(node.label);
        }
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(node.label, clone);
        for(UndirectedGraphNode next : node.neighbors) {
            clone.neighbors.add(dfs(next, map));
        }
        return clone;
    }
    
    
    /*
Use HashMap to look up nodes and add connection to them while performing BFS.
This must be a connected graph. So E >= V and the run time is O(E).
     */
    public UndirectedGraphNode cloneGraph3(UndirectedGraphNode node) {
        if (node == null) return null;
        
        UndirectedGraphNode newNode = new UndirectedGraphNode(node.label); //new node for return
        HashMap<Integer, UndirectedGraphNode> map = new HashMap<>(); //store visited nodes
        
        map.put(newNode.label, newNode); //add first node to HashMap
        
        LinkedList<UndirectedGraphNode> queue = new LinkedList<>(); //to store **original** nodes need to be visited
        queue.add(node); //add first **original** node to queue
        
        while (!queue.isEmpty()) { //if more nodes need to be visited
            UndirectedGraphNode n = queue.pop(); //search first node in the queue
            for (UndirectedGraphNode neighbor : n.neighbors) {
                if (!map.containsKey(neighbor.label)) { //add to map and queue if this node hasn't been searched before
                    map.put(neighbor.label, new UndirectedGraphNode(neighbor.label));
                    queue.add(neighbor);
                }
                map.get(n.label).neighbors.add(map.get(neighbor.label)); //add neighbor to new created nodes
            }
        }
        
        return newNode;
    }
}
