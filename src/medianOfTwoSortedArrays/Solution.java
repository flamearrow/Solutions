package medianOfTwoSortedArrays;

//There are two sorted arrays A and B of size m and n respectively. 
//Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

public class Solution {
	
	// the idea is when you locate two pointers, they are not necessarily the targets
	// because two targets might be in the same array.
	// need to get (smaller one, Math.min(next of Smaller one in the same array, bigger one)
	public double findMedianSortedArrays3(int A[], int B[]) {
		int[] longArr = A.length > B.length ? A : B;
		int[] shortArr = A.length > B.length ? B : A;
		boolean even = (longArr.length + shortArr.length) % 2 == 0;
		int sum = (longArr.length + shortArr.length) / 2 - 1;
		if (shortArr.length == 0) {
			return even ? ((double) longArr[sum] + (double) longArr[sum + 1]) / 2
					: longArr[sum + 1];
		}
		int left = 0, right = shortArr.length - 1;
		int longPtr = 0, shortPtr = 0;

		// loop on shortArr so that we never gets out of bound
		while (left <= right) {
			shortPtr = (left + right) / 2;
			longPtr = sum - shortPtr;
			if (found(longArr, shortArr, longPtr, shortPtr)) {
				if (even) {
					// need to check if two elements are in the same array
					if (longArr[longPtr] > shortArr[shortPtr]
							&& shortPtr < shortArr.length - 1) {
						return ((double) shortArr[shortPtr] + (double) Math
								.min(longArr[longPtr], shortArr[shortPtr + 1])) / 2;
					} else if (longArr[longPtr] < shortArr[shortPtr]
							&& longPtr < longArr.length - 1) {
						return ((double) longArr[longPtr] + (double) Math.min(
								longArr[longPtr + 1], shortArr[shortPtr])) / 2;

					}
					return ((double) longArr[longPtr] + (double) shortArr[shortPtr]) / 2;
				} else {
					if (longArr[longPtr] > shortArr[shortPtr]
							&& shortPtr < shortArr.length - 1) {
						return Math.min(longArr[longPtr],
								shortArr[shortPtr + 1]);
					} else if (longArr[longPtr] < shortArr[shortPtr]
							&& longPtr < longArr.length - 1) {
						return Math.min(longArr[longPtr + 1],
								shortArr[shortPtr]);
					}
					return longArr[longPtr] > shortArr[shortPtr] ? longArr[longPtr]
							: shortArr[shortPtr];
				}
			} else {
				// go left, note we go left when 
				// 1) found() fails
				// 2) longPtr is at edge
				if (longPtr >= 1 && longArr[longPtr - 1] > shortArr[shortPtr]
						|| longPtr == 0
						&& longArr[longPtr] > shortArr[shortPtr]) {
					left = shortPtr + 1;
				} else {
					right = shortPtr - 1;
				}
			}
		}
		// if we reach here, it means either two numbers are in the same array 
		// or there's one at an edge of an array

		// shortArr's elements are too small
		if (left >= shortArr.length) {
			if (even) {
				if (longPtr > 0)
					return ((double) longArr[longPtr] + (double) Math
							.max(longArr[longPtr - 1],
									shortArr[shortArr.length - 1])) / 2;
				else
					return ((double) longArr[longPtr] + shortArr[shortArr.length - 1]) / 2;
			} else
				return longArr[longPtr];

		}
		// shortArr's elements are too big
		if (right < 0) {
			if (even) {
				if (longPtr < longArr.length - 1)
					return ((double) longArr[longPtr] + (double) Math.min(
							longArr[longPtr + 1], shortArr[0])) / 2;
				else
					return ((double) longArr[longPtr] + shortArr[0]) / 2;
			} else {
				if (longPtr < longArr.length)
					return Math.min(longArr[longPtr + 1], shortArr[0]);
				else
					return shortArr[0];
			}
		}
		return -1;
	}

	boolean found(int[] longArr, int[] shortArr, int longPtr, int shortPtr) {
		return longPtr >= 1 && longArr[longPtr - 1] <= shortArr[shortPtr]
				&& shortArr[shortPtr] <= longArr[longPtr] || shortPtr >= 1
				&& shortArr[shortPtr - 1] <= longArr[longPtr]
				&& longArr[longPtr] <= shortArr[shortPtr];
	}
	// bsearch: start, end, move start or end if necessary
	public double findMedianSortedArrays2(int A[], int B[]) {
		int shortArr[], longArr[];
		if (A.length > B.length) {
			shortArr = B;
			longArr = A;
		} else {
			shortArr = A;
			longArr = B;
		}
		int totalLen = A.length + B.length;
		boolean totalOdd = totalLen % 2 != 0;

		if (shortArr.length == 0) {
			return totalOdd ? longArr[totalLen / 2]
					: ((double) longArr[totalLen / 2 - 1] + (double) longArr[totalLen / 2]) / 2;
		} else if (longArr.length == 0) {
			return totalOdd ? shortArr[totalLen / 2]
					: ((double) shortArr[totalLen / 2 - 1] + (double) shortArr[totalLen / 2]) / 2;
		}
		int start = 0, end = shortArr.length - 1;
		int shortPtr = 0, longPtr = 0;
		while (start <= end) {
			shortPtr = (start + end) / 2;
			longPtr = totalLen / 2 - shortPtr - 1;
			if (findMatch(longArr, shortArr, longPtr, shortPtr)) {
				if (totalOdd) {
					return Math.max(longArr[longPtr], shortArr[shortPtr]);
				} else {
					// check if two numbers are in the same array
					if (longArr[longPtr] > shortArr[shortPtr] && longPtr > 0) {
						return ((double) Math.max(longArr[longPtr - 1],
								shortArr[shortPtr]) + (double) longArr[longPtr]) / 2;
					} else if (shortArr[shortPtr] > longArr[longPtr]
							&& shortPtr > 0) {
						return ((double) Math.max(shortArr[shortPtr - 1],
								longArr[longPtr]) + (double) shortArr[shortPtr]) / 2;
					}
					return ((double) longArr[longPtr] + (double) shortArr[shortPtr]) / 2;
				}
			} else {
				// shortPtr is too small, need to search right half
				if (shortArr[shortPtr] < longArr[longPtr]) {
					start = shortPtr + 1;
				}
				// shortPtr is too big, need to search left half
				else {
					end = shortPtr - 1;
				}
			}
		}
		longPtr = totalLen / 2 - shortPtr - 1;
		// right overflow - all numbers in shortArr are smaller than those in longArr
		if (shortArr[shortPtr] < longArr[longPtr]) {
			if (totalOdd) {
				return longArr[longPtr];
			} else {
				// find the biggest two from the three: longArr[longPtr], shortArr[shortPtr], longArr[longPtr-1]
				if (longPtr > 0) {
					return ((double) Math.max(shortArr[shortPtr],
							longArr[longPtr - 1]) + (double) longArr[longPtr]) / 2;
				} else {
					return ((double) longArr[longPtr] + (double) shortArr[shortPtr]) / 2;
				}
			}
		}
		// left overflow - all numbers in shortArr are bigger than those in longArr
		else {
			if (totalOdd) {
				if (longPtr < longArr.length - 1) {
					return Math.min(longArr[longPtr + 1], shortArr[shortPtr]);
				} else
					return shortArr[shortPtr];
			} else {
				// find the smallest two from the three: longArr[longPtr], shortArr[shortPtr], longArr[longPtr+1]
				if (longPtr < longArr.length - 1) {
					return ((double) Math.min(longArr[longPtr + 1],
							shortArr[shortPtr]) + (double) longArr[longPtr]) / 2;
				} else {
					return ((double) longArr[longPtr] + (double) shortArr[shortPtr]) / 2;
				}
			}
		}
	}

	boolean findMatch(int[] longArr, int[] shortArr, int longPtr, int shortPtr) {
		if (longPtr < longArr.length - 1
				&& longArr[longPtr] <= shortArr[shortPtr]
				&& shortArr[shortPtr] <= longArr[longPtr + 1]
				|| shortPtr < shortArr.length - 1
				&& shortArr[shortPtr] <= longArr[longPtr]
				&& longArr[longPtr] <= shortArr[shortPtr + 1])
			return true;
		return false;
	}

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
		int[] A = { 1, 2, 2 };
		int[] B = { 1, 2, 3 };
		System.out.println(new Solution().findMedianSortedArrays2(A, B));
	}
}
