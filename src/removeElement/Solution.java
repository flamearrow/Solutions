package removeElement;

//Given an array and a value, remove all instances of that value in place and return the new length.
//
//The order of elements can be changed. It doesn't matter what you leave beyond the new length. 
public class Solution {
	public int removeElement(int[] A, int elem) {
		int start = 0, end = A.length - 1;
		while (start < end) {
			if (A[start] == elem) {
				int tmp = A[start];
				A[start] = A[end];
				A[end] = tmp;
				end--;
			} else {
				start++;
			}
		}
		if (start < A.length) {
			if (A[start] == elem)
				return start;
			else
				return start + 1;
		} else
			return 0;
	}

	public static void main(String[] args) {
		int[] A = {};
		System.out.println(new Solution().removeElement(A, 2));
		for (int i : A)
			System.out.print(i + " ");
	}
}
