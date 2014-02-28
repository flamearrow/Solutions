package shiftArray;

// e.g [1,2,3,4,5,6,7] , 3
// would be shifted as [4,5,6,7,1,2,3]
public class Solution {
	void shift(int[] arr, int pos) {
		doShift(arr, pos, 0);
	}

	// the idea:
	// swap by bunch:
	// [1,2,3,4,5,6,7]->[4,5,6,1,2,3,7]
	// then when the bunch is not big enough, swap what you can
	// [4,5,6,1,2,3,7]->[4,5,6,7,2,3,1]
	// now we need to left shift 2 bits for the subarray [2,3,1], use recursion
	void doShift(int[] arr, int pos, int start) {
		int size = arr.length;
		pos = pos % size;
		if (pos == 0)
			return;
		int cur = start, prob = start + pos;
		while (prob < arr.length) {
			int tmp = arr[cur];
			arr[cur] = arr[prob];
			arr[prob] = tmp;
			cur++;
			prob++;
		}
		int leftOver = pos - (cur - start) % pos;
		if (leftOver < pos) {
			doShift(arr, leftOver, cur);
		}
	}

	public static void main(String[] args) {
		int[] arr;
		for (int j = 0; j < 10; j++) {
			arr = ini();
			new Solution().shift(arr, j);
			for (int i : arr) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

	static int[] ini() {
		int[] ret = { 1, 2, 3, 4, 5, 6 };
		return ret;
	}
}
