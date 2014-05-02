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
	public ArrayList<ArrayList<Integer>> combinationSum3(int[] num, int target) {
		Arrays.sort(num);
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		boolean[] used = new boolean[num.length];
		doProbe(num, target, 0, new LinkedList<Integer>(), used, ret);
		return ret;
	}

	void doProbe(int[] num, int left, int curIndex,
			LinkedList<Integer> curPath, boolean[] used,
			ArrayList<ArrayList<Integer>> ret) {
		if (left == 0) {
			ret.add(new ArrayList<Integer>(curPath));
			return;
		}
		if (curIndex == num.length)
			return;
		// use current index
		// note when checking reaching 0, should use '=' 
		if (left >= num[curIndex]) {
			// 1 1 1 2 3
			// we only keep using 1 if previous 1 is already used
			if (curIndex == 0
					|| (num[curIndex] != num[curIndex - 1])
					|| (num[curIndex] == num[curIndex - 1] && used[curIndex - 1])) {
				used[curIndex] = true;
				curPath.add(num[curIndex]);
				doProbe(num, left - num[curIndex], curIndex + 1, curPath, used,
						ret);
				used[curIndex] = false;
				curPath.removeLast();
			}
		}
		// don't use current index
		doProbe(num, left, curIndex + 1, curPath, used, ret);
	}

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
		int[] num = { 4, 3, 3, 5, 2 };
		int target = 11;
		ArrayList<ArrayList<Integer>> ret = new Solution().combinationSum3(num,
				target);
		System.out.println(ret);

	}
}
