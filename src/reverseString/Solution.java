package reverseString;

public class Solution {
	// do it recursively
	String reverse(String s) {
		if (s.length() == 1)
			return s;
		else {
			String head = reverse(s.substring(1));
			return head + s.charAt(0);
		}
	}

	public static void main(String[] args) {
		System.out.println(new Solution().reverse("mlgb"));
	}
}
