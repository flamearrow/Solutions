package maxGap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Given an unsorted array, find the maximum difference between the successive elements in its
// sorted form.
//
//        Try to solve it in linear time/space.
//
//        Return 0 if the array contains less than 2 elements.
//
//        You may assume all elements in the array are non-negative integers and fit in the
// 32-bit signed integer range.
public class Solution {
    public static void main(String[] args) {
        int[] nums = {24, 1, 334, 2, 145};
        new Solution().maximumGap(nums);
    }

    public int maximumGap(int[] nums) {
        if(nums.length < 2) {
            return 0;
        }
        // the trick of radix sort is to create a temporary array of size R where R is the number
        //  of digits
        // tmp[i] would be the COUNT of numbers with current digit <= i
        // say way have 10 digits, tmp[9] would be the size of the of array since all numbers at
        //  any digit should be smaller than 9, then tmp[8], tmp[7] etc
        // then we iterate through the original array, what's the digit it is
        //  then we create a new empty array with the same size of original array, fill the array
        // then we put this number to the new array at index tmp[DIGIT]
        //  then we decrease tmp[DIGIT], next time we see the same digit, we add it to the previous
        //  index
        int max = nums[0];
        for (int i : nums) {
            max = Math.max(i, max);
        }

        int exp = 1;
        // int R = 10, up to 10 digits
        int R = 10;
        int[] countArr = new int[R];
        int[] bufArr = new int[nums.length];
        while (max / exp > 0) {
            Arrays.fill(countArr, 0);
            for (int i : nums) {
                int index = (i / exp) % 10;
                countArr[index]++;
            }
            // adjust counts
            for (int i = 1; i < countArr.length; i++) {
                countArr[i] += countArr[i - 1];
            }

            // because the lower bit is already sort, if current bit is equal,
            // then the later one must be bigger

            // e.g 641, 651
            //  if current index is 6, we should pick 651 first, which is the later one
            // thus we look from the end of the array
            for (int j = nums.length - 1; j >= 0; j--) {
                int i = nums[j];
                int index = (i / exp) % 10;
                // next time a similar number should be moved one bucket forward
                countArr[index]--;
                bufArr[countArr[index]] = i;
            }

            System.arraycopy(bufArr, 0, nums, 0, bufArr.length);
            exp *= 10;
        }
        // now that it's fucking sorted we can just pick it stupidly
        int ret = 0;
        for (int i = 1; i < nums.length; i++) {
            ret = Math.max(ret, nums[i] - nums[i - 1]);
        }
        return ret;
    }
}
