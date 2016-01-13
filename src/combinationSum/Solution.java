package combinationSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where
// the candidate numbers sums to T.
//
//The same repeated number may be chosen from C unlimited number of times.
//
//Note:
//
//   All numbers (including target) will be positive integers.
//   Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 <=a2 <= … <= ak).
//   The solution set must not contain duplicate combinations.
//
//For example, given candidate set 2,3,6,7 and target 7,
//A solution set is:
//[7]
//[2, 2, 3] 
public class Solution {
    public List<List<Integer>> combinationSum3(int[] candidates, int target) {
        List<List<Integer>> ret = new LinkedList<>();
        LinkedList<Integer> cur = new LinkedList<>();
        if (candidates.length == 0) {
            return ret;
        }
        Arrays.sort(candidates);
        doShit(candidates, 0, target, ret, cur);
        return ret;
    }

    void doShit(int[] candidats, int index, int rest, List<List<Integer>> ret, LinkedList<Integer> cur) {
        if (rest == 0) {
            ret.add(new LinkedList<>(cur));
            return;
        }
        for (int i = index; i < candidats.length; i++) {
            if (rest >= candidats[i]) {
                cur.addLast(candidats[i]);
                doShit(candidats, i, rest - candidats[i], ret, cur);
                cur.removeLast();
            } else {
                break;
            }
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        Arrays.sort(candidates);
        probe2(candidates, 0, target, new LinkedList<Integer>(), ret);
        return ret;
    }

    void probe2(int[] candidates, int index, int left, LinkedList<Integer> cur,
                List<List<Integer>> ret) {
        if (left == 0) {
            ret.add(new LinkedList<Integer>(cur));
        } else if (index == candidates.length) {
            return;
        } else {
            // don't use current
            probe2(candidates, index + 1, left, cur, ret);
            // use current x times
            int c = candidates[index];
            int added = 0;
            while (left >= c) {
                left -= c;
                added++;
                cur.add(c);
                probe2(candidates, index + 1, left, cur, ret);
            }
            while (added > 0) {
                cur.removeLast();
                added--;
            }
        }
    }

    // naive recursion
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates,
                                                        int target) {
        Arrays.sort(candidates);
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        LinkedList<Integer> path = new LinkedList<Integer>();
        probe(candidates, 0, target, path, ret);
        return ret;
    }

    void probe(int[] candidates, int index, int target,
               LinkedList<Integer> path, ArrayList<ArrayList<Integer>> ret) {
        if (target < 0)
            return;
        if (target == 0) {
            ret.add(new ArrayList<Integer>(path));
        } else {
            for (int i = index; i < candidates.length; i++) {
                path.addLast(candidates[i]);
                probe(candidates, i, target - candidates[i], path, ret);
                path.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = {1, 2};
        int target = 6;
        List<List<Integer>> ret = new Solution().combinationSum3(candidates,
                target);
        System.out.println(ret);
    }
}
