package searchInRotatedSortedArray;

//Suppose a sorted array is rotated at some pivot unknown to you beforehand.
//
//(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
//
//You are given a target value to search. If found in the array return its index, otherwise return -1.
//
//You may assume no duplicate exists in the array.
public class Solution {
	public int search(int[] A, int target) {
		return doSearch(A, 0, A.length - 1, target);
	}

	// compare [target, mid[, and [target, left] or [target, right]
	// to decide go left or right

	// note this works for regular sorted array as well
	int doSearch(int[] A, int start, int end, int target) {
		if (start > end)
			return -1;
		int mid = (start + end) / 2;
		if (A[mid] == target)
			return mid;
		if (A[mid] < target) {
			if (target > A[end]) {
				return doSearch(A, start, mid - 1, target);
			} else {
				return doSearch(A, mid + 1, end, target);
			}
		} else {
			if (A[start] > target) {
				return doSearch(A, mid + 1, end, target);
			} else {
				return doSearch(A, start, mid - 1, target);
			}
		}
	}

	public static void main(String[] args) {
		int[] A = { 1, 2, 3, 4, 5 };
		int target = 6;
		System.out.println(new Solution().search(A, target));
	}
}
