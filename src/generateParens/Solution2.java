package generateParens;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by flamearrow on 1/24/16.
 */
public class Solution2 {
    public static void main(String[] args) {
        new Solution2().generateParenthesis(3).forEach(System.out::println);
    }

    public List<String> generateParenthesis(int n) {
        List<String> ret = new LinkedList<>();
        doShit(ret, n, n, "");
        return ret;
    }

    void doShit(List<String> ret, int left, int right, String cur) {
        if (left == 0 && right == 0) {
            ret.add(cur);
            return;
        }
        if (left > 0) {
            doShit(ret, left - 1, right, cur + "(");
        }
        if (right > left) {
            doShit(ret, left, right - 1, cur + ")");
        }
    }

}
