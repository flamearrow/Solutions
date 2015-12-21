package meetingRooms2;

import java.util.Arrays;
//Given an array of meeting time intervals consisting of 
// start and end times [[s1,e1],[s2,e2],...] (si < ei), 
// find the minimum number of conference rooms required.
//
//For example,
//Given [[0, 30],[5, 10],[15, 20]],
//return 2.
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
}

public class Solution {
	public int minMeetingRooms(Interval[] intervals) {
		int[] starts = new int[intervals.length];
		int[] ends = new int[intervals.length];
		for (int i = 0; i < intervals.length; i++) {
			starts[i] = intervals[i].start;
			ends[i] = intervals[i].end;
		}
		Arrays.sort(starts);
		Arrays.sort(ends);
		int sI = 0, eI = 0;
		int ret = 0, cur = 0;
		while (sI < starts.length) {
			if (starts[sI] < ends[eI]) {
				cur++;
				if (cur > ret) {
					ret = cur;
				}
				sI++;
			} else {
				cur--;
				eI++;
			}
		}
		return ret;
	}
}
