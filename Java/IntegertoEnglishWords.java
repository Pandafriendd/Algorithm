/*
 Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

Example 1:

Input: 123
Output: "One Hundred Twenty Three"
Example 2:

Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"
 */


public class IntegertoEnglishWords {
	
	private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};
	
	public String numberToWords(int num) {
		if(num == 0)
			return "ZERO";
		
		int i = 0;
		String words = "";
		
		while(num > 0) {
			if(num % 1000 != 0)
				words = helper(num % 1000) + THOUSANDS[i] + " " + words;
			num = num / 1000;
			i++;
		}
		
		return words.trim();
	}
	
	private String helper(int num) {
		if(num == 0)
			return "";
		else if(num < 20)
			return LESS_THAN_20[num] + " ";
		else if(num < 100)
			return TENS[num / 10] + " " + helper(num % 10);
		else
			return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
	}
	
	
	// Follow up: what if input is floating number ?
	// If Number is floating even then it is easy
	public String numToWordsFloat(float num) {
		int roundedInt = Math.round(num);
		String s = numberToWords(roundedInt);
		float f= num - roundedInt; // It Will give You float after and let assue we want to calculate after 3 decinmal point
		int tempRaisedFloat = (int) f * 1000;
		s = s + "." + numberToWords(tempRaisedFloat);
		return s;
	}
	
	
	
	// without recursion
	private final String[] map1 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	private final String[] map2 = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	private final String[] map3 = {"", " Thousand", " Million", " Billion"};

	public String numberToWords22(int num) {
	    if (num == 0) return "Zero";
	    
	    int weight = 0;
	    StringBuilder sb = new StringBuilder();
	            
	    while (num > 0) {
	        int n = num % 1000;
	        String s = getEng(n);
	        
	        if(s.length() != 0) {
	            sb.insert(0, " " + s + map3[weight]);   
	        }               
	        weight++;           
	        num = num / 1000;
	    }
	    
	    return sb.toString().trim();
	}

	public String getEng (int num) {
	    if (num == 0)
	        return "";
	    
	    StringBuilder sb = new StringBuilder();
	    
	    int h = num / 100;
	    if(h != 0) {
	        sb.append(map1[h] + " " + "Hundred");
	    }
	    
	    num = num % 100;
	    if(num < 20) {
	        sb.append(" " + map1[num]);
	        return sb.toString().trim();
	    }
	    

	    int t = num / 10;
	    if(t != 0) {
	        sb.append(" " + map2[t]);
	    }
	    num = num % 10;
	    if(num != 0) {
	        sb.append(" " + map1[num]);
	    }
	    
	    return sb.toString().trim();
	}
}
