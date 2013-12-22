package pascalTriangle;

import java.util.ArrayList;

//Given numRows, generate the first numRows of Pascal's triangle.
//
//For example, given numRows = 5,
//Return
//
//[
//     [1],
//    [1,1],
//   [1,2,1],
//  [1,3,3,1],
// [1,4,6,4,1]
//]
public class Solution {
	public ArrayList<ArrayList<Integer>> generate(int numRows) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		if (numRows == 0)
			return ret;

		for (int i = 0; i < numRows; i++) {
			ArrayList<Integer> newList = new ArrayList<Integer>();
			newList.add(1);
			// i+1 is the length of current list
			// i is the last element, should be one
			for (int j = 1; j < i; j++) {
				newList.add(ret.get(i - 1).get(j - 1) + ret.get(i - 1).get(j));
			}
			if (i > 0)
				newList.add(1);
			ret.add(newList);
		}
		return ret;
	}

	public static void main(String[] args) {
		for (ArrayList<Integer> lst : new Solution().generate(10)) {
			for (int i : lst)
				System.out.print(i + " ");
			System.out.println();
		}
	}
}
