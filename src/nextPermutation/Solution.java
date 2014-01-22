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
