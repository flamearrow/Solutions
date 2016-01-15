package houseRobber;

import java.util.Arrays;

//You are a professional robber planning to rob houses along a street.
//Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent
// houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
//
//Given a list of non-negative integers representing the amount of money of each house, 
//determine the maximum amount of money you can rob tonight without alerting the police.
public class Solution {
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 1};
        System.out.println(new Solution().rob2(nums));
    }

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        boolean[] bDp = new boolean[nums.length];
        dp[0] = nums[0];
        bDp[0] = false;
        dp[1] = Math.max(nums[0], nums[1]);
        bDp[1] = nums[1] > nums[0];
        for (int i = 2; i < nums.length; i++) {
            if (bDp[i - 1]) {
                // don't rob i-1
                int v1 = dp[i - 2] + nums[i];
                // rob i-1
                int v2 = dp[i - 1];
                if (v1 > v2) {
                    dp[i] = v1;
                    bDp[i] = true;
                } else {
                    dp[i] = v2;
                    bDp[i] = false;
                }
            } else {
                bDp[i] = true;
                dp[i] = dp[i - 1] + nums[i];
            }
        }
        return dp[nums.length - 1];
    }

    public int robReimpl(int[] nums) {
        int cnt = nums.length;
        if (cnt == 0) {
            return 0;
        }
        int[] robI = new int[cnt];
        robI[0] = nums[0];
        int[] notRobI = new int[cnt];
        for (int i = 1; i < cnt; i++) {
            robI[i] = notRobI[i - 1] + nums[i];
            notRobI[i] = Math.max(notRobI[i - 1], robI[i - 1]);
        }
        return Math.max(robI[cnt - 1], notRobI[cnt - 1]);
    }

    // what if the houses are in a circle?
    public int rob2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        // rob first, can't rob second and last
        int robFirst = nums[0] + (nums.length == 3 ? 0 :
                robReimpl(Arrays.copyOfRange(nums, 2, nums.length - 1)));
        // don't rob first, just calculate from second with regular subproblem
        int notRobFirst = robReimpl(Arrays.copyOfRange(nums, 1, nums.length));
        return Math.max(robFirst, notRobFirst);
    }


    public int rob2DFS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // dfs
        return doShit(nums, 0, new boolean[nums.length], 0);
    }

    int doShit(int[] nums, int curIndex, boolean[] used, int curValue) {
        if (curIndex == nums.length - 1) {
            // can't use last because first is used
            if (used[0]) {
                return curValue;
            }
            // use last because first is not used
            else {
                return curValue + nums[0];
            }
        }
        int notRob = doShit(nums, curIndex + 1, used, curValue);
        int rob = 0;
        if (curIndex > 0 && !used[curIndex - 1]) {
            used[curIndex] = true;
            rob = doShit(nums, curIndex + 1, used, curValue + nums[curIndex]);
            used[curIndex] = false;
        }
        return Math.max(rob, notRob);
    }
}
