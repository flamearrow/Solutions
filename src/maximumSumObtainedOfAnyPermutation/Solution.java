package maximumSumObtainedOfAnyPermutation;
//We have an array of integers, nums, and an array of requests where requests[i] = [starti, endi]. The ith request asks for the sum of nums[starti] + nums[starti + 1] + ... + nums[endi - 1] + nums[endi]. Both starti and endi are 0-indexed.
//
//        Return the maximum total sum of all requests among all permutations of nums.
//
//        Since the answer may be too large, return it modulo 109 + 7.
//
//
//
//        Example 1:
//
//        Input: nums = [1,2,3,4,5], requests = [[1,3],[0,1]]
//        Output: 19
//        Explanation: One permutation of nums is [2,1,3,4,5] with the following result:
//        requests[0] -> nums[1] + nums[2] + nums[3] = 1 + 3 + 4 = 8
//        requests[1] -> nums[0] + nums[1] = 2 + 1 = 3
//        Total sum: 8 + 3 = 11.
//        A permutation with a higher total sum is [3,5,4,2,1] with the following result:
//        requests[0] -> nums[1] + nums[2] + nums[3] = 5 + 4 + 2 = 11
//        requests[1] -> nums[0] + nums[1] = 3 + 5  = 8
//        Total sum: 11 + 8 = 19, which is the best that you can do.
//        Example 2:
//
//        Input: nums = [1,2,3,4,5,6], requests = [[0,1]]
//        Output: 11
//        Explanation: A permutation with the max total sum is [6,5,4,3,2,1] with request sums [11].
//        Example 3:
//
//        Input: nums = [1,2,3,4,5,10], requests = [[0,2],[1,3],[1,1]]
//        Output: 47
//        Explanation: A permutation with the max total sum is [4,10,5,3,2,1] with request sums [19,18,10].
//
//
//        Constraints:
//
//        n == nums.length
//        1 <= n <= 105
//        0 <= nums[i] <= 105
//        1 <= requests.length <= 105
//        requests[i].length == 2
//        0 <= starti <= endi < n

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int maxSum = 0;
//        for (List<Integer> indices : getAllPermutations(nums.length)) {
//
//            int currentSum = getSumFromIndices(requests, nums, indices);
//            if (currentSum > maxSum) {
//                maxSum = currentSum;
//            }
//        }

        for (List<Integer> permutedNums : getPermute(nums)) {
            int currentSum = getSum(requests, permutedNums);
            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }
        return maxSum % (1000000000 + 7);
    }


    List<List<Integer>> getPermute(int[] nums) {
        return doGetPermute(nums, nums.length - 1);
    }

    // return the permutation of firstN numbers
    List<List<Integer>> doGetPermute(int[] nums, int firstN) {
        List<List<Integer>> ret = new ArrayList<>();
        if (firstN == 0) {
            ArrayList<Integer> l = new ArrayList();
            l.add(nums[firstN]);
            ret.add(l);
            return ret;
        } else {
            List<List<Integer>> previousResults = doGetPermute(nums, firstN - 1);
            for (List<Integer> previousResult : previousResults) {
                // need to add i to the end
                for (int i = 0; i <= previousResult.size(); i++) {
                    ArrayList<Integer> l = new ArrayList<>(previousResult);
                    l.add(i, nums[firstN]);
                    ret.add(l);
                }
            }
        }
        return ret;
    }

    int getSum(int[][] requests, List<Integer> nums) {
        int cost = 0;
        for (int[] req : requests) {
            for (int i = req[0]; i <= req[1]; i++) {
                cost += nums.get(i);
            }
        }
        return cost;
    }

    int getSumFromIndices(int[][] requests, int[] nums, List<Integer> indices) {
        int cost = 0;
        for (int[] req : requests) {
            for (int i = req[0]; i <= req[1]; i++) {
                cost += nums[indices.get(i)];
            }
        }
        return cost;
    }

    // input length, return all indices
    // e.g in: 3
    //  return : [0, 1, 2] [0, 2, 1] [1, 0, 2] [1, 2, 0] [2, 0, 1] [2, 1, 0]
    List<LinkedList<Integer>> getAllPermutations(int length) {
        ArrayList<Integer> set = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            set.add(i);
        }
        List<LinkedList<Integer>> ret = new ArrayList<>();
        addPermutation(set, ret, new LinkedList<>());
        return ret;
    }

    void addPermutation(ArrayList<Integer> leftOvers, List<LinkedList<Integer>> results,
                        LinkedList<Integer> current) {
        if (leftOvers.isEmpty()) {
            results.add(new LinkedList<>(current));
        } else {
            for (int i = 0; i < leftOvers.size(); i++) {
                current.addLast(leftOvers.get(i));
                int indexRemoved = leftOvers.remove(i);
                addPermutation(leftOvers, results, current);
                leftOvers.add(i, indexRemoved);
                current.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        int[][] input = {{0, 1}};
        int[] nums = {1, 2, 3};

        System.out.println(new Solution().maxSumRangeQuery(nums, input));
//        System.out.println(new Solution().getPermute(nums));
    }

}
