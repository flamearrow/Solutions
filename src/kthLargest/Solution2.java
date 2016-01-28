package kthLargest;


/**
 * Created by flamearrow on 1/24/16.
 */
public class Solution2 {
    int kthSmallest(int[] array, int k) {
        return doShit(array, 0, array.length - 1, k);
    }

    int doShit(int[] array, int left, int right, int k) {
        int index = shuffle(array, left, right);
        if ((index - left + 1) == k) {
            return array[index];
        } else if ((index - left + 1) < k) {
            return doShit(array, index + 1, right, k - index - 1);
        } else {
            return doShit(array, left, index - 1, k);
        }
    }

    int shuffle(int[] array, int left, int right) {
        int pivot = array[right];
        while (left < right) {
            if (array[left] < pivot) {
                left++;
            } else {
                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
                if (array[right] != pivot) {
                    right--;
                }
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] array = {4, 5, 1, 2, 3, 9, 8, 10, 7, 6};
//        int i = new Solution2().shuffle(array, 0, array.length - 1);
        for (int i = 1; i <= 10; i++) {
            System.out.println(new Solution2().kthSmallest(array, i));
        }
//        IntStream.of(array).forEach(System.out::print);
    }
}
