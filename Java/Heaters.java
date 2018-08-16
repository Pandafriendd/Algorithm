import java.util.Arrays;

/*
 Note:
Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
As long as a house is in the heaters' warm radius range, it can be warmed.
All the heaters follow your radius standard and the warm radius will the same.

Example 1:
Input: [1,2,3],[2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.

Example 2:
Input: [1,2,3,4],[1,4]
Output: 1
Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
 */
public class Heaters {
	/*
The idea is to leverage decent Arrays.binarySearch() function provided by Java.

1. For each house, find its position between those heaters (thus we need the heaters array to be sorted).
2. Calculate the distances between this house and left heater and right heater, get a MIN value of those two values. Corner cases are there is no left or right heater.
3. Get MAX value among distances in step 2. It's the answer.

Time complexity: max(O(nlogn), O(mlogn)) - m is the length of houses, n is the length of heaters.

Index >=0 means the element was found in the heaters array. In this case, a heater was found at the same position where a house is located. 
Value of index is the position of house/heater.
If heater and house are both at the same position, the distance between them is zero and no need to check for this pair of (house, heater). 
The distance cannot be less than zero.
So skip the heater if it's at the same position.
	 */
	public int findRadius(int[] houses, int[] heaters) {
		Arrays.sort(heaters);
		int result = Integer.MIN_VALUE;
		
		for(int house : houses) {
			int index = Arrays.binarySearch(heaters, house);
			if(index < 0) // if didn't find
				index = -(index + 1); 
			int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE; // dist to left heater if any
			int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;  // dist to right heater if any
			System.out.println("house: " + house + " d1: " + dist1 + " d2: " + dist2);
			
			result = Math.max(result, Math.min(dist1, dist2));  // min: closer heater will cover this house, this is only thing we care. Finally, we need to find out the max value of all these min values, which means the maximum distance of a house to a heater.
		}
		return result;
	}
	
	/*
approach 2:
The difficulty of this problem is to understand Math.abs(heaters[i] - house) >= Math.abs(heaters[i + 1] - house
Let's us understand this with a example:
houses: 1, 2, 3, 4
heaters: 1, 4
For house 1, heater 1 is closer to it than heater 4, so we don't move i to i + 1.
For house 2, it is same. heater 1 is closer.
For house 3, it is clear that heater 1 no longer closer, so we move i to i + 1.
For house 4, continue....

The idea here is we move the heater to rightward in case it is closer to the given house.
	 */
	 public int findRadius2(int[] houses, int[] heaters) {
	      Arrays.sort(houses);
	      Arrays.sort(heaters);
	      
	      int i = 0, res = 0;  // i is index in heaters array
	      for(int house: houses){
	        while(i + 1 < heaters.length && Math.abs(heaters[i] - house) >= Math.abs(heaters[i + 1] - house)){ // if right heater is closer
	          i++;    
	        }
	        res = Math.max(res, Math.abs(heaters[i] - house));
	      }
	      return res;
	    }
	
	public static void main(String[] args) {
		Heaters h = new Heaters();
		System.out.println(h.findRadius(new int[] {1,2,3,4,5,6}, new int[] {2000}));
	}
}
