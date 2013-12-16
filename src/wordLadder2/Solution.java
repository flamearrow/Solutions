package wordLadder2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

//Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:
//
//Only one letter can be changed at a time
//Each intermediate word must exist in the dictionary
//For example,
//
//Given:
//start = "hit"
//end = "cog"
//dict = ["hot","dot","dog","lot","log"]
//Return
//  [
//    ["hit","hot","dot","dog","cog"],
//    ["hit","hot","lot","log","cog"]
//  ]
//Note:
//All words have the same length.
//All words contain only lowercase alphabetic characters.
public class Solution {
	public ArrayList<ArrayList<String>> findLadders(String start, String end,
			HashSet<String> dict) {
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();

		Queue<Node> q = new ArrayDeque<Node>();
		Set<String> visited = new HashSet<String>();
		q.offer(new Node(start));
		q.offer(new Node("\0"));
		boolean newLvl = true;
		int len = start.length();
		char[] backArr = new char[len];
		while (!q.isEmpty()) {
			Node next = q.poll();
			// no nodes btn two level, no more, we're done
			if (next.s.equals("\0"))
				return ret;
			visited.add(next.s);
			if (newLvl) {
				newLvl = false;
			}
			if (q.peek().s.equals("\0")) {
				newLvl = true;
				q.poll();
			}
			here: for (int i = 0; i < len; i++) {
				backArr = next.s.toCharArray();
				for (char c = 'a'; c <= 'z'; c++) {
					backArr[i] = c;
					String newStr = new String(backArr);
					if (newStr.equals(end)) {
						ArrayList<String> newList = new ArrayList<String>();
						newList.add(0, end);
						while (next != null) {
							newList.add(0, next.s);
							next = next.prev;
						}
						ret.add(newList);
						break here;
					}
					if (!visited.contains(newStr) && dict.contains(newStr)) {
						visited.add(newStr);
						Node n = new Node(newStr);
						n.prev = next;
						q.offer(n);
					}
				}
			}

			if (newLvl) {
				q.offer(new Node("\0"));
			}
		}
		return ret;
	}

	class Node {
		Node prev;
		String s;

		public Node(String s) {
			this.s = s;
		}

		@Override
		public String toString() {
			return s + " ";
		}
	}

	public static void main(String[] args) {
		HashSet<String> dict = new HashSet<String>();
		// dict.add("ab");
		dict.add("hot");
		dict.add("dog");
		dict.add("dot");
		dict.add("lot");
		dict.add("log");
		for (ArrayList<String> list : new Solution().findLadders("hit", "cog",
				dict)) {
			for (String s : list) {
				System.out.print(s + " ");
			}
			System.out.println();
		}
	}

}
