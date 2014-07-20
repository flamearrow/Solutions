package sqrtX;

//Implement int sqrt(int x).
//
//Compute and return the square root of x
public class Solution {
	static final double THRD = 0.0000000000001;

	public double sqrt2(int x) {
		if (x == 0)
			return 0;
		double left = 0, right = x;
		while (left < right) {
			double mid = (left + right) / 2;
			int thresholdRst = withinThreshold(mid, x);
			if (thresholdRst == 0) {
				return mid;
			} else if (thresholdRst == 1) {
				right = mid;
			} else {
				left = mid;
			}
		}
		return (double) -1;
	}

	int withinThreshold(double mid, int x) {
		double sqr = mid * mid;
		if (Math.abs(x - sqr) < THRD) {
			return 0;
		} else if (sqr > x) {
			return 1;
		} else {
			return -1;
		}
	}

	public int sqrt(int x) {
		if (x <= 1)
			return x;
		int left = 0, right = x;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (x / mid == mid)
				return mid;
			if (x / mid > mid)
				left = mid + 1;
			else {
				right = mid - 1;
			}
		}
		return left - 1;
	}

	public static void main(String[] args) {
		for (int i = 0; i <= 100; i++)
			//		int i = 100;
			System.out.println(i + " : " + new Solution().sqrt2(i));
	}
}
