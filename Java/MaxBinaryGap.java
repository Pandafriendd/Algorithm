/*
 * Input: 22 
 * Output: 2 
 * Explanation: 22 in binary is 0b10110. 
 * In the binary representation of 22, there are three ones, and two consecutive pairs of 1's. 
 * The first consecutive pair of 1's have distance 2. The second consecutive pair of 1's have distance 1. 
 * The answer is the largest of these two distances, which is 2.
 * 
 */
public class MaxBinaryGap {
	public int binaryGap(int N) {
        int binaryGap = 0;
        String binaryString = Integer.toBinaryString(N);
        char[] characters = binaryString.toCharArray();
        int j = 0;
        int nums =0;
        Character c;
        for (int i = 0; i < characters.length; i++) {
            c = characters[i];
            if (c.equals('0')) {
                j++;
            }
            

            if (c.equals('1')) {
                nums++;   // tracking how many 0s
                j++;
                if (j > binaryGap ){
                    binaryGap = j;
                }
                j = 0;
            }

        }
        
        if(nums == 1)
            binaryGap = 0;

        return binaryGap;
    }
}
