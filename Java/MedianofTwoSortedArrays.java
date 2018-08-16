/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log(m+n)).

You may assume nums1 and nums2 cannot be both empty.

 

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 */
public class MedianofTwoSortedArrays {
	/*
	 approach: merge two arrays into one, depending on len is odd or even, return the median accordingly
	 time: O(m + n)  space: O(m + n)
	 */
	public double findMedianSortedArrays0(int[] nums1, int[] nums2) {
		int[] nums;
		int m = nums1.length;
		int n = nums2.length;
		nums = new int[n + m];
		
		if(m == 0) { // nums1 == null
			if(n % 2 == 0) // len of nums2 is even
				return (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0;
			else //odd
				return nums2[n / 2];
		}
		if(n == 0) { //nums2 == null
			if(m % 2 == 0) //even
				return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
			else //odd
				return nums1[m / 2];
		}
		
		// merge two arrays into one
		int count = 0;
		int i = 0, j = 0; // index for nums1 and nums2
		while(count < (m + n)){
			if(i == m) { // nums1 is done
				while(j < n)
					nums[count++] = nums2[j++];
				break;
			}
			if(j == n) { //nums2 is done
				while(i < m)
					nums[count++] = nums1[i++];
				break;
			}
			if(nums1[i] < nums2[j]) {
				nums[count++] = nums1[i++];
			}
			else
				nums[count++] = nums2[j++];
		}
		
		// get median depending on len is odd or even
		if(count % 2 == 0)
			return (nums[count / 2 - 1] + nums[count / 2]) / 2.0;
		else
			return nums[count / 2];
	}
	
	/*
	 approach: recursive solution with binary search
	ʱ�临�Ӷȣ�ÿ����һ��ѭ�������Ǿͼ��� k / 2 ��Ԫ�أ�����ʱ�临�Ӷ��� O��log��k�������� k = ��m + n��/ 2 ���������յĸ���Ҳ���� O��log��m + n������
	�ռ临�Ӷȣ���Ȼ�����õ��˵ݹ飬���ǿ��Կ�������ݹ�����β�ݹ飬���Ա���������Ҫ��ͣ�ض�ջ�����Կռ临�Ӷ�Ϊ O��1����
	http://windliang.cc/2018/07/18/leetCode-4-Median-of-Two-Sorted-Arrays/
	 */
	public double findMedianSortedArrays333(int[] nums1, int[] nums2) {
	    int n = nums1.length;
	    int m = nums2.length;
	    int left = (n + m + 1) / 2;
	    int right = (n + m + 2) / 2;
	    //combine odd and even to one return, if odd it will calculate the same k twice
	    return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;  
	}
	    
		// get Kth element in two ascending arrays from start index to end index
	    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
	        int len1 = end1 - start1 + 1;  // len of nums1
	        int len2 = end2 - start2 + 1; // len of nums2
	        //let len1 is always smaller so that if one array is empty, it should only be nums1
	        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
	        if (len1 == 0) return nums2[start2 + k - 1];

	        if (k == 1) return Math.min(nums1[start1], nums2[start2]);  // end condition

	        int i = start1 + Math.min(len1, k / 2) - 1; // get index of k/2th element in nums1, if k/2 > len, get the last element in num1 
	        int j = start2 + Math.min(len2, k / 2) - 1; // get index of k/2th element in nums2

	        if (nums1[i] > nums2[j]) { // exclude 1th to k/2th elements in nums2
	            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1)); // j - start2 + 1: the number of element excluded, then we need to find (k - n)th element
	        }
	        else {
	            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
	        }
	    }
	
	
	/*
	 Approach: In statistics, the median is used for: Dividing a set into two equal length subsets, that one subset is always greater than the other.
	 (m + n) is even: i + j = m - i + n - j ===> j=(m+n)/2-i ===> if max(A[i-1],B[j-1])<=min(A[i],B[j]) ===> so median=(max(A[i-1],B[j-1]+min(A[i],B[j]))/2
	 (m + n) is odd: i + j = m - i + n - j + 1 ===> j=(m+n+1)/2-i ===> if max(A[i-1],B[j-1]<=min(A[i],B[j]��===>so median=max(A[i-1],B[j-1]) 
	 ==> so no matter (m + n) is odd or even, j=(m+n+1)/2-i, since for int: (even+1)/2=even/2
	 since A[i] > A[i-1], B[j] > B[j-1], so need to ensure B[j-1] <= A[i] and A[i-1] <= B[j]
	 
	  time: O(log(min(m,n))) since we do binary search on smaller array
	 */
	public double findMedianSortedArrays(int[] A, int[] B) {
		int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n , or return findMedianSortedArrays(B,A);
            int[] temp = A; 
            A = B; 
            B = temp;
            int tmp = m; 
            m = n; 
            n = tmp;
        }
        int iMin = 0, iMax = m;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2; //initialize as mid, and then /2,...
            int j = (m + n + 1) / 2 - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small, increase i
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big, decrease i
            }
            else { // i is perfect, deal with boundary
                int maxLeft = 0;
                if (i == 0) 
                	maxLeft = B[j-1];
                else if (j == 0) 
                	maxLeft = A[i-1];
                else 
                	maxLeft = Math.max(A[i-1], B[j-1]);
                if ((m + n) % 2 == 1) 
                	return maxLeft; 

                int minRight = 0;
                if (i == m) 
                	minRight = B[j];
                else if (j == n) 
                	minRight = A[i];
                else 
                	minRight = Math.min(B[j], A[i]); 

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
