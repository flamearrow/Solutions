package splitwise;

import java.util.stream.IntStream;

/**
 * Implement the function for splitwise, an app to calculate how much one should pay for a
 * group event.
 */
public class Solution {

    public static void main(String[] args) {
        int headCount = 3;
        int[] toyInput = {1, 2, 3};
        int[][] splited = splitIt(toyInput);
        for (int from = 0; from < headCount; from++) {
            for (int to = 0; to < headCount; to++) {
                if (splited[from][to] > 0) {
                    System.out.println(from + " should pay " + to + " " + splited[from][to] + " " +
                            "coins");
                }
            }
        }
    }

    /**
     * Split the bill.
     *
     * @param payList A list of number for the amount each one paid
     *                e.g 1,2,3,4 means A paid 1 coin, B paid 2 coins, C paid 3 coins and D paid
     *                4 coins
     * @return A grid representing how much how should pay who.
     *          e.g a[0][2]=5 means A should pay C 5 coins,
     *              a[1][2]=1 means B should Pay C 1 coin
     */
    static int[][] splitIt(int[] payList) {
        int headCount = payList.length;
        int costPerPerson = IntStream.of(payList).sum() / headCount;
        int[] diffs = new int[headCount];

        for (int i = 0; i < headCount; i++) {
            // diffs[i] positive if need to send
            // diffs[i] negative if need to receive
            // if 0, then don't need to send/receive
            diffs[i] = payList[i] - costPerPerson;
        }

        // IntStream.of(diffs).sum() should be 0

        int[][] ret = new int[headCount][headCount];
        int nextPosIndex = findNextIndex(diffs, 0, true);
        int nextNegIndex = findNextIndex(diffs, 0, false);

        // when either index is out of bound we're sure all debit are cleared
        while (nextNegIndex != -1 && nextPosIndex != -1) {
            // how much should be paid
            int amountToPay = Math.min(diffs[nextPosIndex], -diffs[nextNegIndex]);
            ret[nextPosIndex][nextNegIndex] = amountToPay;

            // clear the dept between the two
            diffs[nextPosIndex] -= amountToPay;
            diffs[nextNegIndex] += amountToPay;

            nextPosIndex = findNextIndex(diffs, nextPosIndex, true);
            nextNegIndex = findNextIndex(diffs, nextNegIndex, false);
        }

        return ret;
    }

    static int findNextIndex(int[] array, int currentPosition, boolean positive) {
        for (int i = currentPosition; i < array.length; i++) {
            if (positive) {
                if (array[i] > 0) {
                    return i;
                }
            } else {
                if (array[i] < 0) {
                    return i;
                }
            }
            i++;
        }
        return -1;
    }

}
