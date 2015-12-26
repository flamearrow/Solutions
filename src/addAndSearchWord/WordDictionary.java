package addAndSearchWord;

import implementTrie.Trie;

//Design a data structure that supports the following two operations:
//
//void addWord(word)
//bool search(word)
//search(word) can search a literal word or a regular expression string containing 
// only letters a-z or . 
//A . means it can represent any one letter.
//
//For example:
//
//addWord("bad")
//addWord("dad")
//addWord("mad")
//search("pad") -> false
//search("bad") -> true
//search(".ad") -> true
//search("b..") -> true
//Note:
//You may assume that all words are consist of lowercase letters a-z.
public class WordDictionary {

	public static void main(String[] args) {
		WordDictionary w = new WordDictionary();
		w.addWord("ran");
		w.addWord("rune");
		w.addWord("runner");
		w.addWord("runs");
		w.addWord("add");
		w.addWord("adds");
		w.addWord("adder");
		w.addWord("addee");
		System.out.println(w.search("add."));
	}

	Trie mTrie;

	public WordDictionary() {
		mTrie = new Trie();
	}

	// so the idea is to build it as a trie, when we see dots, do a dfs search
	// for a match
	// Adds a word into the data structure.
	public void addWord(String word) {
		mTrie.insert(word);
	}

	// Returns if the word is in the data structure. A word could
	// contain the dot character '.' to represent any one letter.
	public boolean search(String word) {
		if (word.contains(".")) {
			return mTrie.searchFancy(word);
		} else {
			return mTrie.search(word);
		}
	}
}
