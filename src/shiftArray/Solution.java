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
	// now we need to left shift the subarray [2,3,1], use recursion
	// note if pos == 1 we return immediately to avoid stackoverflow
	void doShift(int[] arr, int pos, int start) {
		int size = arr.length;
		pos = pos % size;
		if (pos == 0)
			return;
		int cur = start, prob = cur + pos;
		while (prob < size) {
			int tmp = arr[cur];
			arr[cur] = arr[prob];
			arr[prob] = tmp;
			cur++;
			prob++;
		}
		if (pos == 1)
			return;
		int leftOver = pos - (cur - start) % pos;
		if (leftOver > 0) {
			doShift(arr, leftOver, cur);
		}
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7 };
		new Solution().shift(arr, 123132);
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}
}
