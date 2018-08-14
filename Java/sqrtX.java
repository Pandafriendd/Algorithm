import java.util.*;
//import java.lang.*;


public class sqrtX {


	//using binary search to find the answer
	public int mysqrt(int x){
		//int high = x;
		int low = 1;  // !
		int high = x / 2;  // reduce high to x/2 because a square root is always less than or equal to x/2
		if(x < 2)// cannot be x <= 0, otherwise it will lead to mid = 0 and throw /0 exception
			return x;
		while( high >= low){
			// int mid = (low + high) / 2; // maybe overflow
			int mid = low + (high - low) / 2;
			//if(x >= mid * mid && x < (mid + 1) * (mid + 1))  //when x>sqrt(Integer.MAX_VALUE), overflow
			if(x / mid >= mid && x / (mid + 1) < (mid + 1))
				return mid;
			else if(x / mid < mid)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return -1;
	}
	
	
	// using Newton's iterative method	
	public int mysqrt2(int x){
		long res = x;
		while(res * res > x)
			res = (res + x / res) / 2;
		return (int) res;
	}
	
	public static void main(String args[]){
		sqrtX s = new sqrtX();
//		System.out.println(s.mysqrt(0));
//		System.out.println(s.mysqrt2(0));
//		System.out.println(s.mysqrt(169));
//		System.out.println(s.mysqrt2(169));
		
//		System.out.println(Integer.MAX_VALUE);
//		System.out.println(Integer.MIN_VALUE);
		
		long begin=System.nanoTime();
		System.out.println(s.mysqrt(Integer.MAX_VALUE));
		System.out.println((System.nanoTime()-begin));
		
		long begin2=System.nanoTime();
		System.out.println(s.mysqrt2(Integer.MAX_VALUE));
		System.out.println((System.nanoTime()-begin2));
		
		System.out.println(1/2);
	}
}
