/*
Given an integer n, print/output all possible if blocks for it. Say n=2 output should be

if {

}

if {

}

<newline>

if {

  if {// here should exist two spaces before each inner block

  }

}
 */

/*
a trick using stringbuilder in dfs:
	int length = sb.length();
	sb.append(...);
	dfs(..., sb);
	sb.setLength(length);
 */

import java.util.*;

public class AllPossibleBlocks {
	// the key problem is how to deal with the indentation. Decouple the problem!!!!
	public static void allBlocks(int n) {
		char[] parentheses = new char[n * 2];
		helper(parentheses, n, n, 0);
	}
	
	private static void helper(char[] parentheses, int leftRemain, int rightRemain, int index) {
		// base case
		if (leftRemain == 0 && rightRemain == 0) {
			printBlock(parentheses);
			return;
		}
		
		if (leftRemain > 0) {
			parentheses[index] = '{';
			helper(parentheses, leftRemain - 1, rightRemain, index + 1);
		}
		if (rightRemain > leftRemain) {
			parentheses[index] = '}';
			helper(parentheses, leftRemain, rightRemain - 1, index + 1);
		}
	}
	
	// formating the output
	private static void printBlock(char[] parentheses) {
		int heading = 0;
		System.out.println("***********");
		for (int i = 0; i < parentheses.length; i++) {
			if (parentheses[i] == '{') {
				printSpace(heading);
				System.out.println("if {");
				heading += 2;
			} else {
				heading -= 2;
				printSpace(heading);
				System.out.println("}");
			}
		}
		System.out.println("***********");
	}
	
	private static void printSpace(int heading) {
		while (heading > 0) {
			System.out.print(" ");
			heading--;
		}
	}
	
	public static void main(String[] args) {
		allBlocks(3);
	}
}

