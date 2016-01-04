package integerToEnglishWords;

import java.util.LinkedList;

//Convert a non-negative integer to its english words representation.
// Given input is guaranteed to be less than 2^31 - 1.
//
//        For example,
//        123 -> "One Hundred Twenty Three"
//        12345 -> "Twelve Thousand Three Hundred Forty Five"
//        1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().numberToWords(50868));
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        String[] units = {"", "Thousand", "Million", "Billion"};
        String[] numbers = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
                "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        String numStr = "" + num;
        int unitsPtr = 0;
        LinkedList<String> rstList = new LinkedList<>();
        while (numStr.length() > 0) {
            int tail;
            if (numStr.length() > 3) {
                tail = Integer.parseInt(numStr.substring(numStr.length() - 3));
                numStr = numStr.substring(0, numStr.length() - 3);
            } else {
                tail = Integer.parseInt(numStr);
                numStr = "";
            }
            if (tail == 0) {
                unitsPtr++;
                continue;
            }
            int indexToAdd = 0;
            if (tail > 99) {
                rstList.add(indexToAdd++, numbers[tail / 100]);
                rstList.add(indexToAdd++, "Hundred");
            }
            tail %= 100;
            if (tail >= 20) {
                rstList.add(indexToAdd++, tens[tail / 10]);
                rstList.add(indexToAdd++, numbers[tail % 10]);
            } else if (tail > 0) {
                rstList.add(indexToAdd++, numbers[tail]);
            }
            rstList.add(indexToAdd++, units[unitsPtr]);
            unitsPtr++;
        }
        StringBuilder sb = new StringBuilder();
        for (String s : rstList) {
            if (s.length() > 0) {
                sb.append(" ");
                sb.append(s);
            }

        }
        return sb.toString().trim();
    }
}
