package findPeakElement;

//A peak element is an element that is greater than its neighbors.
//
//        Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
//
//        The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
//
//        You may imagine that num[-1] = num[n] = -∞.
//
//        For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
//
//        click to show spoilers.
public class Solution {
    // bsearch
    // since num[n] is -∞, if we have a strict increasing array, the end element would be peak
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) {
                // increasing, we're sure there must be a peak on right
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return (left == nums.length - 1 || nums[left] > nums[left + 1]) ? left : right;
    }
}
