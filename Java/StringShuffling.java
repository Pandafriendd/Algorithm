import java.util.Arrays;

/*
	ABCD1234   ->   A1B2C3D4
	
	assume the len of input is even number
	follow up: what if is odd?  // see if/else below
	Input: new int[]{1,2,3,4,5} expected [1,3,2,4,5]
*/

public class StringShuffling {
	
	public static char[] reorder(char[] array) {
	    if (array.length % 2 == 0) {
	      convert(array, 0, array.length - 1);
	    }
	    else {
	      convert(array, 0, array.length - 2);
	    }
	    
	    return array;
	}

	public static void convert(char[] array, int left, int right) {
		// base case
		if (right - left <= 1) {  // if only two elements or one element or no elememnt
			return;
		}

		int size = right - left + 1;
		int mid = left + size / 2;
		int lmid = left + size / 4;
		int rmid = mid + size / 4;
		
		// cd12 -> 12cd
		reverse(array, lmid, mid - 1);
		reverse(array, mid, rmid - 1);
		reverse(array, lmid, rmid - 1);

		convert(array, left, left + (lmid - left) * 2 - 1);   // !!!! left +
		convert(array, left + (lmid - left) * 2, right);
	}

	private static void reverse(char[] array, int l, int r) {
		if (array == null || array.length == 0) {
			return;
		}

		while (l < r) {
			swap(array, l++, r--);
		}
	}

	private static void swap(char[] array, int i, int j) {
		char t = array[i];
		array[i] = array[j];
		array[j] = t;
	}
	
	public static void main(String[] args) {
		String s1 = "1234";
		//String s2 = "ABCD1234";
		char[] a = s1.toCharArray();
		//char[] b = s2.toCharArray();
		convert(a, 0, a.length - 1);
		//convert(b, 0, b.length - 1);
		System.out.println(Arrays.toString(a));
		//System.out.println(Arrays.toString(b));
	}
}
