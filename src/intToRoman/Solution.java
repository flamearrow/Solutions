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
		System.out.println(new Solution().intToRoman(0));
	}
}
