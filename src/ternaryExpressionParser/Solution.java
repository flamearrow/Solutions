package ternaryExpressionParser;
//Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression. You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F (T and F represent True and False respectively).
//
//        Note:
//
//        The length of the given string is ≤ 10000.
//        Each number will contain only one digit.
//        The conditional expressions group right-to-left (as usual in most languages).
//        The condition will always be either T or F. That is, the condition will never be a digit.
//        The result of the expression will always evaluate to either a digit 0-9, T or F.
//        Example 1:
//
//        Input: "T?2:3"
//
//        Output: "2"
//
//        Explanation: If true, then result is 2; otherwise result is 3.
//        Example 2:
//
//        Input: "F?1:T?4:5"
//
//        Output: "4"
//
//        Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
//
//        "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
//        -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
//        -> "4"                                    -> "4"
//        Example 3:
//
//        Input: "T?T?F:5:3"
//
//        Output: "F"
//
//        Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
//
//        "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
//        -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
//        -> "F"                                    -> "F"

public class Solution {
    public String parseTernary(String expression) {
        while (expression.length() > 1) {
            // find the right most ?:pair
            for (int i = expression.length() - 1; i >= 0; i--) {
                if (expression.charAt(i) == ':' && expression.charAt(i - 2) == '?') {
                    // i-3, i+1 is a 5 char expression
                    expression =
                            expression.substring(0, i - 3) + calculate(expression.substring(i - 3,
                                    i + 2)) + expression.substring(i + 2);
                    break;
                }
            }
        }
        return expression;
    }

    char calculate(String fiveCharString) {
        if (fiveCharString.charAt(0) == 'T') {
            return fiveCharString.charAt(2);
        } else {
            return fiveCharString.charAt(4);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().parseTernary("T?T?F:5:3"));
    }
}
