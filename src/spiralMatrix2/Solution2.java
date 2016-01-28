package spiralMatrix2;

import java.util.TreeSet;
import java.util.stream.IntStream;

//Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
//
//        For example,
//        Given n = 3,
//
//        You should return the following matrix:
//        [
//        [ 1, 2, 3 ],
//        [ 8, 9, 4 ],
//        [ 7, 6, 5 ]
//        ]
public class Solution2 {
    public static void main(String[] args) {
        int[][] ret = new Solution2().generateMatrix(10);
        for (int[] mlgb : ret) {
            IntStream.of(mlgb)
                    .mapToObj(i -> i < 10 ? i + "   " : i < 100 ? i + "  " : i + " ")
                    .forEach(System.out::print);
            System.out.println();
        }
        TreeSet<Integer> shit = new TreeSet<>();
        IntStream.range(1, 10).forEach(shit::add);
    }

    public int[][] generateMatrix(int n) {
        int[][] ret = new int[n][n];
        int left = 0, right = n - 1, top = 0, bottom = n - 1;
        int cur = 1;
        while (true) {
            for (int i = left; i <= right; i++) {
                ret[top][i] = cur++;
            }
            top++;
            if (top > bottom) {
                break;
            }
            for (int i = top; i <= bottom; i++) {
                ret[i][right] = cur++;
            }
            right--;
            if (right < left) {
                break;
            }
            for (int i = right; i >= left; i--) {
                ret[bottom][i] = cur++;
            }
            bottom--;
            if (bottom < top) {
                break;
            }
            for (int i = bottom; i >= top; i--) {
                ret[i][left] = cur++;
            }
            left++;
            if (left > right) {
                break;
            }
        }

        return ret;
    }
}
