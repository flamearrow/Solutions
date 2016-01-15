package shortestPalindrome;

//Given a string S, you are allowed to convert it to a palindrome by
// adding characters in front of it. Find and return the shortest palindrome you can
// find by performing this transformation.
//
//        For example:
//
//        Given "aacecaaa", return "aaacecaaa".
//
//        Given "abcd", return "dcbabcd".
public class Solution {
    public static void main(String[] args) {
        String s = "aba";
        String shit = new Solution().shortestPalindrome2(s);
        System.out.println(shit);
    }

    // try to use two pointers to find the longest palindrome from start of the string
    public String shortestPalindrome2(String s) {
        int left = 0, right = s.length() - 1;
        int rightStart = right;
        // from left to rightStart is a palindrome
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                left = 0;
                // match from last but one/last but two...
                rightStart--;
                right = rightStart;
            }
        }
        // note the largest length substring can take is the length of the string itself -
        // returns nothing
        return new StringBuilder(s.substring(rightStart + 1)).reverse() + s;
    }

    public String shortestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        // find center from left, try to probe right, a valid center would be
        //  an index i such that 0 to i is the same with i to 2i
        //  note center shouldn't pass len/2

        int[] len = new int[s.length() % 2 == 0 ? s.length() / 2 : s.length() / 2 + 1];
        int minLen = Integer.MAX_VALUE;
        int minLenIndex = 0;
        boolean center = false;
        for (int i = 0; i < len.length; i++) {
            // use i as left boundary
            if (mathesB(s, i)) {
                len[i] = (s.length() - i) * 2;
                if (len[i] < minLen) {
                    minLen = len[i];
                    minLenIndex = i;
                }
                center = false;
            }
            // use i as center
            else if (matches(s, i)) {
                len[i] = 1 + (s.length() - i) * 2;
                if (len[i] < minLen) {
                    minLen = len[i];
                    minLenIndex = i;
                }
                center = true;
            } else {
                len[i] = Integer.MAX_VALUE;
            }
        }
        return buildFrom(s, minLenIndex, center);
    }

    String buildFrom(String s, int index, boolean isCenter) {
        String center = isCenter ? "" + s.charAt(index) : "";
        String right = s.substring(index + 1);
        String left = new StringBuilder(right).reverse().toString();
        return left + center + right;
    }

    boolean mathesB(String s, int rightBoundary) {
        int leftBoundary = rightBoundary + 1;
        while (rightBoundary >= 0) {
            if (leftBoundary > s.length() - 1) {
                return false;
            }
            if (s.charAt(leftBoundary) == s.charAt(rightBoundary)) {
                rightBoundary--;
                leftBoundary++;
            } else {
                return false;
            }
        }
        return true;
    }

    boolean matches(String s, int center) {
        int rightBoundary = center - 1;
        int leftBoundary = center + 1;
        while (rightBoundary >= 0) {
            if (leftBoundary > s.length() - 1) {
                return false;
            }
            if (s.charAt(leftBoundary) == s.charAt(rightBoundary)) {
                leftBoundary++;
                rightBoundary--;
            } else {
                return false;
            }
        }
        return true;

    }
}
