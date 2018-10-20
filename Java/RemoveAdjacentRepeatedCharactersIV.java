
import java.util.*;

public class RemoveAdjacentRepeatedCharactersIV {
	
	public static String deDup(String input) {
		if (input.isEmpty()) {
			return input;
		}

		Deque<Character> stack = new ArrayDeque<>(); // all processed chars that should be kept
		int j = 0; // fast pionter
		char[] array = input.toCharArray();

		while (j < array.length) {
			if (!stack.isEmpty() && array[j] == stack.peek()) {
				while (j < array.length && array[j] == stack.peek()) {
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
	
	
	public static void main(String[] args) {
		System.out.println(deDup("abbbccd"));
	}
}
