package missing8;

// there's no number 8 in a country, ppl there will skip all numbers that contain 8
// i.e their number counts as follows:
//   1 2 3 4 5 6 7 9 10 11 12 13 14 15 16 17 19 20
// write a function to convert their numbers into regular human readable numbers
public class Solution {
	// a easier solution would be regard this as a 9-base number, but with some check - 
	// if the digit at this bit is smaller than 8, then it is what it is, if it's 9, then we need to regard it as 8 

	// use a mod added from 1 to get each digit...
	int convert(int weirdNo) {
		int ret = 0;
		int mod = 1;
		while (weirdNo >= Math.pow(10, mod - 1)) {
			int cur = (int) Math.pow(10, mod);
			int curDigit = (weirdNo % cur) / (cur / 10);
			if (curDigit == 9) {
				ret += 8 * Math.pow(9, mod - 1);
			} else {
				ret += curDigit * Math.pow(9, mod - 1);
			}
			mod++;
		}
		return ret;
	}
	
	// easy way, just divid weirdNo by 10 each time and get the last one
	int convert2(int weirdNo) {
		int ret = 0;
		int mag = 0;
		while (weirdNo > 0) {
			int cur = weirdNo % 10;
			if (cur == 9) {
				ret += 8 * Math.pow(9, mag);
			} else {
				ret += cur * Math.pow(9, mag);
			}
			mag++;
			weirdNo /= 10;
		}
		return ret;
	}

	// right shift the number and increase magnitude in each round
	// use a recursion to calculate total number skipped at mag L
	//   mag 1 - 10: skpped 1, or skipped (1 + 9 * mag(0)) 
	//   mag 2 - 100: skipped 10 + 9 * mag(1)
	//   mag 3 - 1000: skipped 100 + 9 * mag(2)
	// this is crap
	int convertCrap(int weirdNo) {
		int back = weirdNo;
		int skipped = 0;
		int mag = 1;
		while (weirdNo % 10 > 0) {
			int mod = weirdNo % 10;
			skipped += calSkipped(mod, mag);
			mag++;
			weirdNo /= 10;
		}
		return back - skipped;
	}

	int calSkipped(int num, int mag) {
		if (num > 7) {
			return num * skippedAtMag(mag - 1) + skippedAtMag(mag);
		} else {
			return num * skippedAtMag(mag - 1);
		}
	}

	// mag=0 : 0
	// mag=1 : 9 * mag(0) + 10 ^ (1-1)
	// mag=2 : 9 * mag(1) + 10 ^ (2-1)
	// mag=3 : 9 * mag(2) + 10 ^ (3-1)
	int skippedAtMag(int mag) {
		if (mag == 0)
			return 0;
		else {
			int exp = 1;
			for (int i = 1; i < mag; i++) {
				exp *= 10;
			}
			return 9 * skippedAtMag(mag - 1) + exp;
		}
	}

	public static void main(String[] args) {
		System.out.println(new Solution().convert2(19));
	}
}
