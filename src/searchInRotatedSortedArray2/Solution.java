package searchInRotatedSortedArray2;

//Follow up for "Search in Rotated Sorted Array":
//What if duplicates are allowed?
//
//Would this affect the run-time complexity? How and why?
//
//Write a function to determine if a given target is in the array.
public class Solution {
	public boolean search(int[] A, int target) {
		// don't recurse when you can use while!
		int l = 0, r = A.length - 1;

		while (l <= r) {
			int m = (l + r) / 2;
			if (A[m] == target)
				return true;
			// left half sorted
			if (A[l] < A[m]) {
				// we can definitely go left, go left
				if (A[l] <= target && target < A[m]) {
					r = m - 1;
				} else {
					l = m + 1;
				}
			}
			// right half sorted
			else if (A[l] > A[m]) {
				// we can definitely go right, go right
				if (A[m] < target && target <= A[r]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			// we either hit left bound that isn't the answer, or it's a dup, in
			// this case we just skipped the left one
			else {
				l++;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[] A = { 3, 1 };
		// int target = 3;
		for (int i : A)
			System.out.println(new Solution().search(A, i));
	}
}
