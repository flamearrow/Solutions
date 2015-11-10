package wordPattern2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Given a pattern and a string str, find if str follows the same pattern.
//
//Here follow means a full match, such that there is a bijection between 
//a letter in pattern and a non-empty substring in str.
//
//Examples:
//pattern = "abab", str = "redblueredblue" should return true.
//pattern = "aaaa", str = "asdasdasdasd" should return true.
//pattern = "aabb", str = "xyzabcxzyabc" should return false.
//Notes:
//You may assume both pattern and str contains only lowercase letters.
public class Solution {
	main
	public boolean wordPatternMatch(String pattern, String str) {
		return doMatch(new HashMap<Character, String>(), new HashSet<String>(),
				pattern, str);
	}

	boolean doMatch(Map<Character, String> map, Set<String> set, String p,
			String str) {
		if (p.length() == 0 && str.length() == 0) {
			return true;
		}
		if (p.length() == 0 || str.length() == 0) {
			return false;
		}
		Character c = p.charAt(0);
		if (map.containsKey(c)) {
			String value = map.get(c);
			if (str.startsWith(value)) {
				return doMatch(map, set, p.substring(1),
						str.substring(value.length()));
			} else {
				return false;
			}
		}
		for (int i = 1; i <= str.length(); i++) {
			String value = str.substring(0, i);
			if (set.contains(value)) {
				continue;
			}
			set.add(value);
			map.put(c, value);
			if (doMatch(map, set, p.substring(1), str.substring(i))) {
				return true;
			}
			set.remove(value);
			map.remove(c);
		}
		return false;
	}
}
