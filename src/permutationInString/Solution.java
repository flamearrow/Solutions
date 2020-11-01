package permutationInString;
//Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
// In other words, one of the first string's permutations is the substring of the second string.
//
//
//
//        Example 1:
//
//        Input: s1 = "ab" s2 = "eidbaooo"
//        Output: True
//        Explanation: s2 contains one permutation of s1 ("ba").
//        Example 2:
//
//        Input:s1= "ab" s2 = "eidboaoo"
//        Output: False
//
//
//        Constraints:
//
//        The input strings only contain lower case letters.
//        The length of both given strings is in range [1, 10,000].

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().checkInclusion("adc", "dcda"));
    }

    public boolean checkInclusion(String s1, String s2) {
        int[] letters = new int[26];
        for (char c : s1.toCharArray()) {
            letters[c - 'a']++;
        }
        int start = 0;
        int next = 0;

        char[] s2s = s2.toCharArray();

        while (next < s2s.length) {
            char c = s2s[next];
            next++;
            letters[c - 'a']--;
            // find a hit in a chain, check if it's end
            if (letters[c - 'a'] == 0 && isAllZero(letters)) {
                return true;
            }
            // chain broken, move start to next letter that makes up the -1
            else if (letters[c - 'a'] < 0) {
                int indexToRecover = start;
                while (indexToRecover < next) {
                    char charToRecover = s2s[indexToRecover];
                    letters[charToRecover - 'a']++;
                    indexToRecover++;
                    if (charToRecover == c) {
                        break;
                    }
                }
                start = indexToRecover;
            }
            // otherwise chain continues
        }
        return false;
    }

    boolean isAllZero(int[] letters) {
        for (int i : letters) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}
