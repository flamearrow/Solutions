package implementStrStr;

//implement strstr()
//Returns a pointer to the first occurrence of needle in haystack, or null if needle is not part of haystack.
public class Solution {

	public String strStr4(String haystack, String needle) {
		int[] skipList = buildSkipList3(needle);
		int hPtr = 0, nPtr = 0;
		while (hPtr + nPtr < haystack.length()) {
			if (haystack.charAt(hPtr + nPtr) == needle.charAt(nPtr)) {
				nPtr++;
				if (nPtr == needle.length())
					return haystack.substring(hPtr);
			} else {
				if (nPtr == 0)
					hPtr++;
				else {
					int skipped = nPtr - skipList[nPtr - 1];
					nPtr -= skipped;
					hPtr += skipped;
				}
			}
		}
		return null;
	}

	public String strStr3(String haystack, String needle) {
		int[] skipList = buildSkipList3(needle);
		int hPtr = 0, nPtr = 0;
		while (hPtr + nPtr <= haystack.length()) {
			if (nPtr == needle.length()) {
				return haystack.substring(hPtr);
			} else {
				if (hPtr + nPtr == haystack.length()) {
					break;
				}
				if (haystack.charAt(hPtr + nPtr) == needle.charAt(nPtr)) {
					nPtr++;
				} else {
					if (nPtr == 0) {
						nPtr = 0;
						hPtr++;
					} else {
						// skip (matched length - skipList[nPtr-1])
						int skip = nPtr - skipList[nPtr - 1];
						hPtr += skip;
						nPtr -= skip;
					}
				}
			}
		}
		return null;
	}

	int[] buildSkipList3(String needle) {
		int[] ret = new int[needle.length()];
		for (int i = 1; i < ret.length; i++) {
			if (needle.charAt(i) == needle.charAt(ret[i - 1])) {
				ret[i] = ret[i - 1] + 1;
			}
		}
		return ret;
	}

	public String strStr2(String haystack, String needle) {
		if (needle.length() == 0)
			return haystack;
		int[] skipList = buildSkipList2(needle);
		int hPtr = 0, nPtr = 0;
		// hPtr+nPtr is the advancing ptr
		while (hPtr + nPtr < haystack.length()) {
			if (haystack.charAt(hPtr + nPtr) == needle.charAt(nPtr)) {
				nPtr++;
				if (nPtr == needle.length())
					return haystack.substring(hPtr);
			} else {
				// skipLen = matched length - skipLen[nPtr-1]
				if (nPtr > 0) {
					int skipLen = nPtr - skipList[nPtr - 1];
					hPtr += skipLen;
					nPtr -= skipLen;
				}
				// no match, advance hPtr
				else {
					hPtr++;
					nPtr = 0;
				}
			}
		}
		return null;
	}

	int[] buildSkipList2(String needle) {
		int[] ret = new int[needle.length()];
		for (int i = 1; i < needle.length(); i++) {
			int prevLen = ret[i - 1];
			if (needle.charAt(i) == needle.charAt(prevLen)) {
				ret[i] = prevLen + 1;
			}
		}
		return ret;
	}

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
		String haystack = "mississippi";
		String needle = "issip";
		System.out.println(new Solution().strStr4(haystack, needle));
	}
}
