package epi.bSearch;

// 11.1
// return the index of first occurrence of an element in a sorted array
// return -1 if there's no such element
public class Solution {
	int bSearch(int[] arr, int target) {
		int start = 0, end = arr.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (arr[mid] == target) {
				if (mid == 0 || arr[mid] != arr[mid - 1])
					return mid;
				else
					end = mid - 1;
			} else if (arr[mid] > target) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return -1;
	}

	// if target is in arr, return its index, otherwise return the index to be inserted
	int bSearchInsert(int[] arr, int target) {
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
		return start;

	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 4, 5, 6, 7, 8, 9, 9 };
		for (int i : arr)
			System.out.print(new Solution().bSearch(arr, i) + ", ");
		System.out.println();
		int[] arr2 = { 1, 3, 5, 7, 9 };
		for (int i = 0; i < 11; i++)
			System.out
					.println(i + ", " + new Solution().bSearchInsert(arr2, i));

	}
}
