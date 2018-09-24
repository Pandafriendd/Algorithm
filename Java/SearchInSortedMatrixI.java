
public class SearchInSortedMatrixI {
	public int[] search(int[][] matrix, int target) {
	    // Write your solution here

	    int[] res = new int[2];

	    res = binarySearch(matrix, target);

	    return res;
	}

	private int[] binarySearch(int[][] matrix, int target) {
		int n = matrix.length;
		int m = matrix[0].length;
		int left = 0;
		int right = n * m - 1;

		while(left <= right) {
			int mid = left + (right - left) / 2;
			int row = mid / m;
			int col = mid % m;
			if(matrix[row][col] == target) {
				return new int[] {row, col};
			}
			else if(matrix[row][col] > target) {
				right = mid - 1;
			}
			else {
				left = mid + 1;
			}
		}

		return new int[] {-1, -1};
	}
}
