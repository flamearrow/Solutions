package firstMissingPositive;

//Given an unsorted integer array, find the first missing positive integer.
//
//For example,
//Given [1,2,0] return 3,
//and [3,4,-1,1] return 2.
//
//Your algorithm should run in O(n) time and uses constant space. 
public class Solution {
	public int firstMissingPositive2(int[] A) {
		// i should be at A[i-1]
		int cur = 0;
		while (cur < A.length) {
			int current = A[cur];
			if (current > A.length - 1 || (current - 1) == cur || current <= 0) {
				cur++;
			}
			// swap and don't increase cur
			else {
				int tmp = A[current - 1];
				A[current - 1] = A[cur];
				A[cur] = tmp;
				// note if the two numbers are the same skip
				// otherwise will hit infinite loop
				if (A[current - 1] == A[cur])
					cur++;
			}
		}
		cur = 0;
		while (cur < A.length) {
			if (A[cur] != cur + 1)
				return cur + 1;
			else
				cur++;
		}
		return cur + 1;
	}

	// modify the array, let A[0] = 1, A[1] = 2, A[2] = 3, ... then find the first i where A[i] != i+1
	public int firstMissingPositive(int[] A) {
		int len = A.length;
		int i = 0;
		while (i < len) {
			if (A[i] > len || A[i] < 1) {
				i++;
				continue;
			}
			// we want all A[i] == i+1
			// if not, swap A[i] to the correct place
			// swap A[i] and A[A[i]-1]
			// note if A[i] and A[A[i]-1] are the same, we will skip this one
			if (A[i] > i + 1 || A[i] < i + 1) {
				int tmp = A[i];
				if (A[i] == A[tmp - 1]) {
					i++;
					continue;
				}
				A[i] = A[tmp - 1];
				A[tmp - 1] = tmp;
			} else {
				i++;
			}
		}
		for (i = 0; i < len; i++) {
			if (A[i] != i + 1)
				return i + 1;
		}
		return len + 1;
	}

	public static void main(String[] args) {
		int[] A = { 1, 1 };
		System.out.println(new Solution().firstMissingPositive(A));
	}
}
