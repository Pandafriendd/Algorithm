/*
The integer zip of two non-negative integers A and B is an integer C whose decimal
representation is created from the decimal representations of A and B as follows:
the first (i.e. the most significant) digit of C is the first digit of A;
the second digit of C is the first digit of B;
the third digit of C is the second digit of A;
the fourth digit of C is the second digit of B; etc.
If one of the integers A and B runs out of digits, the remaining digits of the other integer are
appended to the result. The decimal representation of 0 is assumed to be "0".
Write a function:
int solution(int A, int B)
which, given two non-negative integers A and B, returns their integer zip. The function should
return −1 if the result exceeds 100,000,000.
Examples:
solution(12, 34) returns 1324
solution(12345, 678) returns 16273845
solution(1234, 0) returns 10234
Assumptions / constraints:
A and B are integers within the range [0..100,000,000].
 */
import java.util.*;

public class IntegerZip {
	public static int IntegerZip0(int a, int b) {
		if (a > 1000000000 || b > 1000000000) {
			return -1;
		}

		List<Integer> res = new ArrayList<>();
		int divisorA = 1000000000;
		int divisorB = 1000000000;
		boolean isADone = false;
		boolean isBDone = false;
		boolean isdivisorADone = false;
		boolean isdivisorBDone = false;
		
		while (!isADone || !isBDone) {
			while (divisorA != 0 && a / divisorA == 0 && a != 0 && !isdivisorADone) {
				divisorA = divisorA / 10;
			}
			isdivisorADone = true;
			if (isADone == false) {
				res.add(a / divisorA);
				a = a % divisorA;
				divisorA = divisorA / 10;
				if (a == 0) {
					isADone = true;
				}
			}
			
			while (divisorB != 0 && b / divisorB == 0 && b != 0 && !isdivisorBDone) {
				divisorB = divisorB / 10;
			}
			isdivisorBDone = true;
			if (isBDone == false) {
				res.add(b / divisorB);
				b = b % divisorB;
				divisorB = divisorB / 10;
				if (b == 0) {
					isBDone = true;
				}
			}
		}

		int resInt = 0;
		int temp = 1;
		for (int i = res.size() - 1; i >= 0; i--) {
			resInt += res.get(i) * temp;
			temp = temp * 10;
		}

		return resInt;
	}
	
	
	public static void main(String[] args) {
		System.out.println(IntegerZip0(12, 303));
	}
}
