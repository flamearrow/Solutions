package fractionToRecurringDecimal;

import java.util.HashMap;
import java.util.Map;

//Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
//
//        If the fractional part is repeating, enclose the repeating part in parentheses.
//
//        For example,
//
//        Given numerator = 1, denominator = 2, return "0.5".
//        Given numerator = 2, denominator = 1, return "2".
//        Given numerator = 2, denominator = 3, return "0.(6)".
public class Solution {
    public static void main(String[] args) {
//        System.out.println(Math.abs(Integer.MIN_VALUE));
        System.out.println(new Solution().fractionToDecimal(0, 3));
    }

    public String fractionToDecimal(int numerator, int denominator) {
        boolean isMinus = (numerator > 0 && denominator < 0 || numerator < 0 && denominator > 0);
        long lNumerator = Math.abs((long) numerator);
        long lDenominator = Math.abs((long) denominator);
        StringBuilder intBuilder = new StringBuilder();
        if (lNumerator >= lDenominator) {
            intBuilder.append(lNumerator / lDenominator);
            lNumerator = lNumerator % lDenominator;
        }
        Map<Long, Integer> denoMap = new HashMap<>();
        StringBuilder deciBuilder = new StringBuilder();
        int index = 0;
        // now numerator < denominator
        while (lNumerator != 0) {
            lNumerator *= 10;
            if (denoMap.containsKey(lNumerator)) {
                deciBuilder.insert(denoMap.get(lNumerator), "(");
                deciBuilder.append(")");
                break;
            } else {
                if (lNumerator < lDenominator) {
                    deciBuilder.append("0");
                    denoMap.put(lNumerator, index);
                } else {
                    deciBuilder.append(lNumerator / lDenominator);
                    denoMap.put(lNumerator, index);
                    lNumerator = lNumerator % lDenominator;
                }
                index++;
            }
        }
        return (isMinus ? "-" : "") + (intBuilder.length() == 0 ? "0" : intBuilder.toString()) +
                (deciBuilder.length() > 0 ? ("." + deciBuilder.toString()) : "");
    }
}
