package paintFence;

//There is a fence with n posts, each post can be painted with one of the k colors.
//
//You have to paint all the posts such that no more than two adjacent fence posts have the same color.
//
//Return the total number of ways you can paint the fence.
//
//Note:
//n and k are non-negative integers.
public class Solution {
	public static void main(String[] args) {
		System.out.println(new Solution().numWays(9, 10));
		System.out.println(new Solution().numWays2(9, 10));
	}

	public int numWays2(int n, int k) {
        if(n == 0 || k == 0) {
            return 0;
        }
		// do dp shit
		int[] dp = new int[n];
		// dp[i] is the number of ways to paint first i fences
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				dp[i] = k;
			} else if (i == 1) {
				dp[i] = k * k;
			} else {
				// first part: last one color is different from last but one
				// color
				// second pard: last two colors are the same, but different from
				// last but three color
				dp[i] = dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1);
			}
		}

		return dp[n - 1];
	}
	
	// naive recursive way, try to fill the array one by one and mark for duplication colors
	public int numWays(int n, int k) {
		return doShit(n, k, 0, 1, false);
	}

	// if last == 0, we're starting from head
	int doShit(int n, int k, int last, int cur, boolean dupColor) {
		if (cur > n) {
			return 0;
		}
		if (cur == n) {
			if (dupColor) {
				return k - 1;
			} else {
				return k;
			}
		}
		int ret = 0;
		for (int lastColor = 1; lastColor <= k; lastColor++) {
			if (lastColor == last) {
				if (dupColor) {
					continue;
				} else {
					ret += doShit(n, k, lastColor, cur + 1, true);
				}
			} else {
				ret += doShit(n, k, lastColor, cur + 1, false);
			}
		}
		return ret;
	}
}
