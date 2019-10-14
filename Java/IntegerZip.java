public int IntegerZip(int a, int b) {
	if (a > 1000000000 || b > 1000000000) {
		return -1;
	}

	List<Integer> res = new ArrayList<>();
	int dividandA = 1000000000;
	int dividandB = 1000000000;
	boolean isADone = false;
	boolean isBDone = false;
	while (!isADone || !isBDone) {
		while (a % dividandA == 0 && a != 0) {
			 dividandA = dividandA / 10;
		}
		if (isADone == false) {
			res.add(a / dividandA);
			a = a % dividandA;
			dividandA = dividandA / 10;
			if (a == 0) {
				isADone = true;
			}
		}
		while (b % dividandB == 0 && b != 0) {
			dividandB = dividandB / 10;
		}
		if (isBDone == false) {
			res.add(b / dividandB);
			b = b % dividandB;
			dividandB = dividandB / 10;
			if (b == 0) {
				isBDone = true;
			}
		}
	}

	int resInt = 0;
	int temp = 1;
	for (int i = res.length - 1; i >= 0; i++) {
		resInt += res.get(i) * i;
	}

	return res;
}