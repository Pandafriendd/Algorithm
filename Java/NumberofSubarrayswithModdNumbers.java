/*
Given an array of n elements and an integer m, we need to write a program to find the number of contiguous subarrays in the array which contains exactly m odd numbers.

Examples :

Input : arr = {2, 5, 6, 9},  m = 2 
Output : 2
Explanation: subarrays are [2, 5, 6, 9] and [5, 6, 9]

Input : arr = {2, 2, 5, 6, 9, 2, 11},  m = 2
Output : 8
Explanation: subarrays are [2, 2, 5, 6, 9], [2, 5, 6, 9], [5, 6, 9], [2, 2, 5, 6, 9, 2], [2, 5, 6, 9, 2], [5, 6, 9, 2], [6, 9, 2, 11] and [9, 2, 11]
 */
public class NumberofSubarrayswithModdNumbers {
	/*
	 * brute force O(n^2)
	 */
	static public int countSubarrays(int[] a, int m) {
		int count = 0;
		
		// traverse all possible subarrays
		for(int start = 0; start < a.length; start++) {
			int odd = 0;
			for(int end = start; end < a.length; end++) {
				if(a[end] % 2 != 0)
					odd++;
				if(odd == m)
					count++;
			}
		}
		
		return count;
	}
	
	/*
	Using prefix[] array: Prefix[i] stores the number of prefixes which has ¡®i¡¯ odd numbers in it.
	We increase the count of odd numbers if the array element is an odd one. 
	When the count of odd numbers exceeds or is equal to m, add the number of prefixes which has ¡°(odd-m)¡± numbers to the answer. 
	At every step odd>=m, we calculate the number of subarrays formed till a particular index with the help of prefix array.
	prefix[odd-m] provides us with the number of prefixes which has ¡°odd-m¡± odd numbers, which is added to the count to get the number of subarrays till the index.
	
	time: O(n)
	 */
	static public int countSubarrays2(int[] a, int m) {
		int count = 0;
		int[] prefix = new int[a.length];
		int odd = 0;
		
		// traverse array
		for(int i = 0; i < a.length; i++) {
			prefix[odd]++;
			
			// if odd
			if((a[i] & 1) == 1)
				odd++;
			
			// when number of odd elements >= m
			if(odd >= m)
				count += prefix[odd - m];
		}
		
		return count;
	}
	
	// Driver code
    public static void main (String[] args)
    {
        int a[] = {2,1,3,5};
        int m = 2;
         
        System.out.println(countSubarrays2(a, m));
    }
}
