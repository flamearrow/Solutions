package patchingArray;

//Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.
//
//        Example 1:
//        nums = [1, 3], n = 6
//        Return 1.
//
//        Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
//        Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
//        Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
//        So we only need 1 patch.
//
//        Example 2:
//        nums = [1, 5, 10], n = 20
//        Return 2.
//        The two patches can be [2, 4].
//
//        Example 3:
//        nums = [1, 2, 2], n = 5
//        Return 0.
public class Solution {
    public static void main(String[] args) {
        int[] nums = {1, 3};
        System.out.println(new Solution().minPatches(nums, 6));
    }

    // the idea is loop from left to right, try to cover numbers from 1 to n
    // note: give that we already have numbers 1...i, we can cover 1... (1+2+...i)
    //  so if we cover 1 to n, now if patched with (n+1), the covered range goes up to (n+n+1)
    public int minPatches(int[] nums, int n) {
        if (n <= 0) {
            return 0;
        }
        long covered = 0;
        int patched = 0;
        int cur = 0;
        while (covered < n) {
            if (cur < nums.length) {
                if (nums[cur] <= covered + 1) {
                    covered += nums[cur];
                    cur++;
                } else {
                    // patch covered+1
                    covered += covered + 1;
                    patched++;
                }
            } else {
                covered += covered + 1;
                patched++;
            }
        }
        return patched;
    }
}
