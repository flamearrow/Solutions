package longestPalindromicSubstring;

import java.util.LinkedList;

//Given a string S, find the longest palindromic substring in S. 
//You may assume that the maximum length of S is 1000, 
//and there exists one unique longest palindromic substring.
public class Solution {

    public String longestPalindrome(String s) {
        // use a edit distance like solution
        int len = s.length();
        int[][] dp = new int[len][len];
        String reverseS = new StringBuilder(s).reverse().toString();

        int maxLen = 0;
        String ret = "";
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = s.charAt(i) == reverseS.charAt(j) ? 1 : 0;
                } else {
                    dp[i][j] = s.charAt(i) == reverseS.charAt(j) ? dp[i - 1][j - 1] + 1 : 0;
                }
                if (maxLen < dp[i][j]) {
                    maxLen = dp[i][j];
                    ret = s.substring(i - maxLen + 1, i + 1);
                }
            }
        }
        return ret;
    }

    public String longestPalindromeExpand2(String s) {
        if (s == null || s.length() == 0)
            return "";
        int symPoint = 2 * s.length() - 1;
        int longestLen = 1;
        String ret = s.substring(0, 1);
        for (int i = 0; i < symPoint; i++) {
            int len = 0, left = 0, right = 0;
            // symPoint is a character
            if (i % 2 == 0) {
                len = 1;
                left = i / 2 - 1;
                right = i / 2 + 1;
            }
            // symPoint is a split
            else {
                len = 0;
                left = i / 2;
                right = i / 2 + 1;
            }
            while (left >= 0 && right < s.length()
                    && s.charAt(left) == s.charAt(right)) {
                len += 2;
                left--;
                right++;
            }
            if (len > longestLen) {
                ret = s.substring(left + 1, left + 1 + len);
                longestLen = len;
            }
        }
        return ret;
    }

    // select all possible symmetric point and try expanding left and right
    public String longestPalindromeExpand(String s) {
        int len = s.length();
        if (len == 0)
            return "";
        int max = 1;
        String ret = s.charAt(0) + "";
        for (int i = 0; i < len; i++) {
            // symmetric point at i
            int left = i - 1, right = i + 1;
            int curLen = 1;
            while (left >= 0 && right < len
                    && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                curLen += 2;
            }
            if (curLen > max) {
                max = curLen;
                ret = s.substring(left + 1, right);
            }

            // symmetric point at between i and i+1
            left = i;
            right = i + 1;
            curLen = 0;
            while (left >= 0 && right < len
                    && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                curLen += 2;
            }
            if (curLen > max) {
                max = curLen;
                ret = s.substring(left + 1, right);
            }
        }
        return ret;
    }

    // or use a edit distance - like DP
    public String longestPalindromeDP(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        String sR = sb.toString();
        int len = s.length();
        // dp[i][j] is the length of matching substring of s[0-i] and sR[0-j]
        int[][] dp = new int[len][len];
        // since one char is definitely a palindrome,
        // we just start from there
        int maxLen = 1;
        String ret = "" + s.charAt(0);
        for (int i = 0; i < len; i++) {
            dp[i][0] = s.charAt(i) == sR.charAt(0) ? 1 : 0;
            dp[0][i] = sR.charAt(i) == s.charAt(0) ? 1 : 0;
        }

        for (int i = 1; i < len; i++) {
            for (int j = 1; j < len; j++) {
                // the symmetric point is j - dp[i][j] + 1 == len - 1 - i
                dp[i][j] = s.charAt(i) == sR.charAt(j) ? dp[i - 1][j - 1] + 1
                        : 0;
                if ((j - dp[i][j] + 1 == len - 1 - i) && dp[i][j] > maxLen) {
                    ret = sR.substring(j - dp[i][j] + 1, j + 1);
                    maxLen = dp[i][j];
                }
            }
        }
        return ret;
    }

    // first build a suffix tree of s, then revert s as sR
    // then starting from each index of sR, probe as deep as possible
    //  when probing, keep track of length and starting index
    //   starting index + length would be the END of a reversed substring
    //   END = length - original index
    //   which means the probe ends at the starting of a same substring
    //   then we find a candidate
    // O(n^2)
    public String longestPalindromeSuffixTrie(String s) {
        String lS = s.toLowerCase();
        TrieNode suffixTreeRoot = TrieNode.buildSuffixTree(lS);
        StringBuilder sb = new StringBuilder();
        for (int i = lS.length() - 1; i >= 0; i--) {
            sb.append(lS.charAt(i));
        }
        String sR = sb.toString();
        String ret = null;
        int longest = -1;
        // now search sR in the trie
        for (int i = 0; i < sR.length(); i++) {
            TrieNode cur = suffixTreeRoot;
            String sub = sR.substring(i);
            int len = 0;
            for (char c : sub.toCharArray()) {
                if (cur.branch[c - 'a'] != null) {
                    cur = cur.branch[c - 'a'];
                    len++;
                }
                // otherwise we reach the deepest, break
                else
                    break;
            }
            // we either break here or we complete searching
            // check if the end is the start of the chain
            // then start from the next substring
            // the string we found in rS is rS[i, endIndex]
            int endIndex = i + len - 1;
            int startIndex = lS.length() - 1 - endIndex;
            if (cur.indices.contains(startIndex)) {
                if (len > longest) {
                    longest = len;
                    ret = sR.substring(i, endIndex + 1);
                }
            }
        }

        return ret;
    }

    public static void main(String[] args) {
        String s = "abcde";
        System.out.println(new Solution().longestPalindrome(s));
    }
}

class TrieNode {
    TrieNode[] branch = new TrieNode[26];
    LinkedList<Integer> indices;

    public TrieNode() {
        indices = new LinkedList<Integer>();
    }

    static TrieNode buildSuffixTree(String s) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < s.length(); i++) {
            TrieNode cur = root;
            String sub = s.substring(i);
            for (char c : sub.toCharArray()) {
                TrieNode next = cur.branch[c - 'a'];
                if (next == null) {
                    cur.branch[c - 'a'] = new TrieNode();
                    next = cur.branch[c - 'a'];
                }
                next.indices.add(i);
                cur = next;
            }
        }
        return root;
    }
}
