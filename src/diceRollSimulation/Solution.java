package diceRollSimulation;

import java.util.Arrays;

//A die simulator generates a random number from 1 to 6 for each roll. You introduced a constraint to the generator such that it cannot roll the number i more than rollMax[i] (1-indexed) consecutive times.
//
//        Given an array of integers rollMax and an integer n, return the number of distinct sequences that can be obtained with exact n rolls.
//
//        Two sequences are considered different if at least one element differs from each other. Since the answer may be too large, return it modulo 10^9 + 7.
//
//
//
//        Example 1:
//
//        Input: n = 2, rollMax = [1,1,2,2,2,3]
//        Output: 34
//        Explanation: There will be 2 rolls of die, if there are no constraints on the die, there are 6 * 6 = 36 possible combinations. In this case, looking at rollMax array, the numbers 1 and 2 appear at most once consecutively, therefore sequences (1,1) and (2,2) cannot occur, so the final answer is 36-2 = 34.
//        Example 2:
//
//        Input: n = 2, rollMax = [1,1,1,1,1,1]
//        Output: 30
//        Example 3:
//
//        Input: n = 3, rollMax = [1,1,1,2,2,3]
//        Output: 181
//
//
//        Constraints:
//
//        1 <= n <= 5000
//        rollMax.length == 6
//        1 <= rollMax[i] <= 15
public class Solution {
    public int dieSimulator(int n, int[] rollMax) {
//        return (int) (findResults(n, rollMax, Arrays.copyOf(rollMax, 6)) % (Math.pow(10, 9) + 7));
        return (int) (findResults(n, rollMax, Arrays.copyOf(rollMax, 6), -1) % (Math
                .pow(10, 9) + 7));
    }

    int findResults(int n, int[] rollMax, int[] currentRoll, int previous) {
        if (n == 0) {
            return 1;
        }
        int ret = 0;
        for (int i = 0; i < 6; i++) {
            if (currentRoll[i] > 0) {
                int currentRollPrevious = -1;
                if (previous >= 0 && i != previous) {
                    currentRollPrevious = currentRoll[previous];
                    currentRoll[previous] = rollMax[previous];
                }
                currentRoll[i]--;
                ret += findResults(n - 1, rollMax, currentRoll, i);
                currentRoll[i]++;
                if (previous >= 0 && i != previous) {
                    currentRoll[previous] = currentRollPrevious;
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        int n = 20;
        int[] rollMax = {8, 5, 10, 8, 7, 2};
        System.out.println(new Solution().dieSimulator(n, rollMax));
    }
}
