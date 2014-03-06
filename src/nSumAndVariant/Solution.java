package nSumAndVariant;

// extension of twoSum and threeSum: determine if there're n elements in arr that sum up to target
//  what if you need a n sum?
//  what if you can take arbitrary number of elements? 0-1 knapsack problem 
public class Solution {
	// a naive bSearch O(2^n)
	boolean nSum(int[] arr, int target, int n) {
		return doNSumRec(arr, target, n, 0, 0);
	}

	// a variant: just given the target and arr
	// decide whether we can take arbitrary number of elements from arr and add to target
	//  O(nk) where k is value of target
	boolean canSum(int[] arr, int target) {
		// a similar naive O(2^n) approach exits, omitting...
		// use a two dimensional boolean dp table: dp[i][j] means whether the first i elements could add up to j
		boolean[][] dp = new boolean[arr.length][target + 1];
		dp[0][0] = true;
		dp[0][arr[0]] = true;
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j <= target; j++) {
				// to determine if we can use the i element to add to j, we need to check two previous spots
				//  1) if we can use i-1 elements to add to j-arr[i]
				//  2) if we can use i-1 elements to add to j
				// if either one holds, dp[i][j] would hold
				dp[i][j] = j >= arr[i] ? dp[i - 1][j - arr[i]] || dp[i - 1][j]
						: dp[i - 1][j];
			}
		}

		return dp[arr.length - 1][target];
	}

	boolean doNSumRec(int[] arr, int target, int left, int curSum, int curIndex) {
		if (left == 0 && curSum == target)
			return true;
		if (curIndex == arr.length || left == 0)
			return false;
		// use arr[curIndex] or don't use arr[curIndex]
		if (doNSumRec(arr, target, left - 1, curSum + arr[curIndex],
				curIndex + 1)
				|| doNSumRec(arr, target, left, curSum, curIndex + 1))
			return true;
		return false;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int target = 45;
		int n = 9;
		System.out.println(new Solution().nSum(arr, target, n));
		System.out.println(new Solution().canSum(arr, target));
	}
}
