/*
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
*/


/*
"101022415"
Output
["1.01.0224.15","1.010.224.15","1.0102.24.15","1.0102.241.5","10.1.0224.15","10.10.224.15","10.102.24.15","10.102.241.5","101.0.224.15","101.02.24.15","101.02.241.5","101.022.4.15","101.022.41.5","101.0224.1.5"]
Expected
["10.10.224.15","10.102.24.15","10.102.241.5","101.0.224.15"]
*/
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0 || s == "") {
            return res;
        }
        
        StringBuilder sb = new StringBuilder();
        int offsetStart = 0;
        int offsetEnd = 1;
        int partCount = 0; 
        helper(s, sb, offsetStart, offsetEnd, partCount, res);
        return res;
    }
    
    private void helper(String s, StringBuilder sb, int offsetStart, int offsetEnd, int partCount, List<String> res) {
        // base case
        if (partCount == 3) {
            if (offsetStart < s.length()) {  // !!!!!!!!!!!!!!!!
                String part = s.substring(offsetStart); // from offset to the end
                int number = Integer.parseInt(part);
                if (number >= 0 && number <= 255) {    
                    sb.append(part);
                    res.add(sb.toString());
                }
            }
            return;
        }
        
        // recursive rule
        String part = s.substring(offsetStart, offsetEnd);  // start is inclusive and end is exclusive
        int number = Integer.parseInt(part);
        while (number >= 0 && number <= 255) {
            int sbLength = sb.length();
            sb.append(part);
            sb.append('.');
            helper(s, sb, offsetEnd, offsetEnd + 1, partCount + 1, res);
            sb.setLength(sbLength);
            
            if (offsetEnd < s.length()) {
                offsetEnd++;
                part = s.substring(offsetStart, offsetEnd);
                number = Integer.parseInt(part);
            } else {
                break;
            }
        }
    }
}

public class RestoreIPAddresses {
	/*
	 * time: O(n * (n - 1) * (n - 2) * (n - 3)) space: O(n)
	 */
	public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0 || s == "" || s.length() > 12) {  // deal with overflow here!!!!
            return res;
        }
        
        StringBuilder sb = new StringBuilder();
        int offsetStart = 0;
        int offsetEnd = 1;
        int partCount = 0; 
        helper(s, sb, offsetStart, offsetEnd, partCount, res);
        
        return cleaningList(res);
    }
    
    private void helper(String s, StringBuilder sb, int offsetStart, int offsetEnd, int partCount, List<String> res) {
        // base case
        if (partCount == 3) {
            if (offsetStart < s.length()) {  // !!!!!!!!!!!!!!!!
                String part = s.substring(offsetStart); // from offset to the end
                // int number = Integer.parseInt(part);  // overflow??!!!!!
                long number = Long.parseLong(part);  // still overflow!!!
                if (number >= 0 && number <= 255) {    
                    sb.append(part);
                    res.add(sb.toString());
                }
            }
            return;
        }
        
        // recursive rule
        if (offsetStart >= s.length()) {
            return;
        }
        String part = s.substring(offsetStart, offsetEnd);  // start is inclusive and end is exclusive
        long number = Long.parseLong(part);
        while (number >= 0 && number <= 255) {
            int sbLength = sb.length();
            sb.append(part);
            sb.append('.');
            helper(s, sb, offsetEnd, offsetEnd + 1, partCount + 1, res);
            sb.setLength(sbLength);
            
            if (offsetEnd < s.length()) {
                offsetEnd++;
                part = s.substring(offsetStart, offsetEnd);
                number = Long.parseLong(part);
            } else {
                break;
            }
        }
    }
    
    private List<String> cleaningList(List<String> res) {
        List<String> cleaned = new ArrayList<>();
        boolean flag;
        for (String str : res) {
            flag = true; 
            String[] parts = str.split("\\.");  //!!!
            for (int i = 0; i < parts.length; i++) {
                if (parts[i].charAt(0) == '0' && parts[i].length() > 1) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                cleaned.add(str);
            }
        }
        return cleaned;
    } 
    
    public static void main(String[] args) {
    	RestoreIPAddresses obj = new RestoreIPAddresses();
    	System.out.println(obj.restoreIpAddresses("0279245587303"));
    }
}

// time: there is not more than 27 combinations to check.
// space: constant space to keep the solutions, not more than 19 valid IP addresses.
class Solution {
  int n;
  String s;
  LinkedList<String> segments = new LinkedList<String>();
  ArrayList<String> output = new ArrayList<String>();

  public boolean valid(String segment) {
    /*
    Check if the current segment is valid :
    1. less or equal to 255      
    2. the first character could be '0' 
    only if the segment is equal to '0'
    */
    int m = segment.length();
    if (m > 3)
      return false;
    return (segment.charAt(0) != '0') ? (Integer.valueOf(segment) <= 255) : (m == 1);
  }

  public void update_output(int curr_pos) {
    /*
    Append the current list of segments 
    to the list of solutions
    */
    String segment = s.substring(curr_pos + 1, n);
    if (valid(segment)) {
      segments.add(segment);
      output.add(String.join(".", segments));
      segments.removeLast();
    }
  }

  public void backtrack(int prev_pos, int dots) {
    /*
    prev_pos : the position of the previously placed dot
    dots : number of dots to place
    */
    // The current dot curr_pos could be placed 
    // in a range from prev_pos + 1 to prev_pos + 4.
    // The dot couldn't be placed 
    // after the last character in the string.
    int max_pos = Math.min(n - 1, prev_pos + 4);
    for (int curr_pos = prev_pos + 1; curr_pos < max_pos; curr_pos++) {
      String segment = s.substring(prev_pos + 1, curr_pos + 1);
      if (valid(segment)) {
        segments.add(segment);  // place dot
        if (dots - 1 == 0)      // if all 3 dots are placed
          update_output(curr_pos);  // add the solution to output
        else
          backtrack(curr_pos, dots - 1);  // continue to place dots
        segments.removeLast();  // remove the last placed dot 
      }
    }
  }

  public List<String> restoreIpAddresses(String s) {
    n = s.length();
    this.s = s;
    backtrack(-1, 3);
    return output;
  }
}
