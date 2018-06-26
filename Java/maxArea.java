 import java.io.*;
 
public class maxArea {
	public int maxArea(int[] height){
		int[] h = height;
		double area = 0;
		double res = 0;
		for(int i = 0; i <= h.length; i++){
			for(int j = i + 1; j <= h.length; j++){
				int a = h[i];
				int b = h[j];
				area = (a + b) * (j - i) * 0.5;
				res = res > area ? res : area;
			}
		}
		return (int)res;
	}

	void main(){
		int[] a = {1,4,3,4};
		maxArea m = new maxArea();
		System.out.print(m.maxArea(a));
}

}