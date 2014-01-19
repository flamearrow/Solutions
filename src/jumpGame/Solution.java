package jumpGame;

//Given an array of non-negative integers, 
// you are initially positioned at the first index of the array.
//
//Each element in the array represents your maximum jump length at that position.
//
//Determine if you are able to reach the last index.
//
//For example:
//A = [2,3,1,1,4], return true.
//
//A = [3,2,1,0,4], return false. 
public class Solution {
	// naive recursion with a O(n) buffer
	public boolean canJumpRec(int[] A) {
		boolean[] cantGo = new boolean[A.length];
		return probe(A, 0, cantGo);
	}

	// a O(n^2) dp
	public boolean canJump(int[] A) {
		boolean[] dp = new boolean[A.length];
		dp[0] = true;
		here: for (int i = 1; i < A.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (dp[j] && (i - j <= A[j])) {
					dp[i] = true;
					continue here;
				}
			}
			// we can't reach a node in between, let along later nodes
			return false;
		}
		return dp[A.length - 1];
	}

	boolean probe(int[] A, int index, boolean[] cantGo) {
		if (index == A.length - 1)
			return true;
		else if (cantGo[index]) {
			return false;
		} else if (A[index] == 0) {
			return false;
		} else {
			for (int i = 1; i <= A[index]; i++) {
				if (probe(A, index + i, cantGo)) {
					return true;
				}
			}
			cantGo[index] = true;
			return false;
		}
	}

	public static void main(String[] args) {
		int[] A = { 3, 2, 1, 0, 0 };
		System.out.println(new Solution().canJump(A));
	}
}
