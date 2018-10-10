

import java.util.*;
public class Bipartite {
	
	
	public class GraphNode {
	    public int key;
	    public List<GraphNode> neighbors;
	    public GraphNode(int key) {
	      this.key = key;
	      this.neighbors = new ArrayList<GraphNode>();
	    }
	  }
	 
	
	// also can use one HashMap<GraphNode, Integer> replace two HashSets
	// time: O(n + e), n is # of nodes, e is # of edges
	// space: O(n), hashset, queue are used
	public boolean isBipartite(List<GraphNode> graph) {
	    // write your solution here
	    Queue<GraphNode> q = new ArrayDeque<>();
	    HashSet<GraphNode> color1 = new HashSet<>();
	    HashSet<GraphNode> color2 = new HashSet<>();
	    
	    // since the graph can be disconnected graph, so we should go through all nodes in the list
	    for(GraphNode cur : graph) {
	      if(!color1.contains(cur) && !color2.contains(cur)) { // if uncolored
	        color1.add(cur);
	        q.offer(cur);
	      }
	      
	      while(!q.isEmpty()) {
	        GraphNode node = q.poll();  // generate!
	        int color = 0;
	        if(color1.contains(node)) {
	          color = 1;
	        }
	        else {
	          color = 2;
	        }
	        
	        for(GraphNode n : node.neighbors) {
	          if(!color1.contains(n) && !color2.contains(n)) { // if the neighbor is uncolored
	            q.offer(n); // expend this neighbor  !!!!!!!!!!!!!!!!!!!!!!!!!
	            if(color == 1) {
	              color2.add(n);
	            }
	            else {
	              color1.add(n);
	            }
	          }
	          else {  // if the neighbor is colored
	            if(color == 1 && color1.contains(n) || color == 2 && color2.contains(n)) {  // if there is a conflict found!
	              return false;
	            }
	          } 
	        }
	      }
	    }
	    
	    return true;
	    
	  }
	
	
	
}
