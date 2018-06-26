
public class testofUnknow {
	static public void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		String s = "ABCD";
		int i = 0;
		while(i < s.length() - 1) {
			System.out.print("before    " + i + "  ");
			sb.append(s.charAt(++i));
			System.out.println(sb.toString() + "    after" + i);
		}
		
		System.out.println(10 & 1 << 1);
		
		/*
		 * Judging even or not: 
		 * if(x & 1 == 0) even
		 * else 
		 */
		
	}
}
