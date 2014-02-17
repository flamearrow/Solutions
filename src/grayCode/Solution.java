package grayCode;

import java.util.ArrayList;

//The gray code is a binary numeral system where two successive values differ in only one bit.
//
//Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
//
//For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
//
//00 - 0
//01 - 1
//11 - 3
//10 - 2
//Note:
//For a given n, a gray code sequence is not uniquely defined.
//
//For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
//
//For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
public class Solution {
	public ArrayList<Integer> grayCode(int n) {
		// from n = 1 to 2^n, find the least significant 1, and flip that bit to
		// the current number
		ArrayList<Integer> ret = new ArrayList<Integer>();
		int now = 0;
		ret.add(now);
		for (int i = 1; i < Math.pow(2, n); i++) {
			int bitToFlip = 0;
			while ((i & (1 << bitToFlip)) == 0) {
				bitToFlip++;
			}
			now = (now ^ (1 << bitToFlip));
			ret.add(now);
		}
		return ret;
	}

	public ArrayList<Integer> grayCodeFancy(int n) {
		// from n = 1 to 2^n, find the least significant 1, and flip that bit to
		// the current number
		ArrayList<Integer> ret = new ArrayList<Integer>();
		int now = 0;
		ret.add(now);
		int max = (1 << n) - 1;
		for (int i = 1; i <= max; i++) {
			// to find the least significant 1, use this:
			// i&(-i)
			int mask = max & (i & (-i));
			now = now ^ mask;
			ret.add(now);
		}
		return ret;
	}

	public static void main(String[] args) {
		test(8);
	}

	public static void test(int len) {
		ArrayList<Integer> ret = new Solution().grayCode(len);
		ArrayList<Integer> ret2 = new Solution().grayCodeFancy(len);
		int size = ret2.size();
		for (int i = 0; i < size; i++) {
			// can't just compare ret.get(i) == ret2.get(i)
			// It may be worth noting that autoboxing is guaranteed to return the same object for 
			// integral values in the range [-128, 127], but an implementation may, 
			// at its discretion, cache values outside of that range.
			if (ret.get(i).intValue() != ret2.get(i).intValue()) {
				System.out.println("i=" + i);
				throw new RuntimeException() {
					private static final long serialVersionUID = 1L;
				};
			}
		}
		System.out.println("success");
	}
}
