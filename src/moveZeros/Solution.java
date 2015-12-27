package moveZeros;

//Given an array nums, write a function to move all 0's to the end of 
// it while maintaining the relative order of the non-zero elements.
//
//For example, given nums = [0, 1, 0, 3, 12], after calling your function, 
// nums should be [1, 3, 12, 0, 0].
//
//Note:
//You must do this in-place without making a copy of the array.
//Minimize the total number of operations.
public class Solution {
	public void moveZeroes(int[] nums) {
		int head = 0, cur = 0;
		// use cur to find non zero, move it to head
		while (cur < nums.length) {
			if (nums[cur] != 0) {
				nums[head] = nums[cur];
				head++;
			}
			cur++;
		}
		while (head < nums.length) {
			nums[head] = 0;
			head++;
		}
	}
}
