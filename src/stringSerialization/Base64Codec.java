package stringSerialization;

import java.util.Random;

// a simple base 64 codec
// encoded string length is always a multiple of 4, may have one or two '=' as padding
// to encode/decode, first we need to find the ending bit/char of encoding, decoding
//  the end position are dictated by input length%3
// there's still some performance enhancement could be done

// basic idea of Base64Codec:
//  Encoding: Treat the input string as a sequence of 8-bit chars, take 6-bit for each round until there's no more. 
//  Each 6 bit can be mapped to a char in BASE64TABLE. 
//  There might be 3 scenarios:
//    len%3 == 0: in this case the end of last 6 bit would end at the last input char
//    len%3 == 1: in this case the end of last 6 bit would end at the second bit of last input char
//    len%3 == 2: in this case the end of last 6 bit would end at the fifth bit of last input char
//  We pad certain number of '=' for the later two scenarios 
//    make sure shifting the left over bits to the most significant bits to the last valid six-bit
// 
//  Decoding: The reverse way: first convert the string into a array of int ranging from 0 to 63
//    then read each 8-bit and convert them back into a char  
public class Base64Codec {
	// the base table starts with upper case chars
	static String BASE64TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	static char TERMINAL_CHAR = '=';

	// note if len%3 == 2 we pad "="
	//      if len%3 == 1 we pad "=="
	// it's also find to pad with other special string, this is just a conversion, i.e other code is using the same padding
	// the left over bits are shift to the most significant bits of a 6 bits
	String encode2(String input) {
		char[] bytes = input.toCharArray();
		int len = bytes.length;
		boolean padOne = bytes.length % 3 == 2;
		boolean padTwo = bytes.length % 3 == 1;

		int curByte = 0, curBit = 0;
		int termByte = len - 1, terBit = padOne ? 4 : (padTwo ? 6 : 8);

		StringBuilder sb = new StringBuilder();

		int next = 0;
		while (curByte != termByte || curBit != terBit) {
			next = 0;
			for (int i = 0; i < 6; i++) {
				next <<= 1;
				next |= (((bytes[curByte] & (1 << (7 - curBit))) > 0) ? 1 : 0);
				curBit++;
				if (curBit == 8) {
					curBit = 0;
					curByte++;
				}
			}
			sb.append(BASE64TABLE.charAt(next));
			if (curByte == bytes.length)
				break;
		}

		if (padOne) {
			next = ((bytes[termByte] & 0x0f) << 2);
			sb.append(BASE64TABLE.charAt(next));
			sb.append(TERMINAL_CHAR);
		}

		if (padTwo) {
			next = ((bytes[termByte] & 0x03) << 4);
			sb.append(BASE64TABLE.charAt(next));
			sb.append(TERMINAL_CHAR);
			sb.append(TERMINAL_CHAR);
		}

		return sb.toString();
	}

	// we need to first convert input back to an int array so that we get the correct bits
	String decode2(String input) {
		byte[] bytes = input.getBytes();
		int len = input.length();
		int[] indices = new int[len];
		for (int i = 0; i < len; i++) {
			indices[i] = BASE64TABLE.indexOf((char) bytes[i]);
		}
		boolean padOne = false, padTwo = false;
		if (len > 1 && bytes[len - 2] == '=') {
			padTwo = true;
		} else if (bytes[len - 1] == '=') {
			padOne = true;
		}

		int lastChar = padOne ? len - 2 : padTwo ? len - 3 : len;
		int lastBit = padOne ? 4 : padTwo ? 2 : 0;
		int curInt = 0, curBit = 0;
		StringBuilder sb = new StringBuilder();
		// when reading, make sure we always read 8x bits
		while (curInt != lastChar || curBit != lastBit) {
			char next = 0;
			for (int i = 0; i < 8; i++) {
				next <<= 1;
				next |= ((indices[curInt] >> (5 - curBit)) & 1);
				curBit++;
				if (curBit == 6) {
					curBit = 0;
					curInt++;
				}
			}
			sb.append(next);
		}
		return sb.toString();
	}

	// padding strategy:
	// if the length of input is not multiple of 3, pad (len % 3) special chars in the end, use = here
	// i.e 'mlgb' would have a encoding of BwXNyG==
	String encode(String input) {
		if (input == null || input.equals(""))
			return "";
		StringBuilder sb = new StringBuilder();
		char[] rawArr = input.toCharArray();
		int curBit = 0, curChar = 0;
		int endBit = 0, endChar = rawArr.length;
		boolean padOne = false, padTwo = false;

		// when we need to pad one, we stop at the 4th bit of last string
		// use the lest over 4
		// i.e 'ml'
		//     |0 1 1 0 1 1 0 1|0 1 1 0 [stop here]1 1 0 0|
		if (input.length() % 3 == 2) {
			padOne = true;
			endChar = input.length() - 1;
			endBit = 4;
		}
		// when we need to pad two, we stop at the 6th bit of last string
		// use the lest over 2
		// i.e 'm'
		//     |0 1 1 0 1 1 [stop here]0 1|
		else if (input.length() % 3 == 1) {
			padTwo = true;
			endChar = input.length() - 1;
			endBit = 6;
		}

		int nextIndex = 0;
		// if we have exhausted the string, need stop and pad if necessary
		while (!(curBit == endBit && curChar == endChar)) {
			nextIndex = 0;
			for (int i = 0; i < 6; i++) {
				nextIndex <<= 1;
				nextIndex |= getBit(rawArr[curChar], curBit);
				curBit++;
				if (curBit == 8) {
					curBit = 0;
					curChar++;
				}
			}
			sb.append(BASE64TABLE.charAt(nextIndex));
		}

		nextIndex = 0;
		if (padOne) {
			// further left shift nextIndex two bits to make the last four to top
			// i.e 'ml'
			// 	now we have |0 1 1 0 1 1| |0 1 0 1 1 0| |1 1 0 0 (0 0)|
			// 15 masks the last 4 bits
			nextIndex = (15 & rawArr[rawArr.length - 1]) << 2;
			sb.append(BASE64TABLE.charAt(nextIndex));
			sb.append(TERMINAL_CHAR);
		} else if (padTwo) {
			// further left shift nextIndex four bits to make the last two to top
			// i.e 'm'
			// 	now we have |0 1 1 0 1 1| |0 1 (0 0 0 0)|
			// 3 masks the last 2 bits
			nextIndex = (3 & rawArr[rawArr.length - 1]) << 4;
			sb.append(BASE64TABLE.charAt(nextIndex));
			sb.append(TERMINAL_CHAR);
			sb.append(TERMINAL_CHAR);
		}
		return sb.toString();
	}

	// return the ith bit of c(from left to right)
	int getBit(char c, int bit) {
		return ((1 << (7 - bit)) & c) > 0 ? 1 : 0;
	}

	// return the ith bit of i(from left to right, start from the 26th bit)
	int getBit(int i, int bit) {
		return ((1 << (5 - bit)) & i) > 0 ? 1 : 0;
	}

	// according to the previous padding strategy,
	// when there is 1 '=', we go to the 4th bit of last but one char(or first non = char)
	// when there are 2 '=', we go to the 2nd bit of last but two char(or first non = char)
	String decode(String input) {
		StringBuilder sb = new StringBuilder();
		int[] rawArr = new int[input.length()];
		for (int i = 0; i < input.length(); i++) {
			rawArr[i] = BASE64TABLE.indexOf(input.charAt(i));
		}

		int curBit = 0, curInt = 0;
		int endBit = 0, endInt = rawArr.length;

		// find the exact position we need to stop at
		// pad two '='
		// i.e 'm'
		//     |0 1 1 0 1 1| |0 1 [end here]0 0 0 0| |0 0 0 0 0 0| |0 0 0 0 0 0|
		if (input.charAt(input.length() - 2) == '=') {
			endBit = 2;
			endInt = rawArr.length - 3;
		}
		// pad one '='
		// i.e 'ml'
		//     |0 1 1 0 1 1| |0 1 0 1 1 0| |1 1 0 0 [end here]0 0| |0 0 0 0 0 0|
		else if (input.charAt(input.length() - 1) == '=') {
			endBit = 4;
			endInt = rawArr.length - 2;
		}

		while (!(curInt == endInt && curBit == endBit)) {
			char nextChar = 0;
			for (int i = 0; i < 8; i++) {
				nextChar <<= 1;
				nextChar |= getBit(rawArr[curInt], curBit);
				//				nextChar |= ((rawArr[curInt] >> (5 - curBit)) & 1);
				curBit++;
				if (curBit == 6) {
					curBit = 0;
					curInt++;
				}
			}
			sb.append(nextChar);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Base64Codec codec = new Base64Codec();
		String encoded = codec.encode2("mlg");
		System.out.println(encoded);
		String decoded = codec.decode2(encoded);
		System.out.println(decoded);
		doTest();
	}

	static void doTest() {
		System.out.println("starting...");
		String encoded = null, decoded = null, original = null;
		try {
			Base64Codec codec = new Base64Codec();
			int len = 200;
			int tenth = len / 10;
			for (int i = 1; i < len; i++) {
				original = generateRandomString(i);
				encoded = codec.encode2(original);
				decoded = codec.decode2(encoded);
				assertEquals(encoded, decoded, original);
				if (i % tenth == 0) {
					System.out.println(i / tenth + "0% done... ");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("encoded: " + encoded + " decoded: " + decoded
					+ " original: " + original);
		}

		System.out.println("succeed!");
	}

	static String generateRandomString(int length) {
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append((char) rand.nextInt(256));
		}
		return sb.toString();
	}

	static void assertEquals(String encoded, String decoded, String original)
			throws FailException {
		if (!original.equals(decoded)) {
			System.out
					.println("not equal!" + "\n   encoded: " + encoded
							+ "\n   decoded: " + decoded + "\n   original: "
							+ original);
		}
	}
}

class FailException extends Exception {
	private static final long serialVersionUID = 1L;

}
