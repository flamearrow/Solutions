package divideTwoIntegers;

/**
 * Created by flamearrow on 1/24/16.
 */
public class Solution2 {
    public int divideNaive(int dividend, int divisor) {
        int ret = 0;
        while (dividend >= divisor) {
            ret++;
            dividend -= divisor;
        }
        return ret;
    }

    public int divide(int dividend, int divisor) {
        int ret = 0;
        while (dividend >= divisor) {
            int cur = 1;
            int curDivisor = divisor;
            while (dividend >= curDivisor) {
                cur <<= 1;
                curDivisor <<= 1;
            }
            ret += (cur >> 1);
            dividend -= (curDivisor >> 1);

        }
        return ret;
    }

    public int divide3(int dividend, int divisor) {
        int ret = 0;
        while (dividend >= divisor) {
            int cur = 1;
            int curDivisor = divisor;
            while (dividend >= curDivisor) {
                ret += cur;
                dividend -= curDivisor;
                cur <<= 1;
                curDivisor <<= 1;
            }

        }
        return ret;
    }

    public int divide4(int dividend, int divisor) {
        int ret = 0;
        while (dividend >= divisor) {
            for (int mod = 1, tmpDiv = divisor; tmpDiv <= dividend; mod <<= 1, tmpDiv <<= 1) {
                ret += mod;
                dividend -= tmpDiv;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(new Solution2().divide3(100, 24));
    }
}
