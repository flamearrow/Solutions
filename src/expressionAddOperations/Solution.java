package expressionAddOperations;

import java.util.*;

//Given a string that contains only digits 0-9 and a target value,
// return all possibilities to add binary operators (not unary) +, -, or * between
// the digits so they evaluate to the target value.
//
//        Examples:
//        "123", 6 -> ["1+2+3", "1*2*3"]
//        "232", 8 -> ["2*3+2", "2+3*2"]
//        "105", 5 -> ["1*0+5","10-5"]
//        "00", 0 -> ["0+0", "0-0", "0*0"]
//        "3456237490", 9191 -> []
public class Solution {
    public static void main(String[] args) {
        String num = "2147483647";
        long target = 2147483647;
        for (String s : new Solution().addOperators(num, target)) {
            System.out.println(s);
        }

    }

    char[] operators = {'+', '-'};

    // the idea is to split num two halves to add/minus to target
    //  the right half can be a single number or a expression that's split by arbitrary number of '*'s
    // then we have leftString, rightString(can be a '*' expression), rightValue, target
    // recurse leftString with target+/-rightValue
    public List<String> addOperators(String num, long target) {
        Set<String> set = new HashSet<>();
        for (int i = num.length() - 1; i >= 0; i--) {
            String leftStr = num.substring(0, i);
            String rightStr = num.substring(i);
            for (ValNode vn : splitShit(rightStr)) {
                doMoreProbe(leftStr, vn.val, vn.str, target, set);
            }
        }
        return toList(set);
    }

    void doMoreProbe(String left, long rightVal, String rightStr, long target, Set<String> ret) {
        if (left.length() == 0) {
            if (rightVal == target) {
                ret.add(rightStr);
            }
            return;
        }
        for (char c : operators) {
            long newTarget = 0;
            if (c == '+') {
                newTarget = target - rightVal;
            }
            if (c == '-') {
                newTarget = target + rightVal;
            }
            List<String> subOps = addOperators(left, newTarget);
            if (subOps.size() > 0) {
                for (String subOp : subOps) {
                    ret.add(subOp + c + rightStr);
                }
            }
        }
    }

    List<String> toList(Set<String> set) {
        List<String> l = new LinkedList<>();
        l.addAll(set);
        return l;
    }


    // create a list of String value pairs from str
    //  the list should contain all possible permutations for splitting the string by arbitrary number of '*'
    //   and the string itself
    List<ValNode> splitShit(String str) {
        List<ValNode> ret = new LinkedList<>();
        if (str.length() != 0) {
            if (str.equals("0") || !str.startsWith("0")) {
                ret.add(new ValNode(str, Long.parseLong(str)));
            }
        }
        // fill '*' at all positions
        for (int i = 1; i < str.length(); i++) {
            List<ValNode> leftNodes = splitShit(str.substring(0, i));
            List<ValNode> rightNodes = splitShit(str.substring(i));
            for (ValNode leftNode : leftNodes) {
                for (ValNode rightNode : rightNodes) {
                    ValNode newNode = new ValNode(leftNode.str + '*' + rightNode.str, leftNode.val * rightNode.val);
                    ret.add(newNode);
                }
            }
        }
        return ret;
    }

    class ValNode {
        String str;
        long val;

        public ValNode(String aStr, long aVal) {
            str = aStr;
            val = aVal;
        }
    }
}
