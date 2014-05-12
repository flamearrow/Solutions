package epi.bitToy;

import java.util.HashMap;
import java.util.Map;

// count # of bits set in a streak of ints
public class CountSetBits {
	static Map<Integer, Integer> backMap = null;
	static {
		backMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < 256; i++) {
			backMap.put(i, calculateSetBits(i));
		}
	}

	static int calculateSetBits(int i) {
		int ret = 0;
		int lastBit = 0;
		// only when i == 0 would (i&-i) == 0
		while ((lastBit = (i & -i)) > 0) {
			ret++;
			i ^= lastBit;
		}
		return ret;
	}

	static int countSetBits(int num) {
		int ret = 0;
		for (int i = 0; i < 4; i++) {
			ret += backMap.get((num >> (8 * i)) & 255);
		}
		return ret;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++)
			System.out.println(i + " : " + countSetBits(i));
	}
}
