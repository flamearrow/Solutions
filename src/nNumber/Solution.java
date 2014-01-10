package nNumber;

import java.util.LinkedList;
import java.util.List;

// given n, print all numbers with n digits that left bit is larger than right bit
//  i.e n == 1: 1,2,3,4,5,6,7,8,9
//      n == 2: 12, 13, 14.... , 23, 24, 25..... ...81, 82, ....89
public class Solution {
	// just recurse
	List<String> nInt(int n) {
		LinkedList<String> ret = new LinkedList<String>();
		if (n < 1 || n > 9)
			return ret;
		if (n == 1) {
			for (int i = 1; i < 10; i++) {
				ret.add("" + i);
			}
		} else {
			List<String> prevRst = nInt(n - 1);
			for (String s : prevRst) {
				int lastDigit = s.charAt(s.length() - 1) - '0';
				for (int newDigit = lastDigit + 1; newDigit < 10; newDigit++) {
					String newStr = s + newDigit;
					ret.add(newStr);
				}
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		for (String s : new Solution().nInt(3)) {
			System.out.print(s + " ");
		}
	}
}
