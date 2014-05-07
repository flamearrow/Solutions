package longestConsecutiveSeq;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
//
//For example,
//Given [100, 4, 200, 1, 3, 2],
//The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
//
//Your algorithm should run in O(n) complexity.
public class Solution {
	public int longestConsecutive2(int[] num) {
		Set<Integer> back = new HashSet<Integer>();
		for (int i : num)
			back.add(i);
		int ret = -1;
		while (!back.isEmpty()) {
			int i = removeOneValueFromSet(back);
			int curLen = 1;
			int cur = i;
			// probe up
			while (back.contains(cur + 1)) {
				back.remove(cur + 1);
				curLen++;
				cur++;
			}
			cur = i;
			// probe down
			while (back.contains(cur - 1)) {
				back.remove(cur - 1);
				curLen++;
				cur--;
			}
			if (curLen > ret)
				ret = curLen;
		}
		return ret;
	}

	int removeOneValueFromSet(Set<Integer> set) {
		Iterator<Integer> itr = set.iterator();
		int ret = itr.next();
		set.remove(ret);
		return ret;
	}

	public int longestConsecutive(int[] num) {
		// backMap stores: item -> length of sequence containing this item
		HashMap<Integer, Integer> backMap = new HashMap<Integer, Integer>();
		int max = Integer.MIN_VALUE;
		int newLen = 0;
		for (int i : num) {
			if (!backMap.containsKey(i)) {
				newLen = 1;
				if (backMap.containsKey(i - 1) && backMap.containsKey(i + 1)) {
					newLen = backMap.get(i - 1) + backMap.get(i + 1) + 1;
					backMap.put(i, newLen);
				} else if (backMap.containsKey(i - 1)) {
					newLen = backMap.get(i - 1) + 1;
					backMap.put(i, newLen);
				} else if (backMap.containsKey(i + 1)) {
					newLen = backMap.get(i + 1) + 1;
					backMap.put(i, newLen);
				} else {
					backMap.put(i, newLen);
				}
				if (newLen > max)
					max = newLen;
				update(backMap, i);
			}
		}
		return max;
	}

	// for index, find if the map contains it's adjacent index, it contains,
	// update the adjacent index value to index value
	void update(HashMap<Integer, Integer> backMap, int index) {
		if (!backMap.containsKey(index))
			return;
		int probe = index + 1;
		int newValue = backMap.get(index);
		int oldValue = 0;
		// note we only need to update the boundary
		if (backMap.containsKey(probe)) {
			oldValue = backMap.get(probe);
			backMap.put(probe, newValue);
			backMap.put(probe + oldValue - 1, newValue);
		}
		probe = index - 1;
		if (backMap.containsKey(probe)) {
			oldValue = backMap.get(probe);
			backMap.put(probe, newValue);
			backMap.put(probe - oldValue + 1, newValue);
		}
	}

	public static void main(String[] args) {
		int[] input = { 0 };
		System.out.println(new Solution().longestConsecutive(input));
		System.out.println();
	}
}
