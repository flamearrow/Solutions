package sqrtX;

//Implement int sqrt(int x).
//
//Compute and return the square root of x
public class Solution {
	public int sqrt(int x) {
		if (x <= 1)
			return x;
		int left = 0, right = x / 2;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (mid == 0)
				mid++;
			if (x / mid > mid) {
				left = mid + 1;
			} else if (x / mid < mid) {
				right = mid - 1;
			} else {
				return mid;
			}
		}
		return left - 1;
	}

	public static void main(String[] args) {
		// for (int i = 0; i <= Integer.MAX_VALUE; i++)
		int i = Integer.MAX_VALUE;
		System.out.println(i + " : " + new Solution().sqrt(i));
	}
}
