package containsDup;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

//Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array 
//such that nums[i] = nums[j] and the difference between i and j is at most k.

//Given an array of integers, find out whether there are two distinct indices i and j in the array 
// such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
public class Solution {
	public static void main(String[] args) {
		System.out.println(-4 / 5);
	}

	public boolean containsNearbyDuplicate(int[] nums, int k) {
		Map<Integer, Integer> lastSeen = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			if (lastSeen.containsKey(nums[i])
					&& (i - lastSeen.get(nums[i]) <= k)) {
				return true;
			}
			lastSeen.put(nums[i], i);
		}
		return false;
	}

	// the idea is to map each number to a bucket in the number axis, a bucket
	// is of size t
	// Map<bucketNumber, number>
	// when seeing a new index, calculate its bucket number first
	// check if map contains bucketNumber, if it does, then we have a number
	// that's less than t from current number
	// also need to check bucketNumber+-1, and see if the actualy abs is smaller
	// than t
	// note we only keep track of up to last k numbers, so when we go over k, we
	// need to remove nums[i-k] from the map
	boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
		if (nums.length == 0 || nums.length == 1 || t < 0) {
			return false;
		}
		t++;
		Map<Integer, Integer> bucketMap = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			// remove if we're over k
			if (i > k) {
				bucketMap.remove(getBucket(nums[i - k - 1], t));
			}
			int bucket = getBucket(nums[i], t);
			if (bucketMap.containsKey(bucket)) {
				return true;
			}
			int[] arr = { bucket + 1, bucket - 1 };
			for (int newBucket : arr) {
				if (bucketMap.containsKey(newBucket)) {
					if (Math.abs(nums[i] - bucketMap.get(newBucket)) < t) {
						return true;
					}
				}
			}
			bucketMap.put(bucket, nums[i]);
		}
		return false;
	}

	int getBucket(int i, int t) {
		return i < 0 ? (i + 1) / t - 1 : i / t;
	}

	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if (nums.length == 0 || nums.length == 1) {
			return false;
		}
		LinkedList<Integer> q = new LinkedList<Integer>();
		for (int i : nums) {
			int max = getMax(q);
			int min = getMin(q);
			if (Math.abs(i - max) <= t || Math.abs(i - min) <= t) {
				return true;
			}
			q.addLast(i);
			if (q.size() > k) {
				q.removeFirst();
			}
		}
		return false;
	}

	int getMax(LinkedList<Integer> q) {
		int ret = Integer.MIN_VALUE;
		for (int i : q) {
			if (i > ret) {
				ret = i;
			}
		}
		return ret;
	}

	int getMin(LinkedList<Integer> q) {
		int ret = Integer.MAX_VALUE;
		for (int i : q) {
			if (i < ret) {
				ret = i;
			}
		}
		return ret;
	}

}
