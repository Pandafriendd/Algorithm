/*
The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function may be called multiple times.

Example 1: 
Given buf = "abc"
read("abc", 1) // returns "a"
read("abc", 2); // returns "bc"
read("abc", 1); // returns ""

Example 2: 
Given buf = "abc"
read("abc", 4) // returns "abc"
read("abc", 1); // returns ""
 */
public class ReadNCharactersGivenRead4IICallmultipletimes {
	/**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    private int buffPtr = 0;
    private int buffCount = 0;
    private char[] temp = new char[4];
    public int read(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n) {
            //no remaining characters in the temp
            if (buffPtr == 0) {
                buffCount = read4(temp);
            }
            
            if (buffCount == 0) {
                break;// end of file
            }
            
            // copy from temp buffer to buf
            while (ptr < n && buffPtr < buffCount) {
                buf[ptr++] = temp[buffPtr++];
            }
            
            // not because of ptr < n broken out of loop, read to the end of temp buffer
            if (buffPtr >= buffCount) {
                buffPtr = 0;
            }
        }
        return ptr;
    }
}
