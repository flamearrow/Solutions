package pancakeSorting;
//Given an array of integers arr, sort the array by performing a series of pancake flips.
//
//        In one pancake flip we do the following steps:
//
//        Choose an integer k where 1 <= k <= arr.length.
//        Reverse the sub-array arr[1...k].
//        For example, if arr = [3,2,1,4] and we performed a pancake flip choosing k = 3, we reverse the sub-array [3,2,1], so arr = [1,2,3,4] after the pancake flip at k = 3.
//
//        Return the k-values corresponding to a sequence of pancake flips that sort arr. Any valid answer that sorts the array within 10 * arr.length flips will be judged as correct.
//
//
//
//        Example 1:
//
//        Input: arr = [3,2,4,1]
//        Output: [4,2,4,3]
//        Explanation:
//        We perform 4 pancake flips, with k values 4, 2, 4, and 3.
//        Starting state: arr = [3, 2, 4, 1]
//        After 1st flip (k = 4): arr = [1, 4, 2, 3]
//        After 2nd flip (k = 2): arr = [4, 1, 2, 3]
//        After 3rd flip (k = 4): arr = [3, 2, 1, 4]
//        After 4th flip (k = 3): arr = [1, 2, 3, 4], which is sorted.
//        Notice that we return an array of the chosen k values of the pancake flips.
//        Example 2:
//
//        Input: arr = [1,2,3]
//        Output: []
//        Explanation: The input is already sorted, so there is no need to flip anything.
//        Note that other answers, such as [3, 3], would also be accepted.
//
//
//        Constraints:
//
//        1 <= arr.length <= 100
//        1 <= arr[i] <= arr.length
//        All integers in arr are unique (i.e. arr is a permutation of the integers from 1 to arr.length).


import java.util.*;

public class Solution {
    class Node {
        public Node(List<Integer> previousFlips, int[] arr) {
            this.previousFlips = previousFlips;
            this.arr = arr;
        }

        List<Integer> previousFlips;
        int[] arr;

    }

    // Slow af
    public List<Integer> pancakeSortBFS(int[] arr) {
        // bfs
        Node firstNode = new Node(new ArrayList<>(), arr);
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(firstNode);

        Set<Integer> visitedArrayHashes = new HashSet<>();

        int nodesLeftThisLvl = queue.size();
        int lvl = 0;
        while (!queue.isEmpty()) {
            Node next = queue.removeFirst();
            visitedArrayHashes.add(Arrays.hashCode(next.arr));
            if (sorted(next.arr)) {
                return next.previousFlips;
            } else {
                for (int k = 1; k < next.arr.length; k++) {
                    int[] newArray = new int[arr.length];
                    for (int i = 0; i <= k; i++) {
                        newArray[i] = next.arr[k - i];
                    }
                    for (int i = k + 1; i < next.arr.length; i++) {
                        newArray[i] = next.arr[i];
                    }

                    if (!visitedArrayHashes.contains(Arrays.hashCode(newArray))) {
                        List<Integer> newFlips = new ArrayList<>(next.previousFlips);
                        newFlips.add(k + 1);
                        queue.addLast(new Node(newFlips, newArray));
                    }
                }

                if (--nodesLeftThisLvl == 0) {
                    System.out.println("starting lvl " + ++lvl);
                    nodesLeftThisLvl = queue.size();
                }
            }
        }
        return new ArrayList<>();
    }

    boolean sorted(int[] array) {
        if (array.length == 0) {
            return true;
        }
        int cur = array[0];
        for (int i = 1; i < array.length; i++) {
            if (cur > array[i]) {
                return false;
            }
            cur = array[i];
        }
        return true;
    }


    // bubbleSort
    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> ret = new LinkedList<>();
        doSort(arr, arr.length, ret);
        return ret;
    }

    // find the biggest of array[0, n) and move it to array[n-1] by doing two flips
    // first flip the biggest number array[b] to array[0]
    // then flip the array[0] to array[n-1] so the bigest is at n-1
    void doSort(int[] array, int n, List<Integer> ret) {
        if (n == 1) {
            return;
        }
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < n; i++) {
            if (max < array[i]) {
                max = array[i];
                maxIndex = i;
            }
        }
        if (maxIndex != n - 1) {
            // first make max to 0, if it's already 0 skp
            if (maxIndex != 0) {
                flip(array, maxIndex);
                ret.add(maxIndex + 1);
            }
            flip(array, n - 1);
            ret.add(n);
        }
        doSort(array, n - 1, ret);
    }

    void flip(int[] array, int endEndex) {
        for (int i = 0; i <= endEndex / 2; i++) {
            int tmp = array[i];
            array[i] = array[endEndex - i];
            array[endEndex - i] = tmp;
        }
    }


    public static void main(String[] args) {
//        int[] arr = {10, 5, 1, 6, 3, 8, 2, 4, 7, 9};
        int[] arr = {1, 2, 3};
        for (int i : new Solution().pancakeSort(arr)) {
            System.out.println(i);
        }
    }
}
