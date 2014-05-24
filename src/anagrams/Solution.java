package anagrams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//Given an array of strings, return all groups of strings that are anagrams.
//
//Note: All inputs will be in lower-case.
public class Solution {
	public ArrayList<String> anagrams2(String[] strs) {
		Map<String, List<String>> backMap = new HashMap<String, List<String>>();
		ArrayList<String> ret = new ArrayList<String>();
		for (String s : strs) {
			String key = sortStr(s);
			if (!backMap.containsKey(key)) {
				backMap.put(key, new LinkedList<String>());
			}
			backMap.get(key).add(s);
		}
		for (Map.Entry<String, List<String>> entry : backMap.entrySet()) {
			if (entry.getValue().size() > 1)
				ret.addAll(entry.getValue());
		}
		return ret;
	}

	String sortStr(String s) {
		char[] cc = s.toCharArray();
		Arrays.sort(cc);
		return new String(cc);
	}

	// the trick is we don't really need O(n^2) to iterate
	// just do a O(n) iteration, use a map<String, List<String>> to store
	// signature and the actual string
	public ArrayList<String> anagrams(String[] strs) {
		Map<String, List<String>> backMap = new HashMap<String, List<String>>();
		for (String s : strs) {
			String signature = getSignature(s);
			if (backMap.containsKey(signature)) {
				backMap.get(signature).add(s);
			} else {
				List<String> newList = new LinkedList<String>();
				newList.add(s);
				backMap.put(signature, newList);
			}
		}
		ArrayList<String> ret = new ArrayList<String>();
		for (List<String> l : backMap.values()) {
			if (l.size() > 1) {
				for (String s : l)
					ret.add(s);
			}
		}
		return ret;
	}

	String getSignature(String s1) {
		int[] ret = new int[26];
		for (char c : s1.toCharArray()) {
			ret[c - 'a']++;
		}
		return Arrays.toString(ret);
	}

	boolean isAnagram(String s1, String s2) {
		int[] back = new int[26];
		if (s1.length() != s2.length())
			return false;
		for (int i = 0; i < 26; i++) {
			back[i] = 0;
		}
		for (int i = 0; i < s1.length(); i++) {
			back[s1.charAt(i) - 'a']++;
			back[s2.charAt(i) - 'a']--;
		}

		for (int i : back)
			if (i != 0)
				return false;
		return true;
	}

	public static void main(String[] args) {
		String[] strs = { "abc", "bcd", "bca", "dbc" };
		ArrayList<String> ret = new Solution().anagrams2(strs);
		System.out.println(ret);
	}
}
