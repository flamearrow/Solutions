package palindromePermutation;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic 
// permutation could be form.
//
//For example:
//
//Given s = "aabb", return ["abba", "baab"].
//
//Given s = "abc", return [].
public class PalindromePermutation2 {

	public static void main(String[] args) {
		for (String s : generatePalindromes("a")) {
			System.out.println(s);
		}
	}

	public static List<String> generatePalindromes(String s) {
		List<String> ret = new LinkedList<>();
		int[] counts = new int[256];
		for (char c : s.toCharArray()) {
			counts[c]++;
		}
		boolean oddSeen = false;
		int oddChar = -1;
		for (int i = 0; i < 256; i++) {
			if (counts[i] % 2 != 0) {
				if (oddSeen) {
					return ret;
				} else {
					oddSeen = true;
					oddChar = i;
				}
			}
		}

		List<Integer> chars = new LinkedList<>();
		for (int i = 0; i < 256; i++) {
			int count = -1;
			if (i == oddChar) {
				count = (counts[i] - 1) / 2;
			} else {
				count = counts[i] / 2;
			}
			for (int c = 0; c < count; c++) {
				chars.add(i);
			}
		}
		if (chars.size() == 0) {
			if (oddChar > 0) {
				ret.add("" + (char) oddChar);
			}
			return ret;
		}
		Set<String> shuffles = getShuffle(chars);
		for (String ss : shuffles) {
			if (oddChar > 0) {
				ret.add(ss + (char) oddChar + reverse(ss));
			} else {
				ret.add(ss + reverse(ss));
			}
		}
		return ret;
	}

	static String reverse(String ss) {
		StringBuilder sb = new StringBuilder();
		for (int i = ss.length() - 1; i >= 0; i--) {
			sb.append(ss.charAt(i));
		}
		return sb.toString();
	}

	static Set<String> getShuffle(List<Integer> chars) {
		return doShuffle(chars, 0);
	}

	static Set<String> doShuffle(List<Integer> chars, int index) {
		if (index == chars.size() - 1) {
			Set<String> ret = new HashSet<>();
			ret.add("" + (char) chars.get(index).intValue());
			return ret;
		} else {
			Set<String> strings = doShuffle(chars, index + 1);
			int cur = chars.get(index);
			Set<String> ret = new HashSet<>();
			for (String s : strings) {
				for (int i = 0; i <= s.length(); i++) {
					String left = s.substring(0, i);
					String right = s.substring(i);
					ret.add(left + (char) cur + right);
				}
			}
			return ret;
		}
	}
}
