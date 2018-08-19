
public class InttoBinString {
	
	/**
	 * Converts an integer to a 32-bit binary string
	 * @param number
	 *      The number to convert
	 * @param groupSize
	 *      The number of bits in a group
	 * @return
	 *      The 32-bit long bit string
	 */
	public static String intToString(int number, int groupSize) {
	    StringBuilder result = new StringBuilder();

	    for(int i = 3; i >= 0 ; i--) {  // 4 bit int
	        int mask = 1 << i;
	        //System.out.println(Integer.toBinaryString(mask));
	        result.append((number & mask) != 0 ? "1" : "0");  // !!!! != 0 it's not == 1 since 1 bit could be different position, eg 1000, 0100

	        if (i % groupSize == 0)
	            result.append(" ");
	    }
	    //result.replace(result.length() - 1, result.length(), "");  // same as call the String class's trim

	    return result.toString().trim();
	}
	
	public static void main(String[] args) {
	    System.out.println(intToString(9, 4));
	    System.out.println(Integer.toBinaryString(9)); //using built-in
	}
}
