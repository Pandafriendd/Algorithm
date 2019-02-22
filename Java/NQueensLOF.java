/* 
N queens
Get all valid ways of putting N Queens on an N * N chessboard so that no two Queens threaten each other.

Assumptions
N > 0

Return
A list of ways of putting the N Queens
Each way is represented by a list of the Queen's y index for x indices of 0 to (N - 1)

Example
N = 4, there are two ways of putting 4 queens:
[1, 3, 0, 2] --> the Queen on the first row is at y index 1, 
				 the Queen on the second row is at y index 3, 
				 the Queen on the third row is at y index 0
				 the Queen on the fourth row is at y index 2.
[2, 0, 3, 1] --> the Queen on the first row is at y index 2, 
				 the Queen on the second row is at y index 0, 
				 the Queen on the third row is at y index 3 
				 the Queen on the fourth row is at y index 1.
*/

public List<List<Integer>> nqueens(int n) {
	List<List<Integer>> res = new ArrayList<>();
	int[] queensPosition = new int[n];
	nQueensHelper(res, queensPosition, 0, n);

	return res;
}

private void nQueensHelper(List<List<Integer>> res, int[] queensPosition, int currentRow, int n) {
	// base case: last row is done, no row left
	if (currentRow == n) {
		res.add(Arrays.asList(queensPosition));
		return;
	}

	// recursive rule: if the col is valid, go to next row (currentRow + 1)
	for (int col = 0; col < n; col++) {
		if (isValid(queensPosition, col, currentRow)) {
			queensPosition[currentRow] = col;
			nQueensHelper(res, queensPosition, currentRow + 1, n);
		}
	}
}

// check if col, diagonals are valid. Since we put queens by row, don't need to check row
// takes O(n) to check is valid
private boolean isValid(int[] queensPosition, int col, int currentRow) {
	for (int i = 0; i < queensPosition.length; i++) {
		if (queensPosition[i] = col || col + currentRow == queensPosition[i] + i || col - currentRow == queensPosition[i] - i) {
			return false;
		}
	}
	return true;
} 
