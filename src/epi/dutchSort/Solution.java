package epi.dutchSort;

import java.util.Random;

//Problem 6.1 : Write a function that takes an array A and an index i into A, and
//rearranges the elements such that all elements less than A[i] appear first, followed
//by elements equal to A[i], followed by elements greater than A[i]. Your algorithm
//should have O(1) space complexity and O(|A|) time complexity.

// a subroutine of quick sort
public class Solution {

	static void rearrange2(int[] array, int index) {
		int head = 0, tail = array.length - 1, cur = 0;
		int pivot = array[index];
		while (cur <= tail) {
			if (array[cur] < pivot) {
				int tmp = array[head];
				array[head] = array[cur];
				array[cur] = tmp;
				head++;
				// this is only required when pivot is not picked up from first
				if (head > cur)
					cur = head;
			} else if (array[cur] == pivot) {
				cur++;
			} else {
				int tmp = array[tail];
				array[tail] = array[cur];
				array[cur] = tmp;
				tail--;
			}
		}
	}

	// if there're dups, we need to keep FOUR groups
	//  1) smaller than pivot-covered from 0 to start
	//  2) equal to pivot-covered from start to cur
	//  3) bigger than pivot-covered from end to tail
	//  4) not touched-covered from cur to end
	// and while loop need to check equal
	static void rearrange(int[] array, int index) {
		int start = 0, cur = 0, end = array.length - 1;
		int pivot = array[index];
		while (cur <= end) {
			if (array[cur] < pivot) {
				int tmp = array[start];
				array[start] = array[cur];
				array[cur] = tmp;
				start++;
				// this is only required when pivot is not picked up from first
				if (start > cur)
					cur = start;
			} else if (array[cur] == pivot) {
				cur++;
			} else if (array[cur] > pivot) {
				int tmp = array[end];
				array[end] = array[cur];
				array[cur] = tmp;
				end--;
			}
		}
	}

	public static void main(String[] args) {
		//		int[] array = generateArray();
		int[] array = { 2, 1, 3 };
		System.out.println("before");
		for (int i : array) {
			System.out.print(i + " ");
		}
		System.out.println("\nafter");
		rearrange2(array, 0);
		for (int i : array) {
			System.out.print(i + " ");
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
