/*
 Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). 
 n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
 Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container and n is at least 2.
 */



public class ContainerWithMostWater {
	
	//approach 1: brute force time:(n^2), space:(1)
	public int maxArea(int[] height) {
		int maxarea = 0;
		if(height == null || height.length == 0)
			return maxarea;
		
		for(int i = 0; i < height.length; i++) {
			for(int j = i + 1; j < height.length; j++) {
				maxarea = Math.max(maxarea, Math.min(height[i], height[j]) * (j - i));
			}
		}
		
		return maxarea;
	}
	
	
	/*
	 * approach 2: two pointers
	 * The intuition behind this approach is that the area formed between the lines will always be limited by the height of the shorter line. 
	 * Further, the farther the lines, the more will be the area obtained.
	 * Initially we consider the area constituting the exterior most lines. 
	 * Now, to maximize the area, we need to consider the area between the lines of larger lengths. 
	 * If we try to move the pointer at the longer line inwards, we won't gain any increase in area, since it is limited by the shorter line. 
	 * But moving the shorter line's pointer could turn out to be beneficial, as per the same argument, despite the reduction in the width. 
	 * This is done since a relatively longer line obtained by moving the shorter line's pointer might overcome the reduction in area caused by the width reduction.
	 * 
	 * time: O(n)  space; O(1)
	 * 
	 * proof:
	 * 对于（1，6）的情况，如果位置1的长度小于位置6的长度，那么对于位置1而言，它的最大面积已经求出来了，因此右边的线如何移动是无所谓的。
	 * 也就是说（1，2）（1，3）（1，4）（1，5）的情况都可以不用考虑。
	 * 下一步就移动左边的线到位置2。如果位置2的长度大于位置6，那么位置6的最大面积已经求出来了。同理左边的线再怎么移动都无所谓。于是再下一步就移动右边的线到位置5。以此类推。
	 */
	public int maxArea2(int[] height) {
		int maxarea = 0;
		int left = 0, right = height.length - 1;
		while(left < right) {
			maxarea = Math.max(maxarea, (right - left) * Math.min(height[left], height[right]));
			if(height[left] < height[right])
				left++;
			else
				right--;
		}
		return maxarea;
	}
}
