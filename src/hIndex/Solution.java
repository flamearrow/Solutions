package hIndex;

//Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.
//
//According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, 
// and the other N − h papers have no more than h citations each."
//
//For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them 
// had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations 
// each and the remaining two with no more than 3 citations each, his h-index is 3.
//
//Note: If there are several possible values for h, the maximum one is taken as the h-index.
public class Solution {
	public static void main(String[] args) {
		int[] ca = {};
		System.out.println(new Solution().hIndex2(ca));
	}

	public int hIndex(int[] citations) {
		for (int i = citations.length; i > 0; i--) {
			if (getCountOver(i, citations) >= i) {
				return i;
			}
		}
		return 0;
	}

	// find count of numbers in citations that's over i
	int getCountOver(int i, int[] citations) {
		int ret = 0;
		for (int in : citations) {
			if (in >= i) {
				ret++;
			}
		}
		return ret;
	}

	// O(n) algo
	public int hIndex2(int[] citations) {
		int len = citations.length;
		int[] lenCount = new int[len + 1];
		for (int i : citations) {
			if (i >= len) {
				lenCount[len]++;
			} else {
				lenCount[i]++;
			}
		}
		for (int i = len - 1; i >= 0; i--) {
			lenCount[i] += lenCount[i + 1];
		}

		for (int i = len; i >= 0; i--) {
			if (lenCount[i] >= i) {
				return i;
			}
		}
		return -1;
	}

}
