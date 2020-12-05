package findPermutation;
//By now, you are given a secret signature consisting of character 'D' and 'I'. 'D' represents a decreasing relationship between two numbers, 'I' represents an increasing relationship between two numbers. And our secret signature was constructed by a special integer array, which contains uniquely all the different number from 1 to n (n is the length of the secret signature plus 1). For example, the secret signature "DI" can be constructed by array [2,1,3] or [3,1,2], but won't be constructed by array [3,2,4] or [2,1,3,4], which are both illegal constructing special string that can't represent the "DI" secret signature.
//
//        On the other hand, now your job is to find the lexicographically smallest permutation of [1, 2, ... n] could refer to the given secret signature in the input.
//
//        Example 1:
//        Input: "I"
//        Output: [1,2]
//        Explanation: [1,2] is the only legal initial spectial string can construct secret signature "I", where the number 1 and 2 construct an increasing relationship.
//        Example 2:
//        Input: "DI"
//        Output: [2,1,3]
//        Explanation: Both [2,1,3] and [3,1,2] can construct the secret signature "DI",
//        but since we want to find the one with the smallest lexicographical permutation, you need to output [2,1,3]
//        Note:
//
//        The input string will only contain the character 'D' and 'I'.
//        The length of input string is a positive integer and will not exceed 10,000

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Solution {
    public int[] findPermutation(String s) {
        LinkedList<Integer> result = new LinkedList<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= s.length() + 1; i++) {
            numbers.add(i);
        }
        findSmallestPermutation(s, numbers, result);
        int[] ret = new int[s.length() + 1];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = result.get(i);
        }
        return ret;
    }

    boolean findSmallestPermutation(String signature, ArrayList<Integer> numbers,
                                    LinkedList<Integer> result) {
        if (numbers.size() == 0) {
            return true;
        } else {
            if (result.size() == 0) {
                for (int i = 0; i < numbers.size(); i++) {
                    result.addLast(numbers.get(i));
                    numbers.remove(i);
                    if (findSmallestPermutation(signature, numbers, result)) {
                        return true;
                    }
                    numbers.add(i, result.removeLast());
                }
            } else {
                int lastNumber = result.getLast();
                if (signature.charAt(0) == 'D') {
                    for (int i = 0; i < numbers.size(); i++) {
                        if (numbers.get(i) < lastNumber) {
                            result.addLast(numbers.get(i));
                            numbers.remove(i);
                            if (findSmallestPermutation(signature.substring(1), numbers, result)) {
                                return true;
                            }
                            numbers.add(i, result.removeLast());
                        }
                    }
                } else if (signature.charAt(0) == 'I') {
                    for (int i = 0; i < numbers.size(); i++) {
                        if (numbers.get(i) > lastNumber) {
                            result.addLast(numbers.get(i));
                            numbers.remove(i);
                            if (findSmallestPermutation(signature.substring(1), numbers, result)) {
                                return true;
                            }
                            numbers.add(i, result.removeLast());
                        }
                    }
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        Arrays.stream(new Solution().findPermutation("DI")).forEach(System.out::println);
    }
}
