package epi.rearrange;

// 6.1
//Write a function that takes an array A and an index i into A, and
//rearranges the elements such that all elements less than A[i] appear first, followed
//by elements equal to A[i], followed by elements greater than A[i]. Your algorithm
//should have O(1) space complexity and O(|A|) time complexity.

public class Solution {
	// it's used for quick sort and find kth largest number, the swap is tricky
	// we need three pointers, start, cur and end
	// [0,start-1] point to elements that're smaller than pivot
	// [end+1, len-1] point to elements that're bigger than pivot
	// the middle ones are those that are equal
	// merge cur and end
	//  when arr[cur] == pivot, need to skip cur
	//  when arr[cur] < pivot, need to swap to start
	//  when arr[cur] > pivot, need to swap to end
	static void rearrange(int[] arr, int index) {
		int start = 0, end = arr.length - 1, cur = 0;
		int pivot = arr[index];
		while (cur <= end) {
			if (arr[cur] < pivot) {
				int tmp = arr[start];
				arr[start] = arr[cur];
				arr[cur] = tmp;
				start++;
				cur++;
			} else if (arr[cur] > pivot) {
				int tmp = arr[cur];
				arr[cur] = arr[end];
				arr[end] = tmp;
				end--;
			} else {
				cur++;
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = { 3, 1, 3, 4, 2, 5 };
		rearrange(arr, 0);
		for (int i : arr)
			System.out.print(i + " ");
	}
}
