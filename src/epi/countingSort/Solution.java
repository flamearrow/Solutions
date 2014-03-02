package epi.countingSort;

import java.util.HashMap;
import java.util.Map;

// 13.2
// You are given an array of n Person objects. Each Person object has
// a field key. Rearrange the elements of the array so that Person objects 
// with equal keys appear together. 
// The order in which distinct keys appear is not important.
// Your algorithm must run in O(n) time and O(k) additional space. How would your
// solution change if keys have to appear in sorted order?

public class Solution {
	// the idea is to have two hashTable of size k to achieve O(k) space
	// first hash table h1 holds <key, counts> 
	// then based on first hash table, we create a new hash table h2
	//  that holds <key, offsets>
	// the second hash table would be a counter table
	// then we iterate through the second hash table, 
	//  replace the new key to its correct position,
	//  remove an entry when the offset covers all numbers denoted in h1
	//  keep doing this when the hash table becomes empty
	// 
	// if we need to sort the numbers, then h2 should be a sorted map, 
	// like TreeMap
	// the time complexity would be O(n+klogk) where k is the # of keys

	// use int for simplicity
	static void bucketSortUnOrder(int[] arr) {
		// replace cnts with a TreeMap to make the key sorted
		Map<Integer, Integer> cnts = new HashMap<Integer, Integer>();
		for (int i : arr) {
			if (cnts.containsKey(i)) {
				cnts.put(i, cnts.get(i) + 1);
			} else {
				cnts.put(i, 1);
			}
		}
		int offset = 0;
		Map<Integer, Integer> offsets = new HashMap<Integer, Integer>();
		for (int key : cnts.keySet()) {
			offsets.put(key, offset);
			offset += cnts.get(key);
		}

		// now we iterate through offsets, place the element in its correct place
		while (!offsets.isEmpty()) {
			// get the first available key
			int key = offsets.keySet().iterator().next();
			int curIndex = offsets.get(key);

			// swap arr[curIndex] and arr[offsets.get(value)]
			int value = arr[curIndex];
			arr[curIndex] = arr[offsets.get(value)];
			arr[offsets.get(value)] = value;

			// the key 'value' has one more element in place, 
			//  need to advance it's cursor and remove the entry if necessary
			// if this is the last unplaced elements for key, need to remove the entry
			if (cnts.get(value) == 1) {
				cnts.remove(key);
				offsets.remove(key);
			}
			// otherwise we need to decrement the count
			else {
				offsets.put(value, offsets.get(value) + 1);
				cnts.put(value, cnts.get(value) - 1);
			}
		}
	}

	public static void main(String[] args) {
		int[] arr = { 3, 2, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 4, 4, 4, 4, 4,
				5, 5, 5, 5, 5, 5 };
		// note it's not necessarily sorted
		bucketSortUnOrder(arr);
		for (int i : arr) {
			System.out.print(i + " ");
		}
	}
}
