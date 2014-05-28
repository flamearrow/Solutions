package threeSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
// Find all unique triplets in the array which gives the sum of zero.
//
//Note:
//
//    Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
//    The solution set must not contain duplicate triplets.
//
//    For example, given array S = {-1 0 1 2 -1 -4},
//
//    A solution set is:
//    (-1, 0, 1)
//    (-1, -1, 2)

public class Solution {

	public List<List<Integer>> threeSum2(int[] num) {
		Arrays.sort(num);
		List<List<Integer>> ret = new LinkedList<List<Integer>>();
		int i = 0;
		while (i < num.length - 2) {
			int target = 0 - num[i];
			int start = i + 1, end = num.length - 1;
			while (start < end) {
				if (num[start] + num[end] > target) {
					end--;
				} else if (num[start] + num[end] < target) {
					start++;
				} else {
					List<Integer> newList = new LinkedList<Integer>();
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
		return ret;
	}

	// a smarter solution: first sort the array, then mimic two sum
	public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
		ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
		Arrays.sort(num);
		for (int i = 0; i < num.length - 2; i++) {
			// skip dups
			if (i > 0 && num[i] == num[i - 1])
				continue;
			int sum = 0 - num[i];
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
					newList.add(num[i]);
					newList.add(num[start]);
					newList.add(num[end]);
					ret.add(newList);
					start++;
					end--;
				}
			}
		}
		return ret;
	}

	ArrayList<ArrayList<Integer>> ret;

	// naive recursion, use a boolean array to resolve dups
	public ArrayList<ArrayList<Integer>> threeSumRec(int[] num) {
		Arrays.sort(num);
		ret = new ArrayList<ArrayList<Integer>>();
		LinkedList<Integer> curPath = new LinkedList<Integer>();
		boolean[] visited = new boolean[num.length];
		probe(num, 0, 0, 3, curPath, visited);
		return ret;
	}

	public void probe(int[] num, int index, int curSum, int left,
			LinkedList<Integer> curPath, boolean[] visited) {
		if (left == 0) {
			if (curSum == 0)
				ret.add(new ArrayList<Integer>(curPath));
		} else if (index == num.length) {
			return;
		} else {
			// skip this
			probe(num, index + 1, curSum, left, curPath, visited);

			// use this
			// to remove dups, when cur char == previous char and previous char 
			// is NOT used, we won't use cur char
			// i.e for {1 1 1}, we will start with first one, but won't start
			// with second or third one
			if (index > 0 && num[index] == num[index - 1]
					&& !visited[index - 1])
				return;
			visited[index] = true;
			curPath.add(num[index]);
			probe(num, index + 1, curSum - num[index], left - 1, curPath,
					visited);
			curPath.removeLast();
			visited[index] = false;
		}
	}

	public static void main(String[] args) {
		int[] num = { 0, 0, 0 };
		ArrayList<ArrayList<Integer>> ret = new Solution().threeSum(num);
		System.out.println(ret);
	}
}
