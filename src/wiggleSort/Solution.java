package wiggleSort;

import java.util.Arrays;

//Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
//
//For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].
// wiggleSort2:
// reorder in-plae such that nums[0] < nums[1] > nums[2] < nums[3]...
public class Solution {
    public static void wiggleSort(int[] nums) {
        boolean bigger = true;
        for (int i = 1; i < nums.length; i++) {
            if (bigger && nums[i] < nums[i - 1] || !bigger
                    && nums[i] > nums[i - 1]) {
                int tmp = nums[i];
                nums[i] = nums[i - 1];
                nums[i - 1] = tmp;
            }
            bigger = !bigger;
        }
    }

    public static void wiggleSortImple2(int[] nums) {
        boolean bigger = true;
        for (int i = 1; i < nums.length; i++) {
            if (bigger) {
                if (nums[i] < nums[i - 1]) {
                    swap(nums, i, i - 1);
                }
            } else {
                if (nums[i] > nums[i - 1]) {
                    swap(nums, i, i - 1);
                }
            }
            bigger = !bigger;
        }
    }

    static void swap(int[] nums, int from, int to) {
        int tmp = nums[from];
        nums[from] = nums[to];
        nums[to] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 1, 1, 6, 4};
        new Solution().wiggleSort2(nums);
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }

    //    Example:
    //    (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
    //    (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
    public void wiggleSort2(int[] nums) {
        // sort the array
        // then put smaller half to even indices and bigger half to odd indices,
        //  1,2,3,4,5,6
        // becomes
        //  3 6 2 5 1 4
        // note we need to reverse the half to prevent similar numbers like
        //  4 5 5 6
        //  should be
        //  5 4 6 5
        //  instead
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);

        int eventIndex = 0;
        int oddIndex = 1;
        int half = (sorted.length - 1) / 2 + 1;
        for (int i = 0; i < sorted.length; i++) {
            if (i < half) {
                nums[eventIndex] = sorted[half - i - 1];
                eventIndex += 2;
            } else {
                nums[oddIndex] = sorted[sorted.length + half - i - 1];
                oddIndex += 2;
            }
        }
    }


}
