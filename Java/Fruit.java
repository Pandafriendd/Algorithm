
public class Fruit {
	public static int solution(int[] A) {
        // write your code in Java SE 8
        if(A == null) {
            return 0;
        }
        if(A.length <= 2) {
            return A.length;
        }
        
        int start = 0;
        int end = 0;
        int first = 0;
        int second = 0;
        int count = 0;
        while(end < A.length) {
            if(first == 0) {
                first = A[end];
                end++;
            }
            else if(second == 0 && A[end] != first) {
                second = A[end];
                end++;
            }
            else if(first == A[end] || second == A[end]) {
            	end++;
            }
            
            else {
                start = end;
                first = A[end];
                second = 0;
                end++;
            }
            count = Math.max(count, end - start);
        }
        
        return count;
    }
	
	public static void main(String[] args) {
		int res = solution(new int[] {1,2,1,3,4,3,5,1,2});
		System.out.println(res);
	}
}
