package sortTransformedArray;

import java.util.LinkedList;

public class Solution {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        LinkedList<Integer> decreasingList = new LinkedList<>();
        LinkedList<Integer> increasingList = new LinkedList<>();

        for (int x : nums) {
            if (derivative(x, a, b) >= 0) {
                increasingList.addLast(result(x, a, b, c));
            } else {
                decreasingList.addFirst(result(x, a, b, c));
            }
        }
        int[] result = new int[increasingList.size() + decreasingList.size()];
        int i = 0;
        while (!increasingList.isEmpty() || !decreasingList.isEmpty()) {
            int next = -1;
            if (increasingList.isEmpty()) {
                next = decreasingList.removeFirst();
            } else if (decreasingList.isEmpty()) {
                next = increasingList.removeFirst();
            } else {
                next = increasingList.getFirst() < decreasingList.getFirst() ?
                        increasingList.removeFirst() : decreasingList.removeFirst();
            }
            result[i] = next;
            i++;
        }
        return result;

    }

    int result(int x, int a, int b, int c) {
        return x * x * a + x * b + c;
    }

    // derivative of ax^2 + bx + c is 2ax + b
    int derivative(int x, int a, int b) {
        return 2 * x * a + b;
    }
}
