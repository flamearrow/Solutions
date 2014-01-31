package longestPalindromicSubstring;

import java.util.LinkedList;

//Given a string S, find the longest palindromic substring in S. 
//You may assume that the maximum length of S is 1000, 
//and there exists one unique longest palindromic substring.
public class Solution {
	// first build a suffix tree of s, then revert s as sR
	// then starting from each index of sR, probe as deep as possible
	//  when probing, keep track of length and starting index
	//   starting index + length would be the END of a reversed substring
	//   END = length - original index
	//   which means the probe ends at the starting of a same substring
	//   then we find a candidate
	// O(n^2)
	public String longestPalindrome(String s) {
		String lS = s.toLowerCase();
		TrieNode suffixTreeRoot = TrieNode.buildSuffixTree(lS);
		StringBuilder sb = new StringBuilder();
		for (int i = lS.length() - 1; i >= 0; i--) {
			sb.append(lS.charAt(i));
		}
		String sR = sb.toString();
		String ret = null;
		int longest = -1;
		// now search sR in the trie
		for (int i = 0; i < sR.length(); i++) {
			TrieNode cur = suffixTreeRoot;
			String sub = sR.substring(i);
			int len = 0;
			for (char c : sub.toCharArray()) {
				if (cur.branch[c - 'a'] != null) {
					cur = cur.branch[c - 'a'];
					len++;
				}
				// otherwise we reach the deepest, break
				else
					break;
			}
			// we either break here or we complete searching
			// check if the end is the start of the chain
			// then start from the next substring
			// the string we found in rS is rS[i, endIndex]
			int endIndex = i + len - 1;
			int startIndex = lS.length() - 1 - endIndex;
			if (cur.indices.contains(startIndex)) {
				if (len > longest) {
					longest = len;
					ret = sR.substring(i, endIndex + 1);
				}
			}
		}

		return ret;
	}

	public static void main(String[] args) {
		String s = "civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth";
		//		TrieNode root = TrieNode.buildSuffixTree(s);
		System.out.println(new Solution().longestPalindrome(s));
	}
}

class TrieNode {
	TrieNode[] branch = new TrieNode[26];
	LinkedList<Integer> indices;

	public TrieNode() {
		indices = new LinkedList<Integer>();
	}

	static TrieNode buildSuffixTree(String s) {
		TrieNode root = new TrieNode();
		for (int i = 0; i < s.length(); i++) {
			TrieNode cur = root;
			String sub = s.substring(i);
			for (char c : sub.toCharArray()) {
				TrieNode next = cur.branch[c - 'a'];
				if (next == null) {
					cur.branch[c - 'a'] = new TrieNode();
					next = cur.branch[c - 'a'];
				}
				next.indices.add(i);
				cur = next;
			}
		}
		return root;
	}
}
