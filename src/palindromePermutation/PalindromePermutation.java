package palindromePermutation;

//Given a string, determine if a permutation of the string could form a palindrome.
//
//For example,
//"code" -> False, "aab" -> True, "carerac" -> True.
public class PalindromePermutation {
	public boolean canPermutePalindrome(String s) {
		int[] counts = new int[256];
		for (char c : s.toCharArray()) {
			counts[c]++;
		}
		boolean oddSeen = false;
		for (int i : counts) {
			if (i % 2 != 0) {
				if (oddSeen) {
					return false;
				} else {
					oddSeen = true;
				}
			}
		}
		return true;
	}
}
