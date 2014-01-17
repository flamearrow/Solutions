package addBinary;

//Given two binary strings, return their sum (also a binary string).
//
//For example,
//a = "11"
//b = "1"
//Return "100". 
public class Solution {
	// !!
	// CS101!!:
	// add result: a^b
	// carry result: a&b
	public String addBinary(String a, String b) {
		int carry = 0;
		int aEnd = a.length() - 1;
		int bEnd = b.length() - 1;
		char[] retArr = new char[aEnd > bEnd ? aEnd + 2 : bEnd + 2];
		int cur = retArr.length - 1;
		while (aEnd >= 0 && bEnd >= 0) {
			retArr[cur] = getSum(a.charAt(aEnd), b.charAt(bEnd), carry);
			carry = getCarry(a.charAt(aEnd), b.charAt(bEnd), carry);
			aEnd--;
			bEnd--;
			cur--;
		}

		while (aEnd >= 0) {
			retArr[cur] = (char) ((a.charAt(aEnd) - '0' ^ carry) + '0');
			carry = (a.charAt(aEnd) - '0') & carry;
			aEnd--;
			cur--;
		}

		while (bEnd >= 0) {
			retArr[cur] = (char) ((b.charAt(bEnd) - '0' ^ carry) + '0');
			carry = (b.charAt(bEnd) - '0') & carry;
			bEnd--;
			cur--;
		}
		if (carry == 1)
			retArr[cur] = '1';
		String ret = new String(retArr);
		return retArr[0] == '1' ? ret : ret.substring(1);
	}

	int getCarry(char c1, char c2, int carry) {
		return c1 - '0' + c2 - '0' + carry > 1 ? 1 : 0;
	}

	char getSum(char c1, char c2, int carry) {
		return (char) (((c1 - '0') ^ (c2 - '0') ^ carry) + '0');
	}

	public static void main(String[] args) {
		System.out.println(new Solution().addBinary("111", "111"));
	}
}
