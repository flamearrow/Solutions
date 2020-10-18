package countUnhappyFriends;
//You are given a list of preferences for n friends, where n is always even.
//
//        For each person i, preferences[i] contains a list of friends sorted in the order of preference. In other words, a friend earlier in the list is more preferred than a friend later in the list. Friends in each list are denoted by integers from 0 to n-1.
//
//        All the friends are divided into pairs. The pairings are given in a list pairs, where pairs[i] = [xi, yi] denotes xi is paired with yi and yi is paired with xi.
//
//        However, this pairing may cause some of the friends to be unhappy. A friend x is unhappy if x is paired with y and there exists a friend u who is paired with v but:
//
//        x prefers u over y, and
//        u prefers x over v.
//        Return the number of unhappy friends.
//
//
//
//        Example 1:
//
//        Input: n = 4, preferences = [[1, 2, 3], [3, 2, 0], [3, 1, 0], [1, 2, 0]], pairs = [[0, 1], [2, 3]]
//        Output: 2
//        Explanation:
//        Friend 1 is unhappy because:
//        - 1 is paired with 0 but prefers 3 over 0, and
//        - 3 prefers 1 over 2.
//        Friend 3 is unhappy because:
//        - 3 is paired with 2 but prefers 1 over 2, and
//        - 1 prefers 3 over 0.
//        Friends 0 and 2 are happy.
//        Example 2:
//
//        Input: n = 2, preferences = [[1], [0]], pairs = [[1, 0]]
//        Output: 0
//        Explanation: Both friends 0 and 1 are happy.
//        Example 3:
//
//        Input: n = 4, preferences = [[1, 3, 2], [2, 3, 0], [1, 3, 0], [0, 2, 1]], pairs = [[1, 3], [0, 2]]
//        Output: 4
//
//
//        Constraints:
//
//        2 <= n <= 500
//        n is even.
//        preferences.length == n
//        preferences[i].length == n - 1
//        0 <= preferences[i][j] <= n - 1
//        preferences[i] does not contain i.
//        All values in preferences[i] are unique.
//        pairs.length == n/2
//        pairs[i].length == 2
//        xi != yi
//        0 <= xi, yi <= n - 1
//        Each person is contained in exactly one pair.

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {
        int[][] preference = {{1, 3, 2}, {2, 3, 0}, {1, 3, 0}, {0, 2, 1}};
        int[][] pairs = {{1, 3}, {0, 2}};
        System.out.println(new Solution().unhappyFriends(4, preference, pairs));

    }

    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        int numPairs = pairs.length;
        Set<Integer> unhappyFriends = new HashSet<>();
        for (int i = 0; i < numPairs; i++) {
            for (int j = i + 1; j < numPairs; j++) {
                unhappyPPl(pairs[i], pairs[j], preferences, unhappyFriends);
            }
        }
        return unhappyFriends.size();
    }

    void unhappyPPl(int[] pair1, int[] pair2, int[][] pref, Set<Integer> unhappyFriends) {
        int x = pair1[0];
        int y = pair1[1];
        int u = pair2[0];
        int v = pair2[1];

        // x and u are unhappy
        if (unhappy(x, y, u, v, pref)) {
            System.out.println(x + " and " + u + " are unhappy");
            unhappyFriends.add(x);
            unhappyFriends.add(u);
        }
        // x and v are unhappy
        if (unhappy(x, y, v, u, pref)) {
            System.out.println(x + " and " + v + " are unhappy");
            unhappyFriends.add(x);
            unhappyFriends.add(v);
        }
        // y and u are unhappy
        if (unhappy(y, x, u, v, pref)) {
            System.out.println(y + " and " + u + " are unhappy");
            unhappyFriends.add(y);
            unhappyFriends.add(u);
        }
        // y and v are unhappy
        if (unhappy(y, x, v, u, pref)) {
            System.out.println(y + " and " + v + " are unhappy");
            unhappyFriends.add(y);
            unhappyFriends.add(v);
        }
    }


    boolean unhappy(int p11, int p12, int p21, int p22, int[][] prefList) {
        int[] p11_prefs = prefList[p11];
        int[] p21_prefs = prefList[p21];

        return getPrefOrder(p21, p11_prefs) < getPrefOrder(p12, p11_prefs) && getPrefOrder(p11,
                p21_prefs) < getPrefOrder(p22, p21_prefs);
    }

    int getPrefOrder(int ppl, int[] prefList) {
        for (int i = 0; i < prefList.length; i++) {
            if (prefList[i] == ppl) {
                return i;
            }
        }
        // never reaches here
        return -1;
    }

}
