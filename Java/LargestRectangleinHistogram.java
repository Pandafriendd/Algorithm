/*
 */

import java.util.*;
public class LargestRectangleinHistogram {
	/*
	 approach1: brute force, two pointers
	 time: O(n^3). we have to find the min height bar O(n) lying between every pair O(n^2)
	 */
	public int largestRectangleArea(int[] heights) {
		int maxarea = 0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = i; j < heights.length; j++) {
                int minheight = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++)
                    minheight = Math.min(minheight, heights[k]);
                maxarea = Math.max(maxarea, minheight * (j - i + 1));
            }
        }
        return maxarea;
	}
	
	/*
	 *approach 2: better brute force
	time: O(n^2). every possible pair is considered
	 */
	 public int largestRectangleArea2(int[] heights) {
	        int maxarea = 0;
	        for (int i = 0; i < heights.length; i++) {
	            int minheight = Integer.MAX_VALUE;
	            for (int j = i; j < heights.length; j++) {
	                minheight = Math.min(minheight, heights[j]);
	                maxarea = Math.max(maxarea, minheight * (j - i + 1));
	            }
	        }
	        return maxarea;
	    }
	 
	 /*
	  Divide and Conquer
	  time: Average Case: O(nlog(n)).Worst Case: O(n^2).If the numbers in the array are sorted, we don't gain the advantage of divide and conquer.
	  Space complexity : O(n). Recursion with worst case depth nn.
	  */
	 public int calculateArea(int[] heights, int start, int end) {
	        if (start > end)
	            return 0;
	        int minindex = start;
	        for (int i = start; i <= end; i++)
	            if (heights[minindex] > heights[i])
	                minindex = i;
	        return Math.max(heights[minindex] * (end - start + 1), Math.max(calculateArea(heights, start, minindex - 1), calculateArea(heights, minindex + 1, end)));
	    }
	 public int largestRectangleArea3(int[] heights) {
	       return calculateArea(heights, 0, heights.length - 1);
	 }
	 
	 /*
	  using stack
	  O(n) time and O(n) space
	  */
	 /**
	     * Only height is smaller do update happens
	     * Stack for indices
	     * add a zero height into the group
	     */
	 public int largestRectangleArea444(int[] height) {
	        if (height == null || height.length == 0) return 0;
	        height = Arrays.copyOf(height, height.length + 1); // add a zero
	        int max = 0;
	        Stack<Integer> s = new Stack<Integer>(); // store indices
	        for (int i = 0; i < height.length; i++) {
	            while (!s.isEmpty() && height[i] < height[s.peek()]) { // update when current height is smaller
	                int h = height[s.pop()];
	                int w = (s.isEmpty() ? i : i - s.peek() - 1);
	                max = Math.max(max, h * w);
	            }
	            s.push(i); // push index into stack
	        }
	        return max;
	  }
}
