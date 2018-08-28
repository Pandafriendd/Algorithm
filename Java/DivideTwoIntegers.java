/*
Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Note:

Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. 
For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
 */

//bit manipulations
public class DivideTwoIntegers {
	public static void main(String[] args) {
        DivideTwoIntegers d = new DivideTwoIntegers();
        System.out.println(d.divide(15, -3));
    }
    
    /**
     * division simply requires us to find how many times we can subtract the divisor from the the dividend without making the dividend negative.
     * Take care of special cases, 0, +1, -1
     * dividend = a0 * 1 * divisor + a1 * 2 * divisor + a2 * 2^2 * divisor...
     * ai can be 0 or 1, set it to 1 if dividend >= ai * 2^i * divisor
     * All ais added up to result
     * 
     * Suppose we want to divide 15 by 3, so 15 is dividend and 3 is divisor. 
     * Division simply requires us to find how many times we can subtract the divisor from the the dividend without making the dividend negative.
     * We subtract 3 from 15 and we get 12, which is positive. Let's try to subtract more, we shift 3 to the left by 1 bit and we get 6. 
     * Subtracting 6 from 15 still gives a positive result. We shift again and get 12. We subtract 12 from 15 and it is still positive. 
     * We shift again, obtaining 24 and we know we can at most subtract 12. 
     * Since 12 is obtained by shifting 3 to left twice, we know it is 4 times of 3. 
     * How do we obtain this 4? Well, we start from 1 and shift it to left twice at the same time. 
     * We add 4 to an answer (initialized to be 0). In fact, the above process is like 15 = 3 * 4 + 3. We now get part of the quotient (4), with a remainder 3.
     * Then we repeat the above process again. We subtract divisor = 3 from the remaining dividend = 3 and obtain 0. We know we are done. 
     * No shift happens, so we simply add 1 << 0 to the answer.
     */
    public int divide(int dividend, int divisor) {
    	/* 
    	 * special cases
    	 * two cases may cause overflow:
    	 * 1. divisor = 0;
    	 * 2. dividend = INT_MIN and divisor = -1 (because abs(INT_MIN) = INT_MAX + 1).
    	 */
        if (divisor == 0) return Integer.MAX_VALUE;
        if (dividend == 0) return 0;
        if (divisor == 1) return dividend;
        if (divisor == -1) return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : -dividend;
        
        final boolean neg = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);
        long ldividend = Math.abs((long)dividend); // convert to abstract long to avoid integer overflow cases. since -INT_MIN > INT_MAX.
        final long ldivisor = Math.abs((long)divisor);
        int res = 0;
        
        for (int bit = Integer.SIZE - 1; bit >= 0 && ldividend >= ldivisor; bit--) { // bit from 31 to 0, dividend >= divisor
            if (ldividend >= (ldivisor << bit)) {
            	System.out.println("bit； " + bit);
                res += 1 << bit; // set 1 in relative bit in result
                System.out.println("res: " + res);
                ldividend -= ldivisor << bit;
            }
        }
        return neg ? -res : res;
    }
}
