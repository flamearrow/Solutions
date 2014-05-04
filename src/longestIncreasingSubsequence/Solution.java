package longestIncreasingSubsequence;

// find the length of longest increasing subsequence of a given integer array
// e.g { 10, 22, 9, 33, 21, 50, 41, 60, 80 }
// 		return 6 since lis is  {10, 22, 33, 50, 60, 80}
public class Solution {
	int lis(int[] input) {
		// dp[i] is the length of longest subsequence ends at input[i]
		// use a backtrack array to print the path
		int[] dp = new int[input.length];
		// back[i] is the index of previous number of lis ends at input[i]
		//  -1 means i is the first
		int[] back = new int[input.length];
		// note after one loop we need to do another loop in dp[] 
		//  to find the longest possible size, then start form there
		dp[0] = 1;
		back[0] = -1;
		for (int i = 1; i < input.length; i++) {
			int longest = 1;
			int index = -1;
			for (int j = 0; j < i; j++) {
				if (input[j] <= input[i] && dp[j] + 1 > longest) {
					longest = dp[j] + 1;
					index = j;
				}
			}
			dp[i] = longest;
			back[i] = index;
		}
		int maxLen = dp[0];
		int maxEndIndex = 0;
		for (int i = 0; i < dp.length; i++) {
			if (dp[i] > maxLen) {
				maxLen = dp[i];
				maxEndIndex = i;
			}
		}
		StringBuilder sb = new StringBuilder();
		int lastIndex = maxEndIndex;
		while (lastIndex != -1) {
			sb.insert(0, input[lastIndex] + " ");
			lastIndex = back[lastIndex];
		}
		System.out.println(sb.toString());
		return maxLen;
	}

	public static void main(String[] args) {
		int[] input = { 1, 10, 3, 8, 5, 6, 7, 9 };
		System.out.println(new Solution().lis(input));
	}
}
