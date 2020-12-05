package rangeAddition;

//Assume you have an array of length n initialized with all 0's and are given k update operations.
//
//        Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
//
//        Return the modified array after all k operations were executed.
//
//        Example:
//
//        Input: length = 5, updates = [[1,3,2],[2,4,3],[0,2,-2]]
//        Output: [-2,0,3,5,3]
//        Explanation:
//
//        Initial state:
//        [0,0,0,0,0]
//
//        After applying operation [1,3,2]:
//        [0,2,2,2,0]
//
//        After applying operation [2,4,3]:
//        [0,2,5,5,3]
//
//        After applying operation [0,2,-2]:
//        [-2,0,3,5,3]
public class Solution {
    //  0 0 0 0 0
    //  2 2 2 2
    //      3 3 3
    //      1 1
    // This can be represented as
    //  0 0 0 0  0
    //  2       -2
    //      3      -3
    //      1   -1
    // =>
    //  2   4   -3  <- cum[]
    // adding a[i] = a[i-1] + cum[i]
    //  2 2 6 6  3
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] cum = new int[length];
        for (int[] range : updates) {
            cum[range[0]] += range[2];
            if (range[1] + 1 < cum.length) {
                cum[range[1] + 1] -= range[2];
            }
        }
        int[] ret = new int[length];
        ret[0] = cum[0];
        for (int i = 1; i < cum.length; i++) {
            ret[i] = cum[i] + ret[i - 1];
        }
        return ret;
    }

    // if initial array is not all zero, get the ret and apply to original array
}
