/*
There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but different width. 
You want to draw a vertical line from the top to the bottom and cross the least bricks.
The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from left to right.

If your line go through the edge of a brick, then the brick is not considered as crossed. 
You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.

You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.
 */


import java.util.*;
public class BrickWall {
	public int leastBricks(List<List<Integer>> wall) {
        if(wall == null || wall.size() == 0) {
            return 0;
        }
        
        HashMap<Integer, Integer> map = new HashMap<>();  // sum ==> freq of this sum
        for(List<Integer> row : wall) {
            int sum = 0;
            for(int i = 0; i < row.size() - 1; i++) {
                sum = sum + row.get(i);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        
        int res = wall.size();  // cannot be  res = Integer.MAX_VALUE since given input [1][1][1] the set will be empty
        int height = wall.size();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {  // !! traverse hashmap
            res = Math.min(res, height - entry.getValue());  // !!!
        }
        
        return res;
    }
}
