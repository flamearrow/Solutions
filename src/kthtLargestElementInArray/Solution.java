package kthtLargestElementInArray;

//Find the kth largest element in an unsorted array. 
//Note that it is the kth largest element in the sorted order, 
//not the kth distinct element.
//
//For example,
//Given [3,2,1,5,6,4] and k = 2, return 5.
//
//Note: 
//You may assume k is always valid, 1 ≤ k ≤ array's length.
public class Solution {
	public static void main(String[] args) {
		int[] arr = { 3, 1, 4, 2, 5 };
		for (int i = 0; i < arr.length; i++)
			System.out.println(new Solution().findKthLargest(arr, i));
	}

	public int findKthLargest(int[] nums, int k) {
		// do the quicksort like shit
		return doShit(nums, nums.length - k, 0, nums.length - 1);
	}

	// pick the last element as pivot to avoid moving array around
	int doShit(int[] nums, int k, int start, int end) {
		int cur = start;
		int pivot = nums[end];
		// the idea is picking the last element as pivot, for all numbers
		// smaller than pivot
		// condense them to the left half of array
		// in the end, cur either point to end(when nums[end] is biggest)
		// or cur points to the first number that's bigger than pivot
		// now we just swap cur and end
		for (int i = start; i < end; i++) {
			if (nums[i] <= pivot) {
				swap(nums, i, cur);
				cur++;
			}
		}
		swap(nums, cur, end);
		if (cur == k) {
			return nums[cur];
		} else if (cur > k) {
			return doShit(nums, k, start, cur - 1);
		} else {
			return doShit(nums, k, cur + 1, end);
		}
	}

	void swap(int[] arr, int from, int to) {
		int tmp = arr[from];
		arr[from] = arr[to];
		arr[to] = tmp;
	}
}
