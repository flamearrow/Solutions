package climbingStairs;

//You are climbing a stair case. It takes n steps to reach to the top.
//
//Each time you can either climb 1 or 2 steps. 
// In how many distinct ways can you climb to the top?
public class Solution {
	// this is a Fibonacci problem

	public int climbStairs(int n) {
		if (n <= 1)
			return 1;
		else {
			int pre = 1, prePre = 1, ret = 0;
			for (int i = 2; i <= n; i++) {
				ret = pre + prePre;
				prePre = pre;
				pre = ret;
			}
			return ret;
		}
	}

	public int climbStairsRec(int n) {
		if (n <= 1)
			return 1;
		else {
			return climbStairs(n - 1) + climbStairs(n - 2);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 50; i++) {
			System.out.print(new Solution().climbStairs(i) + ", ");
			System.out.println(new Solution().climbStairsRec(i));
		}
	}
}
