package removeInvalidParentheses;

import java.util.*;

//Remove the minimum number of invalid parentheses in order to make 
// the input string valid. Return all possible results.
//
//Note: The input string may contain letters other than the parentheses ( and ).
//
//Examples:
//"()())()" -> ["()()()", "(())()"]
//"(a)())()" -> ["(a)()()", "(a())()"]
//")(" -> [""]
public class Solution {


    public List<String> removeInvalidParentheses(String s) {
        return doBFS(s);
    }

    // idea is try to find all mutations of the string - remove a on char
    // from the string each time
    // find all leaves and continue removing from the leaves, check valid
    // for each leaf and add to result
    List<String> doBFS(String s) {
        if (s == null) {
            return new LinkedList<String>();
        }
        // remove leading ) and trailing ( for a shorter input
        int head = 0, tail = s.length() - 1;
        while (head < s.length() && s.charAt(head) == ')') {
            head++;
        }
        while (tail >= 0 && s.charAt(tail) == '(') {
            tail--;
        }
        s = s.substring(head, tail + 1);
        Set<String> visited = new HashSet<String>();
        List<String> rst = new LinkedList<String>();

        Queue<String> q = new LinkedList<>();
        System.out.println(s);
        q.offer(s);
        visited.add(s);
        boolean found = false;
        while (!q.isEmpty()) {
            String next = q.poll();
            if (valid(next)) {
                rst.add(next);
                found = true;
            }
            // trick: the first time we found a hit, we don't want to search
            // further
            // because that's already the minimal removal, any additional
            // removals are not minimal
            // that's why we're doing BFS - it's impossible that later than this
            // node
            // there's another node that's longer than this node and need
            // further removal
            // so we can savely skip all the rest
            if (found) {
                continue;
            }
            for (String leaf : getLeaves(next)) {
                if (!visited.contains(leaf)) {
                    q.offer(leaf);
                    visited.add(leaf);
                }
            }
        }
        return rst;
    }

    List<String> getLeaves(String root) {
        List<String> ret = new LinkedList<>();
        // remove char at i
        for (int i = 0; i < root.length(); i++) {
            // skip non parens
            if (root.charAt(i) == '(' || root.charAt(i) == ')') {
                // de dup
                if (i > 1 && root.charAt(i) == root.charAt(i - 1)) {
                    continue;
                }
                ret.add(root.substring(0, i) + root.substring(i + 1));
            }
        }
        return ret;
    }

    boolean valid(String s) {
        int open = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
            } else if (c == ')') {
                if (open == 0) {
                    return false;
                } else {
                    open--;
                }
            }
        }
        return open == 0;
    }


    // for problems to find a couple of mutations, if there's no clever ways,
    // exhaust it, always try bfs or dfs
    public static void main(String[] args) {
        String ori = "(()f)a)";
        for (String s : new Solution().removeInvalidParentheses2(ori)) {
            System.out.println(s);
        }
    }

    // A recursive algorithm - need to implement searchFromRight
    //  1) find (())) (  : the first ( after an offending pattern when ) is more then (
    //  2) then for the prefix found, remove numOf) - numOf( of ')'s - generate it recursively, gets
    //  a bunch of prefixes
    //  3) then recursively call the right part to generate suffixes
    //  4) generate all combinations of suffixes and prefixes
    public List<String> removeInvalidParentheses2(String s) {
        List<String> result = new ArrayList<>();
        boolean[] removable = new boolean[s.length()];
        result.addAll(searchFromLeft(s, 0, removable));
//        result.addAll(searchFromRight(s, s.length() - 1));
        return result;
    }

    List<String> searchFromLeft(String s, int start, boolean[] removable) {
        List<String> ret = new ArrayList<>();
        if (start == s.length()) {
            ret.add("");
            return ret;
        }
        Set<String> prefixResults = new HashSet<>();
        int right = -1;
        if (hasLeadingRight(s, start)) {
            StringBuilder sb = new StringBuilder();
            right = start;
            while (right <= s.length() && s.charAt(right) != '(') {
                if (s.charAt(right) != ')') {
                    sb.append(s.charAt(right));
                }
                right++;

            }
            prefixResults.add(sb.toString());
        } else {
            right = rightmostBoundOfOffendingPrefix(s, start, removable, prefixResults);
            if (right < 0) {
                return ret;
            }
        }

        List<String> suffixResults = searchFromLeft(s, right, removable);
        for (String prefix : prefixResults) {
            for (String suffix : suffixResults) {
                ret.add(prefix + suffix);
            }
        }
        return ret;
    }

    boolean hasLeadingRight(String s, int left) {
        while (left < s.length() && s.charAt(left) != '(') {
            if (s.charAt(left) == ')') {
                return true;
            }
            left++;
        }
        return false;
    }


    int rightmostBoundOfOffendingPrefix(String s, int start, boolean[] removable,
                                        Set<String> prefixResults) {

        int opening = 0;
        int toRemove = 0;
        int right = start;
        boolean offending = false;
        while (right < s.length()) {
            if (s.charAt(right) == '(') {
                // left boundary of next search
                if (offending) {
                    break;
                }
                opening++;
            } else if (s.charAt(right) == ')') {
                opening--;
                removable[right] = true;
                if (opening < 0) {
                    offending = true;
                    toRemove++;
                    // find the index of next '('
                }
            }
            right++;
        }
        // get toRemove number of ')' to be removed from removables from start to ret
        boolean[] toBeRemoved = new boolean[s.length()];
        generateAllRemoves(s, start, right, removable, toBeRemoved, toRemove, prefixResults);
        return right;
    }

    void generateAllRemoves(String s, int start, int end, boolean[] removable,
                            boolean[] toBeRemoved,
                            int toRemove, Set<String> results) {
        if (toRemove == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = start; i < end; i++) {
                if (!toBeRemoved[i]) {
                    sb.append(s.charAt(i));
                }
            }
            results.add(sb.toString());
        } else {
            for (int i = start; i < end; i++) {
                if (removable[i]) {
                    toBeRemoved[i] = true;
                    generateAllRemoves(s, start, end, removable, toBeRemoved, toRemove - 1, results);
                    toBeRemoved[i] = false;
                }
            }
        }
    }


    List<String> searchFromRight(String s, int start) {
        return null;
    }

}
