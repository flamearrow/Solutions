package twoSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Given an array of integers, find two numbers 
//such that they add up to a specific target number.
//
//The function twoSum should return indices of the two numbers such that they 
//add up to the target, where index1 must be less than index2. 
//Please note that your returned answers (both index1 and index2) are not zero-based.
//
//You may assume that each input would have exactly one solution.
//input is NOT sorted
public class TwoSum {
	
	public static int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> indics = new HashMap<>();
		for(int i = 0; i < nums.length; i++) {
			indics.put(nums[i],	i);
		}
		Arrays.sort(nums);
		int[] ret = new int[2];
		int start = 0, end = nums.length - 1;
		while(nums[start] + nums[end] != target) {
			if(nums[start] + nums[end] > target) {
				end--;
			} else {
				start++;
			}
		}
		ret[0] = indics.get(nums[start]);
		ret[1] = indics.get(nums[end]);
		Arrays.sort(ret);
		return ret;
	}
	
	public static int[] twoSum2(int[] nums, int target) {
		Map<Integer, Integer> indics = new HashMap<>();
		for(int i = 0; i < nums.length; i++) {
			if(indics.containsKey(nums[i])) {
				int[] ret = new int[2];
				ret[0] = indics.get(nums[i])+1;
				ret[1] = i+1;
				return ret;
			} else {
				int com = target - nums[i];
				indics.put(com, i);
			}
		}
		return null;
	}

	public static void main(String[] args) {
		int[] list = {3,1,5,6,2};
		int target = 11;
		for(int i : twoSum2(list, target)) {
			System.out.println(i);
		}
	}
}
