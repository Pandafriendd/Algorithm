/*
The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Example 1:
Input: buf = "abc", n = 4
Output: "abc"
Explanation: The actual number of characters read is 3, which is "abc".

Example 2:
Input: buf = "abcde", n = 5 
Output: "abcde"
Note:
The read function will only be called once for each test case.
 */



/*
read function returns an int, and the result is stored in the buf[]. 
We are forced to read 4 characters at a time, but you can discard characters that you don't want. 
For example, if n = 6, then call read4 twice and dump the last 2 characters. 
By the way, the result from read4 is stored in buf[].

In reality, hardware or system may have constraints for read from file system. 
eg. hardeware DMA engine may fetch data 128 bytes from disk. 
Another example is that loading 4-bytes in aligned ddr should be obviously faster than load 1 byte 4 times. It is exactly same as read4 problem
 */
public class ReadNCharactersGivenRead4 extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
	public int read(char[] buf, int n) {
		boolean eof = false;      // end of file flag
		int total = 0;            // total bytes have read
		char[] tmp = new char[4]; // temp buffer
		  
		while (!eof && total < n) {
		  int count = read4(tmp);
		    
		  // check if it's the end of the file
		  eof = count < 4;
		    
		  // get the actual count
		  // it's because you can't always assume you read 4 bytes. Say you had an array of 7 bytes. You would read the first 4, then 3 would be left.
		  // So you need to minimize the count variable so that when you're copying from the temp buffer, you don't go out of bounds or incorrectly copy something.
		  count = Math.min(count, n - total);
		    
		  // copy from temp buffer to buf
		  for (int i = 0; i < count; i++) 
		    buf[total++] = tmp[i];
		}
		  
		return total;
	}
}
