package crackingTheCodingInterview.c17;

// find the max of two numbers with use of comparison operators or if else
public class Question4MaxOfTwoNums {
	// let the sign of (a-b)=s, which would be 1 or 0
	// then the max would be (1-s)*a + s*b
	// this won't work if (a-b) overflows, we need to check when one is neg and one is pos
	// when one is pos and one is neg, we need to return the pos one by using their signs
	static int max(int a, int b) {
		// check the condition when one is pos and one is neg to avoid overflow
		int multSign = getSign(a * b);
		if (multSign == 1) {
			int aSign = getSign(a);
			int bSign = getSign(b);
			// return the positive one with sign==0
			return a * bSign + b * aSign;
		}
		// in this case return the pos number
		// mask higher bits!
		int diffSign = getSign(a - b);
		return (1 - diffSign) * a + diffSign * b;
	}

	static int getSign(int n) {
		return (n >> 31) & 1;
	}

	public static void main(String[] args) {
		System.out.println(max(Integer.MAX_VALUE, -1));
	}
}
