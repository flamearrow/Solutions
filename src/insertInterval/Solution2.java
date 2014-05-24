package insertInterval;

import java.util.ArrayList;

public class Solution2 {
	public ArrayList<Interval> insert(ArrayList<Interval> intervals,
			Interval newInterval) {
		ArrayList<Interval> ret = new ArrayList<Interval>();
		Interval cur = newInterval;
		boolean addRest = false;
		for (Interval i : intervals) {
			if (!addRest) {
				int rst = cross(i, cur);
				// i is ahead of cur
				if (rst == -1) {
					ret.add(i);
				}
				// i crosses cur
				else if (rst == 0) {
					cur = merge(i, cur);
				}
				// i is after cur
				else if (rst == 1) {
					ret.add(cur);
					ret.add(i);
					addRest = true;
				}
			} else {
				ret.add(i);
			}
		}
		// we haven't add cur yet, cur should be the last entry
		if (!addRest) {
			ret.add(cur);
		}
		return ret;
	}

	// -1: i1 before i2
	// 1 : i1 after i2
	// 0 : i1 crosses with i2
	private int cross(Interval i1, Interval i2) {
		if (i1.end < i2.start)
			return -1;
		if (i2.end < i1.start)
			return 1;
		return 0;
	}

	private Interval merge(Interval i1, Interval i2) {
		return new Interval(Math.min(i1.start, i2.start), Math.max(i1.end,
				i2.end));
	}

	public static void main(String[] args) {
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		intervals.add(new Interval(3, 4));
		intervals.add(new Interval(6, 7));
		intervals.add(new Interval(9, 10));
		intervals.add(new Interval(12, 16));
		Interval newInterval = new Interval(7, 9);
		ArrayList<Interval> merged = new Solution2().insert(intervals,
				newInterval);
		System.out.println(merged);
	}
}
