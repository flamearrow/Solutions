package jumpGame2;

//Given an array of non-negative integers, you are initially positioned at the first index of the array.
//
//Each element in the array represents your maximum jump length at that position.
//
//Your goal is to reach the last index in the minimum number of jumps.
//
//For example:
//Given array A = [2,3,1,1,4]
//
//The minimum number of jumps to reach the last index is 2. 
//(Jump 1 step from index 0 to 1, then 3 steps to the last index.) 
public class Solution {
	public int jump3(int[] A) {
		if (A.length == 1)
			return 0;
		int maxReach = 0;
		int actualReach = 0;
		int ret = 0;
		for (int i = 0; i < A.length; i++) {
			if (maxReach < i)
				return -1;
			if (i > actualReach) {
				ret++;
				actualReach = maxReach;
			}
			maxReach = Math.max(i + A[i], maxReach);
		}
		return ret;
	}

	public int jump2(int[] A) {
		if (A.length == 1)
			return 0;
		int ret = 1;
		int maxReachable = A[0], curReachable = A[0];
		for (int i = 0; i < A.length; i++) {
			int tmpReachable = A[i] + i;
			// we're at most one step away from solution
			if (tmpReachable >= A.length - 1) {
				return curReachable >= A.length - 1 ? ret : ret + 1;
			}
			// update the furthest we can reach
			if (tmpReachable > maxReachable) {
				maxReachable = tmpReachable;
			}

			// if we need another step, update the furthest we can reach after doing another step
			if (i >= curReachable) {
				curReachable = maxReachable;
				ret++;
			}

		}
		return -1;
	}

	// greedy O(n)
	// search from start to end, i means now we are at point i
	// start min from 0, we only increment min if we found 
	//   we are at a point which is farther then the farthest point we can reach
	// in which case we need to add one more step to min
	//   then update the farthest step we can reach using min+1 steps
	// how to calculate the farthest step?
	//  just try up at each new point we probe
	public int jump(int[] A) {
		int end = 0;
		int min = 0;
		int far = 0;
		for (int i = 0; i < A.length; i++) {
			if (i > end) {
				min++;
				end = far;
			}
			if (i + A[i] > far)
				far = i + A[i];
		}
		return min;
	}

	// a slightly modifed dp:
	// dp[i]: what's the min jump to i?
	// n^2
	public int jumpDP(int[] A) {
		int[] dp = new int[A.length];
		dp[0] = 0;
		int index = 1;
		while (index < A.length) {
			int from = index - 1;
			int min = Integer.MAX_VALUE;
			while (from >= 0) {
				if (A[from] >= index - from && dp[from] + 1 < min) {
					min = dp[from] + 1;
				}
				from--;
			}
			// can't jump to index
			if (min == Integer.MAX_VALUE)
				break;
			dp[index] = min;
			index++;
		}
		return dp[A.length - 1];
	}

	public static void main(String[] args) {
		int[] A = { 1, 1, 1, 1 };
		System.out.println(new Solution().jump3(A));
	}
}
