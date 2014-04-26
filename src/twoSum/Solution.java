package twoSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
	// this is a O(n) solution, use a map that maps complement and index
	public int[] twoSumNew(int[] numbers, int target) {
		// backMap maps <complement, index> such that complement+numbers[index]=target
		Map<Integer, Integer> backMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < numbers.length; i++) {
			if (backMap.containsKey(numbers[i])) {
				int[] ret = new int[2];
				ret[0] = backMap.get(numbers[i]) + 1;
				ret[1] = i + 1;
				return ret;
			} else {
				backMap.put(target - numbers[i], i);
			}
		}
		return null;
	}

	// O(nlogn) naive
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
		int[] input = { 2, 1, 9, 4, 4, 56, 90, 3 };
		int[] ret = s.twoSum(input, 8);
		System.out.println(ret[0] + " : " + ret[1]);
	}
}