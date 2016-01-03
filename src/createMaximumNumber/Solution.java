package createMaximumNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity.
//
//Example 1:
//nums1 = [3, 4, 6, 5]
//nums2 = [9, 1, 2, 5, 8, 3]
//k = 5
//return [9, 8, 6, 5, 3]
//
//Example 2:
//nums1 = [6, 7]
//nums2 = [6, 0, 4]
//k = 5
//return [6, 7, 6, 0, 4]
//
//Example 3:
//nums1 = [3, 9]
//nums2 = [8, 9]
//k = 3
//return [9, 8, 9]
public class Solution {
	public static void main(String[] args) {
		// int[] nums1 = { 3, 9 };
		// int[] nums2 = { 8, 9 };
		// for (int i : new PeekingIterator().maxNumber(nums1, nums2, 3)) {
		// System.out.print(i + " ");
		// }

		int[] arr = { 9, 2, 1, 3, 2 };
		new Solution().maxNumber1Array(arr, 3);

	}
	
	// one array problem: keep popping out numbers smaller than current number
	//  but we can only do this when there's still enough number left to make a k digits number
	//  i.e arr.length - i + s.size() > k
	// to expand to two arrays, we do the same shit, but need to make sure which array the next number is from
	// plus we need to decide when there's the same number, which array to pick from
	//  the way to decide which to pick from is to continue looking the numbers after it, we go with the list with bigger number eventually
	public void maxNumber1Array(int[] arr, int k) {
		Stack<Integer> s = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			while (!s.isEmpty() && s.peek() < arr[i]
					&& arr.length - i + s.size() > k) {
				s.pop();
			}
			s.push(arr[i]);
		}

		StringBuilder sb = new StringBuilder();
		while (!s.isEmpty()) {
			sb.insert(0, s.pop());
		}
		System.out.println(sb.toString());
	}

	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
		int[] ret = new int[k];
		doShit(nums1, 0, nums2, 0, 0, ret);
		return ret;

	}

	boolean doShit(int[] nums1, int index1, int[] nums2, int index2,
			int curIndex, int[] ret) {
		// find max from the two and cut
		// then we get two new arrays, one is cut with head is max
		// take the head and go with k-1
		if (curIndex == ret.length) {
			return true;
		}
		// no enough numbers, quit
		if (nums1.length - index1 + nums2.length - index2 < ret.length
				- curIndex) {
			return false;
		}

		// find the next big number from rest of two arrays, at the end, we
		// should know
		// index of array: n.arrayIndex
		// index of number: n.index
		// the number itself: n.val
		// we will need to try each head, because a large head may run out of
		// digits
		List<Node> nodeList = new ArrayList<>(nums1.length + nums2.length);
		for (int i = index1; i < nums1.length; i++) {
			nodeList.add(new Node(1, i, nums1[i]));
		}

		for (int i = index2; i < nums2.length; i++) {
			nodeList.add(new Node(2, i, nums2[i]));
		}

		Collections.sort(nodeList);
		Collections.reverse(nodeList);

		// start picking from head of list
		boolean found = false;
		int lastHitVal = Integer.MAX_VALUE;
		int maxTotal = 0;
		int[] retBuf = new int[ret.length];
		for (Node n : nodeList) {
			if (found && n.val != lastHitVal) {
				// we already found an array and its head is bigger than
				// current, just break
				break;
			}
			boolean subResult = false;
			ret[curIndex] = n.val;
			// pick number from arrayIndex1
			if (n.arrayIndex == 1) {
				subResult = doShit(nums1, n.index + 1, nums2, index2,
						curIndex + 1, ret);
			} else {
				subResult = doShit(nums1, index1, nums2, n.index + 1,
						curIndex + 1, ret);
			}
			// since we're sorted, the very first returned one is the maximum
			// there might be cases where some nodes with same value is in the
			// list, like 9-9-8-6
			// in this case we'll need to try each head
			if (subResult) {
				lastHitVal = n.val;
				// there was a result before, need to compare the shit
				if (found) {
					int currentTotal = getCurrentTotal(ret);
					if (maxTotal < currentTotal) {
						currentTotal = maxTotal;
						System.arraycopy(ret, 0, retBuf, 0, ret.length);
					}
				} else {
					found = true;
					maxTotal = getCurrentTotal(ret);
					System.arraycopy(ret, 0, retBuf, 0, ret.length);
				}
			}
		}
		if (found) {
			System.arraycopy(retBuf, 0, ret, 0, ret.length);
			return true;
		} else {
			return false;
		}
	}

	int getCurrentTotal(int[] ret) {
		int rett = 0;
		for (int i = ret.length - 1; i >= 0; i--) {
			rett += ret[i] * Math.pow(10, ret.length - 1 - i);
		}
		return rett;
	}

	class Node implements Comparable<Node> {
		int arrayIndex;
		int index;
		int val;

		public Node(int aAIndex, int aIndex, int aVal) {
			arrayIndex = aAIndex;
			index = aIndex;
			val = aVal;
		}

		@Override
		public int compareTo(Node o) {
			return val - o.val;
		}

		@Override
		public String toString() {
			return "arrayIndex: " + arrayIndex + ", index: " + index
					+ ", val: " + val;
		}
	}
}
