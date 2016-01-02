package findDupNum;

//Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
//
//Note:
//You must not modify the array (assume the array is read only).
//You must use only constant, O(1) extra space.
//Your runtime complexity should be less than O(n2).
//There is only one duplicate number in the array, but it could be repeated more than once.
public class Solution {
	// O(n log n)
	// BS, since from 1 to n there' re up to different numbers,
	// if we find mid, then there should be half of the range bigger than mid,
	// half smaller than mid
	// since there's a dup, the count of numbers bigger/ smaller than mid should
	// be bigger
	// according to the count of the breaking half, we do a binary search
	public int findDuplicate(int[] nums) {
		int start = 1;
		int end = nums.length - 1;
		while (start < end) {
			int mid = (start + end) / 2;
			int count = 0;
			for (int i : nums) {
				if (i <= mid) {
					count++;
				}
			}
			if (count > mid) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}
}
