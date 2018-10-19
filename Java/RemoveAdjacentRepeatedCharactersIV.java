// something WRONG !!!!
public String deDup(String input) {
	if (input.isEmpty()) {
		return input;
	}

	Deque<Character> stack = new ArrayDeque<>(); // all processed chars that should be kept
	int j = 1; // fast pionter
	char[] array = input.toCharArray();

	stack.push(array[0]);
	while (j < array.length) {
		char c = array[j];
		if (!stack.isEmpty() && c == stack.peek()) {
			while (j < array.length && c == stack.peek()) {
				j++;
			}
			// now j == a.len || a[j] != stack.peek()
			stack.pop();	
		}
		else { // stack is empty || a[j] != peek
			stack.push(array[j]);
			j++;
		}
	}

	StringBuilder sb = new StringBuilder();
	for (int i = 0; i < stack.size(); i++) {
		sb.append(stack.pop());
	}
	sb.reverse();

	return sb.toString();
}
