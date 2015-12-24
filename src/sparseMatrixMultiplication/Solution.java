package sparseMatrixMultiplication;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//Given two sparse matrices A and B, return the result of AB.
//
//You may assume that A's column number is equal to B's row number.
//
//Example:
//
//A = [
//  [ 1, 0, 0],
//  [-1, 0, 3]
//]
//
//B = [
//  [ 7, 0, 0 ],
//  [ 0, 0, 0 ],
//  [ 0, 0, 1 ]
//]
//
//
//     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
//AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
//                  | 0 0 1 |
public class Solution {
	public static void main(String[] args) {
		int[][] A = { { 1, 0, 0 }, { -1, 0, 3 } };
		int[][] B = { { 7, 0, 0 }, { 0, 0, 0 }, { 0, 0, 1 } };
		int[][] rst = new Solution().multiply(A, B);
		System.out.println("mlgb");
	}

	public int[][] multiply(int[][] A, int[][] B) {
		int height = A.length;
		if (height == 0) {
			return null;
		}
		int width = B[0].length;

		int loop = A[0].length;

		LinkedList<Map<Integer, Integer>> sparseMapA = new LinkedList<>();

		for (int i = 0; i < height; i++) {
			sparseMapA.add(new HashMap<Integer, Integer>());
			for (int j = 0; j < loop; j++) {
				if (A[i][j] != 0) {
					sparseMapA.getLast().put(j, A[i][j]);
				}
			}
		}

		int[][] ret = new int[height][width];

		for (int i = 0; i < height; i++) {
			Map<Integer, Integer> m = sparseMapA.get(i);
			for (int k = 0; k < width; k++) {
				// instead of looping though loop,
				// just loop through m - because only a nonzero number could
				// contribute to the result
				for (int validKey : m.keySet()) {
					ret[i][k] += m.get(validKey) * B[validKey][k];
				}
			}
		}
		return ret;
	}

	int getShit(List<Map<Integer, Integer>> map, int row, int col) {
		return map.get(row).containsKey(col) ? map.get(row).get(col) : 0;
	}
}
