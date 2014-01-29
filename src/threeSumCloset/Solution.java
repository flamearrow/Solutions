package threeSumCloset;

import java.util.Arrays;

//Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. 
//Return the sum of the three integers. You may assume that each input would have exactly one solution.
//
//For example, given array S = {-1 2 1 -4}, and target = 1.
//
//The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

public class Solution {

	// a smarter solution would be mimic the three sum problem, where we mimic a two sum approach
	public int threeSumClosest(int[] num, int target) {
		int min = num[0] + num[1] + num[2];
		Arrays.sort(num);
		for (int i = 0; i < num.length - 2; i++) {
			// skip dups
			if (i > 0 && num[i] == num[i - 1])
				continue;
			int sum = target - num[i];
			int start = i + 1, end = num.length - 1;
			while (start < end) {
				// skip dups
				if (start > i + 1 && num[start - 1] == num[start])
					start++;
				else if (end < num.length - 1 && num[end] == num[end + 1])
					end--;
				else if (num[start] + num[end] < sum) {
					if (Math.abs(sum - num[start] - num[end]) < Math.abs(target
							- min))
						min = num[start] + num[end] + num[i];
					start++;
				} else if (num[start] + num[end] > sum) {
					if (Math.abs(sum - num[start] - num[end]) < Math.abs(target
							- min))
						min = num[start] + num[end] + num[i];
					end--;
				}
				// find a pair
				else {
					return target;
				}
			}
		}
		return min;
	}

	// naively search all combinations
	int min = Integer.MAX_VALUE;

	public int threeSumClosestNaive(int[] num, int target) {
		// we don't even need to resolve dups!
		Arrays.sort(num);
		probe(num, 0, 3, target, 0);
		return min;
	}

	void probe(int[] num, int index, int left, int target, int currentSum) {
		if (left == 0) {
			if (Math.abs(currentSum - target) < Math.abs(min - target)) {
				min = currentSum;
			}
		} else if (index == num.length) {
			return;
		} else {
			probe(num, index + 1, left, target, currentSum);
			probe(num, index + 1, left - 1, target, currentSum + num[index]);
		}
	}

	public static void main(String[] args) {
		int[] num = { 0, 2, 1, -3 };
		int target = 1;
		System.out.println(new Solution().threeSumClosest(num, target));
	}
}
