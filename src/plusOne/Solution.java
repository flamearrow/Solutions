package plusOne;

//Given a number represented as an array of digits, plus one to the number.
public class Solution {
	// System.arraycopy() is lower case, and it's signature is
	// arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
	public int[] plusOne2(int[] digits) {
		int carry = 1;
		int curIndex = digits.length - 1;
		while (curIndex >= 0) {
			int cur = digits[curIndex] + carry;
			digits[curIndex] = cur % 10;
			carry = cur / 10;
			curIndex--;
		}
		if (carry == 1) {
			int[] ret = new int[digits.length + 1];
			ret[0] = 1;
			System.arraycopy(digits, 0, ret, 1, digits.length);
			return ret;
		} else
			return digits;
	}

	public int[] plusOne(int[] digits) {
		int carry = 1;
		int cur = 0;
		int len = digits.length - 1;
		while (cur < digits.length && carry == 1) {
			digits[len - cur] += carry;
			if (digits[len - cur] >= 10) {
				carry = 1;
				digits[len - cur] -= 10;
			} else {
				carry = 0;
			}
			cur++;
		}
		// we have to go one bit further
		if (carry == 1) {
			int[] newRet = new int[len + 2];
			System.arraycopy(digits, 0, newRet, 1, len);
			newRet[0] = 1;
			return newRet;
		} else {
			return digits;
		}
	}

	public static void main(String[] args) {
		int[] digits = { 9, 9, 9 };
		int[] ret = new Solution().plusOne(digits);
		System.out.println(ret);
	}
}
