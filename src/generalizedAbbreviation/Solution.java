package generalizedAbbreviation;

import java.util.LinkedList;
import java.util.List;

//Write a function to generate the generalized abbreviations of a word.
//
//Example:
//Given word = "word", return the following list (order does not matter):
//["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", 
//"w1r1", "1o2", "2r1", "3d", "w3", "4"]
public class Solution {
	public static void main(String[] args) {
		String word = "word";
		for (String s : new Solution().generateAbbreviations(word)) {
			System.out.print(s + " ");
		}
	}

	public List<String> generateAbbreviations(String word) {
		List<String> ret = new LinkedList<String>();
		ret.add(word);
		doShit(word, 0, ret);
		return ret;
	}

	// start from start, try to replace non digit in word for all possible lengths
	void doShit(String word, int start, List<String> ret) {
		if (start >= word.length()) {
			return;
		}
		// we make sure after begin there's no digit
		// note we need to check length equality
		for (int length = 1; length <= word.length() - start; length++) {
			for (int begin = start; begin + length <= word.length(); begin++) {
				String lengthStr = "" + length;
				String newStr = word.substring(0, begin) + lengthStr
						+ word.substring(begin + length);
				ret.add(newStr);
				doShit(newStr, begin + lengthStr.length() + 1, ret);
			}
		}
	}
}
