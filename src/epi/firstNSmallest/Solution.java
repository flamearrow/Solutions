package epi.firstNSmallest;

import java.util.Random;

// find first n smallest numbers in an array in O(log n) time

// note this one uses a different approach from dutch sort to rearrange the elements,
// the while loop skips head and tail that's smaller and bigger then pivot first, then swap array[left] and array[right]
// then it returns left as the result(index), left means after this round, the first numbers are smaller then the right part
//  therefore when we recurse, we should recurse for (left, index-1) and (index, right), note the recursion call is always inclusive
// before we recurse, we check if we actually find any a number that's bigger than 0 ( left < index-1 ) (index < right)
// we only recurse when we can - meaning the rearrange method's left and right must be distinct
public class Solution {
	static void quickSort(int[] array) {
		doQuickSort(array, 0, array.length - 1);
		System.out.println("\nafter:");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	static void firstN(int[] array, int n) {
		doFirstN(array, 0, array.length - 1, n);
		System.out.println("\nafter:");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	static void doFirstN(int[] array, int left, int right, int n) {
		int index = rearrange(array, left, right);
		if (index - left == n) {
			return;
		} else if (index - left > n) {
			doFirstN(array, left, index - 1, n);
		} else {
			doFirstN(array, index, right, n - index + left);
		}
	}

	static void doQuickSort(int[] array, int left, int right) {
		int index = rearrange(array, left, right);
		if (left < index - 1)
			doQuickSort(array, left, index - 1);
		if (index < right)
			doQuickSort(array, index, right);

	}

	static int rearrange(int[] array, int left, int right) {
		int pivot = array[left];
		while (left <= right) {
			while (array[left] < pivot)
				left++;
			while (array[right] > pivot)
				right--;
			if (left <= right) {
				int tmp = array[left];
				array[left] = array[right];
				array[right] = tmp;
				left++;
				right--;
			}
		}
		return left;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1000000; i++) {
			int[] array = generateArray();
			System.out.println("before:");
			for (int m : array)
				System.out.print(m + " ");
			firstN(array, 5);
		}
	}

	static int[] generateArray() {
		int[] ret = new int[10];
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			ret[i] = rand.nextInt(10);
		}
		return ret;
	}
}
