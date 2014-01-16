package combinations;

import java.util.ArrayList;

//Given two integers n and k, return all possible combinations 
//of k numbers out of 1 ... n.
//
//For example,
//If n = 4 and k = 2, a solution is: 
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//]
public class Solution {
	// recursion
	public ArrayList<ArrayList<Integer>> combine(int n, int k) {
		if (k == 0)
			return new ArrayList<ArrayList<Integer>>();
		else if (k == 1) {
			ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
			for (int i = 1; i <= n; i++) {
				ArrayList<Integer> newArr = new ArrayList<Integer>();
				newArr.add(i);
				rst.add(newArr);
			}
			return rst;
		} else {
			ArrayList<ArrayList<Integer>> preRst = combine(n, k - 1);
			ArrayList<ArrayList<Integer>> curRst = new ArrayList<ArrayList<Integer>>();

			for (ArrayList<Integer> arr : preRst) {
				int lastNumber = arr.get(arr.size() - 1);
				if (lastNumber < n) {
					for (int i = lastNumber + 1; i <= n; i++) {
						ArrayList<Integer> newArr = new ArrayList<Integer>(arr);
						newArr.add(i);
						curRst.add(newArr);
					}
				}
			}
			return curRst;
		}
	}

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> rst = new Solution().combine(0, 4);
		for (ArrayList<Integer> arr : rst) {
			for (Integer i : arr) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
