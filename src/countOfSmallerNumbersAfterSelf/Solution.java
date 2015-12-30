package countOfSmallerNumbersAfterSelf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//You are given an integer array nums and you have to return a new counts array. 
//The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
//
//Example:
//
//Given nums = [5, 2, 6, 1]
//
//To the right of 5 there are 2 smaller elements (2 and 1).
//To the right of 2 there is only 1 smaller element (1).
//To the right of 6 there is 1 smaller element (1).
//To the right of 1 there is 0 smaller element.
//Return the array [2, 1, 1, 0].
public class Solution {
	public static void main(String[] args) {
		int[] nums = { 26, 78, 27, 100, 33, 67, 90, 23, 66, 5, 38, 7, 35, 23,
				52, 22, 83, 51, 98, 69, 81, 32, 78, 28, 94, 13, 2, 97, 3, 76,
				99, 51, 9, 21, 84, 66, 65, 36, 100, 41 };
		List<Integer> l = new Solution().countSmaller(nums);
		System.out.println(l);
	}

	// the idea is to build a BST from RIGHT to LEFT
	// each time we insert a new node, we look at the BST to determine how many
	// numbers are smaller than current number
	// each node maintains two variables, count and dup, where count is the size
	// of its left subtree and dup is how many times this node has been seen
	// after we inserting a new node, the number of elements bigger than it is
	// the sum of dup and count for all the nodes we turn right
	// because each time we turn right, we find a number N smaller than current
	// number, and we know there're count smaller than N and
	// N appears dup times
	// this is essentially using a buffer to keep track now may numbers are
	// smaller than N for the entire half
	public List<Integer> countSmaller(int[] nums) {
		if (nums.length == 0) {
			return new ArrayList<Integer>();
		}
		Integer[] ret = new Integer[nums.length];
		ret[nums.length - 1] = 0;
		Node root = new Node(nums[nums.length - 1]);
		for (int i = nums.length - 2; i >= 0; i--) {
			ret[i] = insertShit(root, nums[i], 0);
		}
		return Arrays.asList(ret);
	}

	int insertShit(Node cur, int toInsert, int ret) {
		if (cur.val == toInsert) {
			cur.dup++;
			// we've seen same node before, the return value should add to the numbers smaller than that
			return ret + cur.count;
		} else if (toInsert > cur.val) {
			ret += cur.count;
			ret += cur.dup;
			if (cur.right != null) {
				return insertShit(cur.right, toInsert, ret);
			} else {
				cur.right = new Node(toInsert);
				return ret;
			}
		}
		// if (cur.val < toInsert)
		else {
			cur.count++;
			if (cur.left != null) {
				return insertShit(cur.left, toInsert, ret);
			} else {
				cur.left = new Node(toInsert);
				return ret;
			}
		}
	}

	class Node {
		int val;
		int count, dup;
		Node left, right;

		public Node(int aVal) {
			val = aVal;
			count = 0;
			dup = 1;
		}
	}
}
