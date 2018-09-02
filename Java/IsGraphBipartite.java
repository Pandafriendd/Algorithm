/*
 Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation: 
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation: 
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.
 

Note:

graph will have length in range [1, 100].
graph[i] will contain integers in range [0, graph.length - 1].
graph[i] will not contain i or duplicate values.
The graph is undirected: if any element j is in graph[i], then i will be in graph[j].
 */

import java.util.*;
public class IsGraphBipartite {
	
	/*
DFS Solution:
Our goal is trying to use two colors to color the graph and see if there are any adjacent nodes having the same color, if same color, return false
Initialize a color[] array for each node. Here are three states for colors[] array:
-1: Haven't been colored.
0: Blue.
1: Red.
For each node,
1. If it hasn't been colored, use a color to color it. Then use the other color to color all its adjacent nodes (DFS).
2. If it has been colored, check if the current color is the same as the color that will be used to color it.
	 */
	public boolean isBipartite(int[][] graph) {
		int n = graph.length;
		int[] colors = new int[n];
		Arrays.fill(colors, -1);
		
		for(int i = 0; i < n; i++) { // graph might be a disconnected graph. So check each unvisited node
			if(colors[i] == -1 && !validColor(graph, colors, 0, i)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean validColor(int[][] graph, int[] colors, int color, int node) { 
		if(colors[node] != -1) {
			return colors[node] == color;
		}
		// if uncolored
		colors[node] = color;
		for(int next : graph[node]) {
			if(!validColor(graph, colors, 1 - color, next)) { // use the other color to color all its adjacent nodes
				return false;
			}
		}
		return true;
	}
	
	
	// BFS, same idea using color method
	public boolean isBipartite2(int[][] graph) {
        int[] colors = new int[graph.length];
        Arrays.fill(colors, -1);
        
        for (int i = 0; i < graph.length; i++)
            if (colors[i] == -1) { // uncolored
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                colors[i] = 0;  // color blue
                
                while (!q.isEmpty()) {
                    Integer node = q.poll();
                    
                    for (int adjacent : graph[node]) {
                        if (colors[adjacent] == colors[node])
                            return false;
                        else if (colors[adjacent] == -1) {
                            q.add(adjacent);
                            colors[adjacent] = 1 - colors[node];
                        }
                    }
                }
            }
        return true;
    }
}
