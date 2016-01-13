package combinationSum3;

import java.util.LinkedList;
import java.util.List;

//Find all possible combinations of k numbers that add up to a number n,
// given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
//
//        Ensure that numbers within the set are sorted in ascending order.
//
//
//        Example 1:
//
//        Input: k = 3, n = 7
//
//        Output:
//
//        [[1,2,4]]
//
//        Example 2:
//
//        Input: k = 3, n = 9
//
//        Output:
//
//        [[1,2,6], [1,3,5], [2,3,4]]
public class Solution {
    public static void main(String[] args) {
        List<List<Integer>> ret = new Solution().combinationSum3(3,9);
        System.out.println(ret);
    }
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ret = new LinkedList<>();
        LinkedList<Integer> cur = new LinkedList<>();
        doShit(ret, cur, 1, k, n);
        return ret;
    }

    void doShit(List<List<Integer>> ret, LinkedList<Integer> cur, int curCandidate, int leftCnt, int rest) {
        if (leftCnt == 0) {
            if (rest == 0) {
                ret.add(new LinkedList<>(cur));
            }
            return;
        }
        for (int i = curCandidate; i <= 9; i++) {
            cur.addLast(i);
            doShit(ret, cur, i + 1, leftCnt - 1, rest - i);
            cur.removeLast();
        }
    }
}
