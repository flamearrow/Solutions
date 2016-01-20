package longestPalindromicSubstring;

/**
 * use trie
 */
public class Solution2 {
    public static void main(String[] args) {
        String s = "aabccbaaaaaaaaaaaaa";
        System.out.println(new Solution2().longestPalindrome(s));

    }


    // tricky part: we need to make sure the matched sub string is the SAME substring
    //  e.g abcmlgbcba, reverse abcbglmcba
    //   abc and cba would match, but they are not the SAME substring
    //   return it fucks up
    //  e.g abccbamlgb, reverse bglmabccba
    //   abccba would match and they are the same substring
    public String longestPalindrome(String s) {
        s = s.toLowerCase();
        //build suffix tree
        //match deepest branch with each substring of reverseS starting at each index
        TrieNode2 sTreeRoot = buildSuffixTree(s);
        String reverseS = new StringBuilder(s).reverse().toString();
        int maxLen = 0;
        String ret = "";
        for (int i = 0; i < reverseS.length(); i++) {
            String sub = reverseS.substring(i);
            int matchedLen = matchDeepest(sTreeRoot, sub);
            if (maxLen < matchedLen) {
                String matched = sub.substring(0, matchedLen);
                int startIndexOfOriginalString = s.length() - (i + matchedLen);
                if (startIndexOfOriginalString < 0) {
                    continue;
                }
                if (s.substring(startIndexOfOriginalString, startIndexOfOriginalString +
                        matchedLen).equals(matched)) {
                    maxLen = matchedLen;
                    ret = sub.substring(0, matchedLen);
                }

            }
        }
        return ret;
    }

    int matchDeepest(TrieNode2 root, String s) {
        TrieNode2 cur = root;
        int ret = 0;
        for (char c : s.toCharArray()) {
            if (cur.hasChild(c)) {
                ret++;
                cur = cur.getChild(c);
            } else {
                break;
            }
        }
        return ret;
    }

    public TrieNode2 buildSuffixTree(String s) {
        TrieNode2 root = new TrieNode2();
        for (int i = s.length() - 1; i >= 0; i--) {
            String suffix = s.substring(i);
            TrieNode2 cur = root;
            for (int j = 0; j < suffix.length(); j++) {
                cur = cur.addChildren(suffix.charAt(j), j == suffix.length() - 1);
            }
        }
        return root;
    }
}

class TrieNode2 {
    TrieNode2[] childern;
    public boolean terminate;

    public TrieNode2() {
        this(false);
    }

    public TrieNode2(boolean argTerminate) {
        childern = new TrieNode2[26];
        terminate = argTerminate;
    }

    public TrieNode2 addChildren(char c, boolean argTerminate) {
        if (childern[c - 'a'] == null) {
            childern[c - 'a'] = new TrieNode2(argTerminate);
        } else {
            childern[c - 'a'].terminate |= argTerminate;
        }
        return childern[c - 'a'];
    }

    public boolean hasChild(char c) {
        return childern[c - 'a'] != null;
    }

    public TrieNode2 getChild(char c) {
        return childern[c - 'a'];
    }

}