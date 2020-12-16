package quickSort;

import java.util.Arrays;

// plain old quick sort
public class Solution {
    static void quickSort(int[] toSort) {
        doSort(toSort, 0, toSort.length - 1);
    }

    static int findKthLargestNumber(int[] toSort, int k) {
        return doFindKth(toSort, 0, toSort.length - 1, k);
    }

    static int doFindKth(int[] toSort, int start, int end, int k) {
        int pivotIndex = rearrange(toSort, start, end);
        if (pivotIndex == k) {
            return toSort[pivotIndex];
        } else if (pivotIndex < k) {
            return doFindKth(toSort, pivotIndex + 1, end, k - toSort[pivotIndex]);
        } else {
            return doFindKth(toSort, 0, pivotIndex - 1, k);
        }
    }

    static void doSort(int[] toSort, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivotIndex = rearrange(toSort, start, end);
        doSort(toSort, start, pivotIndex - 1);
        doSort(toSort, pivotIndex + 1, end);
    }

    // when rearrange, return the pivot index
    static int rearrange(int[] toSort, int start, int end) {
        int pivot = toSort[end];
        while (start < end) {
            if (toSort[start] < pivot) {
                start++;
            } else {
                int tmp = toSort[start];
                toSort[start] = toSort[end];
                toSort[end] = tmp;
                // the item moved to right is pivot, don't decrease right
                if (tmp != pivot) {
                    end--;
                }
            }
        }
        return toSort[start] == pivot ? start : end;
    }

    public static void main(String[] args) {
        int[] arr = {5, 1, 2, 7, 8, 6};
//        quickSort(arr);
//        Arrays.stream(arr).forEach(System.out::println);
        System.out.println(findKthLargestNumber(arr, 3));
    }
}
