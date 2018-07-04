
public class POWxn {
	
	//binary search, and some math
	/*
	 * when n is odd, multiply res by i, and then i multiply by itself each time. Repeat until n == 0
	 * x ^ 10 = x ^ 2 * x ^ 8
	 * x ^ 9 = x * x ^ 8
	 */
	public double pow(double x, int n) {
		double res = 1;
		if(n == 0)
			return 1;
		if(n < 0) {
			n = -n;
			x = 1 / x;   //??!!
		}
		for(double i = x; n > 0; n = n >> 1) {  //n >> 1, n / 2
			if((n & 1) == 1) //if n is odd
				res = res * i;
			i = i * i;
			System.out.println("res=" + res + "  i=" + i + "  n=" + n);
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		POWxn p = new POWxn();
		System.out.println(p.pow(2.0, 9));
	}
}
