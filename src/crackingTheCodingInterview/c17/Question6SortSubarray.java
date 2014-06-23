package crackingTheCodingInterview.c17;

// Given an array of integers, find the the m and n 
// where n-m is minimized when you sort the array from m to n
public class Question6SortSubarray {
	// first find two increasing subsequence from left and from right
	// now we have left, middle, right
	// then at the edge of (left, middle) and (middle, right)
	//  move them until end of left is smaller than min(middle)
	//  and start of right is bigger than max(middle)
	// then the edge would be m and n
	static void findNandM(int[] array) {
		// idea is to maintain the invariant 
		// min(middle) > left and max(middle) < right 
		int m = -1, n = -1;
		for (int i = 1; i < array.length; i++) {
			if (array[i - 1] > array[i]) {
				m = i;
				break;
			}
		}
		for (int i = array.length - 1; i >= 0; i--) {
			if (array[i] < array[i - 1]) {
				n = i - 1;
				break;
			}
		}
		// now left shift m and right shift n to make 
		//  min(m to n) > array[m-1] and max(m to n) < array[n+1]
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = m; i <= n; i++) {
			if (array[i] < min) {
				min = array[i];
			}
			if (array[i] > max) {
				max = array[i];
			}
		}
		while (m > 0 && array[m - 1] > min) {
			m--;
		}
		while (n < array.length - 1 && array[n + 1] < max) {
			n++;
		}

		System.out.println(m + " to " + n);
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19 };
		findNandM(array);
	}
}
