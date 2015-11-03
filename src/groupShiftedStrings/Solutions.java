package groupShiftedStrings;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//Given a string, we can "shift" each of its letter to its successive letter, 
// for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
//"abc" -> "bcd" -> ... -> "xyz"
//Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
//For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
//Return:
//[
//  ["abc","bcd","xyz"],
//  ["az","ba"],
//  ["acef"],
//  ["a","z"]
//]
//Note: For the return value, each inner list's elements must follow the lexicographic order.

public class Solutions {
	public static void main(String[] args) {
		String[] strings = { "abc", "bcd", "acef", "xyz", "az", "ba", "a", "z",
				"ace", "bdf" };
		Solutions s = new Solutions();
		s.gropuStrings(strings);
		// String ss = "abcdefghijklmnopqrstuvwxyz";
		// for (char c : ss.toCharArray()) {
		// System.out.println(s.nextChar(c, false));
		// }
	}

	public void gropuStrings(String[] strings) {
		Map<String, List<String>> set = new HashMap<>();
		for (String s : strings) {
			String index = getIndex2(s);
			if (!set.containsKey(index)) {
				List<String> list = new LinkedList<String>();
				set.put(index, list);
			}
			set.get(index).add(s);
		}
		for (String key : set.keySet()) {
			List<String> list = set.get(key);
			System.out.println("key: " + key);
			for (String s : list) {
				System.out.print(s + " ");
			}
			System.out.println();
			System.out.println();
		}
	}

	// idea is to offset all chars to start with a
	// find the difference of first char to 'a', then apply the difference to
	// all other chars, note if it's -1 should add 26
	String getIndex2(String s) {
		short distance = (short) (s.charAt(0) - 'a');
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			short tooAddShort = (c - distance) < (short) 'a' ? (short) ((c - distance) + 26)
					: (short) (c - distance);
			char cToAppend = (char) (tooAddShort);
			sb.append(cToAppend);
		}
		return sb.toString();
	}

	String getIndex(String s) {
		if (s.length() == 1)
			return "a";
		boolean isRight = false;
		if (s.charAt(1) == nextChar(s.charAt(0), true)) {
			isRight = true;
		} else if (s.charAt(1) == nextChar(s.charAt(0), false)) {
			isRight = false;
		} else {
			return s;
		}
		for (int i = 1; i < s.length(); i++) {
			if (isRight && s.charAt(i) == nextChar(s.charAt(i - 1), true)) {
				continue;
			} else if (!isRight
					&& s.charAt(i) == nextChar(s.charAt(i - 1), false)) {
				continue;
			} else {
				return s;
			}
		}
		return generateKey(s.length(), isRight);
	}

	String generateKey(int len, boolean isRight) {
		StringBuilder sb = new StringBuilder();
		char cur = isRight ? 'z' : 'a';
		while (len > 0) {
			char next = nextChar(cur, isRight);
			sb.append(next);
			cur = next;
			len--;
		}
		return sb.toString();
	}

	char nextChar(char cur, boolean isRight) {
		if (isRight) {
			return (char) ((cur - 'a' + 1) % 26 + 'a');
		} else {
			return (char) ((cur - 'a' + 25) % 26 + 'a');
		}
	}

}
