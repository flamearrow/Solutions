package combinationSum2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

//Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
//
//Each number in C may only be used once in the combination.
//
//Note:
//
//   All numbers (including target) will be positive integers.
//   Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 <= a2 <= … <= ak).
//   The solution set must not contain duplicate combinations.
//
//For example, given candidate set 10,1,2,7,6,1,5 and target 8,
//A solution set is:
//[1, 7]
//[1, 2, 5]
//[2, 6]
//[1, 1, 6] 
public class Solution {
	// similar to the dup one, but we need to recurse in two ways
	// note when we need to resolve dup, always try to use a boolean array to
	// mark visited
	public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
		Arrays.sort(num);
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		LinkedList<Integer> path = new LinkedList<Integer>();
		boolean[] visited = new boolean[num.length];
		probe(num, target, 0, path, ret, visited);
		return ret;
	}

	void probe(int[] num, int target, int index, LinkedList<Integer> path,
			ArrayList<ArrayList<Integer>> ret, boolean[] visited) {
		if (target == 0) {
			ret.add(new ArrayList<Integer>(path));
			return;
		}
		if (target < 0 || index == num.length)
			return;

		// don't use num[index]
		probe(num, target, index + 1, path, ret, visited);

		// try use num[index] resolving dup using visited
		// if it's not the first of consecutive, and its previous has not been
		// visited, then we can't visit this
		// i.e for 1, 1, 1: we can visited the second, but can't visited
		// the third
		if (index > 0 && num[index] == num[index - 1] && !visited[index - 1]) {
			return;
		}
		visited[index] = true;
		path.add(num[index]);
		probe(num, target - num[index], index + 1, path, ret, visited);
		path.removeLast();
		visited[index] = false;
	}

	public static void main(String[] args) {
		int[] num = { 8, 7, 4, 3 };
		int target = 11;
		ArrayList<ArrayList<Integer>> ret = new Solution().combinationSum2(num,
				target);
		System.out.println(ret);

	}
}
