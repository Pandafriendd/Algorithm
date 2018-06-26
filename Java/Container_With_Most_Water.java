class Solution {
    public int maxArea(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int maxArea = 0;
        int i = 0, j = height.length - 1;
        while(i < j){
            int h = Math.min(height[i], height[j]);
            maxArea = Math.max(maxArea, h * (j - i));
            while(height[i] <= h && i < j) //i < j is needed when height = [1,1]
                i++;
            while(height[j] <= h && i < j)
                j--;
        }
        return maxArea;
}