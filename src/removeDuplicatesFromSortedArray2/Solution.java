package removeDuplicatesFromSortedArray2;

//Follow up for "Remove Duplicates":
//What if duplicates are allowed at most twice?
//
//For example,
//Given sorted array A = [1,1,1,2,2,3],
//
//Your function should return length = 5, and A is now [1,1,2,2,3]. 
public class Solution {
	public int removeDuplicates(int[] A) {
		return removeDuplicatesAllowN(A, 2);
	}

	// can have up to n dups
	public int removeDuplicatesAllowN(int[] A, int n) {
		if (A.length == 0)
			return 0;
		int end = 0, cur = 0, pre = -1;
		int allowance = n - 1;
		while (cur < A.length) {
			if (pre == -1) {
				pre = cur;
			} else {
				if (A[pre] == A[cur] && allowance > 0) {
					allowance--;
					A[end++] = A[pre];
					pre = cur;
				} else if (A[pre] != A[cur]) {
					allowance = n - 1;
					A[end++] = A[pre];
					pre = cur;
				}
			}
			cur++;
		}
		A[end++] = A[pre];
		return end;
	}

	public static void main(String[] args) {
		int[] A = { 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4 };
		int newLen = new Solution().removeDuplicates(A);
		System.out.println(newLen);
		for (int i = 0; i < newLen; i++) {
			System.out.print(A[i] + " ");
		}
	}
}
