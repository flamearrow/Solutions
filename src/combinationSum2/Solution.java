package combinationSum2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
//
//Each number in C may only be used once in the combination.
//
//Note:
//
//   All numbers (including target) will be positive integers.
//   Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 <= a2 <= … <= ak).
//   The solution set must not contain duplicate combinations.
//
//For example, given candidate set 10,1,2,7,6,1,5 and target 8,
//A solution set is:
//[1, 7]
//[1, 2, 5]
//[2, 6]
//[1, 1, 6] 
public class Solution {
    public List<List<Integer>> combinationSum4(int[] candidates, int target) {
        List<List<Integer>> ret = new LinkedList<>();
        LinkedList<Integer> cur = new LinkedList<>();
        if (candidates.length == 0) {
            return ret;
        }
        Arrays.sort(candidates);
        doShit(candidates, 0, target, ret, cur);
        return ret;
    }

    // note each doShit only pick one number from candidats, starting from index to end
    void doShit(int[] candidats, int index, int rest, List<List<Integer>> ret, LinkedList<Integer> cur) {
        if (rest == 0) {
            ret.add(new LinkedList<>(cur));
            return;
        }
        for (int i = index; i < candidats.length; i++) {
            // we don't want to start after a number appeared once already
            if (i > 0 && candidats[i] == candidats[i - 1] && cur.size() == 0) {
                continue;
            }
            // we don't want to skip and consecutive
            //  2 2 2
            // if we decide to skip second 2, then don't pick third 2
            if (i > index && candidats[i] == candidats[i - 1]) {
                continue;
            }
            if (rest >= candidats[i]) {
                cur.addLast(candidats[i]);
                doShit(candidats, i + 1, rest - candidats[i], ret, cur);
                cur.removeLast();
            } else {
                break;
            }
        }
    }

    public ArrayList<ArrayList<Integer>> combinationSum3(int[] num, int target) {
        Arrays.sort(num);
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        boolean[] used = new boolean[num.length];
        doProbe(num, target, 0, new LinkedList<Integer>(), used, ret);
        return ret;
    }

    void doProbe(int[] num, int left, int curIndex,
                 LinkedList<Integer> curPath, boolean[] used,
                 ArrayList<ArrayList<Integer>> ret) {
        if (left == 0) {
            ret.add(new ArrayList<Integer>(curPath));
            return;
        }
        if (curIndex == num.length)
            return;
        // use current index
        // note when checking reaching 0, should use '='
        if (left >= num[curIndex]) {
            // 1 1 1 2 3
            // we only keep using 1 if previous 1 is already used
            if (curIndex == 0
                    || (num[curIndex] != num[curIndex - 1])
                    || (num[curIndex] == num[curIndex - 1] && used[curIndex - 1])) {
                used[curIndex] = true;
                curPath.add(num[curIndex]);
                doProbe(num, left - num[curIndex], curIndex + 1, curPath, used,
                        ret);
                used[curIndex] = false;
                curPath.removeLast();
            }
        }
        // don't use current index
        doProbe(num, left, curIndex + 1, curPath, used, ret);
    }

    // similar to the dup one, but we need to recurse in two ways
    // note when we need to resolve dup, always try to use a boolean array to
    // mark visited
    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        Arrays.sort(num);
        ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        LinkedList<Integer> path = new LinkedList<Integer>();
        boolean[] visited = new boolean[num.length];
        probe(num, target, 0, path, ret, visited);
        return ret;
    }

    void probe(int[] num, int target, int index, LinkedList<Integer> path,
               ArrayList<ArrayList<Integer>> ret, boolean[] visited) {
        if (target == 0) {
            ret.add(new ArrayList<Integer>(path));
            return;
        }
        if (target < 0 || index == num.length)
            return;

        // don't use num[index]
        probe(num, target, index + 1, path, ret, visited);

        // try use num[index] resolving dup using visited
        // if it's not the first of consecutive, and its previous has not been
        // visited, then we can't visit this
        // i.e for 1, 1, 1: we can visited the second, but can't visited
        // the third
        if (index > 0 && num[index] == num[index - 1] && !visited[index - 1]) {
            return;
        }
        visited[index] = true;
        path.add(num[index]);
        probe(num, target - num[index], index + 1, path, ret, visited);
        path.removeLast();
        visited[index] = false;
    }

    public static void main(String[] args) {
        int[] num = {2, 2, 2};
        int target = 4;
        List<List<Integer>> ret = new Solution().combinationSum4(num,
                target);
        System.out.println(ret);

    }
}
