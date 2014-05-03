package mergeIntervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Solution {
	class Node implements Comparable<Node> {
		int index;
		boolean start;

		public Node(int index, boolean start) {
			this.index = index;
			this.start = start;
		}

		public int compareTo(Node n) {
			if (index == n.index) {
				// start should always go first
				// so (1, 4), (4, 5) will be merged
				if (start)
					return -1;
				else
					return 1;
			} else
				return index - n.index;
		}
	}

	// a stack implementation, note we defined a node object that's comparable
	//  when two nodes have same index, the STARTING node should precede end node
	//  so that intervals like [1, 4] and [4, 5] will be merged
	public ArrayList<Interval> merge2(ArrayList<Interval> intervals) {
		ArrayList<Interval> ret = new ArrayList<Interval>();
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (Interval i : intervals) {
			nodes.add(new Node(i.start, true));
			nodes.add(new Node(i.end, false));
		}
		Collections.sort(nodes);
		Stack<Node> s = new Stack<Node>();
		for (Node n : nodes) {
			if (n.start)
				s.push(n);
			else {
				if (s.size() == 1) {
					ret.add(new Interval(s.pop().index, n.index));
				} else {
					s.pop();
				}
			}
		}
		return ret;
	}

	// a slighted modified O(n^2) solution, can be optimized to O(nlogn) if we
	// sort the array in advance
	public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
		int cur = 0;
		while (cur < intervals.size()) {
			int i = cur + 1;
			boolean curChanged = false;
			while (i < intervals.size()) {
				// shrinks intervals by one
				if (overlaps(intervals.get(i), intervals.get(cur))) {
					Interval newInterval = new Interval(
							intervals.get(i).start < intervals.get(cur).start ? intervals.get(i).start
									: intervals.get(cur).start,
							intervals.get(i).end > intervals.get(cur).end ? intervals
									.get(i).end : intervals.get(cur).end);
					intervals.remove(i);
					intervals.remove(cur);
					intervals.add(cur, newInterval);
					curChanged = true;
				}
				// advances pointer
				else {
					i++;
				}
			}
			if (!curChanged)
				cur++;
		}
		return intervals;
	}

	boolean overlaps(Interval i1, Interval i2) {
		return !(i1.end < i2.start || i2.end < i1.start);
	}

	public static void main(String[] args) {
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(0, 0));
		intervals.add(new Interval(1, 4));
		// intervals.add(new Interval(2, 2));
		// intervals.add(new Interval(3, 4));
		// intervals.add(new Interval(3, 4));
		new Solution().merge2(intervals);
		System.out.println(intervals);
	}
}

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}

	@Override
	public String toString() {
		return "[" + start + ", " + end + "]";
	}
}
