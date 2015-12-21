package read4;

//The API: int read4(char *buf) reads 4 characters at a time from a file.
//
//The return value is the actual number of characters read. For example, 
// it returns 3 if there is only 3 characters left in the file.
//
//By using the read4 API, implement the function int read(char *buf, int n) 
// that reads n characters from the file.
//
//Note:
//The read function will only be called once for each test case.
class Read4 {
	int read4(char[] buf) {
		return 23;
	}
}

public class Solution extends Read4 {
	/**
	 * @param buf Destination buffer
	 * @param n Maximum number of characters to read
	 * @return The number of characters read
	 */
	public int read(char[] buf, int n) {
		int callCnt = n / 4 + 1;
		int readCntAll = 0;
		while (callCnt > 0) {
			char[] tmpRst = new char[4];
			int readCnt = read4(tmpRst);
			System.arraycopy(tmpRst, 0, buf, readCntAll, readCnt);
			readCntAll += readCnt;
			// eof
			if (readCnt < 4) {
				break;
			}
			callCnt--;
		}
		if (readCntAll < n) {
			return readCntAll;
		} else {
			return n;
		}
	}
}
