package threeSum;

import java.util.ArrayList;
//Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
//Find all unique triplets in the array which gives the sum of zero.
//
//Note:
//
// Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
// The solution set must not contain duplicate triplets.
//
// For example, given array S = {-1 0 1 2 -1 -4},
//
// A solution set is:
// (-1, 0, 1)
// (-1, -1, 2)
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

	public static List<List<Integer>> threeSum(int[] num) {
		Arrays.sort(num);
		List<List<Integer>> ret = new ArrayList<>();
		for (int i = 0; i < num.length - 2; i++) {
			if (i > 0 && num[i] == num[i - 1])
				continue;
			int left = i + 1;
			int right = num.length - 1;
			int target = 0 - num[i];
			while (left < right) {
				int cur = num[left] + num[right];
				if (left > i + 1 && num[left] == num[left - 1]) {
					left++;
				} else if (right < num.length - 2
						&& num[right] == num[right + 1]) {
					right--;
				} else if (cur < target) {
					left++;
				} else if (cur > target) {
					right--;
				} else {
					ArrayList<Integer> newList = new ArrayList<Integer>();
					newList.add(num[i]);
					newList.add(num[left]);
					newList.add(num[right]);
					ret.add(newList);
					left++;
					right--;
				}
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		// int[] num = {-1, 0, 1, 2, -1, -4};
		int[] num = { 0, 0, 0, 0 };
		for (List<Integer> list : threeSum(num)) {
			for (int i : list) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}
}
