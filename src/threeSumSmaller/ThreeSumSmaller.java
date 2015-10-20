package threeSumSmaller;

import java.util.Arrays;

//Given an array of n integers nums and a target, find the number of index triplets 
//i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.
//
//For example, given nums = [-2, 0, 1, 3], and target = 2.
//
//Return 2. Because there are two triplets which sums are less than 2:
//
//[-2, 0, 1]
//[-2, 0, 3]
//Follow up:
//Could you solve it in O(n2) runtime?
public class ThreeSumSmaller {
	public int threeSumSmaller(int[] nums, int target) {
		Arrays.sort(nums);
		int ret = 0;
		for (int i = 0; i < nums.length - 2; i++) {
			int start = nums[i];
			int left = i + 1;
			int right = nums.length - 1;
			while (left < right) {
				int sum = start + nums[left] + nums[right];
				if (sum >= target) {
					right--;
				} else if (sum < target) {
					// ** combination from (left,left+1) (left,left+2) ... (left,right)
					ret += right - left;
					left++;
				}
			}
		}
		return ret;
	}
}
