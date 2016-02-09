package passwordBox;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Generate a string stream that enumerats all combinations of 4 digits
 * Make it shortest
 */
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.generateStrings();
        System.out.println(s.length());
        Map<String, Integer> dup = new HashMap<>();
        for (int i = 0; i <= 9999; i++) {
            String ss = solution.getPost(i, 4);
            if (s.contains(ss)) {
                if (dup.containsKey(ss)) {
                    dup.put(ss, dup.get(ss) + 1);
                    System.out.println("found dup: " + ss + " times: " + dup.get(ss));
                } else {
                    dup.put(ss, 1);
                }
            }
        }
        System.out.println(dup);
    }

    // this is not the shortest, in the case where xxx[0-9] are all added
    //   xxx9 would be passed into the next while loop, we added an additional 9
    // and start with xx9[0-9]
    private String generateStrings() {
        Set<Integer> visited = new HashSet<>();
        StringBuilder sb = new StringBuilder("999");
        int num = 9999;
        int count = 0;
        while (visited.size() < 10000) {
            sb.append(num % 10);
            visited.add(num);
            num *= 10;
            num %= 10000;
            int add = 0;
            // find the one that's not added yet
            while (add < 9) {
                if (!visited.contains(num + add)) {
                    break;
                }
                add++;
            }
            num += add;
            count++;
        }
        System.out.println(count);
        return sb.toString();
    }

    private String generateString(int passwordLength) {
        Map<String, Set<Integer>> available = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < passwordLength; i++) {
            sb.append("0");
        }
        available.put(sb.substring(sb.length() - passwordLength + 1), newSet());
        String nextChars = generateNextChars(sb.substring(sb.length() - passwordLength + 1),
                available);
        while (nextChars != null) {
            sb.append(nextChars);
            nextChars = generateNextChars(sb.substring(sb.length() - passwordLength + 1), available);
        }
        return sb.toString();
    }

    String generateNextChars(String key, Map<String, Set<Integer>> available) {
        if (available.containsKey(key)) {
            Set<Integer> availableDigits = available.get(key);
            if (availableDigits.size() > 0) {
                int newDigit = availableDigits.iterator().next();
                availableDigits.remove(newDigit);
                return "" + newDigit;
            } else {
                int charsTaken = 1;
                while (charsTaken <= key.length()) {
                    String newKeyPre = key.substring(charsTaken);
                    int newHits = (int) Math.pow(10, charsTaken);
                    for (int i = 0; i < newHits; i++) {
                        String newKeyPost = getPost(i, charsTaken);
                        String newKey = newKeyPre + newKeyPost;
                        if (available.containsKey(newKey)) {
                            Set<Integer> nextAvailableDigits = available.get(newKey);
                            if (nextAvailableDigits.size() > 0) {
                                int newDigit = nextAvailableDigits.iterator().next();
                                nextAvailableDigits.remove(newDigit);
                                return newKeyPost;
                            }
                        } else {
                            available.put(newKey, newSet());
                            return newKeyPost;
                        }
                    }
                    charsTaken++;
                }
                return null;
            }
        } else {
            available.put(key, newSet());
            return "0";
        }
    }

    String getPost(int i, int length) {
        String ret = "" + i;
        length -= ret.length();
        while (--length > 0) {
            ret = "0" + ret;
        }
        return ret;
    }

    Set<Integer> newSet() {
        Set<Integer> ret = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            ret.add(i);
        }
        return ret;
    }
}
