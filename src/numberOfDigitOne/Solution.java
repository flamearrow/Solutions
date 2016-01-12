package numberOfDigitOne;

//
//Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
//
//        For example:
//        Given n = 13,
//        Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().countDigitOne(1410065408));
    }

    public int countDigitOne(int n) {
        long tens = 1;
        int ret = 0;
        while (n / tens > 0) {
            //  123_5_341
            // left == 123
            // right == 341
            // digit == 5
            // tens == 1000
            long left = n / tens / 10;
            long right = n % tens;
            long digit = (n / tens) % 10;
            if (digit == 1) {
                ret += left * tens;
                ret += (right + 1);
            } else if (digit > 1) {
                ret += (left + 1) * tens;
            } else if (digit < 1) {
                ret += left * tens;
            }
            tens *= 10;
        }
        return ret;
    }
}
