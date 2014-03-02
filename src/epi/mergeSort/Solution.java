package epi.mergeSort;

import java.util.ArrayList;
import java.util.PriorityQueue;

// 10.1
// do a k-way merge sort using a heap
// the trick is to use a Node class to keep track the latest found node belongs to which array
//  then take another value from that array if it still has values
public class Solution {
	void kWayMergeSort(ArrayList<ArrayList<Integer>> input) {
		PriorityQueue<Node> heap = new PriorityQueue<Node>();
		int arrCnt = input.size();
		// current Index of each array
		int[] curs = new int[arrCnt];
		// dump smallest into it
		for (int i = 0; i < arrCnt; i++) {
			heap.offer(new Node(input.get(i).get(0), i));
			curs[i] = 1;
		}
		while (!heap.isEmpty()) {
			Node next = heap.poll();
			System.out.println(next.val);
			if (curs[next.arrIndex] < input.get(next.arrIndex).size()) {
				Node newNode = new Node(input.get(next.arrIndex).get(
						curs[next.arrIndex]), next.arrIndex);
				heap.offer(newNode);
				curs[next.arrIndex]++;
			}
		}
	}

	public static void main(String[] args) {
		int[] a1 = { 1, 4, 7, 10 };
		int[] a2 = { 2, 5, 8, 11 };
		int[] a3 = { 3, 6, 9, 12 };
		ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> aa1 = new ArrayList<Integer>();
		for (int i : a1)
			aa1.add(i);
		ArrayList<Integer> aa2 = new ArrayList<Integer>();
		for (int i : a2)
			aa2.add(i);
		ArrayList<Integer> aa3 = new ArrayList<Integer>();
		for (int i : a3)
			aa3.add(i);
		input.add(aa1);
		input.add(aa2);
		input.add(aa3);
		new Solution().kWayMergeSort(input);
	}
}

class Node implements Comparable<Node> {
	int val;
	int arrIndex;

	public Node(int val, int arrIndex) {
		this.val = val;
		this.arrIndex = arrIndex;
	}

	@Override
	public int compareTo(Node anotherNode) {
		return val - anotherNode.val;
	}
}