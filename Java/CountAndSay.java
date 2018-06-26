import java.util.Arrays;

public class CountAndSay {
	public String Solution(int n) {
		if(n == 1)
			return "1";
		String pre = Solution(n - 1);
		StringBuilder res = new StringBuilder();
		int count = 1;
		for(int i = 1; i <= pre.length(); i++) {
			if(i == pre.length() || pre.charAt(i) != pre.charAt(i - 1)) {
				res.append("" + count).append(pre.charAt(i - 1));
				count = 1;
			}
			else
				count++;
		}
		return res.toString();
	}
	
	
	private void swap(int[] nums, int index1, int index2) {
		int temp = nums[index1];
		nums[index1] = nums[index2];
		nums[index2] = temp;
	}
	
	private void reverse(int[] nums, int index) {
		int tail = nums.length - 1;
		while(index < tail) {
			swap(nums, index, tail);
			index++;
			tail--;
		}
	}
	
	
	public static void main(String[] args) {
		CountAndSay c = new CountAndSay();
		String s = c.Solution(3);
		System.out.println(s);
		int[] a = {1,2,3,4,5};
		System.out.println(Arrays.toString(a));
		c.reverse(a, 0);
		System.out.println(Arrays.toString(a));
	}
}
