package searchForARange;

//Given a sorted array of integers, find the starting and ending position 
//of a given target value.
//
//Your algorithm's runtime complexity must be in the order of O(log n).
//
//If the target is not found in the array, return [-1, -1].
//
//For example,
//Given [5, 7, 7, 8, 8, 10] and target value 8,
//return [3, 4]. 
public class Solution {
	public int[] searchRange(int[] A, int target) {
		int start = -1, end = -1;
		int left = 0, right = A.length - 1;
		int mid = 0;
		while (left <= right) {
			mid = (left + right) / 2;
			if (A[mid] == target)
				break;
			else if (A[mid] > target)
				right = mid - 1;
			else
				left = mid + 1;
		}
		if (left > right) {
			int[] ret = { start, end };
			return ret;
		}

		// now we find the boundary using another two bSearch
		int prevMid = mid;
		left = 0;
		right = prevMid;
		while (left <= right) {
			mid = (left + right) / 2;
			if (A[mid] == target) {
				if (mid == 0) {
					start = 0;
					break;
				} else if (A[mid - 1] < A[mid]) {
					start = mid;
					break;
				} else
					right = mid - 1;
			} else
				left = mid + 1;
		}

		left = prevMid;
		right = A.length - 1;
		while (left <= right) {
			mid = (left + right) / 2;
			if (A[mid] == target) {
				if (mid == A.length - 1) {
					end = A.length - 1;
					break;
				} else if (A[mid + 1] > A[mid]) {
					end = mid;
					break;
				} else
					left = mid + 1;
			} else
				right = mid - 1;
		}

		int[] ret = { start, end };
		return ret;
	}

	public static void main(String[] args) {
		int[] A = { 7 };
		int target = 7;
		int[] ret = new Solution().searchRange(A, target);
		System.out.println(ret);
	}
}
