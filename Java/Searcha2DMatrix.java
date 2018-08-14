/*
 Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Example 1:

Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
 */
public class Searcha2DMatrix {
	
	// binary search to locate row, and then binary search in that row
	// time: O(logm) + O(logn) for m x n matrix
	public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return false;
		int low = 0;
		int high = matrix.length - 1;
		while(low <= high) {
			int mid = low + (high - low) / 2;
			if(matrix[mid][0] == target)
				return true;
			else if(matrix[mid][0] > target)
				high = mid - 1;
			else
				low = mid + 1;
		}
		
		// row index is low - 1
		if(low - 1 < 0 || low - 1 >= matrix.length) // !!
			return false;
		int row = low - 1;
		low = 0;
		high = matrix[row].length - 1;
		while(low <= high) {
			int mid = low + (high - low) / 2;
			if(matrix[row][mid] == target)
				return true;
			else if(matrix[row][mid] > target)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return false;
	}
	
	
	  /**
     * n * m matrix convert to an array => matrix[x][y] => a[x * m + y]
     * an array convert to n * m matrix => a[x] =>matrix[x / m][x % m];
     * 
     * disadvantage: 1. m * n may overflow 2. / and % are expensive
     */
    public boolean searchMatrixBest(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) 
        	return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;
        int mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (matrix[mid / m][mid % m] == target) return true;
            else if (matrix[mid / m][mid % m] > target) right = mid - 1;
            else left = mid + 1;
        }
        return false;
    }
}
