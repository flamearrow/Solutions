package repeatedDNASeq;

import java.util.*;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.IntStream;

//All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T,
// for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated
// sequences within the DNA.
//
//Write a function to find all the 10-letter-long sequences (substrings) that occur more than
// once in a DNA molecule.
//
//For example,
//
//Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
//
//Return:
//["AAAAACCCCC", "CCCCCAAAAA"].
public class Solution {
    public static void main(String[] args) {
        String s = "AAAAAAAAAAA";
        new Solution().findRepeatedDnaSequences(s).forEach(System.out::println);
//        IntStream.of(1, 2, 2).forEach(System.out::println);
//
//        IntPredicate isEven = shit -> shit % 2 == 0;
//        IntStream.range(0, 100).filter(isEven).forEach(System.out::println);
    }


    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> ret = new HashSet<>();
        if (s.length() < 11) {

            return new LinkedList<>();
        }
        //do it in O(n), can use a int to store all bits of each 10 length substrings too
        Set<String> bits = new HashSet<>();
        String cur = s.substring(0, 10);
        bits.add(cur);
        for (int i = 11; i <= s.length(); i++) {
            // can use a bit array to chop head and squeeze tail to make it faster
            String newStr = s.substring(i - 10, i);
            if (bits.contains(newStr)) {
                ret.add(newStr);
            }
            bits.add(newStr);
        }
        List<String> retList = new LinkedList<>();
        ret.forEach(retList::add);
        return retList;
    }
}
