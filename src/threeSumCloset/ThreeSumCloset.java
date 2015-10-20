package threeSumCloset;

import java.util.Arrays;

public class ThreeSumCloset {

	public static int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int minDiff = Integer.MAX_VALUE;
		int ret = 0;
		for (int i = 0; i < nums.length - 2; i++) {
			int start = nums[i];
			int left = i + 1;
			int right = nums.length - 1;
			while (left < right) {
				int sum = start + nums[left] + nums[right];
				int diff = Math.abs(sum - target);
				if (diff < minDiff) {
					minDiff = diff;
					ret = sum;
				}
				if (sum > target) {
					right--;
				} else if (sum < target) {
					left++;
				} else {
					return target;
				}
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		int[] nums = {0,0,0};
		int target = 1;
		System.out.println(threeSumClosest(nums, target));
	}
}
