public String removeSpaces(String input) {
	if (input.isEmpty()) {
		return input;
	}
    char[] array = input.toCharArray();
    int i = 0;
    int j = 0;

    while (j < array.length) {
    	if (array[j] != ' ') {   // keep
    		array[i] = array[j];
    		i++;
    		j++;
    	}
    	else { // a[j] == ' '
    		if (i == 0 || array[i - 1] == ' ') { // ignore
    			j++;
    		}
    		else {  // i != 0 && array[i - 1] != ' ', keep
    			array[i] = array[j];
    			i++;
    			j++;
    		}
    	}
    }

    // post-processing
    if (i > 0 && array[i - 1] == ' ') {
    	i--;
    }

    return new String(array, 0, i);
}
