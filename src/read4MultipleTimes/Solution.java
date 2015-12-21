package read4MultipleTimes;

//The API: int read4(char *buf) reads 4 characters at a time from a file.
//
//The return value is the actual number of characters read. For example, 
//it returns 3 if there is only 3 characters left in the file.
//
//By using the read4 API, implement the function int read(char *buf, int n) 
//that reads n characters from the file.
//Note:
// The read function may be called multiple times.
class Read4 {
	int read4(char[] buf) {
		return 23;
	}
}

public class Solution extends Read4 {
	private char[] internalBuf;

	public int read(char[] buf, int n) {
		int needToRead = internalBuf == null ? n : n - internalBuf.length;
		if (needToRead < n) {
			System.arraycopy(internalBuf, 0, buf, 0, internalBuf.length);
		}
		int callCnt = needToRead / 4 + 1;
		int readCntAll = n - needToRead;
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
			internalBuf = null;
			return readCntAll;
		} else {
			int diff = readCntAll - n;
			if (diff > 0) {
				internalBuf = new char[diff];
				System.arraycopy(buf, n, internalBuf, 0, diff);
			} else {
				internalBuf = null;
			}
			return n;
		}
	}
}
