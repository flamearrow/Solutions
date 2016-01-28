package spiralMatrix;

import java.util.Iterator;

/**
 * Created by flamearrow on 1/27/16.
 */
public class SpiralMatrixIterator implements Iterator<Integer> {
    public static void main(String[] args) {
        int[][] matrix = new spiralMatrix2.Solution2().generateMatrix(10);
        SpiralMatrixIterator itr = new SpiralMatrixIterator(matrix);
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

    private static final int RIGHT = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int UP = 3;
    private int[][] mMatrix;
    private int left, right, top, bottom;
    private int mCurDir;
    private int curX, curY;

    public SpiralMatrixIterator(int[][] matrix) {
        mMatrix = matrix;
        mCurDir = RIGHT;
        left = 0;
        right = matrix[0].length - 1;
        top = 0;
        bottom = matrix.length - 1;
        curX = 0;
        curY = 0;
    }

    @Override
    public boolean hasNext() {
        return left <= right && top <= bottom;
    }

    @Override
    public Integer next() {
        // don't check hasNext() - can throw some exception though
        int ret = mMatrix[curX][curY];
        switch (mCurDir) {
            case RIGHT:
                if (curY < right) {
                    curY++;
                } else {// if (curY == right) {
                    top++;
                    curX = top;
                    mCurDir = DOWN;
                }
                break;
            case DOWN:
                if (curX < bottom) {
                    curX++;
                } else {
                    right--;
                    curY = right;
                    mCurDir = LEFT;
                }
                break;
            case LEFT:
                if (curY > left) {
                    curY--;
                } else {
                    bottom--;
                    curX = bottom;
                    mCurDir = UP;
                }
                break;
            case UP:
                if (curX > top) {
                    curX--;
                } else {
                    left++;
                    curY = left;
                    mCurDir = RIGHT;
                }
                break;
        }
        return ret;
    }
}
