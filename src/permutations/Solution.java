package permutations;

import java.util.ArrayList;

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

	public static void main(String[] args) {
		int[] num = {};
		ArrayList<ArrayList<Integer>> ret = new Solution().permute(num);
		System.out.println(ret);
	}
}
