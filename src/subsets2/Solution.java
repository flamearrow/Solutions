package subsets2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

//Given a collection of integers that might contain duplicates, S, return all possible subsets.
//
//Note:
//Elements in a subset must be in non-descending order.
//The solution set must not contain duplicate subsets.
//For example,
//If S = [1,2,2], a solution is:
//
//[
//  [2],
//  [1],
//  [1,2,2],
//  [2,2],
//  [1,2],
//  []
//]
public class Solution {
	// recurse, three parts:
	// 1) the subsets of previous result
	// 2) all arrays from 1) plus the new biggest number
	// 3) a single element array of the new biggest number
	// note apart from that, we need to calculate how many dups we met and avoid
	// adding duplicates
	public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
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
			int dup = 0;
			int dupCur = index;
			// counting from end - how many dup chars we get?
			while (dupCur >= 0 && num[dupCur] == num[index]) {
				dup++;
				dupCur--;
			}
			ArrayList<ArrayList<Integer>> ret;
			ret = probe(num, index - 1);

			int retSize = ret.size();
			here: for (int i = 0; i < retSize; i++) {
				ArrayList<Integer> ori = ret.get(i);
				if (num[index] != num[index - 1]) {
					ArrayList<Integer> newArr = new ArrayList<Integer>(ori);
					newArr.add(num[index]);
					ret.add(newArr);
				} else {
					int cur = ori.size() - 1;
					// skip all dup chars
					while (cur >= ori.size() - 1 - dup + 2) {
						if (cur < 0 || ori.get(cur) != num[index]) {
							continue here;
						}
						cur--;
					}

					ArrayList<Integer> newArr = new ArrayList<Integer>(ori);
					newArr.add(num[index]);
					ret.add(newArr);
				}
			}
			if (num[index] != num[index - 1]) {
				ArrayList<Integer> newArrSingle = new ArrayList<Integer>();
				newArrSingle.add(num[index]);
				ret.add(newArrSingle);
			}
			return ret;
		}
	}

	// using DFS in this one dup can be removed easier
	public ArrayList<ArrayList<Integer>> subsetsWithDup2(int[] num) {
		Arrays.sort(num);
		LinkedList<Integer> path = new LinkedList<Integer>();
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		probe(num, 0, path, ret);
		return ret;
	}

	void probe(int[] num, int curIndex, LinkedList<Integer> path,
			ArrayList<ArrayList<Integer>> ret) {
		ret.add(new ArrayList<Integer>(path));
		for (int i = curIndex; i < num.length; i++) {
			// note if num[i] == num[i-1] we don't probe i
			// because the result of i will be subset of result of i-1
			if (i > curIndex && num[i] == num[i - 1])
				continue;
			path.add(num[i]);
			probe(num, i + 1, path, ret);
			path.removeLast();
		}
	}

	public static void main(String[] args) {
		int[] num = { 1, 1, 2 };
		ArrayList<ArrayList<Integer>> ret = new Solution().subsetsWithDup2(num);
		System.out.println(ret);
	}
}
