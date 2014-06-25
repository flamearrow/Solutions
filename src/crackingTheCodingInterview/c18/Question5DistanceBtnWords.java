package crackingTheCodingInterview.c18;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Question5DistanceBtnWords {
	static int minDistance(String[] words, String word1, String word2) {
		int min = Integer.MAX_VALUE;
		int lastWord1Position = -1;
		int lastWord2Position = -1;
		int index = 0;
		for (String w : words) {
			if (w.equals(word1)) {
				lastWord1Position = index;
				if (lastWord2Position != -1) {
					int distance = lastWord1Position - lastWord2Position;
					if (distance < min)
						min = distance;
				}
			}
			if (w.equals(word2)) {
				lastWord2Position = index;
				if (lastWord1Position != -1) {
					int distance = lastWord2Position - lastWord1Position;
					if (distance < min)
						min = distance;
				}
			}
			index++;
		}
		return min;
	}

	static int minDistancePrep(String[] words, String w1, String w2) {
		Map<String, List<Integer>> back = new HashMap<String, List<Integer>>();
		int index = 0;
		for (String w : words) {
			if (back.containsKey(w)) {
				back.get(w).add(index);
			} else {
				back.put(w, new LinkedList<Integer>());
				back.get(w).add(index);
			}
			index++;
		}

		// process
		Iterator<Integer> i1 = back.get(w1).iterator();
		Iterator<Integer> i2 = back.get(w2).iterator();
		int left = i1.next();
		int right = i2.next();
		int min = Math.abs(left - right);
		while (i1.hasNext() && i2.hasNext()) {
			if (left > right) {
				if (left - right < min) {
					min = left - right;
				}
				right = i2.next();
			} else if (left < right) {
				if (right - left < min) {
					min = right - left;
				}
				left = i1.next();
			}
		}
		return min;
	}

	public static void main(String[] args) {
		String[] words = { "a", "b", "c", "d", "e", "f", "g", "a", "c" };
		String word1 = "a";
		String word2 = "b";
		System.out.println(minDistance(words, word1, word2));
		System.out.println(minDistancePrep(words, word1, word2));
	}
}
