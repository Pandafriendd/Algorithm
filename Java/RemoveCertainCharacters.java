/*
input = "abcd", t = "ab", delete all instances of 'a' and 'b', the result is "cd".
*/

// time: O(input.len * t.len)
public String remove(String input, String t) {
	int i = 0;  // slow pointer
	int j = 0;  // fast pointer
	char[] array = input.toCharArray();

	while (j < array.length) {
		if (t.indexOf(array[j]) == -1) { // if array[j] not in t, should be kept. Not very efficient
			array[i] = array[j];
			i++;
			j++;
		}
		else { // array[j] in t
			j++;
		}
	}

	return new String(array, 0, i);  // String(a[], offset, length)
}


// time: O(t.len + input.len)
public String remove(String input, String t) {
	int i = 0;
	int j = 0;
	char[] array = input.toCharArray();
	Set<Character> set = new HashSet<>();

	// buildSet
	for (int k = 0; k < t.length(); k++) {
		set.add(t.charAt(k));
	}

	while (j < array.length) {
		if (!set.contains(array[j])) {  // a[j] not in the set
			array[i] = array[j];
			i++;
			j++;
		}
		else {
			j++;
		}
	}

	return new String(array, 0, i); 
}

