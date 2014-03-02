package epi.twoSumVar;

// 11.2
// two sum - but the array is sorted by abs
//  e.g: {1,-2,3,5,-7,9,10,-12}
// another variant:
//  return a pair whose difference is k - have two pointers pointing to end at first and move cursor accordingly

public class Solution {
	// of course you can always suffer a O(n) space hash table for O(1) search

	// for a O(1) space solution, the idea is similar to two sum
	// in two sum, the initialized pointer is pointing to the smallest and biggest
	//  so that we can increase/decrease the sum without missing any combination
	// for this problem, consider the result in three cases:
	// 	1) both are positive
	//  2) both are negative
	//  3) one positive and one negative
	// for 1) and 2) we can do the same as two sum
	// for 3), similarly, we have two pointers pointing to the biggest and smallest
	//  i.e the right most positive number and right most negative number
	//  and do a similar search for that target
	Pair twoSumVar(int[] arr, int target) {
		int start = -1, end = arr.length;
		// case 1)
		start = nextPosIndex(arr, start);
		end = prevPosIndex(arr, end);
		while (start < end) {
			if (arr[start] + arr[end] == target) {
				return new Pair(start, end);
			} else if (arr[start] + arr[end] < target) {
				start = nextPosIndex(arr, start);
			} else {
				end = prevPosIndex(arr, end);
			}
		}

		// case 2)
		start = -1;
		end = arr.length;
		start = nextNegIndex(arr, start);
		end = prevNegIndex(arr, end);
		while (start < end) {
			if (arr[start] + arr[end] == target) {
				return new Pair(start, end);
			} else if (arr[start] + arr[end] < target) {
				end = prevNegIndex(arr, end);
			} else {
				start = nextPosIndex(arr, start);
			}
		}

		// case 3)
		start = arr.length;
		end = arr.length;
		start = prevPosIndex(arr, start);
		end = prevNegIndex(arr, end);
		// now start is the biggest pos, end is the smallest neg

		while (start >= 0 && end >= 0) {
			if (arr[start] + arr[end] == target) {
				return new Pair(start, end);
			} else if (arr[start] + arr[end] < target) {
				end = prevNegIndex(arr, end);
			} else {
				start = prevPosIndex(arr, start);
			}
		}

		return new Pair(-1, -1);

	}

	int nextPosIndex(int[] arr, int start) {
		int cur = start + 1;
		while (cur < arr.length && arr[cur] < 0)
			cur++;
		return cur;
	}

	int prevPosIndex(int[] arr, int end) {
		int cur = end - 1;
		while (cur >= 0 && arr[cur] < 0)
			cur--;
		return cur;
	}

	int nextNegIndex(int[] arr, int start) {
		int cur = start + 1;
		while (cur < arr.length && arr[cur] > 0)
			cur++;
		return cur;
	}

	int prevNegIndex(int[] arr, int end) {
		int cur = end - 1;
		while (cur >= 0 && arr[cur] > 0)
			cur--;
		return cur;
	}

	public static void main(String[] args) {
		int[] arr = { 1, -2, 3, 5, -7, 9, 10, -12 };
		System.out.println(new Solution().twoSumVar(arr, -14));
	}
}

class Pair {
	int start, end;

	public Pair(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public String toString() {
		return "" + start + ", " + end;
	}
}