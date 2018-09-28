/*
There are a total of n courses you have to take, labeled from 0 to n-1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:
Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.

Example 2:
Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites

 */

import java.util.*;
public class CourseSchedule {
	
	/*
Topological sort can be started with the first node OR the last node.
The only difference in the process is removing the out-come edge OR in-come edge.
In this case, I choose start with the first node(indegree == 0);
	 */
	
	// more efficient
	public boolean canFinish0(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        for(int i = 0; i <= prerequisites.length - 1; i++) {
            inDegree[prerequisites[i][0]]++;
        }
        
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i <= inDegree.length - 1; i++) {
            if(inDegree[i] == 0) {
                stack.push(i);  // !!
            }
        }
        
        int zeroDegreeCount = stack.size();
        while(!stack.isEmpty()) {
            int zeroInDegreeNode = stack.pop();
            for(int i = 0; i <= prerequisites.length - 1; i++) {
                if(zeroInDegreeNode == prerequisites[i][1]) {
                    inDegree[prerequisites[i][0]]--;
                    if(inDegree[prerequisites[i][0]] == 0) {
                        stack.push(prerequisites[i][0]);
                        zeroDegreeCount++;
                    }
                }
            }
        }
        
        return (zeroDegreeCount == numCourses);
    }
	
	public static boolean canFinish(int numCourses, int[][] prerequisites) {
		if (numCourses <= 0)
			return false;
		Queue<Integer> queue = new LinkedList<>();
		
		int[] inDegree = new int[numCourses];
		for (int i = 0; i < prerequisites.length; i++) {  // set indgree for each node
			inDegree[prerequisites[i][0]]++;  
		}
		
		for (int i = 0; i < inDegree.length; i++) { // add all zero indegree nodes onto queue
			if (inDegree[i] == 0)
				queue.offer(i);
		}
		
		while (!queue.isEmpty()) {
			int zeroInDgreeNode = queue.poll();
			for (int i = 0; i < prerequisites.length; i++) {
				if (zeroInDgreeNode == prerequisites[i][1]) {
					inDegree[prerequisites[i][0]]--;
					if (inDegree[prerequisites[i][0]] == 0)
						queue.offer(prerequisites[i][0]);
				}
			}
		}
		
		for (int i = 0; i < inDegree.length; i++) {
			if (inDegree[i] != 0)
				return false;
		}
		
		return true;
	}
}
