package twoSum;

import java.util.Arrays;

// Given an array of integers, find two numbers 
// such that they add up to a specific target number.
//
// The function twoSum should return indices of the two numbers such that they 
// add up to the target, where index1 must be less than index2. 
// Please note that your returned answers (both index1 and index2) are not zero-based.
//
// You may assume that each input would have exactly one solution.
// input is NOT sorted
public class Solution {
	public int[] twoSum(int[] numbers, int target) {
		int[] back = numbers.clone();
		Arrays.sort(numbers);
		int head = 0, tail = numbers.length - 1;
		int[] ret = new int[2];
		while (head < tail) {
			if (numbers[head] + numbers[tail] < target) {
				head++;
			} else if (numbers[head] + numbers[tail] > target) {
				tail--;
			} else {
				ret[0] = findIndex(back, numbers[head]) + 1;
				// reverse find to avoid duplicates that point to the same index
				ret[1] = findIndexFromEnd(back, numbers[tail]) + 1;
				if (ret[0] > ret[1]) {
					ret[0] ^= ret[1];
					ret[1] ^= ret[0];
					ret[0] ^= ret[1];
				}
				break;
			}
		}
		return ret;
	}

	public int findIndex(int[] back, int value) {
		// return doFind(back, 0, back.length - 1, value);
		for (int i = 0; i < back.length; i++) {
			if (back[i] == value)
				return i;
		}
		return -1;
	}
	public int findIndexFromEnd(int[] back, int value) {
		// return doFind(back, 0, back.length - 1, value);
		for (int i = back.length - 1; i >= 0; i--) {
			if (back[i] == value)
				return i;
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println("mlgb");
		Solution s = new Solution();
		int[] input = {2, 1, 9, 4, 4, 56, 90, 3};
		int[] ret = s.twoSum(input, 8);
		System.out.println(ret[0] + " : " + ret[1]);
	}
}