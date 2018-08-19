
public class stringReverse {
	
	public static void main(String[] args) {
		stringReverse s = new stringReverse();
		System.out.println(s.recursiveReverse("abcdefg"));
	}
	
	/*
	 * recursively reverse a string
	 */
	public String recursiveReverse(String input) {
		//char last = input.charAt(input.length() - 1);
		char first = input.charAt(0);
		if(input.length() <= 1 || input == null)
			return input;
		
		//return last + recursiveReverse(input.substring(0, input.length() - 1));
		return recursiveReverse(input.substring(1, input.length())) + first;
		
	}
	
	
	//input: this is an interview question
	//output: question interview an is this
	
	//without StringBuilder
	public String reverseA1(String input) {
		String[] words = input.split(" ");
		String res = "";
		for(int i = words.length - 1; i >= 0; i--) {
			res += words[i] + " ";
		}
		return res;
	}
	
	//with StringBuilder
	public String reverseA2(String input) {
		String[] words = input.split(" ");
		StringBuilder sb = new StringBuilder();
		for(int i = words.length - 1; i >= 0; i--) {
			sb.append(words[i]);
			sb.append(" ");
		}
		return sb.toString();
	}
	
	
	//¡°Reverse Me¡± once reversed will be ¡°eM esreveR¡±
	
	//follow by method above
	public String reverseB1(String input) {
		String[] words = input.split(" ");
		StringBuilder sb = new StringBuilder();
		for(int i = words.length - 1; i >= 0; i--) {
			sb.append(help(words[i]));
			sb.append(" ");
		}
		return sb.toString();
	}
	
	private String help(String s) {  //help to reverse a string
		StringBuilder sb = new StringBuilder();
		for(int i = s.length() - 1; i >= 0; i--) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
			
	}
	
	//there is a more straightforward way, since in this case we just do simple reversing
	public String reverseB2(String input) {
		String res = "";
		for(int i = input.length() - 1; i >= 0; i--) {
			res += input.charAt(i);
		}
		return res;
	}
	
	//with StringBuilder
	public String reverseB3(String input) {
		StringBuilder sb = new StringBuilder();
		sb.append(input);
		sb.reverse();
		return sb.toString();
	}
	
	
	/*
	 * 
	 * Input  : "abc de"
	 * Output : edc ba
	 * 
	 * Input : "intern at geeks"
	 * Output : skeegt an retni
	 * 
	 * Input : "Help others"
	 * Output : sreh topleH

	 */
	
	public String reverseC1(String input) {
		char[] inputArray = input.toCharArray();
		char[] resArray = new char[input.length()];
		
		//first mark spaces in resArray
		for(int i = 0; i < inputArray.length; i++) {
			if(inputArray[i] == ' ')
				resArray[i] = ' ';
		}
		
		//traverse inputArray from beginning and put char in resArray from end
		int j = resArray.length - 1;
		for(int i = 0; i < inputArray.length; i++) {
			if(inputArray[i] != ' ') { //ignore spaces in inputArray
				if(resArray[j] == ' ') //ignore spaces in resArray
					j--;
				resArray[j] = inputArray[i];
				j--;
			}
		}
		
		return String.valueOf(resArray);
	}
}
