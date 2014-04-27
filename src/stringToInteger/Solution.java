package stringToInteger;

//Implement atoi to convert a string to an integer.
//
//Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.
//
//Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

// some edge cases I can come up with...
// leading/trailing spaces
// minus/plus
// leading zeros
// sandwiched spaces
// overflow - return Integer.MAX or Integer.MIN
// trailing chars -discarded

public class Solution {
	enum State {
		LEADING_SPACE, SIGN, DIGITS
	}

	public int atoi2(String str) {
		State s = State.LEADING_SPACE;
		long ret = 0;
		boolean pos = true;
		int cur = 0;
		while (cur < str.length()) {
			switch (s) {
			case LEADING_SPACE:
				if (str.charAt(cur) == ' ') {
					cur++;
				} else if (str.charAt(cur) == '+' || str.charAt(cur) == '-') {
					s = State.DIGITS;
					pos = str.charAt(cur) == '-' ? false : true;
					cur++;
				} else if (isDigit(str.charAt(cur))) {
					s = State.DIGITS;
				}
				// invalid
				else {
					return 0;
				}
				break;
			case SIGN:
				if (isDigit(str.charAt(cur))) {
					s = State.DIGITS;
					cur++;
				}
				// invalid
				else {
					return 0;
				}
				break;
			case DIGITS:
				if (isDigit(str.charAt(cur))) {
					s = State.DIGITS;
					ret = ret * 10 + str.charAt(cur) - '0';
					if (pos && ret >= Integer.MAX_VALUE) {
						return Integer.MAX_VALUE;
					} else if (!pos && (0 - ret) <= Integer.MIN_VALUE) {
						return Integer.MIN_VALUE;
					}
					cur++;
				} else {
					if (pos) {
						return (int) ret;
					} else {
						return 0 - (int) ret;
					}
				}
				break;
			default:
			}
		}
		// if we reach here, the last char of string is a digit
		if (pos) {
			return (int) ret;
		} else {
			return 0 - (int) ret;
		}
	}

	boolean isDigit(char c) {
		if (c < '0' || c > '9')
			return false;
		return true;
	}

	// use a little state machine, cheating by trimming the string and use Long.parseLong()
	// we can use a counter adder to replace Long.parseLong(), when counter overflows, return the edge.
	public static final int STATUS_START = 0;
	public static final int STATUS_MINUSPLUS = 1;
	public static final int STATUS_DIGITS = 2;

	public int atoi(String str) {
		int status = STATUS_START;
		String s = str.trim();
		int end = -1;
		here: for (int i = 0; i < s.length(); i++) {
			switch (status) {
			case STATUS_START:
				if (s.charAt(i) == ' ') {
					status = STATUS_START;
				} else if (s.charAt(i) == '+' || s.charAt(i) == '-')
					status = STATUS_MINUSPLUS;
				else if (s.charAt(i) <= '9' && s.charAt(i) >= '0')
					status = STATUS_DIGITS;
				else
					return 0;
				break;
			case STATUS_MINUSPLUS:
				if (s.charAt(i) <= '9' && s.charAt(i) >= '0') {
					status = STATUS_DIGITS;
				} else
					return 0;
				break;
			case STATUS_DIGITS:
				if (s.charAt(i) < '0' || s.charAt(i) > '9') {
					end = i;
					break here;
				}
				break;

			}
		}
		if (status != STATUS_DIGITS)
			return 0;
		if (end < 0)
			end = s.length();
		long l = Long.parseLong(s.substring(0, end));
		if (l < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
		if (l > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		else
			return (int) l;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().atoi2(""));
	}
}
