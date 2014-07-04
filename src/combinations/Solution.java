package combinations;

import java.util.ArrayList;
import java.util.LinkedList;

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

	public ArrayList<ArrayList<Integer>> combine2(int n, int k) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		LinkedList<Integer> cur = new LinkedList<Integer>();
		probe(ret, cur, 1, n, k);
		return ret;
	}

	// return all eligible number combinations of size of count from start to end
	// don't even care about the situation when there's not enough number
	//  e.g from 4 to 5, find 6 numbers - use count<0 to terminate it
	void probe(ArrayList<ArrayList<Integer>> ret, LinkedList<Integer> cur,
			int start, int end, int count) {
		if (count < 0)
			return;
		if (count == 0)
			ret.add(new ArrayList<Integer>(cur));
		else {
			for (int i = start; i <= end; i++) {
				cur.addLast(i);
				probe(ret, cur, i + 1, end, count - 1);
				cur.removeLast();
			}
		}
	}

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
		ArrayList<ArrayList<Integer>> rst = new Solution().combine2(4, 4);
		for (ArrayList<Integer> arr : rst) {
			for (Integer i : arr) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
