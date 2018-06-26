import java.util.*;

public class MultiplyStrings {
	//dasheng's solution
	public String mutiply1(String num1, String num2) {
		int len1 = num1.length();
		int len2 = num2.length();
		int[] pos = new int[len1 + len2];
		
		for(int i = len1 - 1; i >= 0; i--) {
			for(int j = len2 - 1; j >= 0; j--) {
				int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
				int p1 = i + j; //index of carry
				int p2 = i + j + 1;// index of rightmost digit of sum
				int sum = mul + pos[p2];
				
				pos[p1] = pos[p1] + sum / 10;  // pos[p1] += sum / 10
				pos[p2] = sum % 10;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int p : pos) {
			if(!(sb.length() == 0 && p == 0))
				sb.append(p);
		}
		return sb.length() == 0 ? "0" : sb.toString();
	}
	
	public static void main(String[] args) {
		MultiplyStrings a = new MultiplyStrings();
		System.out.println(a.mutiply1("123", "45"));

	}

}
