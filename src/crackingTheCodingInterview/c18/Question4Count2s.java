package crackingTheCodingInterview.c18;

// count number of 2s from 0 to n
// e.g n=12, there would be 2 2s
public class Question4Count2s {
	int count2(int n) {
		int mod = 10;
		int ret = 0;
		while (mod < n) {
			//			ret += n / mod * mod / 10;
			ret += n / 10;
			mod *= 10;
		}
		return ret;
	}
}
