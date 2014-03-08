package searchInsertPosition;

//Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
//
//You may assume no duplicates in the array.
//
//Here are few examples.
//[1,3,5,6], 5 : 2
//[1,3,5,6], 2 : 1
//[1,3,5,6], 7 : 4
//[1,3,5,6], 0 : 0 
public class Solution {
	// if target is in arr, return its index, otherwise return the index to be inserted
	int searchInsert2(int[] arr, int target) {
		int start = 0, end = arr.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (arr[mid] == target)
				return mid;
			if (target < arr[mid]) {
				end = mid - 1;
			} else if (target > arr[mid]) {
				start = mid + 1;
			}
		}
		// bserach always return the start when loop breaks
		return start;

	}

	public int searchInsert(int[] A, int target) {
		int left = 0, right = A.length - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (A[mid] == target)
				return mid;
			else if (A[mid] < target)
				left = mid + 1;
			else if (A[mid] > target)
				right = mid - 1;
		}
		if (left == A.length) {
			return A.length;
		}
		if (right < 0)
			return 0;
		if (target > A[right])
			return left;
		else
			return right;
	}

	public static void main(String[] args) {
		int[] A = { 1, 3, 5, 6 };
		System.out.println(new Solution().searchInsert2(A, 5));
		System.out.println(new Solution().searchInsert2(A, 2));
		System.out.println(new Solution().searchInsert2(A, 7));
		System.out.println(new Solution().searchInsert2(A, 0));
	}
}
