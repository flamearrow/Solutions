package verifyPreorderSeqInBST;

import java.util.LinkedList;
import java.util.Stack;

//Given an array of numbers, verify whether it is the 
// correct preorder traversal sequence of a binary search tree.
//
//You may assume each number in the sequence is unique.
//
//Follow up:
//Could you do it using only constant space complexity?
public class Solution {
	public boolean verifyPreorder(int[] preorder) {
		return doVerify(preorder, 0, preorder.length);
	}

	boolean doVerify(int[] preorder, int start, int end) {
		if (start >= end) {
			return true;
		}
		int pivot = 0;
		int cur = start + 1;
		while (cur < end && preorder[cur] < preorder[start]) {
			cur++;
		}
		pivot = cur;
		while (cur < end && preorder[cur] > preorder[start]) {
			cur++;
		}
		if (cur < end) {
			return false;
		}
		return doVerify(preorder, start + 1, pivot)
				&& doVerify(preorder, pivot, end);

	}
	
	// a valid pre order tree decreases when go left, increases when go right
	// we can detect when it increases and keep track of the root that causes right
	//  root should be a min value, if we ever met a value smaller than root, then it's
	//  not a valid tree
	public boolean verifyPreorderStack(int[] preorder) {
		Stack<Integer> s = new Stack<>();
		int min = Integer.MIN_VALUE;
		for (int i : preorder) {
			if (i < min) {
				return false;
			}
			// going right, min is the last root that causes us go right
			while (!s.isEmpty() && i > s.peek()) {
				min = s.pop();
			}
			s.push(i);
		}
		return true;
	}
}
