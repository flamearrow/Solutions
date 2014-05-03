package sqrtX;

//Implement int sqrt(int x).
//
//Compute and return the square root of x
public class Solution {
	public int sqrt(int x) {
		if (x <= 1)
			return x;
		int left = 0, right = x;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (x/mid == mid)
				return mid;
			if (x/mid > mid)
				left = mid + 1;
			else {
				right = mid - 1;
			}
		}
		return left-1;
	}

	public static void main(String[] args) {
		for (int i = 0; i <= 100; i++)
			//		int i = 100;
			System.out.println(i + " : " + new Solution().sqrt(i));
	}
}
