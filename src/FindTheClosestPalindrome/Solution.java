package FindTheClosestPalindrome;
//Given an integer n, find the closest integer (not including itself), which is a palindrome.
//
//        The 'closest' is defined as absolute difference minimized between two integers.
//
//        Example 1:
//        Input: "123"
//        Output: "121"
//        Note:
//        The input n is a positive integer represented by string, whose length will not exceed 18.
//        If there is a tie, return the smaller one as answer.

public class Solution {
    // find 3 numbers:
    // 1st: mirror right to left
    // 2nd: cut in two halves, increase the left half by 1, mirror to right
    //    e.g: 999 -> cut to 99, 9  99+1=100 -> got 1009 -> mirror to right -> 1001
    //    e.g 14321 > cut to 143 and 21, mirror to 14421
    //    e.g 1499 0> cut to 14 and 99, mirror to 1441
    // 3rd: cut in two halves, decrease the left half by 1, mirror to right
    //    e.g 1000 -> cut to 10 00, decrease to 900 -> mirror to 999
    //    e.g 999 -> cut to 99, 9, decase to 98 -> mirror to 999
    //    e.g 14321 -> mirror to 14241
    // return whichever is closest
    public String nearestPalindromic(String n) {
        long val = Long.parseLong(n);
        long mirrored = mirror(val); // first
        int order = (int) Math.pow(10, n.length() / 2);
        long mirroredLarger = mirror(val / order * order + order);
        long mirroredLesser = mirror(val / order * order - 1);
        // makesure larger and lesser is closer to val
        if (mirrored > val) {
            mirroredLarger = Math.min(mirroredLarger, mirrored);
        } else if (mirrored < val) {
            mirroredLesser = Math.max(mirroredLesser, mirrored);
        }

        return val - mirroredLesser <= mirroredLarger - val ? "" + mirroredLesser :
                "" + mirroredLarger;
    }

    long mirror(long n) {
        char[] arr = ("" + n).toCharArray();
        int left = 0, right = arr.length - 1;
        while (left < right) {
            char c = arr[right];
            arr[right] = arr[left];
            left++;
            right--;
        }
        return Long.parseLong(new String(arr));
    }

    public static void main(String[] args) {
        System.out.println(new Solution().nearestPalindromic("10"));
    }
}
