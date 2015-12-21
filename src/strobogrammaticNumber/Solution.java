package strobogrammaticNumber;

//A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
//
//Write a function to determine if a number is strobogrammatic. The number is represented as a string.
//
//For example, the numbers "69", "88", and "818" are all strobogrammatic.
public class Solution {
	public boolean isStrobogrammatic(String num) {
		int left = 0, right = num.length() - 1;
		while (left < right) {
			if (!valid(num.charAt(left), num.charAt(right))) {
				return false;
			}
			left++;
			right--;
		}
		if (left == right) {
			return num.charAt(left) == '0' || num.charAt(left) == '1'
					|| num.charAt(left) == '8';
		} else
			return true;
	}

	boolean valid(char left, char right) {
		switch (left) {
		case '1':
		case '0':
		case '8':
			return left == right;
		case '6':
			return right == '9';
		case '9':
			return right == '6';
		default:
			return false;
		}
	}
}
