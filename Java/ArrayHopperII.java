/*
Array Hopper II
Determine the minimum number of jumps you need to reach the end of array. If you can not reach the end of the array, return -1.
eg. {3, 3, 1, 0, 4}, the minimum jumps needed is 2 (jump to index 1 then to the end of array)
DP approach:
M[i] represents the minimus number of jumps needed to reach the end of array from index i
[3, 3, 1, 0, 4]
[2, 1,-1,-1, 0]
base rule: M[length - 1] = 0
induction rule: M[i] = min(M[i + j]) + 1 (where 1 <= j <= array[i]), if M[j] != -1
					   -1, if all M[j] are -1
*/
public int minJump(int[] array) {
	int[] M = new int[array.length];
	M[array.length - 1] = 0;
	for (int i = M.length - 2; i >= 0; i--) {
		int min = Integer.MAX_VALUE;
		for (int j = 1; j <= array[i]; j++) {
			if (M[i + j] != -1) {  // !!!
				min = Math.min(min, M[i + j]);  // !!
			}
			if (i + j == M.length - 1) {  // !!
				break;
			}
		}
		M[i] = min == Integer.MAX_VALUE ? -1 : min + 1;
	}
	return M[0];
}

