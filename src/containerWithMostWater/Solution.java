package containerWithMostWater;

//Given n non-negative integers a1, a2, ..., an, 
//where each represents a point at coordinate (i, ai). 
//n vertical lines are drawn such that the two end points 
//  of line i is at (i, ai) and (i, 0).
//Find two lines, which together with x-axis forms a container, 
//such that the container contains the most water.
//
//Note: You may not slant the container. 
public class Solution {
	// there's a naive O(n^2) solution...
	// O(n) solution, start from left/right end, always move the shorter edge
	// tricky correctness prove:
	// 	suppose we are at [i, j]. 
	//  If height[i] < height[j], there would be no jj < j that makes [i, jj] larger then [i,j]
	//   so we move i forward
	// same when moving j backward
	public int maxArea(int[] height) {
		int start = 0, end = height.length - 1;
		int ret = 0;
		while (start < end) {
			ret = Math.max(ret,
					(end - start) * Math.min(height[start], height[end]));
			if (height[end] < height[start]) {
				end--;
			} else {
				start++;
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		int[] arr = { 2, 4, 1, 3 };
		int ret = new Solution().maxArea(arr);
		System.out.println(ret);
	}
}
