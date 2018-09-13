/*
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

Example 1:
Input:
11110
11010
11000
00000
Output: 1

Example 2:
Input:
11000
11000
00100
00011
Output: 3
 */

import java.util.*;
public class NumberofIslands {
	
	/*
Approach 1: DFS
intuition
Treat the 2d grid map as an undirected graph and there is an edge between two horizontally or vertically adjacent nodes of value '1'.
Algorithm
Linear scan the 2d grid map, if a node contains a '1', then it is a root node that triggers a Depth First Search. 
During DFS, every visited node should be set as '0' to mark as visited node. Count the number of root nodes that trigger DFS, 
this number would be the number of islands since each DFS starting at some root identifies an island.
	
Time complexity :  O(M¡ÁN) where  M is the number of rows and N is the number of columns.  
Space complexity : worst case O(M¡ÁN) in case that the grid map is filled with lands where DFS goes by M¡ÁN deep.
	  */
	  public int numIslands(char[][] grid) {
	    if (grid == null || grid.length == 0) {
	      return 0;
	    }

	    int nr = grid.length;
	    int nc = grid[0].length;
	    int num_islands = 0;
	    
	    for (int r = 0; r < nr; r++) {
	      for (int c = 0; c < nc; c++) {
	        if (grid[r][c] == '1') {
	          num_islands++;
	          dfs(grid, r, c);
	        }
	      }
	    }

	    return num_islands;
	  }
	  
	  void dfs(char[][] grid, int r, int c) {
		    int nr = grid.length;
		    int nc = grid[0].length;

		    if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') { // !!! >=
		      return;
		    }

		    grid[r][c] = '0'; // mark as visited !!
		    dfs(grid, r - 1, c); // up
		    dfs(grid, r + 1, c); // down
		    dfs(grid, r, c - 1); // left
		    dfs(grid, r, c + 1); // right
	  }
	  
	  /*
approach 2: BFS
Linear scan the 2d grid map, if a node contains a '1', then it is a root node that triggers a Breadth First Search. 
Put it into a queue and set its value as '0' to mark as visited node. 
Iteratively search the neighbors of enqueued nodes until the queue becomes empty.

Time complexity :  O(M¡ÁN) where  M  is the number of rows and  N  is the number of columns.  
Space complexity :  O(min(M,N)) because in worst case where the grid is filled with lands, the size of queue can grow up to min(M,N).
	   */
	  public int numIslands2(char[][] grid) {
		    if (grid == null || grid.length == 0) {
		      return 0;
		    }

		    int nr = grid.length; // nr = 4
		    int nc = grid[0].length; // nc = 3
		    int num_islands = 0;

		    for (int r = 0; r < nr; r++) {
		      for (int c = 0; c < nc; c++) {
		        if (grid[r][c] == '1') { // !!
		          num_islands++;
		          grid[r][c] = '0'; // mark as visited
		          
		          Queue<Integer> neighbors = new LinkedList<>();
		          neighbors.add(r * nc + c); // !! 0 1 2 3 4 5 6 7 8 9 10 11. r * nc + c is the id of node in the grid
		          
		          while (!neighbors.isEmpty()) {
		            int id = neighbors.remove();
		            int row = id / nc;   // !!! nc
		            int col = id % nc;
		            if (row - 1 >= 0 && grid[row - 1][col] == '1') { //search upward and mark adjacent '1's as '0'.
		              neighbors.add((row - 1) * nc + col); // add the neighbor's id
		              grid[row - 1][col] = '0';
		            }
		            if (row + 1 < nr && grid[row + 1][col] == '1') { //down  < nr !!!
		              neighbors.add((row + 1) * nc + col);
		              grid[row + 1][col] = '0';
		            }
		            if (col - 1 >= 0 && grid[row][col - 1] == '1') { //left
		              neighbors.add(row * nc + col - 1);
		              grid[row][col - 1] = '0';
		            }
		            if (col + 1 < nc && grid[row][col + 1] == '1') { //right
		              neighbors.add(row * nc + col + 1);
		              grid[row][col + 1] = '0';
		            }
		          }
		        }
		      }
		    }

		    return num_islands;
		  }
	  
	  /*
approach 3: union find (aka. Disjoint Set)
a disjoint-set data structure (also called a union¨Cfind data structure or merge¨Cfind set) 
is a data structure that tracks a set of elements partitioned into a number of disjoint (non-overlapping) subsets. 
It provides near-constant-time operations (bounded by the inverse Ackermann function) to add new sets, to merge existing sets, 
and to determine whether elements are in the same set.
 
In addition to many other uses, disjoint-sets play a key role in Kruskal's algorithm for finding the minimum spanning tree of a graph.

Traverse the 2d grid map and union adjacent lands horizontally or vertically, 
at the end, return the number of connected components maintained in the UnionFind data structure.

Time: O(M * N). note that Union operation takes essentially constant time when UnionFind is implemented with both path compression and union by rank
Space: O(M * N) as required by UnionFind data structure

https://leetcode.com/problems/number-of-islands/discuss/56354/1D-Union-Find-Java-solution-easily-generalized-to-other-problems
	   */
	  class UnionFind {
	        int[] farther;  //!!
	        int[] rank;
	        int count;  // # of set
	        
	        // constructor, initialize farther and rank
	        public UnionFind(char[][] grid) {
	            int nr = grid.length;
	            int nc = grid[0].length;
	            
	            farther = new int[nr * nc];
	            rank = new int[nr * nc];
	            count = 0;  // # of set
	            
	            for(int row = 0; row < nr; row++) {
	                for(int col = 0; col < nc; col++) {
	                    if(grid[row][col] == '1') {  //!! when encounter a 1, then we create a new set
	                        farther[row * nc + col] = row * nc + col;  // initialized as itself
	                        count++;   // initially count = # of nodes
	                    }
	                    rank[row * nc + col] = 0; // initialize as 0 for all nodes
	                }
	            }
	        }
	        
	        public int find(int index) {
	            if(farther[index] != index) {  // path compression
	                farther[index] = find(farther[index]);
	            }
	            
	            return farther[index];
	        }
	        
	        public void union(int node1, int node2) { // union with rank
	            int farther1 = find(node1);
	            int farther2 = find(node2); //!!
	            if(farther1 != farther2) {  // if two nodes are in different set
	                if(rank[farther1] < rank[farther2]) { //!!!
	                    //farther[node1] = node2;   
	                    farther[farther1] = farther2;  // !!! should change the farther!!! set farther to be larger rank node's farther
	                }
	                else if(rank[farther1] > rank[farther2]) {
	                    farther[farther2] = farther1;
	                }
	                else { // f2 == f1, pick node1 to be farther
	                    farther[farther2] = farther1;
	                    rank[farther1]++; //!!
	                }
	                count--;   // union two sets into one, so count--
	            }
	        }
	        
	        public int getCount() {
	            return count;
	        }
	    }

	    public int numIslands3333(char[][] grid) {
	        if(grid == null || grid.length == 0 || grid[0].length == 0)
	            return 0;
	        
	        int nr = grid.length;
	        int nc = grid[0].length;
	        UnionFind uf = new UnionFind(grid);
	        
	        for(int i = 0; i < nr; i++) {
	            for(int j = 0; j < nc; j++) {
	                if(grid[i][j] == '1') {
	                    grid[i][j] = '0'; // mark as visited
	                    if(i + 1 < nr && grid[i + 1][j] == '1') {
	                        uf.union(i * nc + j, (i + 1) * nc + j);  // union right 
	                    }
	                    if(i - 1 >= 0 && grid[i - 1][j] == '1') {
	                        uf.union(i * nc + j, (i - 1) * nc + j);  // union left 
	                    }
	                    if(j + 1 < nc && grid[i][j + 1] == '1') { // union down 
	                        uf.union(i * nc + j, i * nc + j + 1);
	                    }
	                    if(j - 1 >= 0 && grid[i][j - 1] == '1') { // union up 
	                        uf.union(i * nc + j, i * nc + j - 1);
	                    }
	                }
	            }
	        }
	        
	        return uf.getCount();        
	    }
}
