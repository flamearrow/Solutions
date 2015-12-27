package minimumSizeSubarraySum;

//Given an array of n positive integers and a positive integer s, 
// find the minimal length of a subarray of which the sum ≥ s. If there isn't one, 
//return 0 instead.
//
//For example, given the array [2,3,1,2,4,3] and s = 7,
//the subarray [4,3] has the minimal length under the problem constraint.
public class Solution {
	public static void main(String[] args) {
		int[] arr = { 2, 3, 1, 2, 4, 3 };
		System.out.println(new Solution().minSubArrayLen(7, arr));
	}

	public int minSubArrayLen(int s, int[] nums) {
		int left = 0, right = 0;
		int min = Integer.MAX_VALUE;
		int curSum = 0;
		while (left < nums.length) {
			if (curSum < s && right < nums.length) {
				curSum += nums[right];
				right++;
			} else if (curSum >= s) {
				min = Math.min(min, right - left);
				curSum -= nums[left];
				left++;
			} else {
				// right go beyond boundary
				break;
			}
		}
		return min == Integer.MAX_VALUE ? 0 : min;
	}
}
