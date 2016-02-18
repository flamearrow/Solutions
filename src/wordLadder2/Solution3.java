package wordLadder2;

import java.util.*;

/**
 * Created by flamearrow on 2/17/16.
 */
public class Solution3 {
    //start = "hit"
    //end = "cog"
    //dict = ["hot","dot","dog","lot","log"]
    //Return
    //  [
    //    ["hit","hot","dot","dog","cog"],
    //    ["hit","hot","lot","log","cog"]
    //  ]


    //    "red"
//            "tax"
//            ["ted","tex","red","tax","tad","den","rex","pee"]
    public static void main(String[] args) {
        String beginWord = "red";
        String endWord = "tax";
        Set<String> wordList = new HashSet<>();
        wordList.add("ted");
        wordList.add("tex");
        wordList.add("red");
        wordList.add("tax");
        wordList.add("tad");
        wordList.add("den");
        wordList.add("rex");
        wordList.add("pee");
        findLadders(beginWord, endWord, wordList).forEach(list -> {
            list.forEach(item -> System.out.print(item + " "));
            System.out.println("\n");
        });
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, Set<String>
            wordList) {
        List<List<String>> ret = new LinkedList<>();
        Queue<LinkedList<String>> queue = new LinkedList<>();
        LinkedList<String> firstList = new LinkedList<>();
        firstList.add(beginWord);
        queue.add(firstList);
        int leftInCurrentLevel = 1;
        boolean found = false;
        while (!queue.isEmpty()) {
            LinkedList<String> next = queue.poll();
            leftInCurrentLevel--;
            String last = next.getLast();
            if (isTransformable(last, endWord)) {
                next.add(endWord);
                ret.add(next);
                found = true;
            } else {
                for (String s : wordList) {
                    if (next.contains(s)) {
                        continue;
                    }
                    if (isTransformable(last, s)) {
                        LinkedList<String> newList = new LinkedList<>(next);
                        newList.add(s);
                        queue.offer(newList);
                    }
                }
            }
            // just done one level
            if (leftInCurrentLevel == 0) {
                // we already find one or more paths in this level, if we go deeper, the path can
                // only be longer
                if (found) {
                    break;
                } else {
                    leftInCurrentLevel = queue.size();
                }
            }
        }
        return ret;
    }

    // return if s1 and s2 only differs by one character
    private static boolean isTransformable(String s1, String s2) {
        boolean ret = false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (ret) {
                    return false;
                } else {
                    ret = true;
                }
            }
        }
        return ret;
    }
}
