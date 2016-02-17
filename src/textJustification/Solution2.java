package textJustification;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Given an array of words and a length L, format the text such that each line has exactly L
// characters and is fully (left and right) justified.
//
//You should pack your words in a greedy approach; that is, pack as many words as you can in each
// line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
//
//Extra spaces between words should be distributed as evenly as possible. If the number of spaces
// on a line do not divide evenly between words, the empty slots on the left will be assigned more
// spaces than the slots on the right.
//
//For the last line of text, it should be left justified and no extra space is inserted between
// words.
//
//For example,
//words: ["This", "is", "an", "example", "of", "text", "justification."]
//L: 16.
//
//Return the formatted lines as:
//[
//   "This    is    an",
//   "example  of text",
//   "justification.  "
//]
public class Solution2 {
    public static void main(String[] args) {
        String[] arr = {"What", "must", "be", "shall", "be."};
        int L = 16;
        fullJustify(arr, L).forEach(System.out::println);
    }

    public static List<String> fullJustify(String[] words, int L) {
        List<String> ret = new LinkedList<>();
        // point to cur word
        int cur = 0;
        while (cur < words.length) {
            int prob = cur;
            int len = words[prob].length();
            // now len should never be greater than L, otherwise there would be word that's
            // longer than L
            // we need to make sure there's another number after prob
            // we need to make sure after the number after prob is added, len is still smaller
            // than L
            while (prob < words.length - 1 && (words[prob + 1].length() + 1 + len <= L)) {
                len += words[prob + 1].length() + 1;
                prob++;
            }
            // add from prob to cur
            ret.add(createString(words, cur, prob, L));
            cur = prob + 1;
        }

        return ret;
    }

    // find how many blanks are there
    // find how long is each blank
    // find how many odds need to be justified
    private static String createString(String[] words, int start, int end, int L) {
        int wordLen = 0;
        for (int i = start; i <= end; i++) {
            wordLen += words[i].length();
        }
        int blanks = end - start;
        // one word, padding spaces after
        if (blanks == 0) {
            StringBuilder paddingSpace = new StringBuilder();
            for (int i = 0; i < L - words[end].length(); i++) {
                paddingSpace.append(" ");
            }
            return words[end] + paddingSpace.toString();
        }
        int blankSpaceBase, blankSpaceOdd;
        // if this is the last line, blank space should always be one
        if (end == words.length - 1) {
            blankSpaceBase = 1;
            blankSpaceOdd = 0;
        } else {
            blankSpaceBase = (L - wordLen) / blanks;
            blankSpaceOdd = (L - wordLen) % blanks;
        }
        StringBuilder blankBuilder = new StringBuilder();
        for (int i = 0; i < blankSpaceBase; i++) {
            blankBuilder.append(" ");
        }
        String blankString = blankBuilder.toString();

        StringBuilder sb = new StringBuilder();
        sb.append(words[start]);
        for (int i = start + 1; i <= end; i++) {
            sb.append(blankString);
            if (blankSpaceOdd > 0) {
                sb.append(" ");
                blankSpaceOdd--;
            }
            sb.append(words[i]);
        }
        // padding tail to L for last line
        if (end == words.length - 1) {
            int toPad = L - sb.length();
            for (int i = 0; i < toPad; i++) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
