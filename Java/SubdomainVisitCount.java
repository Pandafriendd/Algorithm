/*
Example 2:
Input: 
["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
Output: 
["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
Explanation: 
We will visit "google.mail.com" 900 times, "yahoo.com" 50 times, "intel.mail.com" once and "wiki.org" 5 times. 
For the subdomains, we will visit "mail.com" 900 + 1 = 901 times, "com" 900 + 50 + 1 = 951 times, and "org" 5 times.
 */

import java.util.*;

public class SubdomainVisitCount {
	public static List<String> subdomainVisits(String[] cpdomains) {
        List<String> res = new ArrayList<>();
        if (cpdomains == null || cpdomains.length == 0) {
            return res;
        }
        
        Map<String, Integer> map = new HashMap<>();
        for (String cpdomain : cpdomains) {
            String[] cpinfo = cpdomain.split("\\s+");  //!
            int count = Integer.parseInt(cpinfo[0]);
            String domain = cpinfo[1];
            String[] domaininfo = domain.split("\\.");  // split(".") is WRONG! Since You need to escape the dot if you want to split on a literal dot. Otherwise you are splitting on the regex ., which means "any character". Note the double backslash needed to create a single backslash in the regex.
            
            String dm = "";
            for (int i = domaininfo.length - 1; i >= 0; i--) {
                if (i == domaininfo.length - 1) {
                    dm = domaininfo[i];
                }
                else {
                    dm = domaininfo[i] + "." + dm;
                }
                
                map.put(dm, map.getOrDefault(dm, 0) + count);
            }
        }
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String s = entry.getValue() + " " + entry.getKey();
            res.add(s);
        }
        
        return res;
    }
	
	public static void main(String[] args) {
		String[] a = {"9001 discuss.leetcode.com"};
		System.out.println(subdomainVisits(a));
	}
}
