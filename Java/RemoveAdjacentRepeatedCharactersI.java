public String deDup(String input) {
	if (input.isEmpty()) {
		return input;
	}

	int i = 1;  // slow pointer
	int j = 1;  // fast pointer
	char[] array = input.toCharArray();

	while (j < array.length) {
		if (array[j] == array[i - 1]) {  // ignore  !!!!! array[j] == array[i - 1]
			j++;
		}
		else {  // keep
			array[i] = array[j];
			i++;
			j++;
		}
	}

	return new String(array, 0, i);
}
