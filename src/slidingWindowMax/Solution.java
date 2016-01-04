package slidingWindowMax;

import java.util.LinkedList;
import java.util.Queue;

//Given an array nums, there is a sliding window of size k which is moving from the very
// left of the array to the very right. You can only see the k numbers in the window.
// Each time the sliding window moves right by one position.
//
//        For example,
//        Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
//
//        Window position               Max
//        ---------------              -----
//        [1  3  -1] -3  5  3  6  7      3
//        1 [3  -1  -3] 5  3  6  7       3
//        1  3 [-1  -3  5] 3  6  7       5
//        1  3  -1 [-3  5  3] 6  7       5
//        1  3  -1  -3 [5  3  6] 7       6
//        1  3  -1  -3  5 [3  6  7]      7
//        Therefore, return the max sliding window as [3,3,5,5,6,7].
//
//        Note:
//        You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
public class Solution {
    public static void main(String[] args) {
        int[] nums = {1, 3, 1, 2, 0, 5};
        int k = 3;
        for (int i : new Solution().maxSlidingWindow(nums, k)) {
            System.out.println(i);
        }
    }
    // the idea is to use a linked list to keep track of INDICES
    //  the list should not be longer than k,
    //   we check that by looking at the index we're considering - index of head of the queue
    //  the list should only contain possible largest number of window of size k
    //   we ensure this by removing tail of the list if num[tail] is smaller than num[i]
    //    because tail < i, and since num[i] > num[tail],
    //    if there's a window that requires a max containing i,
    //    the max index can't be i because windows containing num[tail] must also contain num[i]
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0 || nums.length == 0) {
            return new int[0];
        }
        int[] rst = new int[nums.length - k + 1];
        LinkedList<Integer> q = new LinkedList<>();
        int curIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            while(!q.isEmpty() && q.peek() < i - k + 1) {
                q.removeFirst();
            }
            while(!q.isEmpty() && nums[q.getLast()] < nums[i]) {
                q.removeLast();
            }
            q.add(i);
            if ((i + 1) >= k) {
                rst[curIndex] = nums[q.peek()];
                curIndex++;
            }
        }
        return rst;
    }
}
