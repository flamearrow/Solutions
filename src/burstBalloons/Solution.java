package burstBalloons;

import java.util.LinkedList;
import java.util.List;

//Given n balloons, indexed from 0 to n-1. 
// Each balloon is painted with a number on it represented by array nums. 
// You are asked to burst all the balloons. 
// If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. 
// Here left and right are adjacent indices of i. 
// After the burst, the left and right then becomes adjacent.
//
//Find the maximum coins you can collect by bursting the balloons wisely.
//
//Note: 
//(1) You may imagine nums[-1] = nums[n] = 1. 
// They are not real therefore you can not burst them.
//(2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
//
//Example:
//
//Given [3, 1, 5, 8]
//
//Return 167
//
//    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
//   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
public class Solution {
	public static void main(String[] args) {
		int[] nums = { 1, 2, 3 };
		System.out.println(new Solution().maxCoins(nums));
	}

	public int maxCoins(int[] nums) {
		List<Integer> list = new LinkedList<>();
		for (int i : nums) {
			list.add(i);
		}
		return doShit(list, 0, Integer.MIN_VALUE);
	}

	int doShit(List<Integer> list, int cur, int max) {
		if (list.size() == 0) {
			return Math.max(cur, max);
		} else {
			int ret = max;
			for (int i = 0; i < list.size(); i++) {
				int added = list.get(i) * (i == 0 ? 1 : list.get(i - 1)) * (i == list.size() - 1 ? 1 : list.get(i + 1));
				int removed = list.remove(i);
				ret = Math.max(max, doShit(list, cur + added, ret));
				list.add(i, removed);
			}
			return ret;
		}
	}
}
