package nextPermutation;

//Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
//
//        If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
//
//        The replacement must be in-place, do not allocate extra memory.
//
//        Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
//        1,2,3 → 1,3,2
//        3,2,1 → 1,2,3
//        1,1,5 → 1,5,1
//    1,2,3,8,6,5,4
//    1,2,4,3,5,6,8


import java.util.stream.IntStream;

public class Solution2 {
    public static void main(String[] args) {
        int[] array = {1, 5, 1};
        new Solution2().nextPermutation(array);
        IntStream.of(array).forEach(System.out::print);
    }


    // search from right, find the fist digit i such that array[i-1] < array[i] - can find a
    // bigger one, if there's no such digit, the numbers are already reversely sorted
    //  then search from right find the first digit j that's bigger than array[i-1], swap i and i-1
    //  now i-1 has the next bigger digit than i
    //  now from i-1 to end is reversely sorted(from big to small), reverse them from small to big
    public void nextPermutation(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        int cur = nums.length - 1;
        while (cur > 0 && nums[cur] <= nums[cur - 1]) {
            cur--;
        }
        if (cur == 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        int left = cur - 1;
        int right = nums.length - 1;
        while (nums[right] <= nums[left]) {
            right--;
        }
        swap(nums, left, right);
        reverse(nums, cur, nums.length - 1);
    }

    void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
