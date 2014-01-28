package implementStrStr;

//implement strstr()
//Returns a pointer to the first occurrence of needle in haystack, or null if needle is not part of haystack.
public class Solution {
	// naive
	public String strStr(String haystack, String needle) {
		if (needle.length() == 0)
			return haystack;
		int needleLen = needle.length();
		int cur = 0;
		while (cur < haystack.length() - needleLen + 1) {
			if (needle.equals(haystack.substring(cur, cur + needleLen)))
				return haystack.substring(cur);
			cur++;
		}
		return null;
	}

	public static void main(String[] args) {
		String haystack = "mlgbmlgbaoao";
		String needle = "gbml";
		System.out.println(new Solution().strStr(haystack, needle));
	}
}
