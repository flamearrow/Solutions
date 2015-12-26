package wordSearch2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//Given a 2D board and a list of words from the dictionary, find all words in the board.
//
//Each word must be constructed from letters of sequentially adjacent cell, 
// where "adjacent" cells are those horizontally or vertically neighboring. 
// The same letter cell may not be used more than once in a word.
//
//For example,
//Given words = ["oath","pea","eat","rain"] and board =
//
//[
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//Return ["eat","oath"].
//Note:
//You may assume that all inputs are consist of lowercase letters a-z.
public class Solution {

	public static void main(String[] args) {
		// abc
		// aed
		// afg
		// char[][] board = { { 'o', 'a', 'a', 'n' }, { 'e', 't', 'a', 'e' },
		// { 'i', 'h', 'k', 'r' }, { 'i', 'f', 'l', 'v' } };
		// String[] words = { "oath", "pea", "eat", "rain" };
		char[][] board = { { 'a', 'b', 'c' }, { 'a', 'e', 'd' },
				{ 'a', 'f', 'g' } };
		String[] words = { "eaabcdgfa" };
		for (String s : new Solution().findWords(board, words)) {
			System.out.println(s);
		}
	}

	// build a trie on words and search from each cell of the board
	// the search method takes a trie node and begin go four directions,
	// returns a list of words if we actually found one
	public List<String> findWords(char[][] board, String[] words) {
		int height = board.length;
		int width = board[0].length;
		TrieNode root = new TrieNode();
		for (String word : words) {
			root.addWord(word);
		}
		Set<String> retSet = new HashSet<>();
		boolean[][] visited = new boolean[board.length][board[0].length];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				probe(board, visited, i, j, retSet, root, "");
			}
		}
		return new LinkedList<String>(retSet);
	}

	// note: when doing probe, need to mark visited and unvisited each time
	// when probe returns, visited is all unvisited
	void probe(char[][] board, boolean[][] visited, int i, int j,
			Set<String> rst, TrieNode root, String cur) {
		int height = board.length;
		int width = board[0].length;
		if (i < 0 || i >= height || j < 0 || j >= width || visited[i][j]) {
			return;
		}
		char curChar = board[i][j];
		if (!root.containsChar(curChar)) {
			return;
		}

		// last char is curChar, need to move root to curChar and check
		// terminate
		root = root.leaves[curChar - 'a'];
		cur = cur + curChar;
		if (root.terminate) {
			rst.add(cur);
		}
		visited[i][j] = true;
		int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		for (int[] dir : dirs) {
			probe(board, visited, i + dir[0], j + dir[1], rst, root, cur);
		}
		visited[i][j] = false;
	}
}

class TrieNode {
	TrieNode[] leaves = new TrieNode[26];
	boolean terminate = false;

	TrieNode setLeaf(char c) {
		if (leaves[c - 'a'] == null) {
			leaves[c - 'a'] = new TrieNode();
		}
		return leaves[c - 'a'];
	}

	public void addWord(String word) {
		String lWord = word.toLowerCase();
		TrieNode cur = this;
		for (char c : lWord.toCharArray()) {
			cur = cur.setLeaf(c);
		}
		// from root to here is a complete word
		cur.terminate = true;
	}

	public boolean containsChar(char c) {
		return leaves[c - 'a'] != null;
	}
}
