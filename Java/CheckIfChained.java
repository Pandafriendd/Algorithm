/*
two string s1, s2 can be chained if the last letter of s1 is identical to the first letter of s2
abc and cd can be chained
abc and dz cannot be chained
 */

public class CheckIfChained {
	/*
	 based on the permutation, time: O(n! * n), space: O(n)
	 */
	public boolean isChained(String[] arr) {
		if (arr == null || arr.length == 0) {
			return false;
		}
		boolean[] res = new boolean[] {false};
		findPermutation(arr, 0, res);
		return res[0];
	}
	
	private void findPermutation(String[] arr, int index, boolean[] res) {
		// base case
		if (index == arr.length) {
			if (checkIfChain(arr)) {
				res[0] = true;
			}
			return;
		}
		
		// recursion rules
		for (int i = index; i < arr.length; i++) {
			if (index == 0 || index > 0 && arr[index - 1].charAt(arr[index - 1].length() - 1) == arr[i].charAt(0)) {   // add restriction for improvement of efficiency
				swap(arr, index, i);
				findPermutation(arr, index + 1, res);
				swap(arr, index, i);
			}
		}
	}
	
	private void swap(String[] arr, int i, int j) {
		String t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}
	
	private boolean checkIfChain(String[] arr) {
		if (arr.length <= 1) {
			return false;
		}
		
		for (int i = 0; i <= arr.length - 2; i++) {
			if (arr[i].charAt(arr[i].length() - 1) != arr[i + 1].charAt(0)) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean checkIfCircular(String[] arr) {
		if (arr.length <= 1) {
			return false;
		}
		
		for (int i = 0; i <= arr.length - 1; i++) {
			if (arr[i].charAt(arr[i].length() - 1) != arr[(i + 1) % arr.length ].charAt(0)) {  // idea of circular array
				return false;
			}
		}
		
		return true;
	}
}
