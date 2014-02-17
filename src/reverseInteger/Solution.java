package reverseInteger;

//Reverse digits of an integer.
//
//Example1: x = 123, return 321
//Example2: x = -123, return -321
//
//click to show spoilers.
//Have you thought about this?
//
//Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!
//
//If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.
//
//Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?
//
//Throw an exception? Good, but what if throwing an exception is not an option? You would then have to re-design the function (ie, add an extra parameter).

public class Solution {
	// assume no overflow
	public int reverseNew(int x) {
		boolean n = x < 0 ? true : false;
		if (n)
			x = -x;
		int ret = 0;
		while (x > 0) {
			ret *= 10;
			ret += x % 10;
			x /= 10;
		}
		return n ? 0 - ret : ret;
	}

	// assume no overflow
	public int reverse(int x) {
		boolean negative = false;
		if (x < 0) {
			negative = true;
			x = 0 - x;
		}
		int mod = 1;
		int ret = 0;
		while (x >= mod) {
			ret *= 10;
			ret += (x % (mod * 10)) / mod;
			mod *= 10;
		}
		return negative ? 0 - ret : ret;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().reverse(-100));
		System.out.println(new Solution().reverseNew(-100));
	}
}
