package differentWaysToAddParens;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//Given a string of numbers and operators,
// return all possible results from computing all the different possible ways to group numbers and operators.
// The valid operators are +, - and *.
//
//
//        Example 1
//        Input: "2-1-1".
//
//        ((2-1)-1) = 0
//        (2-(1-1)) = 2
//        Output: [0, 2]
//
//
//        Example 2
//        Input: "2*3-4*5"
//
//        (2*(3-(4*5))) = -34
//        ((2*3)-(4*5)) = -14
//        ((2*(3-4))*5) = -10
//        (2*((3-4)*5)) = -10
//        (((2*3)-4)*5) = 10
//        Output: [-34, -14, -10, -10, 10]
public class Solution {
    public static void main(String[] args) {
        String input = "2*3+4*5";
        for (int result : new Solution().diffWaysToCompute(input)) {
            System.out.println(result);
        }
    }

    // the idea is to split the problem to left/right half at each operator
    //  and shrink it by combining all results from left part and right part
    // when the input is a valid number, we terminate
    //  this way you don't need to worry about the -
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ret = new LinkedList<>();
        // note we don't really need to build the expressions
        // just create all possible combinations and compute the shit in different orders
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case '+':
                case '-':
                case '*':
                    List<Integer> lefts = diffWaysToCompute(input.substring(0, i));
                    List<Integer> rights = diffWaysToCompute(input.substring(i + 1));
                    for (int left : lefts) {
                        for (int right : rights) {
                            ret.add(calculate(left, right, input.charAt(i)));
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        if (ret.size() == 0) {
            ret.add(Integer.parseInt(input));
        }
        return ret;
    }

    int calculate(int left, int right, char op) {
        switch (op) {
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            default:
                return 0;
        }
    }
}
