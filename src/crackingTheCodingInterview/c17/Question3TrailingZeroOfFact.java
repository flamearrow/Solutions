package crackingTheCodingInterview.c17;

// Write an algorithm to count number of trailing zeros in n factorial
public class Question3TrailingZeroOfFact {
	// first count the number of nums that has factor of 5
	// then 25
	// then 125
	// ...
	static int countZero(int n) {
		int ret = 0;
		while (n >= 5) {
			ret += n / 5;
			n /= 5;
		}
		return ret;
	}

	public static void main(String[] args) {
		System.out.println(countZero(12345));
	}
}
