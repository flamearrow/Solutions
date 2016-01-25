package regularExpressionMatching;

//Implement regular expression matching with support for '.' and '*'.
//
//        '.' Matches any single character.
//        '*' Matches zero or more of the preceding element.
//
//        The matching should cover the entire input string (not partial).
//
//        The function prototype should be:
//        bool isMatch(const char *s, const char *p)
//
//        Some examples:
//        isMatch("aa","a") → false
//        isMatch("aa","aa") → true
//        isMatch("aaa","aa") → false
//        isMatch("aa", "a*") → true
//        isMatch("aa", ".*") → true
//        isMatch("ab", ".*") → true
//        isMatch("aab", "c*a*b") → true
public class Solution2 {
    // handle the case where the string is tailed with .*. and shit like that
    public static void main(String[] args) {
        String s = "ab";
        String p = ".*c";

        System.out.println(new Solution2().isMatch(s, p));
    }

    public boolean isMatch(String s, String p) {
        return doMatch(s, 0, p, 0);
    }

    boolean doMatch(String s, int sPtr, String p, int pPtr) {
        if (sPtr == s.length() && pPtr == p.length()) {
            return true;
        }

        // p is smaller than s, can't have any match
        if (pPtr == p.length()) {
            return false;
        }

        // if s is smaller than p, it's possible that p is tailed with .*.*.*, need to continue
        // matching
        if (pPtr < p.length() - 1 && p.charAt(pPtr + 1) == '*') {
            // no match
            if (doMatch(s, sPtr, p, pPtr + 2)) {
                return true;
            }
            if(sPtr >= s.length()) {
                return false;
            }
            // match 1 or more chars
            while (sPtr < s.length() && matches(s.charAt(sPtr), p.charAt(pPtr))) {
                if (doMatch(s, sPtr + 1, p, pPtr + 2)) {
                    return true;
                }
                sPtr++;
            }
            return false;
        } else {
            if (sPtr >= s.length()) {
                return false;
            }
            if (matches(s.charAt(sPtr), p.charAt(pPtr))) {
                return doMatch(s, sPtr + 1, p, pPtr + 1);
            } else {
                return false;
            }
        }
    }

    boolean matches(char a, char b) {
        return b == '.' || a == b;
    }

}
