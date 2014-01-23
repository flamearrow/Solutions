package divideTwoIntegers;

//Divide two integers without using multiplication, division and mod operator. 
public class Solution {
	// do exponential back off for dividend, then start over for residue
	int divide(int dividend, int divisor) {
		long rem = (long) dividend > 0 ? (long) dividend : 0 - (long) dividend;
		long divisorL = (long) divisor > 0 ? (long) divisor
				: 0 - (long) divisor;
		long ret = 0;

		while (rem >= divisorL) {
			long c = divisorL;
			for (int i = 0; rem >= c; i += 1) {
				rem -= c;
				ret += 1 << i;
				c = divisorL << (i + 1);
			}
		}
		boolean positive = dividend > 0 && divisor > 0 || dividend < 0
				&& divisor < 0;
		return (int) (positive ? ret : 0 - ret);
	}

	// first exponential back off, then bSearch
	// this is crap
	public int divideCrap(int dividend, int divisor) {
		long lDividend = dividend;
		long lDivisor = divisor;
		if (lDividend == 0)
			return 0;
		boolean negative = true;
		if (lDividend > 0 && lDivisor > 0 || lDividend < 0 && lDivisor < 0) {
			negative = false;
		}

		lDividend = lDividend > 0 ? lDividend : 0 - lDividend;
		lDivisor = lDivisor > 0 ? lDivisor : 0 - lDivisor;
		if (lDividend < lDivisor)
			return 0;
		if (lDividend == lDivisor)
			return negative ? -1 : 1;
		long d = lDivisor;
		long r = 1;
		while (lDividend >= d) {
			if (lDividend - d < lDivisor) {
				return (int) (negative ? 0 - r : r);
			}
			d = d << 1;
			r = r << 1;
		}
		long leftD = d >> 1;
		long rightD = d;
		long leftR = r >> 1;
		long rightR = r;
		while (leftD < rightD) {
			long midD = leftD + ((rightD - leftD) >> 1);
			long midR = leftR + ((rightR - leftR) >> 1);
			if (lDividend - midD < 0) {
				rightD = midD;
				rightR = midR;
			} else if (lDividend - midD >= lDivisor) {
				leftD = midD;
				leftR = midR;
			} else {
				return (int) (negative ? 0 - midR : midR);
			}
		}
		return -1;
	}

	// naive
	public int divideNaive(int dividend, int divisor) {
		int ret = 0;
		while (dividend >= divisor) {
			dividend -= divisor;
			ret++;
		}
		return ret;
	}

	public static void main(String[] args) {
		// System.out.println(-2147483648);
		System.out.println(new Solution().divide(-2147483648, 1));
	}
}
