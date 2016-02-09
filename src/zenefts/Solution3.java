package zenefts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by flamearrow on 2/1/16.
 */
public class Solution3 {
    public static void main(String[] args) {
        int[] prices = {97, 1, 3, 99, 100, 2};
        System.out.println(new Solution3().minValue(prices));

//        for (int i = 0; i < 10; i++) {
//            randomize(prices);
//            IntStream.of(prices).forEach(j -> System.out.print(j + " "));
//            System.out.println();
//            System.out.println(new Solution3().minValue(prices));
//        }
    }

    static void randomize(int[] prices) {
        Random rand = new Random();
        for (int i = prices.length; i > 0; i--) {
            int index = rand.nextInt(i);
            int tmp = prices[index];
            prices[index] = prices[i - 1];
            prices[i - 1] = tmp;
        }
    }

    // given a list of prices, find the minimal value to get all of them
    // buying a higher prices item gives you another item with lower price for free
    //  e.g [5,4,3,3] you need 9 to buy all of them - 5-3, 4-3
    int minValue(int[] prices) {
        // dp[i] contains the optimal combination of previous i pairs of item
        // dp[i][0] is the price of more expensive item
        // dp[i][1] is the price of less expensive item
        ArrayList<ArrayList<int[]>> dp = new ArrayList<>(prices.length / 2);
        int[] firstPair = new int[2];
        firstPair[0] = Math.max(prices[0], prices[1]);
        firstPair[1] = Math.min(prices[0], prices[1]);
        dp.add(new ArrayList<>());
        dp.get(0).add(firstPair);
        for (int i = 1; i < prices.length / 2; i += 1) {
            int higher = Math.max(prices[i * 2], prices[i * 2 + 1]);
            int lower = Math.min(prices[i * 2], prices[i * 2 + 1]);
            int[] newPair = {higher, lower};
            // iterate all pairs in dp[i-1], find the pair p such that
            //  p[0] > higher > p[1] > lower
            // in this case we can have p[0] + p[1] to buy them all
            //  instead of p[0] + higher
            int minAdded = higher;
            int[] toReplacedPair = null;
            for (int[] pair : dp.get(i - 1)) {
                int tmpMin = calculateMin(pair, newPair);
                if (tmpMin < minAdded) {
                    minAdded = tmpMin;
                    toReplacedPair = pair;
                }
//                if (pair[0] > minAdded && minAdded > pair[1] && pair[1] > lower) {
//                    minAdded = pair[1];
//                    toReplacedPair = pair;
//                }
            }
//            if (toReplacedPair != null) {
//                newPair[0] = toReplacedPair[1];
//            }
            ArrayList<int[]> newList = new ArrayList<>();
            for (int[] pair : dp.get(i - 1)) {
                if (pair != toReplacedPair) {
                    int[] pairToAdd = {pair[0], pair[1]};
                    newList.add(pairToAdd);
                } else {
                    int[] arr = new int[4];
                    arr[0] = newPair[0];
                    arr[1] = newPair[1];
                    arr[2] = pair[0];
                    arr[3] = pair[1];
                    reverseSort(arr);
                    int[] newPair1 = {arr[0], arr[1]};
                    newPair[0] = arr[2];
                    newPair[1] = arr[3];
                    newList.add(newPair1);
                }
            }
            newList.add(newPair);
            dp.add(newList);
        }
        int ret = 0;
        for (int[] pair : dp.get(prices.length / 2 - 1)) {
            System.out.print(pair[0] + ":" + pair[1] + "  ");
            ret += pair[0];
        }
        return ret;
    }

    int calculateMin(int[] p1, int[] p2) {
        int[] arr = new int[4];
        arr[0] = p1[0];
        arr[1] = p1[1];
        arr[2] = p2[0];
        arr[3] = p2[1];
        reverseSort(arr);
        return arr[0] + arr[2] - p1[0];
    }

    void reverseSort(int[] arr) {
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            left++;
            right--;
        }
    }


}
