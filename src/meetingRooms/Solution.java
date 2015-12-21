package meetingRooms;

import java.util.Arrays;
import java.util.Comparator;

//Given an array of meeting time intervals consisting
//of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
//
//For example,
//Given [[0, 30],[5, 10],[15, 20]],
//return false
public class Solution {
	public boolean canAttendMeetings(Interval[] intervals) {
		Arrays.sort(intervals, new Comparator<Interval>() {

			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});

		int pre = Integer.MIN_VALUE;
		for (Interval i : intervals) {
			if (i.start > i.end || pre > i.start) {
				return false;
			}
			pre = i.end;
		}
		return true;
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
}