package shortestWordDistance2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// mutiple calls
public class WordDistance {
	Map<String, List<Integer>> map;

	public WordDistance(String[] words) {
		map = new HashMap<>();
		for (int i = 0; i < words.length; i++) {
			String s = words[i];
			if (!map.containsKey(s)) {
				map.put(s, new LinkedList<Integer>());
			}
			map.get(s).add(i);
		}
	}

	public int shortest(String word1, String word2) {
		List<Integer> s1 = map.get(word1);
		List<Integer> s2 = map.get(word2);
		int p1 = 0, p2 = 0;
		int ret = Integer.MAX_VALUE;
		while (p1 < s1.size() && p2 < s2.size()) {
			ret = Math.min(ret, Math.abs(s1.get(p1) - s2.get(p2)));
			if (s1.get(p1) > s2.get(p2)) {
				p2++;
			} else {
				p1++;
			}
		}
		return ret;
	}
}

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");