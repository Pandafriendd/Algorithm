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
