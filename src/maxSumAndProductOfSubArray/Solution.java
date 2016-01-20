package maxSumAndProductOfSubArray;

// given an array of integers, find the consecutive sub array with max sum
// given an array of doubles, find the consecutive sub array with max product
public class Solution {
	static void maxProductReimpl(int[] nums) {
		int ret = nums[0];
		int preMax = nums[0];
		int preMin = nums[0];

		for(int i = 1; i < nums.length; i++) {
			int canMax = nums[i] * preMax;
			int canMin = nums[i] * preMin;
			preMax = Math.max(canMax, Math.max(canMin, nums[i]));
			preMin = Math.min(canMax, Math.min(canMin, nums[i]));
			ret = Math.max(ret, preMax);
		}
		System.out.println(ret);
	}

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

	// this doens't work for doubles and is confusing
	// the idea is we always keep the max positive product and min negative product to each seen number
	// update the two according to the new number we see
	// set curMaxProduct and curMinProduct to 1 if we can't get a valid value at this point
	//         3, -4,    5,  -6,    -7
	// curMax  3,  1,    5, 360,   210
	// curMin  1, -12, -60, -30, -2520

	// this one doesn't work for double array
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

	// should be that cumbersome... just compare three candidates each time, don't even bother the plus and minus
	//  compare these three candidates 1) array[i] 2) array[i] * preMax 3) array[i] * preMin
	static double maxProduct2(double[] array) {
		double preMax = array[0];
		double preMin = array[0];
		double ret = Integer.MIN_VALUE;
		for (int i = 1; i < array.length; i++) {
			double bufMax = preMax;
			double bufMin = preMin;
			// there are three candidates for updating preMax and preMin
			//  they are 1) array[i] 2) array[i] * preMax 3) array[i] * preMin
			preMax = max(array[i], array[i] * bufMax, array[i] * bufMin);
			preMin = min(array[i], array[i] * bufMax, array[i] * bufMin);
			ret = Math.max(ret, preMax);
		}
		return ret;
	}

	static double max(double... arr) {
		double ret = Double.MIN_VALUE;
		for (double d : arr) {
			ret = Math.max(ret, d);
		}
		return ret;
	}

	static double min(double... arr) {
		double ret = Double.MAX_VALUE;
		for (double d : arr) {
			ret = Math.min(ret, d);
		}
		return ret;
	}

	public static void main(String[] args) {
//		int[] array = { 1, 2, 3, -4, 7 };
//		//		double[] arrayd = convert(array);
//		double[] arrayd = { -1, 2, 3, 0.1, 0.2, 5 };
//		maxSum2(array);
//		maxProduct(array);
//		System.out.println(maxProduct2(arrayd));
		int[] nums = {-4, -3, -2};
		maxProductReimpl(nums);
	}

	static double[] convert(int[] array) {
		double[] ret = new double[array.length];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = (double) array[i];
		}
		return ret;
	}
}
