package spiralMatrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
//
//For example,
//Given the following matrix:
//
//[
// [ 1, 2, 3 ],
// [ 4, 5, 6 ],
// [ 7, 8, 9 ]
//]
//
//You should return [1,2,3,6,9,8,7,4,5].
public class Solution2 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret = new LinkedList<>();
        int height = matrix.length;
        if (height == 0) {
            return ret;
        }
        int width = matrix[0].length;
        int left = 0, right = width - 1;
        int top = 0, bottom = height - 1;
        while (true) {
            for (int i = left; i <= right; i++) {
                ret.add(matrix[top][i]);
            }
            top++;
            if (top > bottom) {
                break;
            }
            for (int i = top; i <= bottom; i++) {
                ret.add(matrix[i][right]);
            }
            right--;
            if (right < left) {
                break;
            }
            for (int i = right; i >= left; i--) {
                ret.add(matrix[bottom][i]);
            }
            bottom--;
            if (bottom < top) {
                break;
            }
            for (int i = bottom; i >= top; i--) {
                ret.add(matrix[i][left]);
            }
            left++;
            if (left > right) {
                break;
            }
        }
        return ret;
    }
}
