package primes;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {
	// first mark all as prime, then cross over turn by turn
	ArrayList<Integer> primes(int n) {
		boolean[] buf = new boolean[n + 1];
		Arrays.fill(buf, true);
		buf[2] = true;
		int nextPrime = 2;
		while (nextPrime != -1) {
			for (int i = nextPrime * nextPrime; i < buf.length; i += nextPrime) {
				buf[i] = false;
			}
			int idx = nextPrime + 1;
			while (idx < buf.length && !buf[idx])
				idx++;
			if (idx >= buf.length)
				nextPrime = -1;
			else
				nextPrime = idx;
		}
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int i = 1; i < buf.length; i++) {
			if (buf[i])
				ret.add(i);
		}
		return ret;
	}

	public static void main(String[] args) {
		for (int i : new Solution().primes(23))
			System.out.println(i);
	}
}
