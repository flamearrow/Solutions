package lengthOfLastWord;

//Given a string s consists of upper/lower-case alphabets and empty space 
// characters ' ', return the length of last word in the string.
//
//If the last word does not exist, return 0.
//
//Note: A word is defined as a character sequence consists of non-space characters only.
//
//For example,
//Given s = "Hello World",
//return 5. 
public class Solution {
	public int lengthOfLastWord(String s) {
		if (s == null)
			return 0;
		int index = s.length() - 1;
		int ret = 0;
		while (index >= 0 && s.charAt(index) == ' ') {
			index--;
		}
		// string with all spaces
		if (index == 0)
			if (s.charAt(0) == ' ')
				return 0;
			else
				return 1;

		while (index >= 0 && s.charAt(index) != ' ') {
			ret++;
			index--;
		}
		return ret;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().lengthOfLastWord("feaf fae feawf f"));
	}
}
