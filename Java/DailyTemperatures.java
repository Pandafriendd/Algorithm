import java.util.*;
public class DailyTemperatures {
	//O(n^2)
	public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        
        for(int i = 0; i < temperatures.length-1; i++) {
            int x = temperatures[i];
            res[i] = 0;
            for(int j = i + 1; j < temperatures.length; j++) {
                int y = temperatures[j];
                if (y > x){
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
	}
	
	//O(n)
	public int[] dailyTemperatures2(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = temperatures.length - 1; i >= 0; --i) {
            while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) 
                stack.pop();
            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.push(i);
        }
        return ans;
    }
}
