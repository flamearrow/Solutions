package permutations;

import java.util.ArrayList;
import java.util.LinkedList;

//Given a collection of numbers, return all possible permutations.
//
//For example,
//[1,2,3] have the following permutations:
//[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
public class Solution {
	public ArrayList<ArrayList<Integer>> permute(int[] num) {
		return getPermute(num, num.length - 1);
	}

	ArrayList<ArrayList<Integer>> getPermute(int[] num, int firstN) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		if (firstN == 0) {
			ArrayList<Integer> newList = new ArrayList<Integer>();
			newList.add(num[0]);
			ret.add(newList);
		} else if (firstN > 0) {
			ArrayList<ArrayList<Integer>> pre = getPermute(num, firstN - 1);
			for (ArrayList<Integer> arr : pre) {
				for (int i = 0; i <= arr.size(); i++) {
					ArrayList<Integer> newArr = new ArrayList<Integer>(arr);
					newArr.add(i, num[firstN]);
					ret.add(newArr);
				}
			}
		}
		return ret;
	}

	// DFS, use a boolean[] to mark visit
	// we do this because dup resolving will be easier
	public ArrayList<ArrayList<Integer>> permuteDFS(int[] num) {
		LinkedList<Integer> path = new LinkedList<Integer>();
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		boolean[] used = new boolean[num.length];
		probe(num, path, ret, used);
		return ret;
	}

	void probe(int[] num, LinkedList<Integer> path,
			ArrayList<ArrayList<Integer>> ret, boolean[] used) {
		if (path.size() == num.length) {
			ret.add(new ArrayList<Integer>(path));
		}

		for (int i = 0; i < num.length; i++) {
			if (!used[i]) {
				used[i] = true;
				path.add(num[i]);
				probe(num, path, ret, used);
				used[i] = false;
				path.removeLast();
			}
		}
	}

	public static void main(String[] args) {
		int[] num = { 1, 2, 3 };
		ArrayList<ArrayList<Integer>> ret = new Solution().permuteDFS(num);
		System.out.println(ret);
	}
}
