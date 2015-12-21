package missingRanges;

import java.util.LinkedList;
import java.util.List;

//Given a sorted integer array where the range of elements are [lower, upper] inclusive, 
// return its missing ranges.
//
//For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, 
// return ["2", "4->49", "51->74", "76->99"].
public class Solution {
	// go through the array, record last time the interval is broken(start from lower-1)
	// each time check the distance between current and previous
	//  if diff > 1 then we need to add an interval
	public List<String> findMissingRanges(int[] nums, int lower, int upper) {
		List<String> ret = new LinkedList<String>();
		int pre = lower - 1;
		for (int i = 0; i <= nums.length; i++) {
			int cur = i == nums.length ? upper + 1 : nums[i];
			if (cur - pre > 1) {
				int left = pre + 1;
				int right = cur - 1;
				if (left < right)
					ret.add(left + "->" + right);
				else
					ret.add("" + left);
			}
			pre = cur;
		}
		return ret;
	}
}
