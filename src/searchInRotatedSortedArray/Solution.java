package searchInRotatedSortedArray;

public class Solution {

	public int search(int[] A, int target) {
		// don't recurse when you can use while!
		int l = 0, r = A.length - 1;

		while (l <= r) {
			int m = (l + r) / 2;
			if (A[m] == target)
				return m;
			// left half sorted
			// when A[l] == A[m], we have reach left bound, need to go right
			if (A[l] <= A[m]) {
				// we can definitely go left, go left
				if (A[l] <= target && target < A[m]) {
					r = m - 1;
				} else {
					l = m + 1;
				}
			}
			// right half sorted
			else {
				// we can definitely go right, go right
				if (A[m] < target && target <= A[r]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
		}
		return -1;
	}

	public int searchRec(int[] A, int target) {
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
		if (target > A[mid]) {
			// left half must be smaller than target, need to go right
			if (A[mid] >= A[start]) {
				return doSearch(A, mid + 1, end, target);
			}
			// break point is at left half, need to consider two scenarios
			else {
				// A[mid] < A[start] <= target, go left
				if (target >= A[start]) {
					return doSearch(A, start, mid - 1, target);
				}
				// A[mid] < target < A[start], go right
				else {
					return doSearch(A, mid + 1, end, target);
				}
			}
		} else {
			// right half must be bigger than target, go left
			if (A[mid] <= A[end]) {
				return doSearch(A, start, mid - 1, target);
			} else {
				// target <= A[end] < A[mid], go right
				if (target <= A[end]) {
					return doSearch(A, mid + 1, end, target);
				}
				// A[end] < target < A[mid], go left
				else {
					return doSearch(A, start, mid - 1, target);
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] A = { 3, 1 };
		// int target = 3;
		for (int target : A)
			System.out.println(new Solution().search(A, target));
	}
}
