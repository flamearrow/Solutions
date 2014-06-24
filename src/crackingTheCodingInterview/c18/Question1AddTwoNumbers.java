package crackingTheCodingInterview.c18;

// add two numbers without using any arithmetic operator
public class Question1AddTwoNumbers {
	// split carry values and add values and recursively call
	// the time to terminate recursion is when carry drops to 0
	static int add(int left, int right) {
		if (right == 0)
			return left;
		int add = left ^ right;
		int carry = (left & right) << 1;
		return add(add, carry);
	}

	public static void main(String[] args) {
		System.out.println(add(24, 123123));
	}
}
