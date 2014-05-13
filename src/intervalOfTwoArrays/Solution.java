package intervalOfTwoArrays;

public class Solution {
	static void findInterval(int[] arr1, int[] arr2) {
		int arr1Ptr = 0, arr2Ptr = 0;
		while (arr1Ptr < arr1.length && arr2Ptr < arr2.length) {
			if (arr1[arr1Ptr] > arr2[arr2Ptr]) {
				arr2Ptr++;
			} else if (arr1[arr1Ptr] < arr2[arr2Ptr]) {
				arr1Ptr++;
			} else {
				System.out.print(arr1[arr1Ptr] + " ");
				arr1Ptr++;
				arr2Ptr++;
			}
		}
	}

	public static void main(String[] args) {
		int[] arr1 = { 1, 3, 4, 5, 7, 9 };
		int[] arr2 = { 2, 4, 6, 8, 9 };
		findInterval(arr1, arr2);
	}
}
