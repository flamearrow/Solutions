package productOfArraySelf;

import java.util.Arrays;

//Given an array of n integers where n > 1, nums, 
// return an array output such that output[i] is equal to the product of all 
// the elements of nums except nums[i].
//
//Solve it without division and in O(n).
//
//For example, given [1,2,3,4], return [24,12,8,6].
//
//Follow up:
//Could you solve it with constant space complexity? 
// (Note: The output array does not count as extra space for the purpose of 
// space complexity analysis.)
public class Solution {
	public int[] productExceptSelf(int[] nums) {
		// two pass rst[i] is the product of all elements left to i and
		// all elements right to i
		int[] rst = new int[nums.length];
		Arrays.fill(rst, 1);
		for (int i = 1; i < nums.length; i++) {
			rst[i] = rst[i - 1] * nums[i - 1];
		}

		int right = 1;
		for (int i = nums.length - 2; i >= 0; i--) {
			right = right * nums[i + 1];
			rst[i] *= right;
		}
		return rst;
	}
}
