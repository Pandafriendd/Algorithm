
public class atothepowerofb {
	static int c1 = 0;
	static int c2 = 0;
	
	public static long power0(int a, int b) {
		c1++;
		
	    if(b == 0) {  // base case
	      return 1;
	    }
	    if(a == 0) {  // corner case
	      return 0;
	    }
	    
	    long half = power0(a, b / 2);   // !!!  we do the cache here
	    if(b % 2 == 0) {  // b is even number
	      return half * half;
	    }
	    else {  // b is odd number
	      return half * half * a;
	    }
	  }
	
	
	
	// inefficient, since we didn't cache
	public static long power(int a, int b) {
		c2++;
	    
	    if(b == 0) {  // base case
	      return 1;
	    }
	    if(b % 2 == 0) {  // b is even number
	      int newB = b / 2;
	      return power(a, newB) * power(a, newB);
	    }
	    else {  // b is odd number
	      int newB = b / 2;
	      return power(a, newB) * power(a, newB) * a;
	    }
	    
	  }
	
	public static void main(String[] args) {
		power0(2, 5);
		power(2, 5);
		System.out.println(c1);
		System.out.println(c2);
	}
}
