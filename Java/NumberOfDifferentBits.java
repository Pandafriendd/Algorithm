/*
Determine the number of bits that are different for two given integers.

Examples

5(¡°0101¡±) and 8(¡°1000¡±) has 3 different bits
 */


public class NumberOfDifferentBits {
	public int diffBits(int a, int b) {
	    
	    int count = 0;
	    for (int i = a ^ b; i != 0; i = i >>> 1) {
	      count += i & 1;
	    }
	    
	    return count;
	}
}
