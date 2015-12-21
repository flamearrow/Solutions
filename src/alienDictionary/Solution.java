package alienDictionary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

//There is a new alien language which uses the latin alphabet. 
// However, the order among letters are unknown to you. 
// You receive a list of words from the dictionary, where words are sorted 
// lexicographically by the rules of this new language. Derive the order of letters in this language.
//
//For example,
//Given the following words in dictionary,
//
//[
//  "wrt",
//  "wrf",
//  "er",
//  "ett",
//  "rftt"
//]
//The correct order is: "wertf".
//
//Note:
//You may assume all letters are in lowercase.
//If the order is invalid, return an empty string.
//There may be multiple valid order of letters, return any one of them is fine.
public class Solution {
	public static void main(String[] args) {
		// String[] words = { "wrt", "wrf", "er", "ett", "rftt" };
		String[] words = { "abcd", "efghi" };
		System.out.println(new Solution().alienOrder(words));
	}

	public String alienOrder(String[] words) {
		Map<Character, Set<Character>> map = buildMap(words);
		StringBuilder sb = new StringBuilder();
		while (map.size() > 0) {
			char nextHead = stripHeadlessLetter(map, sb);
			if (nextHead == 0) {
				return "";
			}
			sb.append(nextHead);
		}
		return sb.toString();
	}

	Map<Character, Set<Character>> buildMap(String[] words) {
		Map<Character, Set<Character>> map = new HashMap<>();
		doShit(map, words, 0, 0, words.length);
		return map;
	}

	// doShit looks at the column n of each word from words[start] to words[end]
	// DFSly
	void doShit(Map<Character, Set<Character>> map, String[] words, int column,
			int start, int end) {
		if (start == end) {
			return;
		}
		List<Character> chars = new LinkedList<Character>();
		for (int i = start; i < end; i++) {
			if (words[i].length() > column) {
				chars.add(words[i].charAt(column));
			}
		}
		if (!chars.isEmpty() && !map.containsKey(chars.get(0))) {
			map.put(chars.get(0), new HashSet<Character>());
		}
		for (int i = 1; i < chars.size(); i++) {
			char cur = chars.get(i);
			char pre = chars.get(i - 1);
			if (cur == pre) {
				continue;
			}
			if (!map.containsKey(cur)) {
				map.put(cur, new HashSet<Character>());
			}
			map.get(cur).add(pre);
		}
		// recursively call sublists
		int probStart = 0;
		int probEnd = 0;
		while (probStart < chars.size()) {
			while (probEnd < chars.size()
					&& chars.get(probStart) == chars.get(probEnd)) {
				probEnd++;
			}
			// make the indices right: each time, chars starts from zero
			//  when recurse, need to pass in the absolute row of matrix
			//  which is base(start) plus a pointer(probStart)
			doShit(map, words, column + 1, probStart + start, probEnd + start);
			probStart = probEnd;
		}

	}

	// topological sort
	char stripHeadlessLetter(Map<Character, Set<Character>> map,
			StringBuilder visited) {
		for (char c : map.keySet()) {
			Set<Character> pres = map.get(c);
			for (char visitedC : visited.toString().toCharArray()) {
				pres.remove(visitedC);
			}
			if (pres.isEmpty()) {
				map.remove(c);
				return c;
			}
		}
		return 0;
	}
}
