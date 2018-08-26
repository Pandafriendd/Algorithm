/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */

import java.util.*;
public class LongestConsecutiveSequence {
	
	
	// brute force 
	// time: O(n^3)The outer loop runs exactly n times, and because currentNum increments by 1 during each iteration of the while loop, it runs in O(n) time. 
	// Then, on each iteration of the while loop, an O(n) lookup in the array is performed. Therefore, this brute force algorithm is really three nested O(n) loops, which compound multiplicatively to a cubic runtime.
	public int longestConsecutive(int[] nums) {
		int longestStreak = 0;
		
		for(int num : nums) {
			int currentNum = num;
			int currentStreak = 1;
			
			while(arrayContains(nums, currentNum + 1)) {
				currentNum++;
				currentStreak++;
			}
			longestStreak = Math.max(longestStreak, currentStreak);
		}
		
		return longestStreak;
	}
	
	// O(n)
	private boolean arrayContains(int[] nums, int target) {
		for(int i = 0; i < nums.length; i++) {
			if(nums[i] == target)
				return true;
		}
		return false;
	}
	
	
	/*
	 app2: Sorting
	 Before we do anything, we check for the base case input of the empty array. 
	 The longest sequence in an empty array is, of course, 0, so we can simply return that. 
	 For all other cases, we sort nums and consider each number after the first (because we need to compare each number to its previous number). 
	 If the current number and the previous are equal, then our current sequence is neither extended nor broken, so we simply move on to the next number. 
	 If they are unequal, then we must check whether the current number extends the sequence (i.e. nums[i] == nums[i-1] + 1). 
	 If it does, then we add to our current count and continue. 
	 Otherwise, the sequence is broken, so we record our current sequence and reset it to 1 (to include the number that broke the sequence). 
	 It is possible that the last element of nums is part of the longest sequence, so we return the maximum of the current sequence and the longest one.
	 
	 time: O(nlogn)
	 Space: O(1). But if we are not allowed to modify the input array, we must spend linear space to store a sorted copy, so O(n) in this case
	 */
	public int longestConsecutive2(int[] nums) {
		if(nums.length == 0 || nums == null)
			return 0;
		
		Arrays.sort(nums);
		
		int longestStreak = 1;
		int currentStreak = 1;
		
		for(int i = 1; i < nums.length; i++) {
			if(nums[i] != nums[i - 1]) {
				if(nums[i] == nums[i - 1] + 1) {
					currentStreak++;
				}
				else {
					longestStreak = Math.max(longestStreak, currentStreak);
					currentStreak = 1;
				}	
			}
		}
		// It is possible that the last element of nums is part of the longest sequence, so we return the maximum of the current sequence and the longest one.
		return Math.max(longestStreak, currentStreak);
	}
	
	/*
	 app3: HashSet and Intelligent Sequence Building
	 This optimized algorithm contains only two changes from the brute force approach: 
	 1. the numbers are stored in a HashSet (or Set, in Python) to allow O(1)O(1) lookups, 
	 2. we only attempt to build sequences from numbers that are not already part of a longer sequence. 
	 This is accomplished by first ensuring that the number that would immediately precede the current number in a sequence is not present, 
	 as that number would necessarily be part of a longer sequence.
	 
	 Time complexity : O(n). Although the time complexity appears to be quadratic due to the while loop nested within the for loop, 
	 closer inspection reveals it to be linear. 
	 Because the while loop is reached only when currentNum marks the beginning of a sequence (i.e. currentNum-1 is not present in nums), 
	 the while loop can only run for n iterations throughout the entire runtime of the algorithm. This means that despite looking like O(n^2) complexity, 
	 the nested loops actually run in O(n + n)=O(n) time. All other computations occur in constant time, so the overall runtime is linear.
	 
	 eg: 
	 testcase: [100,99, 98, ..., 1]: 
	 Since if (!numsSet.contains(num-1)) will be false for the n-1 items until 1. Then on the last item (1) if statement will be true and it will go in to the while loop. 
	 So it will be n+n the worst case.
	 */
	public int longestConsecutive3(int[] nums) {
		Set<Integer> numsSet = new HashSet<>();
		
		for(int n : nums) {
			numsSet.add(n);
		}
		
		int longestStreak = 0;
		for(int num : nums) {
			if(!numsSet.contains(num - 1)) { // should be the beginning of a sequence
				int currentNum = num;
				int currentStreak = 1;
				
				while(numsSet.contains(currentNum + 1)) {
					currentNum++;
					currentStreak++;
				}
				longestStreak = Math.max(longestStreak, currentStreak);
			}
		}
		
		return longestStreak;
	}

    /**
     * Use a map to store ranges
     * Get lower bound with smaller value
     * Get upper bound with larger value
     * Update max length with new bound
     * Put all possible ranges into map including num[i] ~ num[i], low ~ upp, upp ~ low
     */
    public int longestConsecutive4(int[] num) {
        if (num == null || num.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int maxLen = 0;
        
        for(int i = 0; i < num.length; i++) {
            if (map.containsKey(num[i])) 
            	continue; // skip duplicates
            int low = num[i]; // initialize ranges
            int upp = num[i];
            if (map.containsKey(num[i] - 1)) 
            	low = map.get(num[i] - 1);
            if (map.containsKey(num[i] + 1)) 
            	upp = map.get(num[i] + 1);
            maxLen = Math.max(maxLen, upp - low + 1); // update length
            // put possible ranges into map for next iteration
            map.put(num[i], num[i]);
            map.put(low, upp);
            map.put(upp, low);
        }
        
        return maxLen;
    }
    
    public static void main(String[] args) {
    	LongestConsecutiveSequence l = new LongestConsecutiveSequence();
        int[] a = {100, 4, 200, 1, 3, 2, 4, 5};
        System.out.println(l.longestConsecutive2(a));
    }
	
}
