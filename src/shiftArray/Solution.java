package shiftArray;

//given an array, left shift it for n nodes
//input: [1,2,3,4,5], 3
//output: [4,5,1,2,3]
public class Solution {
	// separate the getDestIndex() method
	void shift(int[] arr, int pos) {
		if (pos < 0)
			return;
		int len = arr.length;
		pos = pos % len;
		if (pos == 0)
			return;
		int left = len;
		int cur = 0;
		int pre = arr[cur];
		int tmp = 0;
		while (left > 0) {
			int swapedIndex = getDestIndex(cur, pos, len);
			tmp = arr[swapedIndex];
			arr[swapedIndex] = pre;
			pre = tmp;
			cur = swapedIndex;
			left--;
		}
	}

	int getDestIndex(int cur, int pos, int len) {
		if (cur - pos >= 0) {
			return cur - pos;
		} else {
			return len + cur - pos;
		}
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5 };
		new Solution().shift(arr, 0);
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}
}
