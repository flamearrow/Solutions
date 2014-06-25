package crackingTheCodingInterview.c18;

// find the smallest n numbers in a array
// n is guaranteed to be smaller than the length of the array
public class Question6SelectionRank {
	static void findSmallestN(int[] array, int n) {
		doShuffle(array, 0, array.length - 1, n);
		for (int i = 0; i < n; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	static void doShuffle(int[] array, int start, int end, int n) {
		int split = doSplit(array, start, end);
		if (split - start == n)
			return;
		if (split < start + n) {
			doShuffle(array, split, end, n - split + start);
		} else if (split > start + n) {
			doShuffle(array, start, split - 1, n);
		}
	}

	// for the reshuffle method, the easiest method is to loop for left boundary and right boundary that's outliers,
	//  then swap, then converge, return left in the end
	// note after doSplit(), we can make sure array[left to index-1] are all smaller than pivot
	// but it's not guaranteed array[index] == pivot
	// 31734 would return 2 and array remains 31734
	static int doSplit(int[] array, int start, int end) {
		int pivot = array[start];
		while (start <= end) {
			while (array[start] < pivot)
				start++;
			while (array[end] > pivot)
				end--;
			if (start <= end) {
				int tmp = array[start];
				array[start] = array[end];
				array[end] = tmp;
				start++;
				end--;
			}
		}
		return start;
	}

	public static void main(String[] args) {
		int[] array = { 3, 1, 7, 3, 4 };
		System.out.println(doSplit(array, 0, array.length - 1));
		for (int i : array)
			System.out.print(i + " ");
		//		for (int i = 1; i < 6; i++)
		//		findSmallestN(array, 2);
	}
}
