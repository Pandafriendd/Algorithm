/*
 * Given a number n, print n-th Fibonacci Number. n from 0
 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, ...
 */
public class NthFibSeq {
	
	/*
	 * recursive
	 * Time Complexity: O(2^n). T(n) = T(n-1) + T(n-2) which is exponential. 
	 * Space: O(n) if we consider the function call stack size, otherwise O(1)
	 */
	public static int fib1(int n) {
		if(n == 0)
			return 0;
		if(n == 1)
			return 1;
		return fib1(n - 1) + fib1(n - 2);
	}
	
	/*
	 * dynamic programming1
	 * recursion + memoization
	 * Time Complexity: O(n)
	 * Extra Space: O(n) since call stack
	 */
	// global DP array
	Integer[] dp = new int[n + 1]; // meaningful index from 0 to n, default all elements as null
	dp[0] = 0;
	dp[1] = 1;
	public static int fibDP(int n) {
		if (dp[n] != null) {
			return dp[n];
		}
		return dp[n] = fibDP(n - 1) + fibDP(n - 2);
	}
	
	/*
	 * dynamic programming2
	 * We can avoid the repeated work by storing the Fibonacci numbers calculated so far.
	 * Time Complexity: O(n)
	 * Extra Space: O(n)
	 */
	public static int fib2(int n) {
		/* Declare an array to store Fibonacci numbers. */
	    int f[] = new int[n+2]; // 1 extra to handle case, n = 0
	    int i;
	      
	    /* 0th and 1st number of the series are 0 and 1*/
	    f[0] = 0;
	    f[1] = 1;
	     
	    for (i = 2; i <= n; i++)
	    {
	       /* Add the previous 2 numbers in the series
	         and store it */
	        f[i] = f[i-1] + f[i-2];
	    }
	      
	    return f[n];
	}
	
	/*
	 * We can optimize the space used in method 2 by storing the previous two numbers only because that is all we need to get the next Fibonacci number in series.
	 * Time Complexity: O(n) 
	 * Extra Space: O(1)
	 */
	 static int fib3(int n)
	    {
	        int a = 0, b = 1, temp;
	        if (n == 0)
	            return a;
	        for (int i = 2; i <= n; i++)
	        {
	            temp = a + b;
	            a = b;
	            b = temp;
	        }
	        return b;
	    }
	 
	 /*
	  * Using power of the matrix {{1,1},{1,0}} 
	  * This another O(n) which relies on the fact that if we n times multiply the matrix M = {{1,1},{1,0}} to itself (in other words calculate power(M, n )), 
	  * then we get the (n+1)th Fibonacci number as the element at row and column (0, 0) in the resultant matrix.
	  * Time Complexity: O(n)
	  * Extra Space: O(1)
	  */
	 static int fib4(int n)
	{
	    int F[][] = new int[][]{{1,1},{1,0}};
	    if (n == 0)
	        return 0;
	    power(F, n-1);
	      
	    return F[0][0];
	    }
	      
	 static void multiply(int F[][], int M[][])
	  {
	    int x =  F[0][0]*M[0][0] + F[0][1]*M[1][0];
	    int y =  F[0][0]*M[0][1] + F[0][1]*M[1][1];
	    int z =  F[1][0]*M[0][0] + F[1][1]*M[1][0];
	    int w =  F[1][0]*M[0][1] + F[1][1]*M[1][1];
	     
	    F[0][0] = x;
	    F[0][1] = y;
	    F[1][0] = z;
	    F[1][1] = w;
	  }
	      
	 /* Helper function that calculates F[][] raise to the power n and puts the result in F[][]
	    Note that this function is designed only for fib() and won't work as general power function */
	static void power(int F[][], int n)
	{
	    int i;
	    int M[][] = new int[][]{{1,1},{1,0}};
	     
	    // n - 1 times multiply the matrix to {{1,0},{0,1}}
	    for (i = 2; i <= n; i++)
	        multiply(F, M);
	}
	  
	  /*
	   * Optimized power of matrix {{1,1},{1,0}}
	   * can be optimized to work in O(Logn) time complexity. We can do recursive multiplication to get power(M, n) in the prevous method 
	   * Time Complexity: O(Logn) 
	   * Extra Space: O(Logn) if we consider the function call stack size, otherwise O(1).
	   */
    static int fib5(int n)
    {
    int F[][] = new int[][]{{1,1},{1,0}};
    if (n == 0)
        return 0;
	    
    power(F, n-1);
      
    return F[0][0];
    }
      
    static void multiply2(int F[][], int M[][])
    {
    int x =  F[0][0]*M[0][0] + F[0][1]*M[1][0];
    int y =  F[0][0]*M[0][1] + F[0][1]*M[1][1];
    int z =  F[1][0]*M[0][0] + F[1][1]*M[1][0];
    int w =  F[1][0]*M[0][1] + F[1][1]*M[1][1];
     
    F[0][0] = x;
    F[0][1] = y;
    F[1][0] = z;
    F[1][1] = w;
    }
      
    /* Optimized version of power() */
    static void power2(int F[][], int n)
    {
    if( n == 0 || n == 1)
      return;
    
    int M[][] = new int[][]{{1,1},{1,0}};  
    power2(F, n/2);
    multiply2(F, F);
      
    if (n%2 != 0) // n is odd
       multiply2(F, M);
    }

	
	
	
	public static void main (String[] args)
	{
		int n = 9;
		System.out.println(fib3(n));
	}
}
