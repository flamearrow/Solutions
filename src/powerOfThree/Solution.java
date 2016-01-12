package powerOfThree;

/**
 * Created by flamearrow on 1/10/16.
 */
public class Solution {
    public static void main(String[] args) {
        for (int n = 0; n < 20; n++) {
            System.out.println(n + ": " + new Solution().isPowerOfThree(n));
        }
    }

    public boolean isPowerOfThree(int n) {
        // convert the string into string representation of 0, 1, 2
        // 3 multiplier would be something like 1000...
        return Integer.toString(n, 3).matches("10*");
    }
}
