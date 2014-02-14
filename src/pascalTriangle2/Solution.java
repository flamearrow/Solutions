package pascalTriangle2;

import java.util.ArrayList;

//Given an index k, return the kth row of the Pascal's triangle.
//
//For example, given k = 3,
//Return [1,3,3,1].
//
//Note:
//Could you optimize your algorithm to use only O(k) extra space?
public class Solution {
	// just add from tail - don't need any buffer
	public ArrayList<Integer> getRowNew(int rowIndex) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int i = 0; i <= rowIndex; i++) {
			for (int j = i - 1; j > 0; j--) {
				ret.set(j, ret.get(j) + ret.get(j - 1));
			}
			ret.add(1);
		}
		return ret;
	}

	public ArrayList<Integer> getRow(int rowIndex) {
		rowIndex++;
		ArrayList<Integer> ret = new ArrayList<Integer>();
		ArrayList<Integer> back = new ArrayList<Integer>();

		ret.add(1);
		for (int i = 0; i < rowIndex; i++) {
			// i th row has i+1 numbers
			// the i th number should be 1
			for (int j = 1; j < i; j++) {
				ret.set(j, back.get(j) + back.get(j - 1));
			}
			if (i > 0)
				ret.add(1);
			back.clear();
			back.addAll(ret);
		}
		return ret;
	}

	public static void main(String[] args) {
		for (int i : new Solution().getRowNew(4)) {
			System.out.print(i + " ");
		}
	}
}
