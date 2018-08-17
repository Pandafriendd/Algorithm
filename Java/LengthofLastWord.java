
public class LengthofLastWord {
	public int lengthOfLastWord(String s) {
        String[] words = s.split(" ");
        if(words.length == 0)
            return 0;
        char[] a = words[words.length - 1].toCharArray();
        return a.length;
    }
	
	public int lengthOfLastWord2(String s) {
	    return s.trim().length()-s.trim().lastIndexOf(" ")-1;
	}
}
