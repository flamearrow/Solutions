package maxSumAndProductOfSubArray;

// given an array of integers, find the consecutive sub array with max sum
// given an array of doubles, find the consecutive sub array with max product
public class Solution {
	static void maxSum(int[] array) {
		// doesn't work for all negatives
		// need to check if all are negatives first if so return the biggest negative one
		if (array.length == 0)
			return;
		int maxStart = 0, maxEnd = 0, curStart = 0, cur = 0;
		int maxSum = Integer.MIN_VALUE, curSum = 0;

		while (cur < array.length) {
			curSum += array[cur];
			if (curSum < 0) {
				cur++;
				curStart = cur;
				curSum = 0;
			} else {
				if (curSum > maxSum) {
					maxSum = curSum;
					maxStart = curStart;
					maxEnd = cur;
				}
				cur++;
			}
		}
		for (int i = maxStart; i <= maxEnd; i++) {
			System.out.print(array[i] + " ");
		}
	}

	// same idea but different implementation, keep a positiveSum we have seen so far, update it when next sum is still positive
	// set it to 0 if next sum is negative
	static void maxSum2(int[] array) {
		int positiveSum = 0;
		int maxSum = 0, biggestNeg = Integer.MIN_VALUE;
		boolean allNeg = true;
		for (int i = 0; i < array.length; i++) {
			if (positiveSum + array[i] > 0) {
				allNeg = false;
				positiveSum += array[i];
				if (positiveSum > maxSum)
					maxSum = positiveSum;
			} else {
				if (array[i] > biggestNeg)
					biggestNeg = array[i];
				positiveSum = 0;
			}
		}
		maxSum = allNeg ? biggestNeg : maxSum;
		System.out.println("maxSum is " + maxSum);
	}

	// the idea is we always keep the max positive product and min negative product to each seen number
	// update the two according to the new number we see
	// set curMaxProduct and curMinProduct to 1 if we can't get a valid value at this point

	//         3, -4,    5,  -6,    -7
	// curMax  3,  1,    5, 360,   210
	// curMin  1, -12, -60, -30, -2520
	static void maxProduct(int[] array) {
		int curMaxProduct = 1, curMinProduct = 1, maxProduct = 1;
		for (int i = 0; i < array.length; i++) {
			if (array[i] > 0) {
				if (curMinProduct < 0) {
					curMinProduct *= array[i];
				}
				curMaxProduct *= array[i];
			} else if (array[i] < 0) {
				int tmp = curMaxProduct;
				if (curMinProduct < 0) {
					curMaxProduct = curMinProduct * array[i];
				} else {
					curMaxProduct = 1;
				}
				curMinProduct = tmp * array[i];
			} else {
				curMinProduct = 1;
				curMaxProduct = 1;
			}
			if (curMaxProduct > maxProduct) {
				maxProduct = curMaxProduct;
			}
		}
		System.out.println("maxProduct is " + maxProduct);
	}

	public static void main(String[] args) {
		int[] array = { -1, -2, -3, -4, 5 };
		maxSum2(array);
		maxProduct(array);
	}
}
