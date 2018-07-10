
public class climbStairs {
	public int solution1(int n ) { //brute force
		int res = 0;
		if(n <= 0)
			return res;
		
		res = climb(0, n);
		
		return res;
	}
	
	private int climb(int i, int n) { //(i, n) node of recursion tree
		if(i > n)
			return 0;
		if(i == n)
			return 1;
		return climb(i + 1, n) + climb(i + 2, n);
	}
}
