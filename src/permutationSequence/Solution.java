package permutationSequence;

//The set [1,2,3,бн,n] contains a total of n! unique permutations.
//
//By listing and labeling all of the permutations in order,
//We get the following sequence (ie, for n = 3):
//
//"123"
//"132"
//"213"
//"231"
//"312"
//"321"
//Given n and k, return the kth permutation sequence.
//
//Note: Given n will be between 1 and 9 inclusive.
public class Solution {
	boolean[] map = new boolean[9];

	public String getPermutation(int n, int k) {
		int[] back = new int[n];
		for (int i = 1; i <= n; i++) {
			back[i - 1] = i;
		}
		while (k-- > 1) {
			if (!getNext(back))
				return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i : back)
			sb.append(i);
		return sb.toString();
	}

	// O(n) solution
	boolean getNext(int[] back) {
		int swapFrom = back.length - 2;
		// first search from end, find the first occurrence
		// back[swapFrom]<back[swapFrom-1]
		while (back[swapFrom] > back[swapFrom + 1]) {
			swapFrom--;
			if (swapFrom < 0)
				return false;
		}
		int swapTo = back.length - 1;
		// we know we need to reshuffle from swapFrom, i.e get the next big one
		// from swapFrom, i.e for 1342, we find swapFrom = 1 because 3<4
		//
		// then we need to find the smallest digit to put at most significant
		// bit(swapFrom) since swapFrom+1 to end is reversely sorted, we just
		// need to search from end
		// i.e for 1432, we need to swap 3 with 4 and get 1432
		while (back[swapFrom] > back[swapTo]) {
			swapTo--;
		}
		// now we have found the smallest digit that's bigger than
		// back[swapFrom], swap them
		int tmp = back[swapFrom];
		back[swapFrom] = back[swapTo];
		back[swapTo] = tmp;

		// now swapFrom+1 to end is reversely sorted, reverse them
		// ie we reverse 32 and get 23-> now 1423
		// now reverse back[swapFrom+1, back.length-1]
		int l = swapFrom + 1;
		int r = back.length - 1;
		while (l < r) {
			back[l] ^= back[r];
			back[r] ^= back[l];
			back[l] ^= back[r];
			l++;
			r--;
		}
		return true;
	}

	// this is a O(n^2) solution
	boolean getNextNSqr(int[] back) {
		int swapFrom = back.length - 2;
		int swapTo = back.length - 1;
		while (back[swapFrom] > back[swapTo]) {
			swapTo--;
			if (swapTo == swapFrom) {
				swapFrom--;
				swapTo = back.length - 1;
				if (swapFrom < 0)
					return false;
			}
		}

		int tmp = back[swapFrom];
		back[swapFrom] = back[swapTo];
		back[swapTo] = tmp;

		// now from swapTo+1 to end, we need to reassemble to the smallest
		// number
		for (int i = swapFrom + 1; i < back.length; i++) {
			map[back[i] - 1] = true;
		}

		int smallestBit = swapFrom + 1;
		for (int i = 0; i < map.length; i++) {
			if (map[i]) {
				back[smallestBit++] = i + 1;
				map[i] = false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		for (int i = 1; i < 24; i++)
			System.out.println(new Solution().getPermutation(4, i));
	}
}
