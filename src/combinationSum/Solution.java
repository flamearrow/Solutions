package combinationSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

//Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
//
//The same repeated number may be chosen from C unlimited number of times.
//
//Note:
//
//   All numbers (including target) will be positive integers.
//   Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 <=a2 <= … <= ak).
//   The solution set must not contain duplicate combinations.
//
//For example, given candidate set 2,3,6,7 and target 7,
//A solution set is:
//[7]
//[2, 2, 3] 
public class Solution {
	// naive recursion
	public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates,
			int target) {
		Arrays.sort(candidates);
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		LinkedList<Integer> path = new LinkedList<Integer>();
		probe(candidates, 0, target, path, ret);
		return ret;
	}

	void probe(int[] candidates, int index, int target,
			LinkedList<Integer> path, ArrayList<ArrayList<Integer>> ret) {
		if (target < 0)
			return;
		if (target == 0) {
			ret.add(new ArrayList<Integer>(path));
		} else {
			for (int i = index; i < candidates.length; i++) {
				path.addLast(candidates[i]);
				probe(candidates, i, target - candidates[i], path, ret);
				path.removeLast();
			}
		}
	}

	public static void main(String[] args) {
		int[] candidates = { 2, 3, 6, 7 };
		int target = 2;
		ArrayList<ArrayList<Integer>> ret = new Solution().combinationSum(
				candidates, target);
		System.out.println(ret);
	}
}
