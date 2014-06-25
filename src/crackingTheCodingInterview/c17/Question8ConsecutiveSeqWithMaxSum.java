package crackingTheCodingInterview.c17;

// You're give an array of integers of pos and neg
// find the larget sum of consecutive elemnts
public class Question8ConsecutiveSeqWithMaxSum {
	static int maxSum(int[] arr) {
		boolean allNeg = true;
		int maxNeg = Integer.MIN_VALUE;
		int curSum = 0;
		int maxSum = Integer.MIN_VALUE;
		for (int i : arr) {
			if (i > 0) {
				allNeg = false;
			} else {
				if (i > maxNeg)
					maxNeg = i;
			}
			if (curSum + i < 0) {
				curSum = 0;
			} else {
				curSum += i;
				if (curSum > maxSum)
					maxSum = curSum;
			}
		}
		return allNeg ? maxNeg : maxSum;
	}

	public static void main(String[] args) {
		int[] arr = { 1, 3, -3, 4 };
		System.out.println(maxSum(arr));
	}
}
