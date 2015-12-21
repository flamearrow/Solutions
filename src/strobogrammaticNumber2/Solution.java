package strobogrammaticNumber2;

import java.util.LinkedList;
import java.util.List;

//A strobogrammatic number is a number that looks the same 
// when rotated 180 degrees (looked at upside down).
//
//Find all strobogrammatic numbers that are of length = n.
//
//For example,
//Given n = 2, return ["11","69","88","96"].
public class Solution {
	public List<String> findStrobogrammatic(int n) {
		char[] chars = { '0', '1', '8' };
		int half = n / 2;
		List<String> halfStrs = generateHalfStrs(half);
		List<String> ret = new LinkedList<String>();
		if (n % 2 == 0) {
			for (String s : halfStrs) {
				if (s.startsWith("0")) {
					continue;
				}
				ret.add(s + reverse(s));
			}
		} else {
			for (String s : halfStrs) {
				if (s.startsWith("0")) {
					continue;
				}
				for (char c : chars)
					ret.add(s + c + reverse(s));
			}
		}
		return ret;
	}

	String reverse(String s) {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (c == '6') {
				sb.insert(0, '9');
			} else if (c == '9') {
				sb.insert(0, '6');
			} else {
				sb.insert(0, c);
			}
		}
		return sb.toString();
	}

	List<String> generateHalfStrs(int len) {
		List<String> ret = new LinkedList<String>();
		doFill(ret, 0, len, "");
		return ret;
	}

	void doFill(List<String> ret, int now, int length, String cur) {
		if (now == length) {
			ret.add(cur);
			return;
		}
		doFill(ret, now + 1, length, cur + '0');
		doFill(ret, now + 1, length, cur + '1');
		doFill(ret, now + 1, length, cur + '8');
		doFill(ret, now + 1, length, cur + '6');
		doFill(ret, now + 1, length, cur + '9');
	}
}
