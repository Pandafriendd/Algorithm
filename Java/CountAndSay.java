/*
 The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

Example 1:
Input: 1
Output: "1"

Example 2:
Input: 4
Output: "1211"
 */
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
