package crackingTheCodingInterview.c18;

//count number of 2s from 0 to n
//e.g n=12, there would be 2 2s
public class Question4Count2s {
	// count them digit by digit
	// at digit i: 
	//  if(n[i] > 2) then we see 2 (a+1)*mod times where a is the digit after i
	// 			e.g 234, for digit 3, since 3>2, we see 2 (2+1)*mod times 
	//  if(n[i] < 2) then we see 2 (a)*mod times
	//			e.g 231, for digit 1, since 1<2, we see 2 2*mod times
	//  if(n[i] == 2) then we see 2 (a)*mod times + (b+1) times where b is the number from i to end
	//          e.g for 324, for digit 2, since 2==2, we see 2 (3*mod + 24 + 1) times
	static int count2(int n) {
		int mod = 10;
		int cur = n;
		int ret = 0;
		while (cur > 0) {
			if (cur % 10 == 2) {
				ret += (cur / 10) * mod / 10;
				ret += n - cur * mod / 10 + 1;
			}
			if (cur % 10 < 2) {
				ret += (cur / 10) * mod / 10;
			}
			if (cur % 10 > 2) {
				ret += (cur / 10 + 1) * mod / 10;
			}
			mod *= 10;
			cur /= 10;
		}
		return ret;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 13; i++)
			System.out.println(count2(i));
	}
}
