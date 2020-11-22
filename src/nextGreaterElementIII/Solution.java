package nextGreaterElementIII;

import java.util.ArrayList;

//Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly
//        the same digits existing in the integer n and is greater in value than n. If no such
//        positive 32-bit integer exists, you need to return -1.
//
//        Example 1:
//
//        Input: 12
//        Output: 21
//
//
//        Example 2:
//
//        Input: 21
//        Output: -1
public class Solution {
    public int nextGreaterElement(int n) {
        ArrayList<Integer> digits = new ArrayList<>();
        while (n > 0) {
            // add to end
            digits.add(0, n % 10);
            n = n / 10;
        }
        int len = digits.size();
        // find a pair to swap
        // left is as right as possible, therefore start from len-2
        // right is as right as possible, therefore start from len-1
        // Note: at any time in the loop, digits right to leftToSwap is already reversely sorted
        // and since d[leftToSwap] is compared from the smallest item of the reversley sorted
        // subarray, once there's a hit d[leftToSwap] < d[rightToSwap] and we did a swap, the
        // subarray from leftToSwap+1 to end is also reversely sorted.
        int leftToSwap = -1, rightToSwap = -1;
        out:
        for (int l = len - 2; l >= 0; l--) {
            for (int r = len - 1; r > l; r--) {
                if (digits.get(l) < digits.get(r)) {
                    leftToSwap = l;
                    rightToSwap = r;
                    break out;
                }
            }
        }
        if (leftToSwap == -1) {
            return -1;
        }

        // swap
        swap(digits, leftToSwap, rightToSwap);

        // reverse the ones after leftToSwap
        for (int l = leftToSwap + 1, r = len - 1; l < r; l++, r--) {
            swap(digits, l, r);
        }

        long ret = 0;
        for (Integer digit : digits) {
            ret = ret * 10 + digit;
        }
        if (ret > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) ret;

    }

    void swap(ArrayList<Integer> digits, int left, int right) {
        int tmp = digits.get(left);
        digits.set(left, digits.get(right));
        digits.set(right, tmp);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().nextGreaterElement(1999999999));
    }
}
