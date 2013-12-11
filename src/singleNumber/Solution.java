package singleNumber;

//Given an array of integers, every element appears twice except for one. Find that single one.
//
//Note:
//Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
public class Solution {
	public int singleNumber(int[] A) {
		int ret = 0;
		for (int i : A) {
			ret ^= i;
		}
		return ret;
	}

	public static void main(String[] args) {
		int[] A = {};
		System.out.println(new Solution().singleNumber(A));
	}

}
