package epi.longestNonDescreasingSeq;

// calculate the longest non decreasing subsequence of an array
public class Solution {
	// an O(n^2) solution: O(n) space, a naive dp
	//  dp[i] means the length of longest subseq from arr[i]
	//  starting filling the sequence from last index
	//  for all previous indices j, check k=from j to len-1, 
	//   if arr[j] < arr[k], we get a new length arr[k]+1, 
	//	 try all k, find max for arr[j]

	// a fancy O(nlogn) solution
	//  keep a array of length m
	//  m[i] means the index of smallest possible value of the last element 
	//    of an nondecreasing subsequence of length i
	// e.g:
	//   index:   0 1 2 3 4 5
	//   arr  :   0 8 4 7 6 3
	//   m    :   - 0 5 4
	//   the longest increasing subsequence would be length 3:
	//       0, 4, 6
	// note arr[m[1]] < arr[m[2]] < arr[m[3]] ... 
	// so for each new arr[i], we bSearch it's position in m, and hopefully can 
	//  extend the longest sequence we have seen so far
	// code will be far more cleaner if we don't need to rebuild the prev[] array 
	int longestNonDescreasingSeq(int[] arr) {
		int[] m = new int[arr.length + 1];
		int[] prev = new int[arr.length];
		m[1] = 0;
		int ret = 1;
		for (int i = 1; i < arr.length; i++) {
			int pos = bSearch(arr, m, arr[i], ret);
			// arr[m[pos]] would be the biggest number that's smaller then arr[i], i.e arr[i]'s predecessor

			// if we find a longer length, then prev should be the last one
			if (pos > ret) {
				prev[i] = m[ret];
				ret = pos;
			}
			// otherwise i would only be covering a previous one
			else if (pos > 0) {
				prev[i] = m[pos - 1];
			}
			m[pos] = i;
		}
		// print out the sequence
		// the first one should be last one of the sequence, i.e arr[m[ret]]
		int pre = m[ret];
		System.out.println("reverse order: ");
		for (int i = 0; i < ret; i++) {
			System.out.print(arr[pre] + ", ");
			// now the next one should be the current one's predecessor
			pre = prev[pre];
		}
		System.out.println();
		return ret;
	}

	// search for which index ret of m should target be inserted such that 
	//  arr[m[ret]] > target && arr[m[ret-1]] < target
	// since m is fully populated, we use len for the right bound
	int bSearch(int[] arr, int[] m, int target, int len) {
		int left = 1, right = len;
		while (left <= right) {
			int mid = (left + right) >> 1;
			if (arr[m[mid]] >= target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	public static void main(String[] args) {
		int[] arr = { 5, 4, 3, 2, 1 };
		System.out.println(new Solution().longestNonDescreasingSeq(arr));
	}
}
