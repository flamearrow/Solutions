package permutations2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

//Given a collection of numbers that might contain duplicates, return all possible unique permutations.
//
//For example,
//[1,1,2] have the following unique permutations:
//[1,1,2], [1,2,1], and [2,1,1].
public class Solution {
	// a similar DFS solution
	public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
		Arrays.sort(num);
		boolean[] used = new boolean[num.length];
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		LinkedList<Integer> path = new LinkedList<Integer>();
		probe(num, used, ret, path);
		return ret;
	}

	void probe(int[] num, boolean[] used, ArrayList<ArrayList<Integer>> ret,
			LinkedList<Integer> path) {
		if (path.size() == num.length) {
			ret.add(new ArrayList<Integer>(path));
		}

		for (int i = 0; i < num.length; i++) {
			// since they are sorted, the result of all path starts with num[i]
			// is subset of that of num[i-1]
			// here both !used[i-1] and used[i-1] would work
			if (i > 0 && !used[i - 1] && num[i] == num[i - 1]) {
				continue;
			}
			if (!used[i]) {
				used[i] = true;
				path.add(num[i]);
				probe(num, used, ret, path);
				used[i] = false;
				path.removeLast();
			}
		}
	}

	public static void main(String[] args) {
		int[] num = { 1, 1, 3 };
		ArrayList<ArrayList<Integer>> ret = new Solution().permuteUnique(num);
		System.out.println(ret);
	}
}
