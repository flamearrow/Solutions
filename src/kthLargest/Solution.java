package kthLargest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

// find the first k smallest number in a 
// 1) iterator
// 2) array
public class Solution {
	// use a priority queue or a bounded TreeSet
	//( more appropriate because we only need first k)
	static void kthSmallest(Iterator<Integer> itr, int k) {
		//		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		TreeSet<Integer> set = new TreeSet<Integer>();
		while (itr.hasNext()) {
			int next = itr.next();
			set.add(next);
			if (set.size() > k)
				set.pollLast();
		}
		for (int i : set)
			System.out.print(i + ", ");
	}

	// simulate quick sort
	static void kthSmallest(int[] arr, int k) {
		doFindKth(arr, k, 0, arr.length - 1);
		for (int i = 0; i < k; i++) {
			System.out.print(arr[i] + ", ");
		}
	}

	static void doFindKth(int[] arr, int k, int start, int end) {
		if (start > end)
			return;
		int pivot = arr[start];
		int cur = start + 1;
		while (start <= end) {
			if (arr[cur] <= pivot) {
				arr[cur++] = arr[start++];
			} else {
				int tmp = arr[end];
				arr[end--] = arr[start];
				arr[start] = tmp;
			}
		}
		arr[cur] = pivot;
		if (cur == k - 1)
			return;
		else if (cur > k - 1) {
			doFindKth(arr, k, start, cur - 1);
		} else {
			doFindKth(arr, k, cur + 1, end);
		}
	}

	public static void main(String[] args) {
		int[] arr = { 31, 42, 41, 21, 55, 2, 13, 56, 87 };
		ArrayList<Integer> aL = new ArrayList<Integer>();
		for (int i : arr)
			aL.add(i);
		kthSmallest(aL.iterator(), 5);
		System.out.println();
		kthSmallest(arr, 2);
	}
}
