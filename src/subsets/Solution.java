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

	public static void main(String[] args) {
		int[] num = { 0 };
		ArrayList<ArrayList<Integer>> ret = new Solution().subsets(num);
		System.out.println(ret);
	}
}
