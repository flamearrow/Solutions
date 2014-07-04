package allWaysAddToSum;

import java.util.ArrayList;
import java.util.LinkedList;

// find all possible ways to add to a given number
// e.g: input 2, output {{1, 1}, {2}} 
// 	    input 3, output {{1, 1, 1}, {1, 2}, {3}}
public class Solution {
	ArrayList<ArrayList<Integer>> getWays(int input) {
		LinkedList<Integer> curPath = new LinkedList<Integer>();
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		doProbe(ret, curPath, input);
		return ret;
	}

	void doProbe(ArrayList<ArrayList<Integer>> ret,
			LinkedList<Integer> curPath, int input) {
		if (input == 0) {
			ret.add(new ArrayList<Integer>(curPath));
			return;
		}
		for (int i = 1; i <= input; i++) {
			if (curPath.size() > 0 && curPath.getLast() > i)
				continue;
			curPath.add(i);
			doProbe(ret, curPath, input - i);
			curPath.removeLast();
		}
	}

	public static void main(String[] args) {
		for (ArrayList<Integer> list : new Solution().getWays(5)) {
			System.out.println(list);
		}
	}
}
