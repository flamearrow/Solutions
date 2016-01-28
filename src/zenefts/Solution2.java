package zenefts;

import java.util.*;

/**
 * Created by flamearrow on 1/24/16.
 */
public class Solution2 {
    public static void main(String[] args) {
        String s = "bcda";
//        new Solution2().toSortedLexOrder(chars).forEach(System.out::println);
        System.out.println(new Solution2().lexicographicRange2(s));
    }

    // calculate the range by finding how many strings are smaller than current string
    //  say string is BCDA
    //   AXXX is smaller than it, making them 1*3!
    //   BBXX, BAXX are smaller than it, making them 2*2!
    //   BCAX is smaller than it, making it 1*1!
    int lexicographicRange2(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        Map<Character, Integer> rangeMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            rangeMap.put(chars[i], i);
        }
        int ret = 1;
        for (int i = 0; i < s.length(); i++) {
            // A*B!
            int A = rangeMap.get(s.charAt(i));
            for (int j = 0; j < i; j++) {
                if (rangeMap.get(s.charAt(j)) < rangeMap.get(s.charAt(i))) {
                    A--;
                }
            }
            int B = s.length() - i - 1;
            ret += A * facorial(B);
        }
        return ret;
    }

    int facorial(int i) {
        int ret = 1;
        while (i > 0) {
            ret *= i;
            i--;
        }
        return ret;
    }

    //
//    Given a string, find its rank among all its permutations sorted lexicographically.
// For example, rank of “abc” is 1, rank of “acb” is 2, and rank of “cba” is 6.
//
//    For simplicity, let us assume that the string does not contain any duplicated characters.
    int lexicographicRange(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        int rank = 1;
        for (String ss : toSortedLexOrder(chars)) {
            if (ss.equals(s)) {
                return rank;
            }
            rank++;
        }
        return -1;
    }

    List<String> toSortedLexOrder(char[] chars) {
        ArrayList<Character> charsList = new ArrayList<>();
        for (char c : chars) {
            charsList.add(c);
        }
        List<String> ret = new LinkedList<>();
        doShit(ret, charsList, "");
        return ret;
    }

    void doShit(List<String> ret, ArrayList<Character> charsList, String cur) {
        if (charsList.size() == 0) {
            ret.add(cur);
            return;
        }
        for (int i = 0; i < charsList.size(); i++) {
            char toAdd = charsList.remove(i);
            doShit(ret, charsList, cur + toAdd);
            charsList.add(i, toAdd);
        }
    }
}
