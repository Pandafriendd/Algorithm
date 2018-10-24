/*
"bcdfbd" is "bcdf"
*/

public int longest(String input) {
	// using sliding window techniques
	int start = 0;  // [start, end) is the window, start is slow pinter and end is the faster pointer
	int end = 0;   
	int res = 0;
	Set<Character> set = new HashSet<>();
	while (end < input.length()) {
		if (set.add(input.charAt(end))) {
			res = Math.max(res, end - start + 1);
			end++;
		}
		else {  // when there is a duplicate
			set.remove(input.charAt(start));
			start++;
		}
	}

	return res; 
}
