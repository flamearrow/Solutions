package crackingTheCodingInterview.c17;

// find the max of two numbers with use of comparison operators or if else
public class Question4MaxOfTwoNums {
	// let the sign of (a-b)=s, which would be 1 or 0
	// then the max would be (1-s)*a + s*b
	static int max(int a, int b) {
		// mask higher bits!
		int sign = ((a - b) >> 31) & 1;
		return (1 - sign) * a + sign * b;
	}

	public static void main(String[] args) {
		System.out.println(max(2, 1));
	}
}
