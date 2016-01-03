package validNumber;

//Validate if a given string is numeric.
//
//Some examples:
//"0" => true
//" 0.1 " => true
//"abc" => false
//"1 a" => false
//"2e10" => true
//
//Note: It is intended for the problem statement to be ambiguous. 
//You should gather all requirements up front before implementing one. 

// some edge cases
// "2 e10" is it valid? -false
// "2e 10" is it valid? -false
// "2E10" is it valid? -true
// "20E9" is it valid? -true
// "2E01" is it valid? -true
// ".1" is it valid? -true
// "3." is it valid? -ture - this is shitty
// "." -false
// "46.e3"
public class Solution {

	public boolean isNumberRegex(String s) {
		// regex 101:
		// [mlgb] chose one from m, l, g, b
		// ?: match 0 or 1 times
		// *: match 0 or more times
		// +: match 1 or more times
		// {10}: match 10 times
		// (regex1|regex2|regex3) match regex1 or regex2 or regex3, can append {10} to match 10 times
		// \\d match digits
		// \\D match non digits
		// \\w match word chars a-zA-Z_0-9
		// \\W match non word chars
		String regex = "[+-]?(\\d+|\\d*\\.\\d+)(e[+-]?\\d+)?";
		// the commented one passed oj, but it regards "3." as a valid number
		// String regex = "[+-]?(\\d+|\\d+\\.\\d*|\\d*\\.\\d+)(e[+-]?\\d+)?";
		return s.trim().matches(regex);
	}

	// this is shitty
	// state transfer, what's valid for next char?
	// digit - state1
	// space - state2
	// e/E - state3
	// . - state4
	// plusminus

	// the trick is when entering a new state, we need to make sure what is the
	// previous state so that we know what the next state would be

	public static int DIGIT = 1 << 0;
	public static int SPACE = 1 << 1;
	public static int eE = 1 << 2;
	public static int DOT = 1 << 3;
	public static int PLUSMINUS = 1 << 4;
	public static int LEADING_SPACE = DIGIT | DOT | SPACE | PLUSMINUS;
	public static int LEADING_PLUSMINUS = DIGIT | DOT;
	public static int TRAILING_SPACE = SPACE;

	public boolean isNumber(String s) {
		if (s.length() == 0)
			return false;
		int cur = 0;
		int state = LEADING_SPACE;
		while (cur < s.length()) {
			char c = s.charAt(cur);
			cur++;
			// space is legal
			if (c == ' ' && ((state & SPACE) > 0)) {
				// can only be in leading or trailing
				if (state != LEADING_SPACE) {
					state = TRAILING_SPACE;
				} else {
					// we have found an empty string
					if (cur == s.length())
						return false;
				}
			}
			// digit is legal
			else if (isDigit(c) && ((state & DIGIT) > 0)) {
				// we find first digit
				if (state == LEADING_SPACE || state == LEADING_PLUSMINUS) {
					state = DIGIT | DOT | eE | SPACE;
				}
				// we have seen eE before
				else if (state == (DIGIT | PLUSMINUS)) {
					state = DIGIT | SPACE;
				}
				// we have seen dot before
				else if (state == (DIGIT | SPACE | eE)) {
					state = DIGIT | SPACE | eE;
				}
				// two scenarios
				// a) ".1" after .
				// 2) "1.2e+3" after +
				else if (state == DIGIT) {
					if (s.charAt(cur - 2) == '.') {
						state = DIGIT | SPACE | eE;
					} else {
						state = DIGIT | SPACE;
					}
				}

				// otherwise are still expecting DIGIT | DOT | eE | SPACE
			}
			// dot is legal
			else if (c == '.' && ((state & DOT) > 0)) {
				// if we have found nothing before the dot, we can only expect
				// for a digit, because "." won't be valid
				if (state == LEADING_SPACE || state == LEADING_PLUSMINUS) {
					// if we have reached end then we have found something like
					// "  +." or "." or " -.", which isn't valid, return false
					if (cur == s.length())
						return false;
					state = DIGIT;
				}
				// otherwise we expect digit or space or eE, "2.e10" is valid
				else
					state = DIGIT | SPACE | eE;
			}
			// eE is legal
			else if ((c == 'e' || c == 'E') && ((state & eE) > 0)) {
				// we have found something ends with eE
				if (cur == s.length()) {
					return false;
				}
				// after eE, we expect digit or plusminus
				state = DIGIT | PLUSMINUS;
			} else if ((c == '-' || c == '+') && (state & PLUSMINUS) > 0) {
				// we have found something end with -
				if (cur == s.length())
					return false;
				if (state == LEADING_SPACE)
					state = LEADING_PLUSMINUS;
				// other wise we have seen something like "1.2e+2"
				else {
					state = DIGIT;
				}
			} else {
				return false;
			}
		}
		return true;
	}

	boolean isDigit(char c) {
		return ('0' <= c) && (c <= '9');
	}

	public boolean isNumber2(String s) {
		return s.matches("^\\s*[+-]?(\\d+|\\d*\\.\\d+|\\d+\\.\\d*)([eE][+-]?\\d+)?\\s*$");
	}

	public static void main(String[] args) {
		//		String[] ss = { "1e+1e1", "1e1.1", "1e-1.1", "1e-11", "1e11", "1e+11" };
		//		for (String s : ss)
		//			System.out.println(new PeekingIterator().isNumber(s));
		String s = "  .2e81";
		System.out.println(new Solution().isNumberRegex(s));
	}
}
