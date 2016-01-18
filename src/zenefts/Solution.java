package zenefts;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by flamearrow on 1/16/16.
 */
public class Solution {
    public static void main(String[] args) {
//        int[] arr = {1, 2, 3};
//        System.out.println(countUneatenLeaves4(24, arr));
//        for (Set<Integer> set : getSetsSizeOf(3, arr)) {
//            for (int i : set) {
//                System.out.print(i + " ");
//            }
//            System.out.println();
//            System.out.println(getLcmList(set));
//        }
        List<Long> bigIntegers = new LinkedList<>();

        Set<Integer> integers = new HashSet<>();
        int[] ints = {1000000000, 999999999, 999999998, 999999997, 999999996, 999999995,
                999999994, 999999993, 999999992, 999999991};
        for (int i : ints) {
            integers.add(i);
        }

        BigInteger lcm = getLcmList(integers);
        System.out.println(lcm + " is lcm");
        for (int i : ints) {
            System.out.println(lcm.mod(new BigInteger(String.valueOf(i))));
        }

    }

    static int bitFlip(int[] arr) {
        int oneCount = 0;
        int zeroCount = 0;
        int maxZero = 0;
        for (int i : arr) {
            if (i == 1) {
                oneCount++;
                zeroCount--;
                if (zeroCount < 0) {
                    zeroCount = 0;
                }
            } else {
                zeroCount++;
                maxZero = Math.max(zeroCount, maxZero);
            }
        }
        return oneCount + maxZero;
    }

    static int bitFlip2(int[] arr) {
        int[] dp = new int[arr.length];
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (i == j) {
                    dp[j] = arr[j] == 1 ? 0 : 1;
                } else {
                    dp[j] = arr[j] == 1 ? dp[j - 1] : dp[j - 1] + 1;
                }
                max = Math.max(max, dp[j]);
            }
        }
        return max;
    }


    static int countUneatenLeaves(int N, int[] A) {
        boolean[] left = new boolean[N + 1];
        Arrays.fill(left, true);
        left[0] = false;
        for (int i : A) {
            int mod = i;
            while (mod < left.length) {
                left[mod] = false;
                mod += i;
            }
        }
        int ret = 0;
        for (boolean b : left) {
            if (b) {
                ret++;
            }
        }
        return ret;
    }

    static int countUneatenLeaves2(int N, int[] A) {
        Set<Integer> hit = new HashSet<>();
//        Arrays.sort(A);
        for (int i : A) {
            if (hit.contains(i)) {
                continue;
            }

            int mod = i;
            while (mod <= N) {
                hit.add(mod);
                mod += i;
            }
        }
        return N - hit.size();
    }

    static int countUneatenLeaves4(int N, int[] A) {
        long longN = (long) N;
        long ret = longN;
        for (int i = 1; i <= A.length; i++) {
            boolean over = true;
            for (Set<Integer> multipliers : getSetsSizeOf(i, A)) {
                long lcm = getLcmList(multipliers).longValue();
                if (lcm <= 0 || lcm > longN) {
                    continue;
                }
                over = false;
                if (i % 2 == 1) {
                    ret -= longN / lcm;
                } else {
                    ret += longN / lcm;
                }
            }
            if (over) {
                break;
            }
        }
        return (int) ret;
    }

    static Set<Set<Integer>> getSetsSizeOf(int size, int[] A) {
        Set<Integer> cur = new HashSet<>();
        Set<Set<Integer>> ret = new HashSet<>();
        doProbe(ret, cur, size, A, 0);
        return ret;
    }

    static void doProbe(Set<Set<Integer>> ret, Set<Integer> cur, int left, int[] arr, int
            curIndex) {
        if (left == 0) {
            ret.add(new HashSet<>(cur));
            return;
        }
        for (int i = curIndex; i < arr.length; i++) {
            cur.add(arr[i]);
            doProbe(ret, cur, left - 1, arr, i + 1);
            cur.remove(arr[i]);
        }
    }


    static BigInteger getLcmList(Set<Integer> multipliers) {
        BigInteger ret = new BigInteger(String.valueOf(1));
        for (int i : multipliers) {
            ret = getLcm(new BigInteger(String.valueOf(i)), new BigInteger(String.valueOf(ret)));
        }

        return ret;
    }

    static BigInteger getLcm(BigInteger bigA, BigInteger bigB) {
        return bigA.multiply(bigB).divide(gcd(bigA, bigB));
    }

    static BigInteger gcd(BigInteger a, BigInteger b) {
        BigInteger bigA = new BigInteger(String.valueOf(a));
        BigInteger bigB = new BigInteger(String.valueOf(b));

        while (!bigB.equals(BigInteger.ZERO)) {
            BigInteger tmp = bigB;
            bigB = bigA.mod(bigB);
            bigA = tmp;
        }
        return bigA;
    }
}
