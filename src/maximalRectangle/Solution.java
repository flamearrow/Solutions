package maximalRectangle;

import java.util.Arrays;
import java.util.Stack;

//Given a 2D binary matrix filled with 0's and 1's,
//find the largest rectangle containing all ones and return its area. 
public class Solution {
	public int maximalRectangle3(char[][] matrix) {
		if (matrix.length == 0)
			return 0;
		int height = matrix.length;
		int width = matrix[0].length;
		int[][] m = new int[height][width];
		for (int i = 0; i < width; i++) {
			m[0][i] = matrix[0][i] == '1' ? 1 : 0;
		}
		for (int i = 1; i < height; i++) {
			for (int j = 0; j < width; j++) {
				m[i][j] = matrix[i][j] == '1' ? m[i - 1][j] + 1 : 0;
			}
		}

		// now each m[i] is a set of heights or histogram
		int area = 0;
		for (int i = 0; i < height; i++) {
			int curArea = calculateMaxArea(m[i]);
			if (curArea > area) {
				area = curArea;
			}
		}
		return area;
	}

	int calculateMaxArea(int[] heights) {
		Stack<Node> s = new Stack<Node>();
		s.push(new Node(0, heights[0]));
		int ret = 0;
		for (int i = 1; i < heights.length; i++) {
			int maxHeight = s.peek().height;
			if (maxHeight < heights[i]) {
				s.push(new Node(i, heights[i]));
			} else if (maxHeight > heights[i]) {
				int startIndex = s.peek().index;
				while (!s.isEmpty() && s.peek().height > heights[i]) {
					Node next = s.pop();
					int curArea = (i - next.index) * next.height;
					if (curArea > ret)
						ret = curArea;
					startIndex = next.index;
				}
				s.push(new Node(startIndex, heights[i]));
			}
		}

		while (!s.isEmpty()) {
			Node next = s.pop();
			int curArea = (heights.length - next.index) * next.height;
			if (curArea > ret) {
				ret = curArea;
			}
		}
		return ret;
	}

	// there's a naive O((mn)^2) solution
	// this one is a O(m*m*n) solution with an enlightened dp
	// idea: to maintain three dp tables,
	//  	h[i][j]: hang line length. 
	// 				 a hang line is a set of consecutive 1s from top to bottom
	// 		l[i][j]: how far can node[i][j] reach to left
	// 				 a node can reach left if node and left are both 1
	// 		r[i][j]: how far can node[i][j] reach to right
	// 				 a node can reach right if node and right are both 1
	// 		
	// there are two dp cases:
	// 		1) if(matrix[i][j]) == '1'
	// 			then we can add hang line length by one: h[i][j] = h[i-1][j]+1
	// 			then we need to search how left [i][j] can reach:
	//			 left[i][j] = max(left[i-1][j], leftmost index [i][j] can reach on this row
	// 			 left[i][j] should find the right one
	//			then we need to search now right [i][j] can reach:
	//			 right[i][j] = min(right[i-1][j], rightmost index [i][j] can reach
	// 			 right[i][j] should find the left one
	// 		2) if(matrix[i][j]) == '0'
	// 			then we clear hang line length: h[i][j] = 0
	//	  		then we need to extend left to left edge left[i][j] = 0
	// 			then we need to extend right to right edge right[i][j] = matrix[i].length-1
	// 		note we don't really need three two dimen back matrixes, 
	// 			only three arrays is enough
	public int maximalRectangleDP1(char[][] matrix) {
		if (matrix.length == 0)
			return 0;
		int width = matrix[0].length;
		int maxArea = 0;
		int[] h = new int[width]; // height / hang line length
		int[] l = new int[width]; // left most index
		int[] r = new int[width]; // right most index
		Arrays.fill(r, width - 1);

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < width; j++) {
				if (matrix[i][j] == '0') {
					h[j] = 0;
					l[j] = 0;
					r[j] = width - 1;
				} else {
					h[j]++;
					int newLeft = j;
					while (newLeft > l[j] && (newLeft > 0)
							&& matrix[i][newLeft - 1] == '1')
						newLeft--;
					l[j] = newLeft;
					int newRight = j;
					while (newRight < r[j] && (newRight < width - 1)
							&& matrix[i][newRight + 1] == '1')
						newRight++;
					r[j] = newRight;
					int newArea = h[j] * (r[j] - l[j] + 1);
					if (newArea > maxArea) {
						maxArea = newArea;
					}
				}
			}
		}
		return maxArea;
	}

	// when search for left and right we're doing another while loop, but this loop can be avoided
	// because from left to right, l[j] will always be increasing, 
	// and from right to left, r[j] will always be decreasing
	public int maximalRectangle(char[][] matrix) {
		if (matrix.length == 0)
			return 0;
		int width = matrix[0].length;
		int maxArea = 0;
		int[] h = new int[width]; // height / hang line length
		int[] l = new int[width]; // left most index
		int[] r = new int[width]; // right most index
		Arrays.fill(r, width - 1);

		for (int i = 0; i < matrix.length; i++) {
			int left = 0, right = width - 1;
			for (int j = 0; j < width; j++) {
				if (matrix[i][j] == '0') {
					h[j] = 0;
					l[j] = 0;
					r[j] = width - 1;
					left = j + 1;
				} else {
					h[j]++;
					l[j] = l[j] > left ? l[j] : left;
				}
			}
			for (int j = width - 1; j >= 0; j--) {
				if (matrix[i][j] == '0') {
					right = j - 1;
				} else {
					r[j] = r[j] < right ? r[j] : right;
					int newArea = h[j] * (r[j] - l[j] + 1);
					if (newArea > maxArea) {
						maxArea = newArea;
					}
				}
			}
		}
		return maxArea;
	}

	// another O(mn) solution would be set each row as the X axis 
	// and calculate the largest histogram from beginning
	// we need to calculate m rows and for each row 
	// 	we need O(n) to calculate the largest histogram
	// the overall complexity would be O(mn)
	public int maximalRectangleHistogram(char[][] matrix) {
		if (matrix.length == 0)
			return 0;
		int width = matrix[0].length;
		int[] histogramHeights = new int[width];
		int maxArea = 0;
		for (char[] newRow : matrix) {
			for (int i = 0; i < width; i++) {
				histogramHeights[i] = newRow[i] == '1' ? histogramHeights[i] + 1
						: 0;
			}
			int newArea = largestRectangleArea(histogramHeights);
			if (newArea > maxArea) {
				maxArea = newArea;
			}
		}
		return maxArea;
	}

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
				// if previous node's height equals to current height, don't do anything
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

	public static void main(String[] args) {
		char[][] matrix = { { '0', '1', '1', '0', '0' },
				{ '1', '1', '1', '1', '1' }, { '0', '1', '1', '1', '0' } };
		//		System.out.println(new Solution().maximalRectangleHistogram(matrix));
		System.out.println(new Solution().maximalRectangle3(matrix));

	}
}
