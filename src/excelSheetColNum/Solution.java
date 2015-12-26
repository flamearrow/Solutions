package excelSheetColNum;

//Related to question Excel Sheet Column Title
//
//Given a column title as appear in an Excel sheet, return its corresponding column number.
//
//For example:
//
//    A -> 1
//    B -> 2
//    C -> 3
//    ...
//    Z -> 26
//    AA -> 27
//    AB -> 28 

// convert them back and forth
public class Solution {
	public static void main(String[] args) {
		System.out.println(new Solution().titleToNumber("Z"));
		System.out.println(new Solution().convertToTitle(26));
	}

	public int titleToNumber(String s) {
		char[] arr = s.toCharArray();
		int ret = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			ret += (arr[i] - 'A' + 1) * Math.pow(26, (s.length() - 1 - i));
		}
		return ret;
	}

	public String convertToTitle(int n) {
		String table = "ZABCDEFGHIJKLMNOPQRSTUVWXY";
		StringBuilder sb = new StringBuilder();
		while (n > 0) {
			int mod = n % 26;
			sb.insert(0, table.charAt(mod));
			// mod==0 is a z, we remove this manually
			if (mod == 0) {
				n -= 26;
			}
			n /= 26;
		}
		return sb.toString();
	}
}
