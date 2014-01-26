package palindromeNumber;

//Determine whether an integer is a palindrome. Do this without extra space.
public class Solution {
	public boolean isPalindrome(int x) {
		if (x < 0)
			return false;
		if (x < 10)
			return true;
		int small = 10;
		int big = 10;
		while (x / big >= 10)
			big *= 10;
		while (big >= small) {
			int left = (x / big) % 10;
			int right = (x % small) / (small / 10);
			if (left != right)
				return false;
			big /= 10;
			small *= 10;
		}
		return true;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10000; i++) {
			if (new Solution().isPalindrome(i))
				System.out.println(i);
		}
	}
}
