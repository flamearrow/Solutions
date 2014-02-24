package matrixBoundaries;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// given a couple of matrices lies on x axis, output their boundaries
// since all matrices are lying on upper bound of x axis, they can be represented as a line parallel to x axis
public class Solution {
	List<Line> findBoundary(List<Line> matrices) {
		Node[] arr = new Node[matrices.size() * 2];
		int cur = 0;
		for (Line l : matrices) {
			arr[cur++] = new Node(l.startx, l.y, true);
			arr[cur++] = new Node(l.endx, l.y, false);
		}
		Arrays.sort(arr);
		List<Line> ret = new LinkedList<Line>();
		int start = 0, end = arr.length - 1;
		while (start < end) {
			int count = 1;
			int next = start + 1;
			// skip if next is shorter than start
			while (arr[next].yVal <= arr[start].yVal) {
				// in case we have couple of lines that has same y value
				if (arr[next].yVal == arr[start].yVal) {
					if (arr[next].isStart) {
						count++;
					} else {
						count--;
						if (count == 0)
							break;
					}
				}
				next++;
			}
			ret.add(new Line(arr[start].xVal, arr[next].xVal, arr[start].yVal));
			if (arr[next].isStart) {
				start = next;
			} else {
				start = next + 1;
			}
			if (start >= end)
				break;
			count = 1;
			int prev = end - 1;
			// skip if next is shorter than start
			while (arr[prev].yVal <= arr[end].yVal) {
				// in case we have couple of lines that has same y value
				if (arr[prev].yVal == arr[end].yVal) {
					if (!arr[prev].isStart) {
						count++;
					} else {
						count--;
						if (count == 0)
							break;
					}
				}
				prev--;
			}
			ret.add(new Line(arr[prev].xVal, arr[end].xVal, arr[end].yVal));
			if (!arr[prev].isStart) {
				end = prev;
			} else {
				prev = prev - 1;
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		List<Line> input = new LinkedList<Line>();
		input.add(new Line(1, 4, 2));
		input.add(new Line(2, 7, 1));
		input.add(new Line(3, 6, 4));
		input.add(new Line(5, 8, 3));
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