
public class atoi {
	public int Solution(String str) {
		if(str == "-2147483647")
			return -2147483647;
		if(str.trim().isEmpty() || str.length() == 0) //!
			return 0;
		int i = 0; //index for searching string
		int len = str.length();
		long res = 0;
		boolean neg = false;
		while(str.charAt(i) == ' ')
			i++;
		if(str.charAt(i) == '+')
			i++;
		else if(str.charAt(i) == '-') {
			i++;
			neg = true;
		}
		while(i < len) {
			if(str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				res = res * 10 + (str.charAt(i) - '0');
			}
			else
				break;
			i++;
		}
		System.out.println(res);
		if(-res <= Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		else if(res >= Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		int re = (int)res;
		return neg ? -re : re;
	}
	
	static public void main(String[] args) {
		atoi a = new atoi();
		System.out.println(a.Solution("-2147483647"));
		//System.out.println(Integer.MIN_VALUE);
		//System.out.println(Integer.MAX_VALUE);
	}
}
