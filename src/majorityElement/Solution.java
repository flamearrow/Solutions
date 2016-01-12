package majorityElement;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

//Given an array of size n, find the majority element. The majority element is the element that appears
// more than ⌊ n/2 ⌋ times.
//
//        You may assume that the array is non-empty and the majority element always exist in the array.
public class Solution {
    // Moore voting: since one element happens over n/2 times, we can keep a counter,
    // each time we see one number,
    //  if the number is the same with prev, increment the counter
    //   otherwise decrement the counter
    //  if counter decrement to 0, change the candidate to current number
    public int majorityElement(int[] nums) {
        int count = 0;
        int ret = 0;
        for (int i : nums) {
            if (count == 0) {
                ret = i;
            }
            if (i == ret) {
                count++;
            } else {
                count--;
            }
        }
        return ret;
    }

    // what if majority occurs over n/3 times?
    // use O(1) space
    public List<Integer> majorityElement2(int[] nums) {
        List<Integer> ret = new LinkedList<>();
        if(nums.length == 0) {
            return ret;
        }
        // similar to n/2, use a 2 candidates moore voting algorithm
        int[] can = new int[2];
        int[] cnt = new int[2];
        can[1] = 2;
        for (int i : nums) {
            if (i == can[0]) {
                cnt[0]++;
            } else if (i == can[1]) {
                cnt[1]++;
            } else if (cnt[0] == 0) {
                can[0] = i;
                cnt[0] = 1;
            } else if (cnt[1] == 0) {
                can[1] = i;
                cnt[1] = 1;
            } else {
                cnt[0]--;
                cnt[1]--;
            }
        }
        // loop the array again to verify the numbers are actually over 1/3
        int thres = nums.length / 3;
        Arrays.fill(cnt, 0);
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < nums.length; i++) {
                if (can[j] == nums[i]) {
                    cnt[j]++;
                }
            }
        }
        for (int i = 0; i < 2; i++) {
            if (cnt[i] > thres)
                ret.add(can[i]);
        }
        return ret;
    }
}
