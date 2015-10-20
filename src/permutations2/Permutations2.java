package permutations2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//Given a collection of numbers that might contain duplicates, return all possible unique permutations.
//
//For example,
//[1,1,2] have the following unique permutations:
//[1,1,2], [1,2,1], and [2,1,1].
public class Permutations2 {
	public List<List<Integer>> permuteUnique(int[] nums) {
		Arrays.sort(nums);
		boolean[] used = new boolean[nums.length];
		List<List<Integer>> ret = new ArrayList<>();
		LinkedList<Integer> curPath = new LinkedList<>();
		probe(nums, used, curPath, ret);
		return ret;
	}

	void probe(int[] nums, boolean[] used, LinkedList<Integer> curPath,
			List<List<Integer>> ret) {
		if (curPath.size() == nums.length) {
			ret.add(new LinkedList<>(curPath));
		} else {
			for (int i = 0; i < nums.length; i++) {
				if (i > 0 && !used[i - 1] && nums[i] == nums[i - 1]) {
					continue;
				}
				if (!used[i]) {
					curPath.add(nums[i]);
					used[i] = true;
					probe(nums, used, curPath, ret);
					curPath.removeLast();
					used[i] = false;
				}
			}
		}
	}
}
