package alienDictionary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//There is a new alien language which uses the latin alphabet.
// However, the order among letters are unknown to you.
// You receive a list of words from the dictionary, where words are sorted
// lexicographically by the rules of this new language. Derive the order of letters in this language.
//
//For example,
//Given the following words in dictionary,
//
//[
//  "wrt",
//  "wrf",
//  "er",
//  "ett",
//  "rftt"
//]
//The correct order is: "wertf".
//
//Note:
//You may assume all letters are in lowercase.
//If the order is invalid, return an empty string.
//There may be multiple valid order of letters, return any one of them is fine.
public class Solution2 {
    // The idea is to recursively look at a column of the strings, the strings on upper comes first
    // Then divide strings in to groups with same first letter and recursive call sub groups to
    // get newer and smaller columns and orders
    //
    // finally create a map<c, set<c>> to map c's predecessors
    //
    // then use topologicla sort to build the order
    //  always take the c without any predecessors and remove it from other prececessors
    //
    // trick: trying to add kep to the map on the fly is troublesome, just iterate though all
    // words and create the map first
    public static void main(String[] args) {
        Solution2 s = new Solution2();
        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.println(s.alienOrder(words));
    }

    // first build a map<char, set<char>> representing which letters are before a letter
    // then do topological sort
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> predecessorMap = buildMap(words);
        return doTopologicalSort(predecessorMap);
    }

    Map<Character, Set<Character>> buildMap(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<>();
        for (String s : words) {
            for (char c : s.toCharArray()) {
                if (!map.containsKey(c)) {
                    map.put(c, new HashSet<>());
                }
            }
        }
        doProb(map, words, 0, 0, words.length);
        return map;
    }

    void doProb(Map<Character, Set<Character>> map, String[] words, int column, int from, int to) {
        char preChar = 0;
        int preFrom = from;
        // when to==from+1, it means there's only one word in this recurse, there's no info we
        // can retrieve, just terminate
        for (int row = from; row < to; row++) {
            String curWord = words[row];
            if (curWord.length() > column) {
                char curChar = curWord.charAt(column);
                // the is the first char of a running
                if (preChar == 0) {
                    preChar = curChar;
                }
                // the is a start of a new running, we find a pair and a subset of words to recurse
                else if (preChar != curChar) {
                    // preChar is predecessor of curChar
                    map.get(curChar).add(preChar);
                    doProb(map, words, column + 1, preFrom, row);
                    preChar = curChar;
                }
            }
        }

    }

    String doTopologicalSort(Map<Character, Set<Character>> preMap) {
        StringBuilder sb = new StringBuilder();
        Set<Character> removed = new HashSet<>();
        while (sb.length() < preMap.size()) {
            boolean foundHead = false;
            // take the headless char each time, if there's no headless char, return ""
            for (Character key : preMap.keySet()) {
                Set<Character> predecessors = preMap.get(key);
                predecessors.removeAll(removed);
                if (predecessors.isEmpty() && !removed.contains(key)) {
                    removed.add(key);
                    sb.append(key);
                    foundHead = true;
                    break;
                }
            }
            if (!foundHead) {
                return "";
            }
        }
        return sb.toString();
    }
}
