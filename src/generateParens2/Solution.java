package generateParens2;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a array of size n, generating all possible parentheses combinations
 * this can be applied to probe an correct parentheses placement for an expression equation 
 * 
 * i.e n == 3 return:
 * (). .() (.) ...
 * n == 4 return 
 * ().. ..() (..) .(). ()() .(.) (.). ....
 * @author flamearrow
 *
 */
public class Solution {
	static Set<String> generateParen2(int n) {
		Set<String> ret = new HashSet<String>();
		char[] current = new char[n];
		for (int i = 0; i < n; i++) {
			current[i] = '.';
		}
		doProbe(ret, current, 0);
		return ret;
	}

	static void doProbe(Set<String> ret, char[] current, int cur) {
		ret.add(new String(current));
		for (int i = cur; i < current.length - 1; i++) {
			if (current[i] != '.')
				continue;
			for (int j = i + 1; j < current.length; j++) {
				if (current[j] == '.') {
					current[i] = '(';
					current[j] = ')';
					doProbe(ret, current, cur + 1);
					current[i] = '.';
					current[j] = '.';
				}
			}
		}
	}

	public static void main(String[] args) {
		for (String s : generateParen2(4)) {
			System.out.println(s);
		}
	}
}
