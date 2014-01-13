package largestRectangleInHistogram;

import java.util.Stack;

//Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.
//
//
//Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
//
//
//The largest rectangle is shown in the shaded area, which has area = 10 unit.
//
//For example,
//Given height = [2,1,5,6,2,3],
//return 10. 

public class Solution {
	int largestRectangleArea(int[] height) {
		if (height.length == 0)
			return 0;
		Stack<Node> s = new Stack<Node>();
		s.push(new Node(0, height[0]));
		int maxArea = 0;
		for (int i = 1; i < height.length; i++) {
			if (height[i] > s.peek().height) {
				s.push(new Node(i, height[i]));
			} else if (height[i] < s.peek().height) {
				int rightBound = i - 1;
				Node pre = null;
				while (!s.isEmpty() && s.peek().height > height[i]) {
					pre = s.pop();
					int newArea = (rightBound - pre.index + 1) * pre.height;
					if (newArea > maxArea)
						maxArea = newArea;
				}
				// if previous node's height equals to current height, don't do
				// anything
				if (s.isEmpty() || s.peek().height < height[i]) {
					// pre is non-null
					s.push(new Node(pre.index, height[i]));
				}
			}
		}

		int rightBound = height.length - 1;
		while (!s.isEmpty()) {
			Node pre = s.pop();
			int newArea = (rightBound - pre.index + 1) * pre.height;
			if (newArea > maxArea) {
				maxArea = newArea;
			}
		}

		return maxArea;
	}

	class Node {
		int index, height;

		public Node(int index, int height) {
			this.index = index;
			this.height = height;
		}

		@Override
		public String toString() {
			return "index: " + index + ", height: " + height;
		}
	}
}
