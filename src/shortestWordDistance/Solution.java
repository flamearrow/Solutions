package shortestWordDistance;

import java.util.LinkedList;
import java.util.List;

//Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
//
//For example,
//Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
//
//Given word1 = “coding”, word2 = “practice”, return 3.
//Given word1 = "makes", word2 = "coding", return 1.
public class Solution {
	// find two closest distance in two sorted arrays: 
	//  use two pointer, calculate distance, update min
	//  then increment the pointer to the smaller number
	public int shortestDistance(String[] words, String word1, String word2) {
		List<Integer> s1 = new LinkedList<>();
		List<Integer> s2 = new LinkedList<>();
		for (int i = 0; i < words.length; i++) {
			String s = words[i];
			if (s.equals(word1)) {
				s1.add(i);
			}
			if (s.equals(word2)) {
				s2.add(i);
			}
		}
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
