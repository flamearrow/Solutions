package factorCombinations;

import java.util.LinkedList;
import java.util.List;

//Numbers can be regarded as product of its factors. For example,
//
//8 = 2 x 2 x 2;
//  = 2 x 4.
//Write a function that takes an integer n and return all possible combinations of its factors.
//
//Note: 
//Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
//You may assume that n is always positive.
//Factors should be greater than 1 and less than n.
//Examples: 
//input: 1
//output: 
//[]
//input: 37
//output: 
//[]
//input: 12
//output:
//[
//  [2, 6],
//  [2, 2, 3],
//  [3, 4]
//]
//input: 32
//output:
//[
//  [2, 16],
//  [2, 2, 8],
//  [2, 2, 2, 4],
//  [2, 2, 2, 2, 2],
//  [2, 4, 4],
//  [4, 8]
//]
public class Solution {
	public static void main(String[] args) {
		for (List<Integer> ll : new Solution().getFactors(12)) {
			for (int i : ll) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

	public List<List<Integer>> getFactors(int n) {
		// return doFind(n, n, 2);
		List<List<Integer>> ret = new LinkedList<>();
		LinkedList<Integer> path = new LinkedList<Integer>();
		doFind2(ret, path, n, 2);
		return ret;
	}

	void doFind2(List<List<Integer>> ret, LinkedList<Integer> path, int n,
			int last) {
		if (n == 1 && path.size() > 1) {
			ret.add(new LinkedList<Integer>(path));
			return;
		}

		for (int i = last; i <= n; i++) {
			if (n % i == 0) {
				int factor = n / i;
				if (i >= last) {
					path.add(i);
					doFind2(ret, path, factor, i);
					path.removeLast();
				}
			}
		}
	}

	List<List<Integer>> doFind(int n, int original, int last) {
		List<List<Integer>> ret = new LinkedList<>();
		for (int f = last; f <= n / 2; f++) {
			if (n % f == 0) {
				int other = n / f;
				if (other < f) {
					continue;
				}
				for (List<Integer> subList : doFind(other, n, f)) {
					List<Integer> subL = new LinkedList<Integer>();
					subL.add(f);
					subL.addAll(subList);
					ret.add(subL);
				}
			}
		}
		if (n != original) {
			List<Integer> l = new LinkedList<Integer>();
			l.add(n);
			ret.add(l);
		}
		return ret;
	}

}
