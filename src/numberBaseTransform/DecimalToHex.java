package numberBaseTransform;

public class DecimalToHex {

	static String decimalToHex(int decimal) {
		if (decimal == 0)
			return "0x0";
		StringBuilder sb = new StringBuilder();
		// use a table to do fast conversion...
		String base16 = "0123456789ABCDEF";
		while (decimal > 0) {
			sb.insert(0, base16.charAt(decimal % 16));
			decimal /= 16;
		}
		sb.insert(0, "0x");
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(decimalToHex(0));
	}
}
