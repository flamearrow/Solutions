package shortestWordDistance3;

//This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
//
//Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
//
//word1 and word2 may be the same and they represent two individual words in the list.
//
//For example,
//Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
//
//Given word1 = “makes”, word2 = “coding”, return 1.
//Given word1 = "makes", word2 = "makes", return 3.
public class Solution {
	public static void main(String[] args) {
		String[] words = {"the","quick","brown","fox","quick"};
		System.out.println(new Solution().shortestWordDistance(words, "quick", "quick"));
	}

	public int shortestWordDistance(String[] words, String word1, String word2) {
		int ret = Integer.MAX_VALUE;
		int hit1 = -1;
		int hit2 = -1;
		for (int i = 0; i < words.length; i++) {
			String s = words[i];
			// don't switch back and forth, just check match for both and compare with last index
			if (s.equals(word1)) {
				hit1 = i;
			} else if (s.equals(word2)) {
				hit2 = i;
			}
			if (hit1 >= 0 && hit2 >= 0 && hit1 != hit2) {
				ret = Math.min(ret, Math.abs(hit1 - hit2));
			}
			// note here we set hit2 = hit1 to avoid setting i to both when it doesn't really match
			if (word1.equals(word2)) {
				hit2 = hit1;
			}
		}
		return ret;
	}
}
