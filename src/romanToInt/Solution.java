package romanToInt;

//I 	1
//V 	5
//X 	10
//L 	50
//C 	100
//D 	500
//M 	1,000

//Given a roman numeral, convert it to an integer.
//
//Input is guaranteed to be within the range from 1 to 3999.
public class Solution {
	public int romanToInt(String s) {
		int ret = 0;
		int cur = 0;
		int prob = cur;
		while (cur < s.length()) {
			char c = s.charAt(cur);
			if (isOne(c)) {
				prob = cur;
				while (prob + 1 < s.length()
						&& (s.charAt(prob + 1) == c
								|| s.charAt(prob + 1) == getRelativeFive(c) || s
								.charAt(prob + 1) == getRelativeTen(c))) {
					prob++;
				}
				ret += getInt(s.substring(cur, prob + 1));
				cur = prob + 1;
			} else {
				prob = cur;
				while (prob + 1 < s.length()
						&& s.charAt(prob + 1) == getRelativeOne(c)) {
					prob++;
				}
				ret += getInt(s.substring(cur, prob + 1));
				cur = prob + 1;
			}
		}

		return ret;
	}

	int getInt(String s) {
		int last = s.length() - 1;
		// first is one
		if (isOne(s.charAt(0))) {
			// last is five
			if (isFive(s.charAt(last))) {
				return getTen(s.charAt(0)) * (5 - last);
			}
			// last is ten
			else if (s.charAt(last) != s.charAt(0)) {
				return getTen(s.charAt(0)) * 9;
			}
			// a streak of ones
			else
				return getTen(s.charAt(0)) * s.length();
		}
		// first is five
		else {
			return getTen(s.charAt(0)) * (5 + last);
		}
	}

	int getTen(char oneOrFive) {
		switch (oneOrFive) {
		case ('I'):
		case ('V'):
			return 1;
		case ('X'):
		case ('L'):
			return 10;
		case ('C'):
		case ('D'):
			return 100;
		case ('M'):
			return 1000;
		default:
			return -1;
		}
	}

	char getRelativeOne(char five) {
		switch (five) {
		case ('V'):
			return 'I';
		case ('L'):
			return 'X';
		case ('D'):
			return 'C';
		default:
			return '!';
		}
	}

	char getRelativeFive(char one) {
		switch (one) {
		case ('I'):
			return 'V';
		case ('X'):
			return 'L';
		case ('C'):
			return 'D';
		default:
			return '!';
		}
	}

	char getRelativeTen(char one) {
		switch (one) {
		case ('I'):
			return 'X';
		case ('X'):
			return 'C';
		case ('C'):
			return 'M';
		default:
			return '!';
		}
	}

	boolean isOne(char c) {
		if (c == 'I' || c == 'X' || c == 'C' || c == 'M')
			return true;
		return false;
	}

	boolean isFive(char c) {
		if (c == 'V' || c == 'L' || c == 'D')
			return true;
		return false;
	}

	public static void main(String[] args) {
		String s = "MMMCMXCIX";
		System.out.println(new Solution().romanToInt(s));
	}
}
