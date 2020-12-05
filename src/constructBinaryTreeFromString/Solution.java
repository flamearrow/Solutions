package constructBinaryTreeFromString;

import treeUtils.TreeNode;

//You need to construct a binary tree from a string consisting of parenthesis and integers.
//
//        The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.
//
//        You always start to construct the left child node of the parent first if it exists.
//
//
//
//        Example 1:
//
//
//        Input: s = "4(2(3)(1))(6(5))"
//        Output: [4,2,6,3,1,5]
//        Example 2:
//
//        Input: s = "4(2(3)(1))(6(5)(7))"
//        Output: [4,2,6,3,1,5,7]
//        Example 3:
//
//        Input: s = "-4(2(3)(1))(6(5)(7))"
//        Output: [-4,2,6,3,1,5,7]
//
//
//        Constraints:
//
//        0 <= s.length <= 3 * 104
//        s consists of digits, '(', ')', and '-' only.
public class Solution {
    public TreeNode str2tree(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        int indexOfFirstLeftBracket = s.indexOf("(");
        if (indexOfFirstLeftBracket < 0) {
            return new TreeNode(Integer.parseInt(s));
        }
        TreeNode root = new TreeNode(Integer.parseInt(s.substring(0, indexOfFirstLeftBracket)));
        s = s.substring(indexOfFirstLeftBracket);
        if (s.length() > 0) {
            // inclusive, get left
            int closingBracketIndex = findClosingBracket(s);
            root.left = str2tree(s.substring(1, closingBracketIndex));
            // has right
            if (closingBracketIndex < s.length() - 1) {
                root.right = str2tree(s.substring(closingBracketIndex + 2, s.length() - 1));
            }
        }
        return root;
    }

    // s[0] is (, find the index of ) that closes this particular bracket
    int findClosingBracket(String s) {
        int leftBrackets = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                leftBrackets--;
                if (leftBrackets == 0) {
                    return i;
                }
            } else if (s.charAt(i) == '(') {
                leftBrackets++;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().str2tree("4(2(3)(1))(6(5))"));
    }


}
