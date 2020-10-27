package scoreOfParenthesis;
//Given a balanced parentheses string S, compute the score of the string based on the following rule:
//
//        () has score 1
//        AB has score A + B, where A and B are balanced parentheses strings.
//        (A) has score 2 * A, where A is a balanced parentheses string.
//
//
//        Example 1:
//
//        Input: "()"
//        Output: 1
//        Example 2:
//
//        Input: "(())"
//        Output: 2
//        Example 3:
//
//        Input: "()()"
//        Output: 2
//        Example 4:
//
//        Input: "(()(()))"
//        Output: 6

public class Solution {
    public int scoreOfParentheses(String S) {
        return getScoreFrom(S, 0, S.length() - 1);
    }

    int getScoreFrom(String s, int left, int right) {
        // crossed, the previous call is from an adjacent parenthesis
        if (left >= right) {
            return 0;
        }
        int start = left, end;
        int score = 0;
        while (start < right) {
            // find right, get a (xxx) group
            // end is inclusive
            end = nextClosingRight(s, start);
            int subGroupScore = getScoreFrom(s, start + 1, end - 1);
            score += subGroupScore == 0 ? 1 : 2 * subGroupScore;
            start = end + 1;
        }
        return score;
    }

    int nextClosingRight(String s, int left) {
        int pendingLeft = 1;
        for (int right = left + 1; right < s.length(); right++) {
            if (s.charAt(right) == '(') {
                pendingLeft++;
            } else if (s.charAt(right) == ')') {
                pendingLeft--;
                if (pendingLeft == 0) {
                    return right;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "(()(()))";
        System.out.println(new Solution().scoreOfParentheses(s));
    }
}
