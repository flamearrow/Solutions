package wiggleSort;

//Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
//
//For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
public class Solution {
	public static void main(String[] args) {
		int[] nums = { 3, 4, 2, 1, 6, 4 };
		wiggleSort(nums);
		for (int i : nums) {
			System.out.print(i + " ");
		}
	}

	public static void wiggleSort(int[] nums) {
		boolean bigger = true;
		for (int i = 1; i < nums.length; i++) {
			if (bigger && nums[i] < nums[i - 1] || !bigger
					&& nums[i] > nums[i - 1]) {
				int tmp = nums[i];
				nums[i] = nums[i - 1];
				nums[i - 1] = tmp;
			}
			bigger = !bigger;
		}
	}
}
