package insertInterval;

import java.util.ArrayList;

//Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
//
//You may assume that the intervals were initially sorted according to their start times.
//
//Example 1:
//Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
//
//Example 2:
//Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].
//
//This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10]. 
public class Solution {
	// given that the worst case of the following is still O(n)( when we need to merge up to O(n) intervals), 
	// we can do it naively by looking at intervals one by one and merge if needed
	public ArrayList<Interval> insert2(ArrayList<Interval> intervals,
			Interval newInterval) {
		ArrayList<Interval> ret = new ArrayList<Interval>();
		boolean endMergedInterval = true;
		int start = newInterval.start, end = newInterval.end;
		for (int i = 0; i < intervals.size(); i++) {
			Interval nextInterval = intervals.get(i);
			if (nextInterval.start > newInterval.end) {
				if (endMergedInterval) {
					ret.add(new Interval(start, end));
					endMergedInterval = false;
				}
				ret.add(new Interval(nextInterval.start, nextInterval.end));
			} else if (nextInterval.end < newInterval.start) {
				ret.add(new Interval(nextInterval.start, nextInterval.end));
			} else {
				if (nextInterval.start < start) {
					start = nextInterval.start;
				}
				// we find the end of merged interval
				if (nextInterval.end > newInterval.end) {
					end = nextInterval.end;
					ret.add(new Interval(start, end));
					endMergedInterval = false;
				}
				// if the next interval's end is before newInterval's end, 
				// then we close the merged interval for next interval iN whose start is bigger the newInterval.end
				else {
					endMergedInterval = true;
				}
			}
		}
		// in the case where the interval needs to be added to end
		if (endMergedInterval) {
			ret.add(new Interval(start, end));
		}
		return ret;
	}

	public ArrayList<Interval> insert(ArrayList<Interval> intervals,
			Interval newInterval) {
		// first use bSearch to find the start interval and end interval based
		// on interval.start and interval.end
		// then merge/delete/create new intervals for these bunch of intervals
		// and newInterval
		int start, end;
		int len = intervals.size();
		if (len == 0) {
			intervals.add(newInterval);
			return intervals;
		}
		if (newInterval.start < intervals.get(0).start) {
			start = 0;
		} else if (newInterval.start > intervals.get(len - 1).start) {
			start = len - 1;
		} else {
			start = bSearch(intervals, 0, len - 1, newInterval.start);
		}

		if (newInterval.end < intervals.get(0).start) {
			end = 0;
		} else if (newInterval.end > intervals.get(len - 1).start) {
			end = len - 1;
		} else {
			end = bSearch(intervals, 0, len - 1, newInterval.end);
		}

		// new interval overlaps a couple of intervals, need to merge
		if (start != end) {
			int newEnd = intervals.get(end).end > newInterval.end ? intervals
					.get(end).end : newInterval.end;
			int newStart, toRemove;
			// if start overlaps with newInterval, then we merge from start to
			// end
			if (intervals.get(start).end >= newInterval.start) {
				newStart = intervals.get(start).start < newInterval.start ? intervals
						.get(start).start : newInterval.start;
				Interval newI = new Interval(newStart, newEnd);
				toRemove = end - start + 1;
				while (toRemove > 0) {
					intervals.remove(start);
					toRemove--;
				}
				intervals.add(start, newI);
			}
			// otherwise we leave start as it is and merge from newInterval to
			// end
			else {
				newStart = newInterval.start;
				Interval newI = new Interval(newStart, newEnd);
				toRemove = end - start;
				while (toRemove > 0) {
					intervals.remove(start + 1);
					toRemove--;
				}
				intervals.add(start + 1, newI);
			}
		}
		// either no overlap, or only overlaps with start
		else {
			// no overlap
			if (intervals.get(start).end < newInterval.start
					|| newInterval.end < intervals.get(start).start) {
				if (intervals.get(start).start > newInterval.start) {
					intervals.add(start, newInterval);
				} else {
					intervals.add(start + 1, newInterval);
				}
			}
			// overlaps
			else {
				Interval newI = new Interval(
						newInterval.start < intervals.get(start).start ? newInterval.start
								: intervals.get(start).start,
						newInterval.end > intervals.get(start).end ? newInterval.end
								: intervals.get(start).end);
				intervals.set(start, newI);
			}

		}
		return intervals;
	}

	// search the first interval i in intervals where i.start<=val
	int bSearch(ArrayList<Interval> intervals, int start, int end, int val) {
		while (start < end) {
			int mid = (start + end) / 2;
			if (intervals.get(mid).start > val) {
				end = end - 1;
			} else {
				start = start + 1;
			}
		}
		if (intervals.get(end).start <= val)
			return end;
		else
			return end - 1;
	}

	public static void main(String[] args) {
		ArrayList<Interval> intervals = new ArrayList<Interval>();
		//		intervals.add(new Interval(3, 5));
		//		intervals.add(new Interval(6, 7));
		//		intervals.add(new Interval(8, 10));
		//		intervals.add(new Interval(12, 16));
		Interval newInterval = new Interval(1, 17);
		ArrayList<Interval> merged = new Solution().insert2(intervals,
				newInterval);
		System.out.println(merged);
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
