package meetingRooms3;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a list of intervals, find the invervals without meetings
 * e.g
 * [
 * [[1, 3], [6, 7]],
 * [[2, 4]],
 * [[2, 3], [9, 12]]
 * ]
 * return
 * [[4, 6], [7, 9]]
 */
public class Solution {
    public static void main(String[] args) {
        List<int[]> lists = new LinkedList<>();
        lists.add(new int[]{1, 3});
        lists.add(new int[]{6, 7});
        lists.add(new int[]{2, 4});
        lists.add(new int[]{2, 3});
        lists.add(new int[]{9, 12});
        for (int[] invl : meetingRooms(lists)) {
            System.out.println(invl[0] + " : " + invl[1]);
        }
    }

    static class Interval implements Comparable<Interval> {
        // when value equates to each other, left would be bigger than right
        static int LEFT = 1, RIGHT = 0;
        int value;
        int side;

        public Interval(int argVal, int argSide) {
            value = argVal;
            side = argSide;
        }

        @Override
        public int compareTo(Interval o) {
            return value == o.value ? side - o.side : value - o.value;
        }
    }

    static List<int[]> meetingRooms(List<int[]> intervals) {
        List<int[]> ret = new LinkedList<>();
        List<Interval> intervalsList = new LinkedList<>();
        for (int[] interval : intervals) {
            intervalsList.add(new Interval(interval[0], Interval.LEFT));
            intervalsList.add(new Interval(interval[1], Interval.RIGHT));
        }
        Collections.sort(intervalsList);
        int runningInterval = 0;
        int preStart = Integer.MIN_VALUE;
        for (Interval i : intervalsList) {
            // start of interval
            if (i.side == Interval.LEFT) {
                if (runningInterval == 0 && preStart > Integer.MIN_VALUE) {
                    int[] newInterval = new int[2];
                    newInterval[0] = preStart;
                    newInterval[1] = i.value;
                    ret.add(newInterval);
                }
                runningInterval++;
            }
            // end of interval
            else {
                runningInterval--;
                if (runningInterval == 0) {
                    preStart = i.value;
                }
            }
        }
        return ret;
    }
}