/* 
N queens
Get all valid ways of putting N Queens on an N * N chessboard so that no two Queens threaten each other

Assumptions
N > 0

Return
A list of ways of putting the N Queens
Each way is represented by a list of the Queen's y index for x indices of 0 to (N - 1)

Example
N = 4, there are two ways of putting 4 queens:
[1, 3, 0, 2] --> the Queen on the first row is at y index 1, 
				 the Queen on the second ro
				 
				 w is at y index 3, 
				 the Queen on the third row is at y index 0
				 the Queen on the fourth row is at y index 2.
[2, 0, 3, 1] --> the Queen on the first row is at y index 2, 
				 the Queen on the second row is at y index 0, 
				 the Queen on the third row is at y index 3 
				 the Queen on the fourth row is at y index 1.
*/

// time: O(n! * n) space: O(n)
public List<List<Integer>> nqueens(int n) {
	List<List<Integer>> res = new ArrayList<>();
	List<Integer> queensPosition = new ArrayList<>();
	nQueensHelper(res, queensPosition, 0, n);

	return res;
}

private void nQueensHelper(List<List<Integer>> res, List<Integer> queensPosition, int currentRow, int n) {
	// base case: last row is done, no row left
	if (currentRow == n) {
		res.add(new ArrayList<>(queensPosition));
		return;
	}

	// recursive rule: if the col is valid, go to next row (currentRow + 1)
	for (int col = 0; col < n; col++) {
		if (isValid(queensPosition, col, currentRow)) {
			queensPosition.add(col);
			nQueensHelper(res, queensPosition, currentRow + 1, n);
			queensPosition.remove(queensPosition.size() - 1);
		}
	}
}

// check if col, diagonals are valid. Since we put queens by row, don't need to check row
// takes O(n) to check is valid
private boolean isValid(List<Integer> queensPosition, int col, int currentRow) {
	for (int i = 0; i < queensPosition.size(); i++) {
		if (queensPosition.get(i) == col || (col + currentRow == queensPosition.get(i) + i ) || (col - currentRow == queensPosition.get(i) - i)) {
			return false;
		}
	}
	return true;
}  
