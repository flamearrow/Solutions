package largestRectangleInHistogram;

import java.util.Stack;

public class Solution {
	// a naive solution: for each histogram, search from any previous to
	// current, note we update minV each time we see a smaller one, then
	// calculate the largest
	// O(n^2)
	int largestRectangleAreaNaive(int[] height) {
		int maxArea = 0;
		for (int i = 0; i < height.length; i++) {
			int minHeight = height[i];
			for (int j = i; j >= 0; j--) {
				minHeight = minHeight < height[j] ? minHeight : height[j];
				int area = minHeight * (i - j + 1);
				if (area > maxArea)
					maxArea = area;
			}
		}
		return maxArea;
	}

	// we calculated too much dups, in fact when height[i+1] > height[i], we
	// know height[i+1] can definitely form a bigger rectangle than height[i]
	// therefore we can skip height[i]
	// still O(n^2), though we skipped some
	int largestRectangleAreaSkipBars(int[] height) {
		int maxArea = 0;
		for (int end = 0; end < height.length; end++) {
			// search for a correct right bound
			while (end < height.length - 1 && height[end + 1] > height[end]) {
				end++;
			}
			int minHeight = height[end];
			for (int j = end; j >= 0; j--) {
				minHeight = minHeight < height[j] ? minHeight : height[j];
				int area = (end - j + 1) * minHeight;
				if (area > maxArea)
					maxArea = area;
			}
		}
		return maxArea;
	}

	// O(n) solution
	// use a stack to keep track of smallest height we've seen so far, when looking at height[i],
	// 	1) if height[i] > height[i-1], then we need to push (height[i], i) into stack
	//  2) if height[i] == height[i-1], then we need to skip it
	//  3) if height[i] < height[i-1], then we need to keep poping from the stack, calculate all possible areas, update maxArea, then push (height[i], appropriate index) into stack
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
		int height, index;

		public Node(int height, int index) {
			this.height = height;
			this.index = index;
		}

		@Override
		public String toString() {
			return "height: " + height + ", index: " + index;
		}
	}

	public static void main(String[] args) {
		int[] arr = { 3, 2, 1 };
		int rst = new Solution().largestRectangleArea(arr);
		System.out.println(rst);
	}
}
