package minimumWindowSubstring;

import java.util.HashMap;

//Given a string S and a string T, 
//find the minimum window in S which will contain all 
//the characters in T in complexity O(n).
//
//For example,
//S = "ADOBECODEBANC"
//T = "ABC"
//
//Minimum window is "BANC".
//
//Note:
//If there is no such window in S that covers all characters in T, 
//return the emtpy string "".
//
//If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S. 
public class Solution {
	// build a HashMap<Char, Int> to represent how many char we still need to
	// satisfy a window
	// keep two pointers left and right, first move right until hash map has no
	// 0 value - we have found a window
	// then move right until we break a window - when there's value over 0
	// then move left when all key drops to 0 again
	// keep doing this until we break a window and right can't move further
	public String minWindow(String S, String T) {
		HashMap<Character, Integer> needs = new HashMap<Character, Integer>();
		for (char c : T.toCharArray()) {
			if (needs.containsKey(c)) {
				needs.put(c, needs.get(c) + 1);
			} else {
				needs.put(c, 1);
			}
		}
		int left = 0, right = 0;
		char[] sChars = S.toCharArray();
		int minWindow = Integer.MAX_VALUE;
		int targetLeft = -1, targetRight = -1;
		while (true) {
			// not a window, we need to probe further, hopefully find a window
			if (!isWindow(needs)) {
				// reach right end, can't get further chars to form window
				if (right == sChars.length) {
					break;
				}
				if (needs.containsKey(sChars[right])) {
					needs.put(sChars[right], needs.get(sChars[right]) - 1);
				}
				right++;
			}
			// found a window, we need to shrink left, hopefully find a smaller
			// window
			else {
				if (right - left < minWindow) {
					targetLeft = left;
					targetRight = right;
					minWindow = right - left;
				}
				if (needs.containsKey(sChars[left])) {
					needs.put(sChars[left], needs.get(sChars[left]) + 1);
				}
				left++;
			}
		}
		if (targetLeft >= 0)
			return S.substring(targetLeft, targetRight);
		else
			return "";
	}

	boolean isWindow(HashMap<Character, Integer> needs) {
		for (int value : needs.values()) {
			if (value > 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		String S = "ABC";
		String T = "ABC";
		System.out.println(new Solution().minWindow(S, T));
	}
}
