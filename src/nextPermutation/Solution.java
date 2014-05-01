package nextPermutation;

//Implement next permutation, which rearranges numbers into the lexicographically 
// next greater permutation of numbers.
//
//If such arrangement is not possible, it must rearrange it as the lowest possible order 
// (ie, sorted in ascending order).
//
//The replacement must be in-place, do not allocate extra memory.
//
//Here are some examples. Inputs are in the left-hand column and its corresponding outputs
// are in the right-hand column.
//1,2,3 -> 1,3,2
//3,2,1 -> 1,2,3
//1,1,5 -> 1,5,1
public class Solution {
	public void nextPermutation2(int[] num) {
		int cur = num.length - 1;
		// if this loop overflow, then we would end up having number like 4321, which doesn't have the next permutation
		while (cur > 0 && num[cur] <= num[cur - 1])
			cur--;
		if (cur == 0) {
			// 4321 to 1234
			reverse2(num, 0);
			return;
		}
		// 1 5 9 6 4 2
		//     |
		//    cur
		// num[cur] > num[cur-1]
		int swapFrom = cur - 1;
		int swapTo = cur;
		while (swapTo + 1 < num.length && num[swapTo + 1] > num[swapFrom])
			swapTo++;
		//  from
		//   |
		// 1 5 9 6 4 2
		//       |
		//       to
		int tmp = num[swapFrom];
		num[swapFrom] = num[swapTo];
		num[swapTo] = tmp;
		// 1 6 9 5 4 2

		reverse2(num, swapFrom + 1);
	}

	void reverse2(int[] num, int start) {
		int end = num.length - 1;
		while (end > start) {
			int tmp = num[start];
			num[start] = num[end];
			num[end] = tmp;
			start++;
			end--;
		}
	}

	// note we may have duplicate, need to assert for equal as well
	public void nextPermutation(int[] num) {
		int swapFrom = num.length - 2;
		while (swapFrom >= 0 && num[swapFrom] >= num[swapFrom + 1]) {
			swapFrom--;
		}
		// reversely sorted
		if (swapFrom < 0) {
			swapFrom = 0;
			int swapTo = num.length - 1;
			while (swapFrom < swapTo) {
				int tmp = num[swapFrom];
				num[swapFrom] = num[swapTo];
				num[swapTo] = tmp;
				swapFrom++;
				swapTo--;
			}
			return;
		}
		// now num[swapFrom] < num[swapFrom+1];
		int swapTo = num.length - 1;
		while (num[swapTo] <= num[swapFrom]) {
			swapTo--;
		}
		// now num[swapTo] > num[swapFrom]
		int tmp = num[swapFrom];
		num[swapFrom] = num[swapTo];
		num[swapTo] = tmp;

		// now revsere num[swapFrom+1 to end]
		swapFrom++;
		swapTo = num.length - 1;
		while (swapFrom < swapTo) {
			tmp = num[swapFrom];
			num[swapFrom] = num[swapTo];
			num[swapTo] = tmp;
			swapFrom++;
			swapTo--;
		}
	}

	public static void main(String[] args) {
		int[] num = { 5, 1, 1 };
		new Solution().nextPermutation(num);
		System.out.println(num);
	}
}
