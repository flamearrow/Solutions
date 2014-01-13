package mergeSortedArray;

//Given two sorted integer arrays A and B, merge B into A as one sorted array.
//
//Note:
//You may assume that A has enough space to hold additional elements from B. 
//The number of elements initialized in A and B are m and n respectively.
public class Solution {
	public void merge(int A[], int m, int B[], int n) {
		int cur = A.length - 1;
		m--;
		n--;
		while (m >= 0 && n >= 0) {
			if (A[m] > B[n]) {
				A[cur--] = A[m--];
			} else {
				A[cur--] = B[n--];
			}
		}
		// if there're some A elements left over let it be
		// if (m >= 0) {
		// for (int i = m; i >= 0; i--) {
		// A[cur--] = A[m--];
		// }
		// }
		if (n >= 0) {
			for (int i = n; i >= 0; i--) {
				A[cur--] = B[n--];
			}
		}
	}

	public static void main(String[] args) {
		int[] A = { 1, 3, 5, 7, 9, 0, 0, 0, 0, 0, 0 };
		int[] B = { 2, 4, 6, 8, 10 };
		new Solution().merge(A, 5, B, 5);
		System.out.println(A);
	}

}
