public class Solution {
  public static int[] dedup(int[] input) {
   if (input.length <= 1 || input == null) {
			return input;
   }
		
		int slow = 1;
		int fast = 1;
		
		while (fast < input.length) {
			if (input[fast] != input[slow - 1]) {  // keep it (copy)
				input[slow++] = input[fast++];
			} else {  // ignore it
				fast++;
			}
		}
		
		return reSize(input, slow);
	}
	
	private static int[] reSize(int[] input, int slow) {
		int[] output = new int[slow];
		for (int i = 0; i < slow; i++) {
			output[i] = input[i];
		}
		
		return output;
	}
}
