package medianOfTwoSortedArrays;

//There are two sorted arrays A and B of size m and n respectively. 
//Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

public class Solution {
	// the idea is to keep two pointers in A and B with sum = (m+n)/2,
	// then do bSearch in A and move pointer in B accordingly
	// until we find A[aPtr] < B[bPtr] < A[aPtr+1]
	// or B[bPtr] < A[aPtr] < B[bPtr+1]
	// if we can't find, then on array overflows, we need to check if it's left
	// over flow and right over flow and check the boundary accordingly
	//
	// Note:
	// *)
	// can first swap A to the shorter array so that we never over flow in B
	// *)
	// when we hit a result during bSearch,
	// we can't just return A[aPtr] and B[bPtr],
	// the purpose is to find the biggest two numbers
	// from A[0-aPtr] and B[0-bPtr]
	// we need to compare
	// A[aPtr] and B[bPtr-1] for A[aPtr] < B[bPtr] < A[aPtr+1]
	// and
	// B[bPtr] and A[aPtr-1] for B[bPtr] < A[aPtr] < B[bPtr+1]
	// in order to get the biggest two
	public double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length, n = B.length;
		boolean even = (m + n) % 2 == 0;
		if (m == 0) {
			return even ? ((double) B[n / 2 - 1] + (double) B[n / 2]) / 2
					: B[n / 2];
		} else if (n == 0) {
			return even ? ((double) A[m / 2 - 1] + (double) A[m / 2]) / 2
					: A[m / 2];
		}
		// we need to make sure we always search from the smaller array, so that
		// when we do (m+n)/2 - aPtr - 1 in the big array, we won't overflow
		int start = 0, end = 0;
		if (m < n) {
			end = m - 1;
		} else {
			end = n - 1;
			int[] tmp = A;
			A = B;
			B = tmp;
		}
		int aPtr = 0, bPtr = 0;
		while (start <= end) {
			aPtr = (start + end) / 2;
			bPtr = (m + n) / 2 - aPtr - 1;
			if (findValidPosition(A, aPtr, B, bPtr)) {
				// if even, the two medians are the biggest two number
				// of A[0-aPtr+1], B[0-bPtr+1]
				// note two medians might be in the same array
				if (even) {
					// A[aPtr] < B[bPtr] < A[aPtr+1]
					// we need to check B[bPtr-1] and B[bPtr+1]
					if (A[aPtr] < B[bPtr]) {
						// the two medians are in B
						if (bPtr > 0 && B[bPtr - 1] > A[aPtr]) {
							return ((double) B[bPtr] + (double) B[bPtr - 1]) / 2;
						}

						// not in the same array
						else {
							return ((double) A[aPtr] + (double) B[bPtr]) / 2;
						}
					}
					// B[bPtr] < A[aPtr] < B[bPtr+1]
					// we need to check A[aPtr-1] and A[aPtr+1]
					else {
						// the two medians are in A
						if (aPtr > 0 && A[aPtr - 1] > B[bPtr]) {
							return ((double) A[aPtr] + (double) A[aPtr - 1]) / 2;
						}
						// not in the same array
						else {
							return ((double) A[aPtr] + (double) B[bPtr]) / 2;
						}
					}
				}
				// if odd, then when we terminate searching,
				// the bigger pointer should be pointing to the median
				else {
					return A[aPtr] > B[bPtr] ? A[aPtr] : B[bPtr];
				}
			}
			if (A[aPtr] < B[bPtr]) {
				start = aPtr + 1;
			} else {
				end = aPtr - 1;
			}
		}
		// now the solution should be at edge
		if (even) {
			// left over flow, elements in A are too big, now A[aPtr] is at 0
			// we need to take B[bPtr](smaller), and find the next biggest one
			if (end < 0) {
				if ((bPtr + 1) < B.length && B[bPtr + 1] < A[aPtr]) {
					return ((double) B[bPtr] + (double) B[bPtr + 1]) / 2;
				} else {
					return ((double) A[aPtr] + (double) B[bPtr]) / 2;
				}
			}
			// right over flow, elements in A are too small,
			// now A[aPtr] is at end
			// we need to take B[bPtr](bigger),
			// and find the previous biggest one
			else {
				if (bPtr > 0 && B[bPtr - 1] > A[aPtr]) {
					return ((double) B[bPtr] + (double) B[bPtr - 1]) / 2;
				} else {
					return ((double) A[aPtr] + (double) B[bPtr]) / 2;
				}
			}
		} else {
			// left over flow, elements in A are too big, now A[aPtr] is at end
			if (end < 0) {
				return A[aPtr] < B[bPtr + 1] ? A[aPtr] : B[bPtr + 1];
			}
			// right over flow, elements in A are too small
			else {
				return B[bPtr];
			}
		}
	}

	boolean findValidPosition(int[] A, int aPtr, int[] B, int bPtr) {
		// find if (A[aPtr] <= B[bPtr] <= A[aPtr+1])
		// or (B[bPtr] <= A[aPtr] <= B[bPtr+1])
		if (aPtr < A.length - 1) {
			if (A[aPtr] <= B[bPtr] && B[bPtr] <= A[aPtr + 1]) {
				return true;
			}
		}
		if (bPtr < B.length - 1) {
			if (B[bPtr] <= A[aPtr] && A[aPtr] <= B[bPtr + 1]) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int[] A = { 3, 6 };
		int[] B = { 1, 2, 4, 5 };
		System.out.println(new Solution().findMedianSortedArrays(A, B));
	}
}