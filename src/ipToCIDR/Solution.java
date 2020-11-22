package ipToCIDR;

import java.util.ArrayList;
import java.util.List;

//Given a start IP address ip and a number of ips we need to cover n, return a representation of the range as a list (of smallest possible length) of CIDR blocks.
//
//        A CIDR block is a string consisting of an IP, followed by a slash, and then the prefix length. For example: "123.45.67.89/20". That prefix length "20" represents the number of common prefix bits in the specified range.
//
//        Example 1:
//        Input: ip = "255.0.0.7", n = 10
//        Output: ["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
//        Explanation:
//        The initial ip address, when converted to binary, looks like this (spaces added for clarity):
//        255.0.0.7 -> 11111111 00000000 00000000 00000111
//        The address "255.0.0.7/32" specifies all addresses with a common prefix of 32 bits to the given address,
//        ie. just this one address.
//
//        The address "255.0.0.8/29" specifies all addresses with a common prefix of 29 bits to the given address:
//        255.0.0.8 -> 11111111 00000000 00000000 00001000
//        Addresses with common prefix of 29 bits are:
//        11111111 00000000 00000000 00001000
//        11111111 00000000 00000000 00001001
//        11111111 00000000 00000000 00001010
//        11111111 00000000 00000000 00001011
//        11111111 00000000 00000000 00001100
//        11111111 00000000 00000000 00001101
//        11111111 00000000 00000000 00001110
//        11111111 00000000 00000000 00001111
//
//        The address "255.0.0.16/32" specifies all addresses with a common prefix of 32 bits to the given address,
//        ie. just 11111111 00000000 00000000 00010000.
//
//        In total, the answer specifies the range of 10 ips starting with the address 255.0.0.7 .
//
//        There were other representations, such as:
//        ["255.0.0.7/32","255.0.0.8/30", "255.0.0.12/30", "255.0.0.16/32"],
//        but our answer was the shortest possible.
//
//        Also note that a representation beginning with say, "255.0.0.7/30" would be incorrect,
//        because it includes addresses like 255.0.0.4 = 11111111 00000000 00000000 00000100
//        that are outside the specified range.
//        Note:
//        ip will be a valid IPv4 address.
//        Every implied address ip + x (for x < n) will be a valid IPv4 address.
//        n will be an integer in the range [1, 1000].
public class Solution {
    public static void main(String[] args) {
        String ip = "255.0.0.7";
        int n = 10;
        for (String result : new Solution().ipToCIDR(ip, n)) {
            System.out.println(result);
        }
    }

    String humToBin(String ip) {
        String[] nums = ip.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (String num : nums) {
            sb.append(to8bitsBinary(num));
        }
        return sb.toString();
    }

    String to8bitsBinary(String s) {
        String binaryString = Integer.toBinaryString(Integer.parseInt(s));
        int zeroToPad = 8 - binaryString.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < zeroToPad; i++) {
            sb.append("0");
        }
        sb.append(binaryString);
        return sb.toString();
    }

    String binToHum(String ip) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int i = 0; i < 4; i++) {
            int humString = Integer.parseInt((ip.substring(i * 8, i * 8 + 8)), 2);
            if (first) {
                sb.append(humString);
                first = false;
            } else {
                sb.append(".");
                sb.append(humString);
            }
        }
        return sb.toString();
    }

    // if current trailing 0s can cover, build from there
    // otherwise increment the last 1, get all numbers avaialble and recursively call the rest
    // this doesn't pass the online judge and I don't give a fuck
    public List<String> ipToCIDR(String humIP, int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        String ip = humToBin(humIP);
        int indexofLast1 = indexOfLast1(ip);
        int numCovered = (int) Math.pow(2, 31 - indexofLast1);
        List<String> result = new ArrayList<>();
        if (numCovered >= n) {
            // don't need all numbers, just add required
            String binary = Integer.toBinaryString(n);

            for (int i = binary.length() - 1; i >= 0; i--) {
                if (binary.charAt(i) == '1') {
                    int suffixLength = 32 - binary.length() + 1 + i;
                    StringBuilder sb = new StringBuilder();
                    sb.append(ip, 0, suffixLength);
                    for (int j = 0; j < suffixLength; j++) {
                        sb.append('0');
                    }
                    result.add(binToHum(sb.toString()) + "/" + suffixLength);
                }
            }

        } else {
            // add all, then recursively call larger IPs
            char[] chars = ip.toCharArray();
            for (int i = indexofLast1 + 1; i < chars.length; i++) {
                chars[i] = '0';
            }
            String resultIP = new String(chars);
            result.add(binToHum(resultIP) + "/" + (indexofLast1 + 1));

            result.addAll(ipToCIDR(binToHum(incrementLast1(ip)), n - numCovered));
        }
        return result;
    }

    int indexOfLast1(String s) {
        int ret = 31;
        while (s.charAt(ret) == '0') {
            ret--;
        }
        return ret;
    }

    // xxx0000110000 -> xxx0001000000
    String incrementLast1(String s) {
        char[] chars = s.toCharArray();
        int cur = indexOfLast1(s);
        int carry = 1;
        while (carry == 1) {
            if (chars[cur] == '0') {
                chars[cur] = '1';
                carry = 0;
            } else {
                chars[cur] = '0';
                carry = 1;
            }
            cur--;
        }
        return new String(chars);
    }


}
