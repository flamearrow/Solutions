package mergeIntervals;

import java.util.ArrayList;
//Given a collection of intervals, merge all overlapping intervals.
//
//For example,
//Given [1,3],[2,6],[8,10],[15,18],
//return [1,6],[8,10],[15,18]. 
// they are not sorted

public class Solution {
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
		// intervals.add(new Interval(2, 3));
		// intervals.add(new Interval(5, 5));
		// intervals.add(new Interval(2, 2));
		// intervals.add(new Interval(3, 4));
		// intervals.add(new Interval(3, 4));
		new Solution().merge(intervals);
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
