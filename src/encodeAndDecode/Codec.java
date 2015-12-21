package encodeAndDecode;

import java.util.LinkedList;
import java.util.List;
//Design an algorithm to encode a list of strings to a string. 
// The encoded string is then sent over the network and is decoded back to the original list of strings.
//
//Machine 1 (sender) has the function:
//
//string encode(vector<string> strs) {
//  // ... your code
//  return encoded_string;
//}
//Machine 2 (receiver) has the function:
//vector<string> decode(string s) {
//  //... your code
//  return strs;
//}
//So Machine 1 does:
//
//string encoded_string = encode(strs);
//and Machine 2 does:
//
//vector<string> strs2 = decode(encoded_string);
//strs2 in Machine 2 should be the same as strs in Machine 1.
//
//Implement the encode and decode methods.
//
//Note:
//The string may contain any possible characters out of 256 valid ascii characters. 
// Your algorithm should be generalized enough to work on any possible characters.
//Do not use class member/global/static variables to store states. 
// Your encode and decode algorithms should be stateless.
//Do not rely on any library method such as eval or serialize methods. 
// You should implement your own encode/decode algorithm.

public class Codec {
	// encode it as lenthg/actualStr
	public String encode(List<String> strs) {
		StringBuilder sb = new StringBuilder();
		for (String s : strs) {
			sb.append(s.length() + "/" + s);
		}
		return sb.toString();
	}

	// Decodes a single string to a list of strings.
	public List<String> decode(String s) {
		List<String> ret = new LinkedList<>();
		while (s.length() > 0) {
			int index = s.indexOf('/');
			int nextLen = Integer.valueOf(s.substring(0, index));
			int newStart = index + nextLen + 1;
			ret.add(s.substring(index + 1, newStart));
			s = s.substring(newStart);
		}
		return ret;
	}

	String getsubstring(String s, int start, int end) {
		if (start >= s.length()) {
			return "";
		} else {
			return s.substring(start, end);
		}
	}

	String getsubstring(String s, int start) {
		if (start >= s.length()) {
			return null;
		} else {
			return s.substring(start);
		}
	}
}
