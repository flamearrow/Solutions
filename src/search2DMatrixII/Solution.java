package search2DMatrixII;

//Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
//
//        Integers in each row are sorted in ascending from left to right.
//        Integers in each column are sorted in ascending from top to bottom.
//        For example,
//
//        Consider the following matrix:
//
//        [
//        [1,   4,  7, 11, 15],
//        [2,   5,  8, 12, 19],
//        [3,   6,  9, 16, 22],
//        [10, 13, 14, 17, 24],
//        [18, 21, 23, 26, 30]
//        ]
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {

        int height = matrix.length;
        int width = matrix[0].length;
        int x = 0, y = width - 1;
        while(matrix[x][y] != target) {
            if(matrix[x][y] < target) {
                x++;
            } else {
                y--;
            }
            if(x >= height || y < 0) {
                return false;
            }
        }
        return true;
    }
}
