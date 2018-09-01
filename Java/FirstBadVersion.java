/*
You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

Example:

Given n = 5, and version = 4 is the first bad version.

call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true

Then 4 is the first bad version. 

 */
public class FirstBadVersion {
	
	/*
binary search
Here is a helpful tip to quickly prove the correctness of your binary search algorithm during an interview. 
We just need to test an input of size 2. 
Check if it reduces the search space to a single element (which must be the answer) for both of the scenarios above. If not, your algorithm will never terminate.
	 */
	public int firstBadVersion(int n) {
	    int left = 1;
	    int right = n;
	    while (left < right) {
	        int mid = left + (right - left) / 2;
	        if (isBadVersion(mid)) { // mid is bad means first one might be at the left or is itseft
	            right = mid;
	        } else { // mid is good, can only at the right
	            left = mid + 1;
	        }
	    }
	    return left;
	}
	
	
	
	//Linear Scan
	public int firstBadVersion11(int n) {
	    for (int i = 1; i < n; i++) {
	        if (isBadVersion(i)) {
	            return i;
	        }
	    }
	    return n;
	}
}
