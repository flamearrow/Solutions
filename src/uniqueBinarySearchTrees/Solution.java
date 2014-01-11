package uniqueBinarySearchTrees;

//Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
//
//For example,
//Given n = 3, there are a total of 5 unique BST's.
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3
public class Solution {
	int[] cache;

	public int numTrees(int n) {
		cache = new int[n];
		return doNumTrees(n);
	}

	public int doNumTrees(int n) {
		if (n <= 1)
			return 1;
		if (cache[n - 1] != 0)
			return cache[n - 1];
		int ret = 0;
		// to calculate n, use i=1...n as root
		// then to left of i, there are numTrees(i-1) subtrees
		// to right of i, there are numTrees(n-i) subtrees
		for (int i = 1; i <= n; i++) {
			ret += doNumTrees(i - 1) * doNumTrees(n - i);
		}
		cache[n - 1] = ret;
		return ret;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().numTrees(3));
	}
}
