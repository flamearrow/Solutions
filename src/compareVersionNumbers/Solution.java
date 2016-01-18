package compareVersionNumbers;

import java.util.StringTokenizer;

//Compare two version numbers version1 and version2.
//        If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.
//
//        You may assume that the version strings are non-empty and contain only digits and the . character.
//        The . character does not represent a decimal point and is used to separate number sequences.
//        For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.
//
//        Here is an example of version numbers ordering:
//
//        0.1 < 1.1 < 1.2 < 13.37
public class Solution {
    public static void main(String[] args) {
        String s1 = "3.23";
        String s2 = "3.22";
//        System.out.println(s1.compareTo(s2));
        System.out.println(new Solution().compareVersion(s1, s2));
    }

    public int compareVersion(String version1, String version2) {
        String[] versions1;
        String[] versions2;
        if (version1.contains(".")) {
            versions1 = version1.split("\\.");
        } else {
            versions1 = new String[1];
            versions1[0] = version1;
        }
        if (version2.contains(".")) {
            versions2 = version2.split("\\.");
        } else {
            versions2 = new String[1];
            versions2[0] = version2;
        }
        int curIndex = 0;
        while (curIndex < versions1.length && curIndex < versions2.length) {
            int v1 = Integer.parseInt(versions1[curIndex]);
            int v2 = Integer.parseInt(versions2[curIndex]);
            if (v1 > v2) {
                return 1;
            } else if (v1 < v2) {
                return -1;
            } else {
                curIndex++;
            }
        }
        return versions1.length > versions2.length ? 1 : -1;
    }
}
