package facebook;

import java.util.LinkedList;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Created by flamearrow on 1/21/16.
 */
public class Solution {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        Random r = new Random(3);
        for (int i = 0; i < 10; i++) {
            list.add(r.nextBoolean() ? 1 : 0);
        }
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        IntStream.of(array).forEach(System.out::print);
        swap(array);
        IntStream.of(array).forEach(System.out::print);
    }

    static int swap(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            if (array[left] == 0) {
                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
                right--;
            } else {
                left++;
            }
        }
        return array[right] == 0 ? left : left + 1;
    }
}
