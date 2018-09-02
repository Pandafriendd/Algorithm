import java.util.*;

public class testofUnknow {
	static public void main(String[] args) {
		/*
		test of ++i and i++, they are different!!! Same with what I leanred from hnu class.
		StringBuilder sb = new StringBuilder();
		String s = "ABCD";
		int i = 0;
		while(i < s.length() - 1) {
			System.out.print("before    " + i + "  ");
			sb.append(s.charAt(++i));
			System.out.println(sb.toString() + "    after" + i);
		}
		
		System.out.println(10 & 1 << 1);
		*/
		
		/*
		 * Judging even or not: 
		 * #1:
		 * if((x & 1) == 0) even
		 * else odd
		 * 
		 * #2
		 * if(x % 2 == 1) even
		 * else odd
		 */
		
		//test of String.valueOf(c) and c.toString();
		//char[] c = {'a', 'b', 'c', 'd'};	
		//System.out.println(c.toString());   //output [C@1175e2db !! primitive don't have an object
		//System.out.println(String.valueOf(c)); //output abcd
		
		//test of return value of Arrays.binarySearch()
		/*
		int[] a = {-999,5,6,7};
		System.out.println(Arrays.binarySearch(a, -1));
        */
		/*
		String s = "       3+5";
		s = s.replaceAll("\\s", ""); //clear the string first, it does not add any time complexity to the original function and therefore has little performance impact.
		System.out.println(s);
		*/
		HashSet<Integer> set = new HashSet<>();
		set.contains(1); 
	}
	
}
