package facTrailingZeros;

//Given an integer n, return the number of trailing zeroes in n!.
//
//        Note: Your solution should be in logarithmic time complexity.
public class Solution {
    public static void main(String[] args) {
//        System.out.println(new Solution().trailingZeroes(25));
//        System.out.println(Math.log10(10));
        System.out.println(new Solution().trailingZeros2(30));
    }

    public int trailingZeros2(int n) {
        int s = 0;
        while (n > 1) s += (n /= 5);
        return s;
    }

    public int trailingZeroes(int n) {
        // each 5 contributes to a zero
        // each 5*5 contributes to 2 zeros
        // each 5*5*5 contributes to 3 zeros
        int mod = 5;
        int ret = 0;
        while (n > mod) {
            ret += n / mod;
            mod *= 5;
        }
        return ret;
    }
}
