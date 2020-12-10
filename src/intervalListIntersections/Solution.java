package intervalListIntersections;
//Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.
//
//        Return the intersection of these two interval lists.
//
//        (Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)
//
//
//
//        Example 1:
//
//
//
//        Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
//        Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
//
//
//        Note:
//
//        0 <= A.length < 1000
//        0 <= B.length < 1000
//        0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9
//        Accepted
//        151,700
//        Submissions
//        223,415

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> result = new ArrayList<>();
        int indexA = 0, indexB = 0;
        while (indexA < A.length && indexB < B.length) {
            int[] iA = A[indexA];
            int[] iB = B[indexB];
            int left = Math.max(iA[0], iB[0]);
            int right = Math.min(iA[1], iB[1]);
            if (left <= right) {
                result.add(new int[]{left, right});
            }
            if (iA[1] > iB[1]) {
                indexB++;
            } else if (iA[1] < iB[1]) {
                indexA++;
            } else {
                indexA++;
                indexB++;
            }
        }
        int[][] ret = new int[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            ret[i] = result.get(i);
        }
        return ret;
    }

    public static void main(String[] args) {
        int[][] A = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] B = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        Arrays.stream(new Solution().intervalIntersection(A, B)).forEach(intArray -> {
                    System.out.println();
                    for (int i : intArray) {
                        System.out.print(i + " ");
                    }
                }
        );
    }
}
