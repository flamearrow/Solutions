package addBinary;

//Given two Binary strings, return their sum (also a binary string).
//
//For example,
//a = "11"
//b = "1"
//Return "100". 
public class Solution {
	public String addBinary3(String a, String b) {
		int aPtr = a.length() - 1;
		int bPtr = b.length() - 1;
		int carry = 0;
		StringBuilder sb = new StringBuilder();
		while (aPtr >= 0 && bPtr >= 0) {
			int rst = doCal(a.charAt(aPtr), b.charAt(bPtr), carry);
			if (rst > 1) {
				rst -= 2;
				carry = 1;
			} else {
				carry = 0;
			}
			sb.insert(0, rst);
			aPtr--;
			bPtr--;
		}

		String leftStr = aPtr >= 0 ? a : b;
		int leftPtr = aPtr >= 0 ? aPtr : bPtr;
		while (leftPtr >= 0) {
			int rst = doCal(leftStr.charAt(leftPtr), '0', carry);
			if (rst > 1) {
				rst -= 2;
				carry = 1;
			} else {
				carry = 0;
			}
			sb.insert(0, rst);
			leftPtr--;
		}

		if (carry > 0)
			sb.insert(0, carry);
		return sb.toString();
	}

	int doCal(char c1, char c2, int carry) {
		return c1 - '0' + c2 - '0' + carry;
	}

	// String index always starts with 0
	// just do the stupid way to calculate carry and result of three int: add them, then compare them with 1 
	// when returning a char from int, need to do (char)('0'+intYouCal);
	// carry spells as 'carry' but not 'carray'
	public String addBinary2(String a, String b) {
		int aIndex = a.length() - 1;
		int bIndex = b.length() - 1;
		StringBuilder sb = new StringBuilder();
		char carry = '0';
		while (aIndex >= 0 && bIndex >= 0) {
			char rst = calculate(a.charAt(aIndex), b.charAt(bIndex), carry);
			carry = calculateCarry(a.charAt(aIndex), b.charAt(bIndex), carry);
			sb.insert(0, rst);
			aIndex--;
			bIndex--;

		}
		if (aIndex >= 0 || bIndex >= 0) {
			int leftIndex = aIndex >= 0 ? aIndex : bIndex;
			String leftString = aIndex >= 0 ? a : b;
			while (leftIndex >= 0) {
				char rst = calculate(leftString.charAt(leftIndex), '0', carry);
				carry = calculateCarry(leftString.charAt(leftIndex), '0', carry);
				sb.insert(0, rst);
				leftIndex--;
			}
		}
		if (carry == '1')
			sb.insert(0, carry);
		return sb.toString();
	}

	char calculate(char a, char b, char carry) {
		int ia = a - '0';
		int ib = b - '0';
		int iCarry = carry - '0';
		return (char) ('0' + (ia ^ ib ^ iCarry));
	}

	char calculateCarry(char a, char b, char carry) {
		int ia = a - '0';
		int ib = b - '0';
		int iCarry = carry - '0';
		int ret = ia + ib + iCarry > 1 ? 1 : 0;
		return (char) ('0' + ret);
	}

	// !!
	// CS101!!:
	// add result: a^b
	// carry result: a&b
	// note carry result can't be conjuncted e.g 'a&b&c' screws up
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
		System.out.println(new Solution().addBinary3("11", "1"));
	}
}
