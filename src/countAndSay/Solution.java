package countAndSay;

//The count-and-say sequence is the sequence of integers beginning as follows:
//1, 11, 21, 1211, 111221, ...
//
//1 is read off as "one 1" or 11.
//11 is read off as "two 1s" or 21.
//21 is read off as "one 2, then one 1" or 1211.
//
//Given an integer n, generate the nth sequence.
//
//Note: The sequence of integers will be represented as a string. 
public class Solution {
	public String countAndSay(int n) {
		if (n == 1)
			return "1";
		else {
			String prev = countAndSay(n - 1);
			int count = 0;
			char digit = prev.charAt(0);
			int index = 0;
			StringBuilder sb = new StringBuilder();
			while (index < prev.length()) {
				if (prev.charAt(index) == digit) {
					count++;
				} else {
					sb.append(count);
					sb.append(digit);
					count = 1;
					digit = prev.charAt(index);
				}
				index++;
			}
			sb.append(count);
			sb.append(digit);
			return sb.toString();
		}
	}

	public static void main(String[] args) {
		System.out.println(new Solution().countAndSay(3));
	}
}
