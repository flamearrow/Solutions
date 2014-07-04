package minimumWindowSubstring;

import java.util.HashMap;
import java.util.Map;

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
//return the empty string "".
//
//If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S. 
public class Solution {

	// count <= 0 means we found all required chars at the moment
	public String minWindow2(String US, String UT) {
		// suppose all cap
		int[] quota = new int[26];
		int start = 0, end = -1;
		int count = UT.length();
		for (char c : UT.toCharArray()) {
			quota[c - 'A']++;
		}
		int minWindow = Integer.MAX_VALUE, minStart = -1, minEnd = -1;
		boolean expanding = true;
		while (true) {
			if (expanding) {
				end++;
				if (end == US.length())
					break;
				char c = US.charAt(end);
				// find a valid one, might have found all, check count
				// if count==0 then we found all, begin shrinking
				if (--quota[c - 'A'] >= 0) {
					count--;
					if (count == 0) {
						if (end - start < minWindow) {
							minStart = start;
							minEnd = end;
							minWindow = end - start;
						}
						expanding = false;
					}
				}
				// otherwise we need to continue expanding
			} else {
				char c = US.charAt(start);
				start++;
				// we have removed a valid char, need to expand
				if (++quota[c - 'A'] > 0) {
					count++;
					expanding = true;
				}
				// otherwise the removed start is not required
				// we probably found a smaller window, continue shrinking
				else {
					if (end - start < minWindow) {
						minStart = start;
						minEnd = end;
						minWindow = end - start;
					}
				}
			}
		}
		if (minStart < 0)
			return "";
		else
			return US.substring(minStart, minEnd + 1);
	}

	public String minWindowMap(String US, String UT) {
		Map<Character, Integer> quota = new HashMap<Character, Integer>();
		int start = 0, end = -1;
		int count = UT.length();
		for (char c : UT.toCharArray()) {
			if (quota.containsKey(c)) {
				quota.put(c, quota.get(c) + 1);
			} else {
				quota.put(c, 1);
			}
		}
		int minWindow = Integer.MAX_VALUE, minStart = -1, minEnd = -1;
		boolean expanding = true;
		while (true) {
			if (expanding) {
				end++;
				if (end == US.length())
					break;
				char c = US.charAt(end);
				// find a valid one, might have found all, check count
				// if count==0 then we found all, begin shrinking
				if (!quota.containsKey(c))
					continue;
				quota.put(c, quota.get(c) - 1);
				if (quota.get(c) >= 0) {
					count--;
					if (count == 0) {
						if (end - start < minWindow) {
							minStart = start;
							minEnd = end;
							minWindow = end - start;
						}
						expanding = false;
					}
				}
				// otherwise we need to continue expanding
			} else {
				char c = US.charAt(start);
				start++;

				boolean movedValid = false;
				if (quota.containsKey(c)) {
					quota.put(c, quota.get(c) + 1);
					if (quota.get(c) > 0)
						movedValid = true;
				}

				// we have removed a valid char, need to expand
				if (movedValid) {
					count++;
					expanding = true;
				}
				// otherwise the removed start is not required
				// we probably found a smaller window, continue shrinking
				else {
					if (end - start < minWindow) {
						minStart = start;
						minEnd = end;
						minWindow = end - start;
					}
				}
			}
		}
		if (minStart < 0)
			return "";
		else
			return US.substring(minStart, minEnd + 1);
	}

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
		String S = "ABDDCBNA";
		String T = "ACB";
		System.out.println(new Solution().minWindowMap(S, T));
	}
}
