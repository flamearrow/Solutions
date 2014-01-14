package trappingRainWater;

//Given n non-negative integers representing an elevation map 
// where the width of each bar is 1, 
// compute how much water it is able to trap after raining.
//
//For example,
//Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
//
public class Solution {
	// O(n) approach, have two pointers at head and end
	// when search from left, find the first one that's >= than left board as
	// right board
	// if we can't find right board, then left is higher than all right boards
	// when search from right, find the first one that's >= than right board as
	// left board
	// if we can't find left board, then right is higher than all left boards
	public int trap(int[] A) {
		int left = 0, right = A.length - 1;
		int rightBoard = left + 1;
		int filled = 0;
		int ret = 0;
		while (true) {
			filled = 0;
			while (rightBoard < A.length && A[rightBoard] < A[left]) {
				filled += A[rightBoard];
				rightBoard++;
			}
			// we found a rightBoard >= left
			// calculate how much water we can have
			if (rightBoard < A.length) {
				ret += (rightBoard - left - 1) * A[left] - filled;
				left = rightBoard;
				rightBoard++;
			}
			// we have reached end, there's no board right higher than left
			else {
				rightBoard = left;
				break;
			}
		}
		// now highestBoard should point to the highest board, search from right
		// to left
		int leftBoard = right - 1;
		while (true) {
			filled = 0;
			while (leftBoard >= rightBoard && A[leftBoard] < A[right]) {
				filled += A[leftBoard];
				leftBoard--;
			}
			// we found a leftBoard >= right
			// calculate how much water can we have
			if (leftBoard >= rightBoard) {
				ret += (right - leftBoard - 1) * A[right] - filled;
				right = leftBoard;
				leftBoard--;
			}
			// we have reached right board
			else {
				break;
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2 };
		int ret = new Solution().trap(arr);
		System.out.println(ret);
	}
}
