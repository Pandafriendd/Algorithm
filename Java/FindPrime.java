import java.util.*;

public class FindPrime {
	public List<Integer> FindPrime(int n){
		List<Integer> res = new ArrayList<Integer>();
		res.add(2);
		for(int i = 3; i <= n ; i++) {
			for(int j = 2; j < i; j++) {
				if(i % j == 0)
					break;
				if(j == i - 1)
					res.add(i);
			}
		}
		return res;
	}
	
	public List<Integer> FindPrime2(int n){
		List<Integer> res = new ArrayList<Integer>();
		res.add(2);
		res.add(3);
		for(int i = 3; i <= n; i+=2) {
			for(int j = 2; j <= (int)Math.sqrt(i); j++) {
				if(i % j == 0)
					break;
				if(j == (int)Math.sqrt(i))
					res.add(i);
			}
		}
		
		return res;
	}
	
	public static void main(String args[]) {
		int n = 200;
		FindPrime f = new FindPrime();
		long time1 = System.nanoTime();
		List<Integer> list = f.FindPrime(n);
		System.out.println(System.nanoTime() - time1);
		
		long time2 = System.nanoTime();
		List<Integer> list2 = f.FindPrime2(n);
		System.out.println(System.nanoTime() - time2);
		
		for(int i = 0; i < list.size(); i++)
			System.out.print(" " + list.get(i));
		System.out.println();
		for(int i = 0; i < list2.size(); i++)
			System.out.print(" " + list2.get(i));
	}
}
