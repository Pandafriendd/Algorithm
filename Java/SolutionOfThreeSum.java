import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SolutionOfThreeSum {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new LinkedList();
		if(nums == null || nums.length == 0)
			return res;
		
		Arrays.sort(nums);
		
		for(int i = 0; i < nums.length - 2; i++) {
			if(i - 1 >= 0 && nums[i] == nums[i-1]) {
				continue;
			}
			// i < low < high
			int low = i + 1;
			int high = nums.length - 1;
			while(low < high) {
				int sum = nums[i] + nums[low] + nums[high];
				
				if(sum == 0) {
					res.add(Arrays.asList(nums[i], nums[low], nums[high]));
					while(nums[low] == nums[low + 1] && low + 1< high)
						low++;
					while(nums[high] == nums[high] - 1 && high - 1 > low)
						high--;
					low++;
					high--;
				}
				else if(sum < 0)
					low++;
				else
					high--;
			}
		}
		return res;
	}
	
	//************************************input output*******************************************
	public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
          return new int[0];
        }
    
        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }
    
    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }
    
        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }
    
    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }
    
    public static String int2dListToString(List<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[");
        for (List<Integer> list: nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",");
        }
    
        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            SolutionOfThreeSum s = new SolutionOfThreeSum();
            
            List<List<Integer>> ret = s.threeSum(nums);
            
            String out = int2dListToString(ret);
            
            System.out.print(out);
        }
    }
}
