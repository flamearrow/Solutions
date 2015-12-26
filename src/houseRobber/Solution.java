package houseRobber;

//You are a professional robber planning to rob houses along a street. 
//Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent
// houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
//
//Given a list of non-negative integers representing the amount of money of each house, 
//determine the maximum amount of money you can rob tonight without alerting the police.
public class Solution {
	public int rob(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		int[] dp = new int[nums.length];
		boolean[] bDp = new boolean[nums.length];
		dp[0] = nums[0];
		bDp[0] = false;
		dp[1] = Math.max(nums[0], nums[1]);
		bDp[1] = nums[1] > nums[0];
		for (int i = 2; i < nums.length; i++) {
			if (bDp[i - 1]) {
				// don't rob i-1
				int v1 = dp[i - 2] + nums[i];
				// rob i-1
				int v2 = dp[i - 1];
				if (v1 > v2) {
					dp[i] = v1;
					bDp[i] = true;
				} else {
					dp[i] = v2;
					bDp[i] = false;
				}
			} else {
				bDp[i] = true;
				dp[i] = dp[i - 1] + nums[i];
			}
		}
		return dp[nums.length - 1];
	}
}
