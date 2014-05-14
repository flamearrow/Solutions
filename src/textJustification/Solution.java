package textJustification;

import java.util.ArrayList;

//Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
//
//You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
//
//Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
//
//For the last line of text, it should be left justified and no extra space is inserted between words.
//
//For example,
//words: ["This", "is", "an", "example", "of", "text", "justification."]
//L: 16.
//
//Return the formatted lines as:
//[
//   "This    is    an",
//   "example  of text",
//   "justification.  "
//]
public class Solution {
	class StringTooLongException extends RuntimeException {
		private static final long serialVersionUID = 1L;
	}

	public ArrayList<String> fullJustify2(String[] words, int L) {
		int curLen = 0;
		ArrayList<String> ret = new ArrayList<String>();
		LinkedList<String> curStrs = new LinkedList<String>();
		for (String str : words) {
			if (curStrs.size() == 0) {
				if (str.length() <= L) {
					curStrs.add(str);
					curLen += str.length();
				} else {
					throw new StringTooLongException();
				}
			} else {
				if (curLen + 1 + str.length() <= L) {
					curStrs.add(str);
					curLen += 1 + str.length();
				} else {
					ret.add(buildNewLine(curStrs, L));
					curStrs.clear();
					curStrs.add(str);
					curLen = str.length();
				}
			}
		}
		if (curStrs.size() > 0)
			ret.add(buildLastLine(curStrs, L));
		return ret;
	}

	String buildNewLine(LinkedList<String> curStrs, int L) {
		if (curStrs.size() == 1) {
			return buildLastLine(curStrs, L);
		}
		StringBuilder sb = new StringBuilder();
		int len = calLen(curStrs);
		int spaceCnt = (L - len) / (curStrs.size() - 1);
		int additionalSpaceCnt = (L - len) % (curStrs.size() - 1);
		boolean first = true;
		for (String s : curStrs) {
			if (!first) {
				for (int i = 0; i < spaceCnt; i++)
					sb.append(' ');
				if (additionalSpaceCnt-- > 0)
					sb.append(' ');
			}
			first = false;
			sb.append(s);
		}
		return sb.toString();
	}

	int calLen(LinkedList<String> curStrs) {
		int ret = 0;
		for (String s : curStrs)
			ret += s.length();
		return ret;
	}

	String buildLastLine(LinkedList<String> curStrs, int L) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String str : curStrs) {
			if (!first) {
				sb.append(' ');
			}
			first = false;
			sb.append(str);
		}
		int padSpace = L - sb.length();
		for (int i = 0; i < padSpace; i++) {
			sb.append(' ');
		}
		return sb.toString();
	}
	
	public ArrayList<String> fullJustify(String[] words, int L) {
		int start = 0, end = 0;
		int curLen = L;
		int wordLenCount = 0;
		ArrayList<String> ret = new ArrayList<String>();
		while (true) {
			if (curLen >= words[end].length()) {
				curLen -= words[end].length();
				wordLenCount += words[end].length();
				end++;
				if (end == words.length)
					break;
				// if we don't hit boundary, add a space for next word
				if (curLen > 0) {
					curLen--;
				}
			}
			// we can't hold more words or we have hit boundary, need to add
			// a new line
			if (curLen < words[end].length() || curLen == 0) {
				StringBuilder sb = new StringBuilder();
				// single word
				if (end == start + 1) {
					sb.append(words[start]);
					sb.append(getSpaceString(L - sb.length()));
				} else {
					// find uneven spaces we need to add
					int oddSpace = (L - wordLenCount) % (end - 1 - start);
					// find avg spaces width
					int spaceWidth = (L - wordLenCount) / (end - 1 - start);
					// build a new Line from start to end
					for (int i = start; i < end - 1; i++) {
						sb.append(words[i]);
						sb.append(getSpaceString(spaceWidth));
						if (oddSpace > 0) {
							sb.append(' ');
							oddSpace--;
						}
					}
					sb.append(words[end - 1]);
				}
				ret.add(sb.toString());

				// continue searching, note we won't skip end here
				start = end;
				curLen = L;
				wordLenCount = 0;
			}
		}

		// now end == words.length, we need to build the last line
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < end - 1; i++) {
			sb.append(words[i]);
			sb.append(' ');
		}
		sb.append(words[end - 1]);
		if (sb.length() < L) {
			sb.append(getSpaceString(L - sb.length()));
		}
		ret.add(sb.toString());

		return ret;
	}

	String getSpaceString(int width) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < width; i++) {
			sb.append(' ');
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String[] words = { "This", "isd", "asn", "exampdlde", "of", "text",
				"justification." };
		for (String s : new Solution().fullJustify(words, 20)) {
			System.out.println(s);
		}
	}
}
