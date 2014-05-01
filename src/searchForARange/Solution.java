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
	public int[] searchRange2(int[] A, int target) {
		int start = 0, end = A.length - 1;
		// first bSearch: check existence
		while (start <= end) {
			int mid = (start + end) / 2;
			if (A[mid] == target)
				break;
			if (A[mid] > target) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		if (start > end) {
			int[] ret = { -1, -1 };
			return ret;
		}
		// second bSearch: check left bound
		int left = -1, right = -1;
		start = 0;
		end = A.length - 1;

		while (start <= end) {
			int mid = (start + end) / 2;
			if (A[mid] == target && (mid == 0 || A[mid - 1] != target)) {
				left = mid;
				break;
			}
			if (A[mid] >= target) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}

		// third bSearch: check right bound
		start = 0;
		end = A.length - 1;

		while (start <= end) {
			int mid = (start + end) / 2;
			if (A[mid] == target
					&& (mid == A.length - 1 || A[mid + 1] != target)) {
				right = mid;
				break;
			}
			if (A[mid] <= target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		int[] ret = new int[2];
		ret[0] = left;
		ret[1] = right;
		return ret;
	}

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
