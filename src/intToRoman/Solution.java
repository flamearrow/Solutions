package intToRoman;

//I 	1
//V 	5
//X 	10
//L 	50
//C 	100
//D 	500
//M 	1,000

//Given an integer, convert it to a roman numeral.
//
//Input is guaranteed to be within the range from 1 to 3999.
public class Solution {
	// map all special cases, not we need to map 9xx and 4xx
	// the idea is always find the biggest number to append to sb
	public String intToRoman3(int num) {
		int[] nums = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X",
				"IX", "V", "IV", "I" };
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while (num > 0) {
			int times = num / nums[i];
			num -= times * nums[i];
			while (times > 0) {
				sb.append(symbols[i]);
				times--;
			}
			i++;
		}
		return sb.toString();
	}

	// use a back string to ease searching...
	static String codes = "IVXLCDM";

	String intToRoman2(int i) {
		StringBuilder sb = new StringBuilder();
		int cur = 0;
		while (i > 0) {
			int value = i % 10;
			sb.insert(0, getChar(value, cur));
			i /= 10;
			cur += 2;
		}
		return sb.toString();
	}

	String getChar(int value, int cur) {
		if (value == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		// 1-3
		if (value < 4) {
			for (int i = 0; i < value; i++) {
				sb.append(codes.charAt(cur));
			}
		}
		// 4
		else if (value == 4) {
			sb.append(codes.charAt(cur));
			sb.append(codes.charAt(cur + 1));
		}
		// 5-8
		else if (value < 9) {
			// over five
			sb.append(codes.charAt(cur + 1));
			for (int i = 5; i < value; i++) {
				sb.append(codes.charAt(cur));
			}
		}
		// 9
		else {
			sb.append(codes.charAt(cur));
			sb.append(codes.charAt(cur + 2));
		}

		return sb.toString();
	}

	public String intToRoman(int num) {
		StringBuilder sb = new StringBuilder();
		for (int mod = 1000; mod > 0; mod /= 10) {
			int toAppend = (num / mod) % 10;
			sb.append(getRomanExpr(toAppend, mod));
		}
		return sb.toString();
	}

	String getRomanExpr(int toAppend, int mod) {
		if (toAppend == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		char one = ' ', five = ' ', ten = ' ';
		switch (mod) {
		case (1):
			one = 'I';
			five = 'V';
			ten = 'X';
			break;
		case (10):
			one = 'X';
			five = 'L';
			ten = 'C';
			break;
		case (100):
			one = 'C';
			five = 'D';
			ten = 'M';
			break;
		case (1000):
			one = 'M';
			break;
		default:
			break;
		}

		if (toAppend < 4) {
			for (int i = 0; i < toAppend; i++) {
				sb.append(one);
			}
			return sb.toString();
		}
		if (toAppend == 4) {
			sb.append(one);
			sb.append(five);
			return sb.toString();
		}
		if (toAppend < 9) {
			sb.append(five);
			for (int i = 0; i < toAppend - 5; i++) {
				sb.append(one);
			}
			return sb.toString();
		}
		// toAppend is 9
		sb.append(one);
		sb.append(ten);
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(new Solution().intToRoman3(2014));
	}
}
