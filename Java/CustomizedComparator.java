// customized Comparator, I want deceading order

// 1. Collections.reverseOrder()
PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

//2. Top-level class
class MyComparator implements Comparator<Student> {
	@Override
	public int compare(Student s1, Student s2) {
		...
	}
}

Collections.sort(students, new MyComparator());


// 3. Static nested class
class Solution {

	private static class MyComparator implements Comparator<Student> {
		@Override
		public int compare(Student s1, Student s2) {
			...
		}	
	}
}

// 4, Anonymous class
Collections.sort(students, new Comparator<Student> {
	@Override
	public int compare(Student s1, Student s2) {
		...
	}
});


// Lambda expressions
Collections.sort(students, (s1, s2) -> s1.getName().compareTo(s2.getName()));

Collections.sort(students, (s1, s2) -> {
	if(s1.getAge() == s2.getAge()) {
		return 0;
	}
	else {
		return s1.getAge() < s2.getAge() ? -1 : 1;
	}
});
