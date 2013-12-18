package validPalindrome;

//Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
//
//For example,
//"A man, a plan, a canal: Panama" is a palindrome.
//"race a car" is not a palindrome.
//
//Note:
//Have you consider that the string might be empty? This is a good question to ask during an interview.
//
//For the purpose of this problem, we define empty string as valid palindrome.
public class Solution {
	public boolean isPalindrome(String s) {
		if (s.length() == 0)
			return true;
		char[] charArr = s.toCharArray();
		int start = 0, end = charArr.length - 1;
		while (start < end - 1) {
			while (notChar(charArr[start])) {
				if (start == end)
					return true;
				start++;
			}
			while (notChar(charArr[end])) {
				if (start == end)
					return true;
				end--;
			}
			if (!eqlIgnoreCase(charArr[start], charArr[end]))
				return false;
			start++;
			end--;
		}

		// odd
		if (start == end)
			return true;
		// even
		else if (start > end || end < start)
			return true;
		else {
			if (notChar(charArr[start]) || notChar(charArr[end])) {
				return true;
			} else {
				return eqlIgnoreCase(charArr[start], charArr[end]);
			}
		}
	}

	boolean eqlIgnoreCase(char c1, char c2) {
		if (c1 > 'Z')
			c1 -= 'a' - 'A';
		if (c2 > 'Z')
			c2 -= 'a' - 'A';
		return c1 == c2;
	}

	boolean notChar(char c) {
		return c < '0' || c > '9' && c < 'A' || c > 'Z' && c < 'a' || c > 'z';
	}

	public static void main(String[] args) {
		System.out.println(new Solution().isPalindrome("1a2"));
	}
}
