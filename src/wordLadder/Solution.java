package wordLadder;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

//Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:
//
//Only one letter can be changed at a time
//Each intermediate word must exist in the dictionary
//For example,
//
//Given:
//start = "hit"
//end = "cog"
//dict = ["hot","dot","dog","lot","log"]
//As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//return its length 5.
//
//Note:
//Return 0 if there is no such transformation sequence.
//All words have the same length.
//All words contain only lowercase alphabetic characters.
public class Solution {
	public int ladderLength2(String start, String end, HashSet<String> dict) {
		Queue<String> q = new LinkedList<String>();
		Set<String> visited = new HashSet<String>();
		q.offer(start);
		int lvl = 1;
		int cnt = 1;
		while (!q.isEmpty()) {
			String cur = q.poll();
			if (cur.equals(end)) {
				return lvl;
			}

			for (String child : getChildren(cur, dict)) {
				if (!visited.contains(child)) {
					q.offer(child);
					visited.add(child);
				}
			}

			if (--cnt == 0) {
				lvl++;
				cnt = q.size();
			}
		}
		return -1;
	}

	List<String> getChildren(String cur, HashSet<String> dict) {
		char[] arr = cur.toCharArray();
		List<String> ret = new ArrayList<String>();
		for (int i = 0; i < arr.length; i++) {
			for (char c = 'a'; c <= 'z'; c++) {
				// note here we need to recover arr[i] to its original value after done
				char original = arr[i];
				arr[i] = c;
				String next = new String(arr);
				if (!next.equals(cur) && dict.contains(next)) {
					ret.add(next);
				}
				arr[i] = original;
			}
		}
		return ret;
	}

	public int ladderLength(String start, String end, HashSet<String> dict) {
		Queue<String> q = new ArrayDeque<String>();
		Set<String> visited = new HashSet<String>();
		int jmp = 0;
		q.offer(start);
		q.offer("\0");
		boolean newLvl = true;
		int len = start.length();
		char[] backArr = new char[len];
		while (!q.isEmpty()) {
			String next = q.poll();
			// no nodes btn two level, no more, we're done
			if (next.equals("\0"))
				return 0;
			visited.add(next);
			if (newLvl) {
				jmp++;
				newLvl = false;
			}
			if (q.peek().equals("\0")) {
				newLvl = true;
				q.poll();
			}

			for (int i = 0; i < len; i++) {
				backArr = next.toCharArray();
				for (char c = 'a'; c <= 'z'; c++) {
					backArr[i] = c;
					String newStr = new String(backArr);
					if (newStr.equals(end)) {
						return jmp + 1;
					}
					if (!visited.contains(newStr) && dict.contains(newStr)) {
						visited.add(newStr);
						q.offer(newStr);
					}
				}
			}

			if (newLvl) {
				q.add("\0");
			}

		}
		return 0;
	}

	// it's naive because when trying to search for leaf, it's checking all
	// strings left in the toBeVisited set, which is O(n)
	// we can use O(l) to probe by replacing 26 letters of each char of a string
	public int ladderLengthNaive(String start, String end, HashSet<String> dict) {
		// BFS
		Queue<String> q = new ArrayDeque<String>();
		Set<String> toBeVisited = new HashSet<String>(dict);
		Set<String> toRemove = new HashSet<String>();
		int jmp = 0;
		q.offer(start);
		q.offer("\0");
		boolean newLvl = true;
		while (!q.isEmpty()) {
			String next = q.poll();
			// no nodes btn two level, can't serach more, we're don
			if (next.equals("\0"))
				return 0;
			toBeVisited.remove(next);
			if (newLvl) {
				jmp++;
				newLvl = false;
			}
			if (q.peek().equals("\0")) {
				newLvl = true;
				q.poll();
			}

			if (canTransform(next, end))
				return jmp + 1;
			for (String s : toBeVisited) {
				if (canTransform(s, next)) {
					toRemove.add(s);
					q.add(s);
				}
			}
			// can't modify the set in the previous loop, will throw
			// concurrentModificationException
			for (String s : toRemove) {
				toBeVisited.remove(s);
			}
			toRemove.clear();

			if (newLvl) {
				q.add("\0");
			}
		}
		return 0;
	}

	// if from and to only differs in one letter, return true
	boolean canTransform(String from, String to) {
		if (from.equals(to))
			return false;
		int dif = 0;
		for (int i = 0; i < from.length(); i++) {
			if (from.charAt(i) != to.charAt(i)) {
				dif++;
				if (dif > 1)
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		HashSet<String> dict = new HashSet<String>();
		// dict.add("ab");
		dict.add("hot");
		dict.add("dog");
		dict.add("dot");
		//		dict.add("cog");
		//		dict.add("tot");
		//		dict.add("hop");
		//		dict.add("pot");
		//		dict.add("hog");
		//		System.out
		//				.println(new PeekingIterator().ladderLengthNaive("hot", "hot", dict));
		System.out.println(new Solution().ladderLength2("hot", "dog", dict));
	}
}
