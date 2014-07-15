package digitsToWords;

// given a number in digits, translate it into english words
// 1,234,567,890,123
// one trillion two hundred and thirty four million five hundred and sixty 
public class Solution {
	static String[] buf = { "", "thousand", "million", "trillion" };
	static String[] buf2 = { "one", "two", "three", "four", "five", "six",
			"seven", "eigth", "nine", "ten", "eleven", "twelve", "thirteen",
			"fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen", "twenty" };
	static String[] buf3 = { "ten", "twenty", "thirty", "forty", "fifty",
			"sixty", "seventy", "eigthy", "ninety" };
	static String hundred = "hundred";

	String convert(int i) {
		if (i == 0)
			return "zero";
		StringBuilder sb = new StringBuilder();
		int bufCur = 0;
		while (i > 0) {
			int cur = i % 1000;
			// now cur is from 0 to 999
			if (cur != 0) {
				String curStr = buildCurStr(cur);
				sb.insert(0, buf[bufCur]);
				sb.insert(0, ' ');
				sb.insert(0, curStr);
				sb.insert(0, ' ');
			}
			bufCur++;
			i /= 1000;
		}
		sb.replace(0, 1, "");
		return sb.toString();
	}

	String buildCurStr(int cur) {
		if (cur < 20) {
			return buf2[cur - 1];
		} else {
			StringBuilder sb = new StringBuilder();
			int h = cur / 100;
			if (h > 0) {
				sb.append(buf2[h - 1]);
				sb.append(' ');
				sb.append(hundred);
				sb.append(' ');
			}
			cur %= 100;
			if (cur != 0) {
				if (cur < 20) {
					sb.append(buf2[cur - 1]);
				} else {
					int ten = cur / 10;
					sb.append(buf3[ten - 1]);
					if (cur % 10 != 0) {
						sb.append(' ');
						sb.append(buf2[cur % 10 - 1]);
					}
				}
			}
			return sb.toString();
		}
	}

	public static void main(String[] args) {
		int i = 23;
		System.out.println(i + ":" + new Solution().convert(i));
	}

}
