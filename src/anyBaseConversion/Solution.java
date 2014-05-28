package anyBaseConversion;

public class Solution {
	public static final String TABLE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	String toBase(int baseTenNo, int base) {
		StringBuilder sb = new StringBuilder();
		while (baseTenNo >= base) {
			sb.insert(0, TABLE.charAt(baseTenNo % base));
			baseTenNo /= base;
		}
		if (baseTenNo > 0)
			sb.insert(0, TABLE.charAt(baseTenNo % base));
		return sb.toString();
	}

	int fromBase(String baseXNo, int base) {
		int ret = 0;
		int mod = 1;
		for (int i = baseXNo.length() - 1; i >= 0; i--) {
			ret += mod * TABLE.indexOf(baseXNo.charAt(i));
			mod *= base;
		}
		return ret;
	}

	public static void main(String[] args) {
		int base = 2;
		String to = new Solution().toBase(43, base);
		int from = new Solution().fromBase(to, base);
		System.out.println(to);
		System.out.println(from);
	}
}
