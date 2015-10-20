package countPrimes;

import java.util.Arrays;

//Description:
//
//Count the number of prime numbers less than a non-negative number, n.
public class CountPrimes {
	public static void main(String[] args) {

	}

	public int countPrimes(int n) {
		if (n == 0 || n == 1) {
			return 0;
		}
		boolean[] primeMasks = getPrimeMasks(n);
		int ret = 0;
		for (int i = 1; i < n; i++) {
			if (primeMasks[i]) {
				ret++;
			}
		}
		return ret;
	}

	boolean[] getPrimeMasks(int n) {
		boolean[] masks = new boolean[n];
		Arrays.fill(masks, true);
		masks[1] = false;
		int primeIndex = 2;
		// We only need to check sqrt(n) numbers, as if we go beyond this the big numbers are 
		// already tested
		while (primeIndex * primeIndex < masks.length) {
			for (int i = primeIndex * primeIndex; i < masks.length; i += primeIndex) {
				masks[i] = false;
			}
			primeIndex++;
			while (primeIndex < masks.length && !masks[primeIndex]) {
				primeIndex++;
			}
		}
		return masks;
	}
}
