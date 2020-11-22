package nextGreaterElementII;

import java.util.Arrays;
import java.util.LinkedList;

//Given a circular array (the next element of the last element is the first element of the array),
// print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array,
// which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.
//
//        Example 1:
//        Input: [1,2,1]
//        Output: [2,-1,2]
//        Explanation: The first 1's next greater number is 2;
//        The number 2 can't find next greater number;
//        The second 1's next greater number needs to search circularly, which is also 2.
// Note: The length of given array won't exceed 10000.
public class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int cnt = nums.length;
        int[] res = new int[cnt];

        LinkedList<Integer> stack = new LinkedList<>();

        for (int i = cnt - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        // do it twice because the first run might miss cases like
        // 8, 2, 1, 5, 7
        // where first is the largest
        for (int i = cnt - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }
            res[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {7, 2, 1, 5, 8};
        System.out.println(Arrays.toString(new Solution().nextGreaterElements(nums)));
    }
}