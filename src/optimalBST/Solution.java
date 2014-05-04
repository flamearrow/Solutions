package optimalBST;

//Given a sorted array keys[0.. n-1] of search keys and an array freq[0.. n-1] of frequency counts, 
// where freq[i] is the number of searches to keys[i]. 
// Construct a binary search tree of all keys such that the total cost of all the searches is as small as possible.
//
//Let us first define the cost of a BST. The cost of a BST node is level of that node multiplied by its frequency. Level of root is 1.
//
//Example 1
//Input:  keys[] = {10, 12}, freq[] = {34, 50}
//There can be following two possible BSTs 
//       10                       12
//         \                     / 
//          12                 10
//         I                     II
//Frequency of searches of 10 and 12 are 34 and 50 respectively.
//The cost of tree I is 34*1 + 50*2 = 134
//The cost of tree II is 50*1 + 34*2 = 118 
//
//Example 2
//Input:  keys[] = {10, 12, 20}, freq[] = {34, 8, 50}
//There can be following possible BSTs
//   10                12                 20         10              20
//     \             /    \              /             \            /
//     12          10     20           12               20         10  
//       \                            /                 /           \
//        20                        10                12             12  
//    I               II             III             IV             V
//Among all possible BSTs, cost of the fifth BST is minimum.  
//Cost of the fifth BST is 1*50 + 2*34 + 3*8 = 142

public class Solution {

	// for a BST problem, try to divide it into left/right sub
	int optimalBSTval(int[] freq, int[] tags) {
		return calOptimalBST(freq, 0, freq.length - 1);
	}

	// the idea is to try each number as root, and recursively calculate its left and right subtree
	// note there's a trick here: depend on the result of a subtree, how do we calculate the result of current tree?
	// 		we can't do result(current) = freq[root] + 2*result(leftSub) + 2*result(rightSub)
	//    but should do result(current) = sum(all nodes in the tree) + result(leftSub) + result(rightSub)
	//      because when a subtree drops one level, we need to add another copy of its entire sum
	//      e.g: 
	//               1
	//             /
	//            2
	//        would have 1 + 2*2 = 4

	//        when it drops one level
	//                3
	//               /
	//              1
	//             /
	//            2
	//        the subtree of 1, 2 now costs 1+2*2+1+2 = 1*2 + 2*3 = 8
	// therefore
	// f(start, end) = sum(freq[start] to freq[end]) + min(f(start, i) + f(i, end)) 
	//                 where i = [start, end]
	// can have a two dimentional dp array to buffer results of particular start and end pair
	int calOptimalBST(int[] freq, int start, int end) {
		if (start == end)
			return freq[start];
		if (start > end)
			return 0;
		int freqSum = 0;
		for (int i = start; i <= end; i++)
			freqSum += freq[i];
		int ret = Integer.MAX_VALUE;
		for (int i = start; i <= end; i++) {
			int tmpRst = calOptimalBST(freq, start, i - 1)
					+ calOptimalBST(freq, i + 1, end);
			if (tmpRst < ret)
				ret = tmpRst;
		}
		return ret + freqSum;
	}

	public static void main(String[] args) {
		int[] freq = { 34, 8, 50 };
		System.out.println(new Solution().optimalBSTval(freq, null));
	}
}
