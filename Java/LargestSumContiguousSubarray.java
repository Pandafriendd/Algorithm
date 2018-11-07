// MaxSubarraySum

// method1: brute force
public int maxSubarraySum(int[] nums) {
	int max = Integer.MIN_VALUE;
	for (int start = 0; start < nums.length; start++) {
		for (int end = start; end < nums.length; end++) {
			int sum = 0;
			for (int i = start; i <= end; i++) {
				sum += nums[i];
			}
			max = Math.max(max, sum);
		}
	}

	return max;
}


// method2: accumulated sum
public int maxSubarraySum2(int[] nums) {
	int accSum[] = new int[nums.length];
	int accS = 0;
	for (int i = 0; i < accSum.length; i++) {
		accS += nums[i];
		accSum[i] = accS;
	}

	int maxSum = accSum[0];
	int minByFar = accSum[0] < 0 ? accSum[0] : 0;
	for (int i = 1; i < accSum.length; i++) {
		maxSum = Math.max(maxSum, accSum[i] - minByFar);
		minByFar = Math.min(minByFar, accSum[i]);
	}

	return maxSum;
}
