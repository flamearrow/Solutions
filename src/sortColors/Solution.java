package sortColors;

//Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
//
//Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
//
//Note:
//You are not suppose to use the library's sort function for this problem. 
public class Solution {
	// keep two pointers tail0 and head2
	// iterate the array tail head2,
	// if i == 0, swap it with tail0
	// if i == 2, swap it with head2
	// if i == 1, move cursor forward
	public void sortColors(int[] A) {
		int tail0 = 0, head2 = A.length - 1, cur = 0;
		int tmp;
		while (cur <= head2) {
			if (A[cur] == 0) {
				if (cur == tail0) {
					cur++;
				} else {
					tmp = A[cur];
					A[cur] = A[tail0];
					A[tail0] = tmp;
				}
				tail0++;
			} else if (A[cur] == 2) {
				tmp = A[cur];
				A[cur] = A[head2];
				A[head2] = tmp;
				head2--;
			} else {
				cur++;
			}
		}
	}

	public static void main(String[] args) {
		int[] A = { 2, 2 };
		new Solution().sortColors(A);
		for (int i : A)
			System.out.println(i);
	}
}
