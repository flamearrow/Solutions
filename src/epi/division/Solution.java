package epi.division;
// 5.5
// calculate y/x using only +, -, shift
public class Solution {
	// y/x = (y-x)/x + 1 when y >= x
	//     = 0 when y < x
	static int divide(int y, int x) {
		if (y == 0)
			return 0;
		if (y < x)
			return 0;
		return 1 + divide(y - x, x);
	}

	// note we can do fewer recursion by looking for the max k such that y-(2^k)x > 0
	// in which case y/x = (y-(2^k)x)/x + 2^k
	static int divideFancy(int y, int x) {
		if (y == 0)
			return 0;
		if (y < x)
			return 0;
		int k = 0;
		// x * (2^k) == x << k
		while (y > (x << k)) {
			k++;
		}
		return (2 << k) + divide(y - (x << k), x);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1025; i++) {
			if (divide(1024, 3) != (1024 / 3)) {
				System.out.println(i + " is wrong!");
			}
		}
	}
}
