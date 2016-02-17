package spiralMatrix2;

import java.util.Iterator;

/**
 * Created by flamearrow on 2/16/16.
 */
public class IteratorSolution {
    public static void main(String[] args) {
        int[][] matrix = new spiralMatrix2.Solution2().generateMatrix(10);
        SpiralIterator itr = new SpiralIterator(matrix);
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

    }
}

/**
 * a) left, right, up, bottom are all inclusive bounds
 * b) when change direction, do 3 things:
 *    1) reduce bound 2) move pointer to the new bound 3) change dir
 */
class SpiralIterator implements Iterator<Integer> {
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int UP = 3;
    private int[][] mArr;
    int left, right, top, bottom;
    int width, height;
    int curX, curY;
    int dir;

    public SpiralIterator(int[][] arr) {
        height = arr.length;
        width = arr[0].length;
        mArr = arr;
        left = 0;
        right = width - 1;
        top = 0;
        bottom = height - 1;
        curX = 0;
        curY = 0;
        dir = RIGHT;
    }

    @Override
    public boolean hasNext() {
        return curX >= left && curX <= right && curY >= top && curY <= bottom;
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            Integer ret = mArr[curY][curX];
            switch (dir) {
                case RIGHT:
                    if (curX < right) {
                        curX++;
                    } else {
                        top++;
                        curY = top;
                        dir = DOWN;
                    }
                    break;
                case DOWN:
                    if (curY < bottom) {
                        curY++;
                    } else {
                        right--;
                        curX = right;
                        dir = LEFT;
                    }
                    break;
                case LEFT:
                    if (curX > left) {
                        curX--;
                    } else {
                        bottom--;
                        curY = bottom;
                        dir = UP;
                    }
                    break;
                case UP:
                    if (curY > top) {
                        curY--;
                    } else {
                        left++;
                        curX = left;
                        dir = RIGHT;
                    }
                    break;
            }
            return ret;
        } else {
            return null;
        }
    }
}
