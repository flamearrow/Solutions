package implementTrie;

//Implement a trie with insert, search, and startsWith methods.
//
//Note:
//You may assume that all inputs are consist of lowercase letters a-z.
class TrieNode {
	boolean terminate;
	TrieNode[] indices;

	// Initialize your data structure here.
	public TrieNode() {
		indices = new TrieNode[26];
	}

	TrieNode getChild(char c) {
		return indices[c - 'a'];
	}

	TrieNode insert(char c) {
		if (indices[c - 'a'] == null) {
			indices[c - 'a'] = new TrieNode();
		}
		return indices[c - 'a'];
	}
}

public class Trie {
	protected TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	// Inserts a word into the trie.
	public void insert(String word) {
		TrieNode cur = root;
		for (char c : word.toCharArray()) {
			cur = cur.insert(c);
		}
		cur.terminate = true;
	}

	// Returns if the word is in the trie.
	public boolean search(String word) {
		TrieNode cur = root;
		for (char c : word.toCharArray()) {
			cur = cur.getChild(c);
			if (cur == null) {
				return false;
			}
		}
		return cur.terminate;
	}

	// word contains dot.
	public boolean searchFancy(String word) {
		return startSearchFancy(root, word.toCharArray(), 0);
	}

	boolean startSearchFancy(TrieNode cur, char[] arr, int curIndex) {
		char curChar = arr[curIndex];
		if (arr[curIndex] != '.') {
			TrieNode next = cur.getChild(curChar);
			if (next == null) {
				return false;
			} else {
				if (curIndex == arr.length - 1) {
					if (next.terminate) {
						return true;
					} else {
						return false;
					}
				} else {
					return startSearchFancy(next, arr, curIndex + 1);
				}
			}
		} else {
			for (TrieNode next : cur.indices) {
				if (next != null) {
					// dot is at the end and there's a word end at here, return
					// true
					if (curIndex == arr.length - 1) {
						if (next.terminate) {
							return true;
						} else {
							// note here we continue because there might another node ends with dot here
							continue;
						}
					}
					// otherwise search for each other non null children
					if (startSearchFancy(next, arr, curIndex + 1)) {
						return true;
					}
				}
			}
			return false;
		}
	}

	// Returns if there is any word in the trie
	// that starts with the given prefix.
	public boolean startsWith(String prefix) {
		TrieNode cur = root;
		for (char c : prefix.toCharArray()) {
			cur = cur.getChild(c);
			if (cur == null) {
				return false;
			}
		}
		return true;
	}
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");