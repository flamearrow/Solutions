package maximumSubArray;

//Find the contiguous subarray within an array 
//(containing at least one number) which has the largest sum.
//
//For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
//the contiguous subarray [4,−1,2,1] has the largest sum = 6. 
public class Solution {
	// apart from the O(n) solution, there's a divide and conquer O(nlogn) solution
	// get mid, then the answer should be chosen from following four subarrays:
	// 1) [0, mid-1]
	// 2) [mid+1, end]
	// 3) [0, mid] or [1, mid] or [2, mid] or ... [mid-1, mid]
	// 4) [mid, mid+1] or [mid, mid+2] or ... or [mid, end]
	public int maxSubArray(int[] A) {
		int maxSum = Integer.MIN_VALUE;
		int curSum = 0;
		int end = 0;
		int max = Integer.MIN_VALUE;
		while (end < A.length) {
			if (max < A[end])
				max = A[end];
			curSum += A[end++];
			if (curSum > 0) {
				if (curSum > maxSum)
					maxSum = curSum;
			} else {
				curSum = 0;
			}
		}
		if (maxSum < 0)
			return max;
		else
			return maxSum;
	}

	public static void main(String[] args) {
		int[] A = { -2, -1 };
		System.out.println(new Solution().maxSubArray(A));
	}
}
