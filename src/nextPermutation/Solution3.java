package nextPermutation;

import java.util.Arrays;

public class Solution3 {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return;
        }
        int leftToReverse = -1;
        here:
        for (int left = len - 2; left >= 0; left--) {
            for (int right = len - 1; right > left; right--) {
                if (nums[right] > nums[left]) {
                    leftToReverse = left;
                    swap(nums, left, right);
                    break here;
                }
            }
        }
        for (int left = leftToReverse + 1, right = len - 1; left < right; left++, right--) {
            swap(nums, left, right);
        }
    }

    void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 0, 2, 3, 2, 0};
        new Solution3().nextPermutation(nums);
        Arrays.stream(nums).forEach(System.out::print);
    }
}
