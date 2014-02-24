package matrixBoundaries;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

// given a couple of matrices lies on x axis, output their boundaries
// since all matrices are lying on upper bound of x axis, they can be represented as a line parallel to x axis
public class Solution {
	// the idea is to first sort all starting/ending points with its height
	// then we go from left to right, keep track of all visited heights and keep track of current height
	// once we find a potential span, we need to create a new height with maximum height possible
	// to keep track of height, just use a Set to store height, 
	//	when we met a starting point, add it's height to set 
	// 	when we met an ending point, remove its height from set
	// note here to find next highest height it's O(n), we can have a data structure like a heap to implement O(log n) insertion and O(1) retrieval
	List<Line> findBoundary(List<Line> matrices) {
		Node[] arr = new Node[matrices.size() * 2];
		int cur = 0;
		for (Line l : matrices) {
			arr[cur++] = new Node(l.startx, l.y, true);
			arr[cur++] = new Node(l.endx, l.y, false);
		}
		Arrays.sort(arr);
		List<Line> ret = new LinkedList<Line>();
		cur = 0;
		Set<Integer> heights = new HashSet<Integer>();
		int curHeight = arr[cur].yVal;
		heights.add(curHeight);
		while (cur < arr.length - 1) {
			int next = cur + 1;
			while (arr[next].yVal < curHeight) {
				if (arr[next].isStart) {
					heights.add(arr[next].yVal);
				} else {
					heights.remove(arr[next].yVal);
				}
				next++;
			}
			// now next is pointing to an edge that's higher or equal to cur
			// note if curHeight < 0, then there's no coverage in this span
			if (curHeight > 0)
				ret.add(new Line(arr[cur].xVal, arr[next].xVal, curHeight));
			if (arr[next].isStart) {
				heights.add(arr[next].yVal);
			} else {
				heights.remove(arr[next].yVal);
			}
			cur = next;
			curHeight = getHeight(heights);

		}
		return ret;
	}

	// find the highest height so far, we can have a smarter ds to faster the searching...
	int getHeight(Set<Integer> heights) {
		int max = -1;
		for (int i : heights) {
			if (i > max) {
				max = i;
			}
		}
		return max;
	}

	public static void main(String[] args) {
		List<Line> input = new LinkedList<Line>();
		//		input.add(new Line(1, 4, 2));
		//		input.add(new Line(2, 7, 1));
		//		input.add(new Line(3, 6, 4));
		//		input.add(new Line(5, 8, 3));
		input.add(new Line(1, 4, 1));
		input.add(new Line(2, 6, 2));
		input.add(new Line(3, 5, 3));
		for (Line l : new Solution().findBoundary(input)) {
			System.out.println(l.startx + ", " + l.endx + ", " + l.y);
		}
	}

}

// used for input( representing a matrix ) and output( representing a boundary)
class Line {
	int startx, endx, y;

	public Line(int sX, int eX, int y) {
		startx = sX;
		endx = eX;
		this.y = y;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Line))
			return false;
		if (o == this)
			return true;
		Line l = (Line) o;
		return l.startx == startx && l.endx == endx && l.y == y;
	}

	public int hashCode() {
		return startx + 36 * endx + 7 * y;
	}

	@Override
	public String toString() {
		return "(" + startx + ", " + endx + ", " + y + ")";
	}
}

class Node implements Comparable<Node> {
	int xVal, yVal;
	boolean isStart;

	public Node(int xVal, int yVal, boolean isStart) {
		this.xVal = xVal;
		this.yVal = yVal;
		this.isStart = isStart;
	}

	@Override
	public int compareTo(Node o) {
		return this.xVal - o.xVal;
	}

}