package zigzagConversion;

import java.util.ArrayList;
import java.util.List;

//The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
//
//P   A   H   N
//A P L S I I G
//Y   I   R
//
//And then read line by line: "PAHNAPLSIIGYIR"
//
//Write the code that will take a string and make this conversion given a number of rows:
//
//string convert(string text, int nRows);
//
//convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR". 
public class Solution {

	public String convert(String s, int nRows) {
		if (nRows == 1)
			return s;
		List<StringBuilder> lists = new ArrayList<StringBuilder>(nRows);
		for (int i = 0; i < nRows; i++) {
			lists.add(new StringBuilder());
		}
		boolean goingDown = true;
		int index = 0;
		for (char c : s.toCharArray()) {
			lists.get(index).append(c);
			if (goingDown) {
				if (index + 1 < nRows)
					index++;
				else {
					goingDown = false;
					index--;
				}
			} else {
				if (index - 1 >= 0)
					index--;
				else {
					goingDown = true;
					index++;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (StringBuilder subSub : lists) {
			sb.append(subSub);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(new Solution().convert("PAYPALISHIRING", 2));
	}
}
