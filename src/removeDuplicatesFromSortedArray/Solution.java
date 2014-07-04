package removeDuplicatesFromSortedArray;

//Given a sorted array, remove the duplicates in place such that 
// each element appear only once and return the new length.
//
//Do not allocate extra space for another array, you must do this 
// in place with constant memory.
//
//For example,
//Given input array A = [1,1,2],
//
//Your function should return length = 2, and A is now [1,2]. 
public class Solution {

	public int removeDuplicates2(int[] A) {
		if (A.length == 0)
			return 0;
		int ret = 1, cur = 1;
		while (cur < A.length) {
			if (A[cur] != A[cur - 1]) {
				A[ret++] = A[cur];
			}
			cur++;
		}
		return ret;
	}

	public int removeDuplicates(int[] A) {
		if (A.length == 0)
			return 0;
		int end = 0, cur = 0, pre = -1;
		while (cur < A.length) {
			if (pre == -1) {
				pre = cur;
			} else if (A[pre] != A[cur]) {
				A[end++] = A[pre];
				pre = cur;
			}
			cur++;
		}
		A[end++] = A[pre];
		return end;
	}

	public static void main(String[] args) {
		int[] A = {};
		int newLen = new Solution().removeDuplicates2(A);
		System.out.println(newLen);
		for (int i = 0; i < newLen; i++) {
			System.out.print(A[i] + " ");
		}
	}
}
