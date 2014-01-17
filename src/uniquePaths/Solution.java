package uniquePaths;

//A robot is located at the top-left corner of a m x n grid 
//
//The robot can only move either down or right at any point in time. 
//
//The robot is trying to reach the bottom-right corner of the grid 
//
//How many possible unique paths are there?

//Note: m and n will be at most 100
public class Solution {
	public int uniquePaths(int m, int n) {
		if (m == 0 || n == 0)
			return 0;
		int min = m < n ? m : n;

		int a = timesBack(m + n, min);

		int b = facN(m - min);

		return a / b;
	}

	int timesBack(int start, int loop) {
		if (loop == 1)
			return start;
		else
			return start * timesBack(start - 1, loop - 1);
	}

	static int facN(int n) {
		if (n == 1)
			return 1;
		else
			return n * facN(n - 1);
	}

	public static void main(String[] args) {
		System.out.println(facN(29));
		System.out.println(new Solution().uniquePaths(36, 7));

	}
}
