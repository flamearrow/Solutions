package findTheSmallestDivisorGivenAThreshold;
//Given an array of integers nums and an integer threshold, we will choose a positive integer divisor and divide all the array by it and sum the result of the division. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
//
//        Each result of division is rounded to the nearest integer greater than or equal to that element. (For example: 7/3 = 3 and 10/2 = 5).
//
//        It is guaranteed that there will be an answer.
//
//
//
//        Example 1:
//
//        Input: nums = [1,2,5,9], threshold = 6
//        Output: 5
//        Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1.
//        If the divisor is 4 we can get a sum to 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2).
//        Example 2:
//
//        Input: nums = [2,3,5,7,11], threshold = 11
//        Output: 3
//        Example 3:
//
//        Input: nums = [19], threshold = 5
//        Output: 4
//
//
//        Constraints:
//
//        1 <= nums.length <= 5 * 10^4
//        1 <= nums[i] <= 10^6
//        nums.length <= threshold <= 10^6

import java.util.Arrays;

public class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int largest = Integer.MIN_VALUE;
        for (int i : nums) {
            if (i > largest) {
                largest = i;
            }
        }

        return getDivisor(nums, 1, largest, threshold);
    }

    // simple binary search with left/right boundaries
    int getDivisor(int[] nums, int left, int right, int threshold) {
        int divisor = (left + right) / 2;
        if (left == right) {
            return moveDivisor(nums, divisor, threshold);
        }
        long currentResult = calculateResult(nums, divisor);

        if (left + 1 == right) {
            // if we reach here, the result is guaranteed to be in either left or right.
            // currentResult is calculated from left and bigger
            // if the bigger one doesn't hold, use right to calculate result, which is smaller.
            return currentResult <= threshold ? moveDivisor(nums, left, threshold) :
                    moveDivisor(nums, right, threshold);
        }
        if (currentResult > threshold) {
            return getDivisor(nums, divisor, right, threshold);
        } else if (currentResult < threshold) {
            return getDivisor(nums, left, divisor, threshold);
        } else {
            return moveDivisor(nums, divisor, threshold);
        }
    }

    // for this particular problem, it is possible that the binary search reaches a divisor that has
    // calculateResult(nums, divisor) == threshold, but there exists smaller divisors that also
    // satisfy calculateResult(nums, divisor) == threshold, prob left until we find one that
    // breaks this.
    int moveDivisor(int[] nums, int divisor, int threshold) {
        if (calculateResult(nums, divisor) != threshold) {
            return divisor;
        } else {
            while (calculateResult(nums, divisor) == threshold) {
                if (divisor == 1) {
                    return 1;
                } else {
                    divisor--;
                }
            }
            return divisor + 1;
        }

    }

    int calculateResult(int[] nums, int divisor) {
        return Arrays.stream(nums).map(item -> divide(item, divisor)).sum();
    }


    int divide(int a, int b) {
        return a % b == 0 ? (a / b) : (a / b + 1);
    }


    public static void main(String[] args) {
        int[] nums = {2, 2, 2, 2};
        int threshold = 4;
        System.out.println(new Solution().smallestDivisor(nums, threshold));
    }
}
