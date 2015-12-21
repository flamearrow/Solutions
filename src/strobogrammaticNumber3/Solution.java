package strobogrammaticNumber3;

import java.util.LinkedList;
import java.util.List;

//A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
//
//Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.
//
//For example,
//Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.
//
//Note:
//Because the range might be a large number, the low and high numbers are represented as string.
public class Solution {
	// instead of looping from low - high
	// we loop from length(low) to length(high) and generate all strings with
	// the same length
	// the funny catch to compare string represented numbers: just use
	// String.compareTo() method to determine
	// which one is biggger
	public static void main(String[] args) {
		System.out.println(new Solution().strobogrammaticInRange("0", "0"));
	}

	public int strobogrammaticInRange(String low, String high) {
		int ret = 0;
		for (int len = low.length(); len <= high.length(); len++) {
			List<String> list = findStrobogrammatic(len);
			int toAdd = list.size();
			if (len == low.length()) {
				for (String s : list) {
					if (s.compareTo(low) < 0) {
						toAdd--;
					}
				}
			}
			if (len == high.length()) {
				for (String s : list) {
					if (s.compareTo(high) > 0) {
						toAdd--;
					}
				}
			}
			ret += toAdd;
		}
		return ret;
	}

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
