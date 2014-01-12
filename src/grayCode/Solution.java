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

	public static void main(String[] args) {
		ArrayList<Integer> ret = new Solution().grayCode(0);
		System.out.println(ret);
	}

}
