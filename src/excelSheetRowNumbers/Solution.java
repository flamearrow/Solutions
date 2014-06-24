package excelSheetRowNumbers;

//Given the sequence S1 = {a,b,c,d,…,x,y,z,aa,ab,ac…. } and 
//given that this sequence corresponds (term for term) to the sequence S2 = {0,1,2,3,….}.
//Write code to convert an element of S2 to the corresponding element of S1.
public class Solution {
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


	static String convert2(int i) {
		if (i < 1)
			return "a";
		i += 1;
		StringBuilder sb = new StringBuilder();
		while (i > 26) {
			sb.insert(0, table.charAt(i % 26));
			i /= 26;
		}
		sb.insert(0, table.charAt(i % 26));
		return sb.toString();
	}


	public static void main(String[] args) {
		for (int i = 0; i < 728; i++)
			System.out.println(i + " : " + convert(i));
	}
}
