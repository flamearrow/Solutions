package fourSum;

import java.util.ArrayList;
import java.util.Arrays;

//Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
// Find all unique quadruplets in the array which gives the sum of target.
//
//Note:
//Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a �� b �� c �� d)
//The solution set must not contain duplicate quadruplets.
//    For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
//
//    A solution set is:
//    (-1,  0, 0, 1)
//    (-2, -1, 1, 2)
//    (-2,  0, 0, 2)
public class Solution {
	public List<List<Integer>> fourSum2(int[] num, int target) {
		Arrays.sort(num);
		List<List<Integer>> ret = new LinkedList<List<Integer>>();
		int k = 0;
		while (k < num.length - 3) {
			int tmpTarget = target - num[k];
			int i = k + 1;
			while (i < num.length - 2) {
				int tmp = tmpTarget - num[i];
				int start = i + 1, end = num.length - 1;
				while (start < end) {
					if (num[start] + num[end] > tmp) {
						end--;
					} else if (num[start] + num[end] < tmp) {
						start++;
					} else {
						List<Integer> newList = new LinkedList<Integer>();
						newList.add(num[k]);
						newList.add(num[i]);
						newList.add(num[start]);
						newList.add(num[end]);
						ret.add(newList);
						int left = num[start];
						int right = num[end];
						while (start < num.length && num[start] == left) {
							start++;
						}
						while (end >= 0 && num[end] == right) {
							end--;
						}
					}
				}
				int cur = num[i];
				while (i < num.length - 2 && num[i] == cur)
					i++;
			}
			int current = num[k];
			while (k < num.length - 3 && num[k] == current)
				k++;
		}
		return ret;
	}
	
	// ok let's imitate 3 sum... this will be a O(n^3) solution
	public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		Arrays.sort(num);
		for (int k = 0; k < num.length - 3; k++) {
			// skip dups
			if (k > 0 && num[k] == num[k - 1])
				continue;
			for (int i = k + 1; i < num.length - 2; i++) {
				// skip dups
				// note we need to make sure num[k] and num[i] are unique
				if (i > k + 1 && num[i] == num[i - 1])
					continue;
				int sum = target - num[k] - num[i];
				int start = i + 1, end = num.length - 1;
				while (start < end) {
					// skip dups
					if (start > i + 1 && num[start - 1] == num[start])
						start++;
					else if (end < num.length - 1 && num[end] == num[end + 1])
						end--;
					else if (num[start] + num[end] < sum) {
						start++;
					} else if (num[start] + num[end] > sum) {
						end--;
					}
					// find a pair
					else {
						ArrayList<Integer> newList = new ArrayList<Integer>();
						newList.add(num[k]);
						newList.add(num[i]);
						newList.add(num[start]);
						newList.add(num[end]);
						ret.add(newList);
						start++;
						end--;
					}
				}
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		int[] num = { 1, 0, -1, 0, -2, 2, -1, -1, -1, -1, -1, -1, -1 };
		int target = 0;
		System.out.println(new Solution().fourSum(num, target));
	}
}
