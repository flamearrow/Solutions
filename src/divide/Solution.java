package divide;

import java.util.HashMap;
import java.util.Map;

// given two integers a and b, return the exact result of a/b
// bracket repetitive numbers
// e.g 10/3 = 3.(3)  5/2=2.5
// 1/123 = 0.(00813)

// the idea is to use a map to store (result, index) and add bracket accordingly
// note in the case when tmp divisor is not big enough and we increment it by 10
// we need to sandwich an additional 0 in between
public class Solution {
	static int MAXPOINT = 10;

	static String divide(int divisor, int divident) {
		StringBuilder sb = new StringBuilder();
		int i = divisor / divident;
		sb.append(i);
		sb.append('.');
		divisor %= divident;
		int cur = sb.length();
		int max = cur + MAXPOINT;
		// need to provide a way to check a couple of reapetitive numbers
		// this map maps <result, indexInSB that gets that result>
		Map<Integer, Integer> backMap = new HashMap<Integer, Integer>();

		while (divisor != 0) {
			// we're moving one point back
			divisor *= 10;
			// if there's still not enough point, we need to append a 0
			while (divisor < divident) {
				cur++;
				sb.append('0');
				divisor *= 10;
			}
			int rst = divisor / divident;
			// we have seen this result before, get a repetitive loop
			if (backMap.containsKey(rst)) {
				// we need to add '(' to backMap.get(rst)
				// and then append ')'
				int leftBracketIndex = backMap.get(rst);
				sb.insert(leftBracketIndex, '(');
				sb.append(')');
				break;
			} else {
				backMap.put(rst, cur);
				sb.append(rst);
			}
			divisor %= divident;
			if (cur++ == max)
				break;
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(divide(10, 123));
	}
}
