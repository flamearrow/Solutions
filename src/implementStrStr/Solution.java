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
	
	// using Knuth-Morris-Pratt algorithm
	public String strStrKMP(String haystack, String needle) {
		if (needle.length() == 0)
			return haystack;
		int[] skipList = buildSkipList(needle);
		int hayCur = 0, needleCur = 0;
		int hayLen = haystack.length();
		int needleLen = needle.length();
		// reason why it's O(n): we actually have two pointers: hayCur and (hayCur+needleCur)
		//  !!none of the two pointers move back when probing!!
		// though we reset needleCur to 0, (hayCur+needleCur) never falls back
		while (hayCur + needleCur < hayLen) {
			if (haystack.charAt(hayCur + needleCur) == needle.charAt(needleCur)) {
				needleCur++;
				if (needleCur == needleLen) {
					return haystack.substring(hayCur);
				}
			} else {
				// the first char doesn't match, then we have no choice but advancing hayCur
				if (needleCur == 0) {
					hayCur++;
					needleCur = 0;
				}
				// otherwise we can jump
				// now partial match length is needleCur
				// we can skip hayCur by more than 1
				else {
					// skipped length == currentMatched length - skipList[l-1]
					//  note shorter the skipList[] length is, the more we can jump
					// i.e for [a b c a e] and [a b c a c a b c d e]
					//  the list would be [0 0 0 1 0]
					//  when we mismatch at last e, the number we can jump is 4-1=3
					// /: hayCur, |: hayCur+needleCur
					//  /       |       /         |
					// [a b c a e] and [a b c a c a b c d e]
					// after skipping, we would have
					//        / |             / |
					// [a b c a e] and [a b c a c a b c d e]
					// note neither / nor | move back
					int skippedLen = needleCur - skipList[needleCur - 1];
					hayCur += skippedLen;
					needleCur -= skippedLen;
				}
			}
		}
		return null;
	}

	// skip lst: s[i] would be the length of the longest matching suffix and prefix(can't be equal) of needle.substring(0, i+1)
	//  e.g       need.substring() = aaaa, then the length would be 3 because the longest matching suffix and prefix is 'aaa'
	// we can build the list in O(k) time: just keep the length of last index, for next index j,
	//  if needle[length] == needle[j], then it means we can extend the previous suffix by one
	//  otherwise the length of j would be 0
	int[] buildSkipList(String needle) {
		int[] ret = new int[needle.length()];
		int prevLen = 0;
		ret[0] = 0;
		for (int i = 1; i < needle.length(); i++) {
			if (needle.charAt(i) == needle.charAt(prevLen)) {
				ret[i] = prevLen + 1;
			} else {
				ret[i] = 0;
			}
			prevLen = ret[i];
		}
		return ret;
	}
	
	
	public static void main(String[] args) {
		String haystack = "mlgbmlgbaoao";
		String needle = "gbml";
		System.out.println(new Solution().strStr(haystack, needle));
	}
}
