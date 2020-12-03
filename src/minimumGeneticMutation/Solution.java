package minimumGeneticMutation;

import java.util.*;
import java.util.stream.Collectors;

//A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".
//
//        Suppose we need to investigate about a mutation (mutation from "start" to "end"), where ONE mutation is defined as ONE single character changed in the gene string.
//
//        For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
//
//        Also, there is a given gene "bank", which records all the valid gene mutations. A gene must be in the bank to make it a valid gene string.
//
//        Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations needed to mutate from "start" to "end". If there is no such a mutation, return -1.
//
//        Note:
//
//        Starting point is assumed to be valid, so it might not be included in the bank.
//        If multiple mutations are needed, all mutations during in the sequence must be valid.
//        You may assume start and end string is not the same.
//
//
//        Example 1:
//
//        start: "AACCGGTT"
//        end:   "AACCGGTA"
//        bank: ["AACCGGTA"]
//
//        return: 1
//
//
//        Example 2:
//
//        start: "AACCGGTT"
//        end:   "AAACGGTA"
//        bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
//
//        return: 2
//
//
//        Example 3:
//
//        start: "AAAAACCC"
//        end:   "AACCCCCC"
//        bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
//
//        return: 3
public class Solution {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> bankSet = Arrays.stream(bank).collect(Collectors.toSet());
        LinkedList<String> queue = new LinkedList<>();

        int mutations = 0;

        int nodesLeftAtCurrentLevel = 1;
        queue.addLast(start);
        while (!queue.isEmpty()) {
            String next = queue.removeFirst();
            nodesLeftAtCurrentLevel--;

            if (nodesLeftAtCurrentLevel == 0) {
                mutations++;
            }

            for (String mutation : findValidMutationsFromBank(next, bankSet)) {
                if (mutation.equals(end)) {
                    return mutations;
                } else {
                    bankSet.remove(mutation);
                    queue.addLast(mutation);
                }
            }

            if (nodesLeftAtCurrentLevel == 0) {
                nodesLeftAtCurrentLevel = queue.size();
            }
        }
        return -1;
    }

    List<String> findValidMutationsFromBank(String next, Set<String> bank) {
        List<String> ret = new ArrayList<>();
        for (String candidate : bank) {
            if (canMutate(next, candidate)) {
                ret.add(candidate);
            }
        }
        return ret;
    }

    boolean canMutate(String s1, String s2) {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
                if (diff > 1) {
                    return false;
                }
            }
        }
        return diff == 1;
    }
}
