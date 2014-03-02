package epi.localMin;

//Variant 11.1.1
//Let A be an unsorted array of n integers, with A[0] ≥ A[1] and
//A[n − 2] ≤ A[n − 1]. Call an index i a local minimum if A[i] is less than or equal to its
//neighbors. How would you efficiently find a local minimum, if one exists?

public class Solution {
	// the idea is to look at the mid, it's either local min or not
	// if it's not then there's a neighbor smaller than it
	//   we know the side where it has a smaller neighbor must have a local min
	//   so we recurse that half
	// why? because the array has a shape of \ ... /
	//    if we can't find a local min, then we find something like \ or /
	//  so we have \ ... \ ... / or \ ... / ... /
	// we know there must be a local min in right/left half!
	int localMin(int[] arr) {
		int start = 0, end = arr.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (mid > 0 && mid < arr.length - 1 && arr[mid - 1] > arr[mid]
					&& arr[mid] < arr[mid + 1]) {
				return mid;
			} else if (mid > 0 && arr[mid] > arr[mid - 1]) {
				end = mid - 1;
			} else if (mid < arr.length - 1 && arr[mid] > arr[mid + 1]) {
				start = mid + 1;
			}
			// hit bound
			else
				break;
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] arr = { 4, 2, 9, 1, 3, -4, 99 };
		System.out.println(new Solution().localMin(arr));
	}
}
