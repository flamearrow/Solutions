package excelSheetRowNumbers;

//Given the sequence S1 = {a,b,c,d,…,x,y,z,aa,ab,ac…. } and 
//given that this sequence corresponds (term for term) to the sequence S2 = {0,1,2,3,….}.
//Write code to convert an element of S2 to the corresponding element of S1.
public class Solution {
	//Given the sequence S1 = {a,b,c,d,…,x,y,z,aa,ab,ac…. } and
	//given that this sequence corresponds (term for term) to the sequence S2 = {0,1,2,3,….}.
	//Write code to convert an element of S2 to the corresponding element of S1.
	static String table = "zabcdefghijklmnopqrstuvwxy";

	static String convert(int i) {
		String table = "zabcdefghijklmnopqrstuvwxy";
		StringBuilder sb = new StringBuilder();
		i++;
		while (i > 0) {
			int mod = i % 26;
			sb.insert(0, table.charAt(mod));
			// note if mod is 0, then this bit is z,
			// in the case we see a z, we need to remove the last z manually
			// otherwise i/=26 would result in an additional one
			if (mod == 0) {
				i -= 26;
			}
			i /= 26;
		}
		return sb.toString();
	}

	static int convertBack(String str) {
		if (str.equals("z"))
			return 25;
		int ret = 0;
		for (int i = str.length() - 1; i >= 0; i--) {
			int cur = table.indexOf(str.charAt(i));
			if (cur == 0) {
				cur = 26;
			}
			ret += cur * Math.pow(26, str.length() - 1 - i);
		}
		return ret - 1;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			String str = convert(i);
			System.out.println(i + " : " + str + " : " + convertBack(str));
			if (i != convertBack(str))
				System.err.println("ERROR: " + i);
		}

	}
}
