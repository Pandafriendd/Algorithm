/*
 * Starting with a positive integer N, we reorder the digits in any order (including the original order) such that the leading digit is not zero. 
 * Return true if and only if we can do this in a way such that the resulting number is a power of 2.
 */


/*
 * counter will counter the number of digits 9876543210 in the given number. 
 * Then I just compare counter(N) with all counter(power of 2). 
 * For example, in java, counter(124) will return 10110, which represents one 4, no 3, one 2, one 1 and no 0. 
 */
public class ReorderedPowerof2 {
	public boolean reorderedPowerOf2(int N) {
        for (int i = 0, c = counter(N); i < 32; i++)
            if (counter(1 << i) == c) return true;
        return false;
    }
    public int counter(int N) {
        int res = 0;
        for (; N > 0; N /= 10) res += (int)Math.pow(10, N % 10);
        return res;
    }
}
