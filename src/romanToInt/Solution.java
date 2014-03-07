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
	
	
	static Map<Character, Integer> backMap;
	static {
		int[] integer = { 1000, 500, 100, 50, 10, 5, 1 };
		char[] roman = { 'M', 'D', 'C', 'L', 'X', 'V', 'I' };
		backMap = new HashMap<Character, Integer>();
		for (int i = 0; i < integer.length; i++) {
			backMap.put(roman[i], integer[i]);
		}
	}
	// the best way: just check s[i] and s[i+1], if s[i] >= s[i+1], then we need to add, otherwise we minus
	int romanToInt2(String roman) {
		int ret = backMap.get(roman.charAt(roman.length() - 1));
		for (int i = roman.length() - 2; i >= 0; i--) {
			char curChar = roman.charAt(i);
			if (backMap.get(curChar) >= backMap.get(roman.charAt(i + 1))) {
				ret += backMap.get(curChar);
			} else {
				ret -= backMap.get(curChar);
			}
		}
		return ret;
	}
	
	// a smarter and easier idea, map all possible values!
	// this can be used in intToRoman as well
	public int romanToInt(String s) {
		int[] integer = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] roman = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X",
				"IX", "V", "IV", "I" };
		int cur = 0;
		int ret = 0;
		while (cur < s.length()) {
			int i = -1;
			String sub = null;
			if (cur < s.length() - 1) {
				sub = s.substring(cur, cur + 2);
				for (int j = 0; j < roman.length; j++) {
					if (sub.equals(roman[j])) {
						i = j;
						break;
					}
				}
			}
			if (i > 0) {
				ret += integer[i];
				cur += 2;
			} else {
				sub = s.substring(cur, cur + 1);
				for (int j = 0; j < roman.length; j++) {
					if (sub.equals(roman[j])) {
						i = j;
						break;
					}
				}
				ret += integer[i];
				cur += 1;
			}
		}
		return ret;
	}

	public int romanToIntOld(String s) {
		// to be cleaner, search from start, probe for a valid candidate each
		// time
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
		System.out.println(new Solution().romanToIntOld(s));
	}
}
