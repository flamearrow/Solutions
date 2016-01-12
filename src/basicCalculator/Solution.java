package basicCalculator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

//Implement a basic calculator to evaluate a simple expression string.
//
//        The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
//
//        You may assume that the given expression is always valid.
//
//        Some examples:
//        "1 + 1" = 2
//        " 2-1 + 2 " = 3
//        "(1+(4+5+2)-3)+(6+8)" = 23
public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().calculate2("3+5 / 2"));
    }


    public int calculate(String s) {
        return doShit(new StringTokenizer(s, " +-()", true));
    }

    // the recursive idea is to iterate the tokenizer once - even though we recurse
    //  if we see a number, update cur
    //  if we see a op, buffer it
    //  if we see ( - recurse
    //  if we see ) - return
    //  if we see space, skip it
    int doShit(StringTokenizer tk) {
        int ret = 0;
        boolean plus = true;
        while (tk.hasMoreTokens()) {
            String next = tk.nextToken();
            switch (next) {
                case " ":
                    break;
                case "+":
                    plus = true;
                    break;
                case "-":
                    plus = false;
                    break;
                case "(":
                    int right = doShit(tk);
                    ret = plus ? ret + right : ret - right;
                    break;
                case ")":
                    return ret;
                // number
                default:
                    ret = plus ? ret + Integer.parseInt(next) : ret - Integer.parseInt(next);
                    break;
            }
        }
        return ret;
    }

    // given String without paren, only contains +-, return its value
    // doesn't support minus
    //  note when ever eval a string as equation, do recusive call to avoid double minus issue
    int eval(String s) {
        if (!(s.contains("+") || s.contains("-"))) {
            return Integer.parseInt(s);
        }
        int i = 0;
        for (; i < s.length(); i++) {
            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                break;
            }
        }
        int left = Integer.parseInt(s.substring(0, i));
        int right = eval(s.substring(i + 1));
        return s.charAt(i) == '+' ? left + right : left - right;
    }

    // no parentheses, but contains +-*/
    // use a stack, look at each number/optr
    //  if optr is +-, push
    //  if optr is */, apply preNumber and next, push
    // finally pop everything from stack and calculate result
    public int calculate2(String s) {
        LinkedList<String> list = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(s, " +-*/", true);
        while (st.hasMoreTokens()) {
            String cur = st.nextToken();
            switch (cur) {
                case " ":
                    break;
                case "+":
                case "-":
                    list.addLast(cur);
                    break;
                case "*":
                case "/":
                    String next = st.nextToken();
                    while(next.equals(" ")) {
                        next = st.nextToken();
                    }
                    String prev = list.removeLast();
                    list.addLast(createNew(prev, next, cur));
                    break;
                default:
                    list.addLast(cur);
                    break;
            }
        }

        Iterator<String> itr = list.iterator();
        int ret = 0;
        boolean plus = true;
        while (itr.hasNext()) {
            String cur = itr.next();
            switch (cur) {
                case "+":
                    plus = true;
                    break;
                case "-":
                    plus = false;
                    break;
                default:
                    int right = Integer.parseInt(cur);
                    ret = plus ? ret + right : ret - right;
                    break;
            }
        }
        return ret;
    }

    String createNew(String left, String right, String op) {
        int iLeft = Integer.parseInt(left);
        int iRight = Integer.parseInt(right);
        if (op.equals("*")) {
            return "" + (iLeft * iRight);
        } else {
            return "" + (iLeft / iRight);
        }
    }


}
