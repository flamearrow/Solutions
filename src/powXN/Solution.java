package powXN;

//Implement pow(x, n).
public class Solution {
	// non rec b search - avoid stack overflow
	// i.e 2^5: 5 == 0b101 === 1&0b100 + 0&0b010 + 1&0b001
	// therefore we have 2^7 = 1*2^4 + 0*2^2 + 1*2^1
	// can be achieved by looping throw the binary representation of n
	public double pow(double x, int n) {
		if (x == 0)
			return 0;
		if (n == 0)
			return 1;
		if (n == 1)
			return x;
		boolean minus = false;
		if (n < 0) {
			n = -n;
			minus = true;
		}
		double ret = 1, cur = x;
		while (n > 0) {
			// this bit will be taken
			if (n % 2 == 1) {
				ret *= cur;
			}
			cur *= cur;
			n >>= 1;
		}
		return minus ? 1 / ret : ret;
	}

	// sleek b search
	public double powRec(double x, int n) {
		if (x == 0)
			return 0;
		if (n == 0)
			return 1;
		if (n == 1)
			return x;
		if (n < 0)
			return 1 / powRec(x, -n);
		if (n % 2 == 1)
			return x * powRec(x * x, n / 2);
		else
			return powRec(x * x, n / 2);
	}

	public double powExponentialBackoff(double x, int n) {
		if (n == 0)
			return 1;
		double ret = x;
		int pow = 1;
		while ((pow << 1) <= n) {
			ret *= ret;
			pow <<= 1;
		}
		int rest = n - pow;
		while (rest-- > 0) {
			ret *= x;
		}

		return ret;
	}

	public double powNaive(double x, int n) {
		double ret = 1;
		while (n-- > 0)
			ret *= x;
		return ret;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().pow(2, -3));
	}
}
