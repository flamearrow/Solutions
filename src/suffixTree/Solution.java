package suffixTree;

// find the longest substring that appears more than once from a string
// e.g "abcabcaacb" -> "abc"
// e.g "aababa" -> "aba"
public class Solution {
	String longestDupSubstring(String s) {
		SuffixTree tree = new SuffixTree();
		for (int i = s.length() - 1; i >= 0; i--) {
			tree.insert(s.substring(i));
		}
		return tree.sub;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().longestDupSubstring("abcabcaacb"));
	}
}

class SuffixTree {
	TrieNode root;
	int maxLvl;
	String sub;

	public SuffixTree() {
		root = new TrieNode(0);
		maxLvl = 0;
		sub = null;
	}

	public void insert(String s) {
		TrieNode cur = root;
		for (int i = 0; i < s.length(); i++) {
			char curChar = s.charAt(i);
			cur.insert(curChar);
			cur = cur.children[curChar - 'a'];
			if (cur.dup && cur.lvl > maxLvl) {
				maxLvl = cur.lvl;
				sub = s.substring(0, i + 1);
			}
		}
	}
}

class TrieNode {
	TrieNode[] children;
	int lvl;
	boolean dup;

	public TrieNode(int lvl) {
		children = new TrieNode[26];
		this.lvl = lvl;
		dup = false;
	}

	public void insert(char c) {
		if (children[c - 'a'] == null) {
			children[c - 'a'] = new TrieNode(lvl + 1);
		} else {
			children[c - 'a'].dup = true;
		}
	}
}