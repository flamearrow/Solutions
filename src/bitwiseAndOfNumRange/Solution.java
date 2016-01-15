package bitwiseAndOfNumRange;

//Given a range [m, n] where 0 <= m <= n <= 2147483647,
// return the bitwise AND of all numbers in this range, inclusive.
//
//        For example, given the range [5, 7], you should return 4.

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().rangeBitwiseAnd(1, 1));
    }

    public int rangeBitwiseAnd(int m, int n) {
        // find common prefix, clear the rest
        int i = 31;
        for (; i >= 0; i--) {
            if ((m & (1 << i)) != (n & (1 << i))) {
                break;
            }
        }
        while (i >= 0) {
            m &= ~(1 << i);
            i--;
        }
        return m;
    }
}
