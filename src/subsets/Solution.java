package subsets;

import java.util.ArrayList;
import java.util.Arrays;

//Given a set of distinct integers, S, return all possible subsets.
//
//Note:
//Elements in a subset must be in non-descending order.
//The solution set must not contain duplicate subsets.
//For example,
//If S = [1,2,3], a solution is:
//[
// [3],
// [1],
// [2],
// [1,2,3],
// [1,3],
// [2,3],
// [1,2],
// []
//]
public class Solution {
	// recurse, three parts:
	// 1) the subsets of previous result
	// 2) all arrays from 1) plus the new biggest number
	// 3) a single element array of the new biggest number
	public ArrayList<ArrayList<Integer>> subsets(int[] num) {
		Arrays.sort(num);
		ArrayList<ArrayList<Integer>> ret = probe(num, num.length - 1);
		ret.add(new ArrayList<Integer>());
		return ret;
	}

	ArrayList<ArrayList<Integer>> probe(int[] num, int index) {
		if (index == 0) {
			ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> newArr = new ArrayList<Integer>();
			newArr.add(num[0]);
			ret.add(newArr);
			return ret;
		} else {
			ArrayList<ArrayList<Integer>> ret = probe(num, index - 1);
			int retSize = ret.size();
			for (int i = 0; i < retSize; i++) {
				ArrayList<Integer> ori = ret.get(i);
				ArrayList<Integer> newArr = new ArrayList<Integer>(ori);
				newArr.add(num[index]);
				ret.add(newArr);
			}
			ArrayList<Integer> newArrSingle = new ArrayList<Integer>();
			newArrSingle.add(num[index]);
			ret.add(newArrSingle);
			return ret;
		}
	}

	// another solution, use DFS
	// probe is searching for all combinations starting from curIndex
	// in each iteration, we try to find a path starting from curIndex to end
	// since the path before curIndex is already in place we just need to
	// concatenate

	// this is DFS because we are searching a btree of height num.length-1,
	// at each level i we need to decide to take num[i] or not
	public ArrayList<ArrayList<Integer>> subsets2(int[] num) {
		Arrays.sort(num);
		ArrayList<Integer> curSet = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		probe(num, 0, curSet, ret);
		return ret;
	}

	void probe(int[] num, int curIndex, ArrayList<Integer> currentPath,
			ArrayList<ArrayList<Integer>> result) {
		result.add(new ArrayList<Integer>(currentPath));
		for (int i = curIndex; i < num.length; i++) {
			currentPath.add(num[i]);
			probe(num, i + 1, currentPath, result);
			currentPath.remove(currentPath.size() - 1);
		}
	}

	// a more human readable way, consider this as a btree,
	// going left is 'take the current element'
	// going right is 'not to take the current element'
	// we only add to result when we go left
	public ArrayList<ArrayList<Integer>> subsets3(int[] num) {
		Arrays.sort(num);
		ArrayList<Integer> curSet = new ArrayList<Integer>();
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		ret.add(new ArrayList<Integer>(curSet));
		probe2(num, 0, curSet, ret);
		return ret;
	}

	void probe2(int[] num, int curIndex, ArrayList<Integer> currentPath,
			ArrayList<ArrayList<Integer>> result) {
		if (curIndex == num.length)
			return;
		currentPath.add(num[curIndex]);
		result.add(new ArrayList<Integer>(currentPath));
		probe2(num, curIndex + 1, currentPath, result);
		currentPath.remove(currentPath.size() - 1);
		probe2(num, curIndex + 1, currentPath, result);
	}

	public static void main(String[] args) {
		int[] num = { 1, 2, 3 };
		ArrayList<ArrayList<Integer>> ret = new Solution().subsets3(num);
		System.out.println(ret);
	}
}
