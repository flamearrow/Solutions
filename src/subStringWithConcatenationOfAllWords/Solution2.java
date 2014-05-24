package subStringWithConcatenationOfAllWords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Solution2 {
	// starting from 0-(loop-1) where loop is the length of a single word
	// note we need to clear the map and reset matched in each loop
	// note the is a sliding window, 
	//  for "abca" and {"a", "b", "c"}
	//   when we hit the third c and find a match, we can't clear every thing
	//   need to shrink left side and assign a new block for a
	public ArrayList<Integer> findSubstring(String S, String[] L) {
		Map<String, Integer> quota = new HashMap<String, Integer>();

		ArrayList<Integer> ret = new ArrayList<Integer>();
		int loop = L[0].length();
		int len = S.length();
		int matched = 0;
		for (int i = 0; i < loop; i++) {
			int cur = i;
			int startIndex = i;
			matched = 0;
			quota.clear();
			for (String s : L) {
				if (quota.containsKey(s)) {
					quota.put(s, quota.get(s) + 1);
				} else {
					quota.put(s, 1);
				}
			}

			while (cur + loop <= len) {
				String curStr = S.substring(cur, cur + loop);
				// need to start from next
				if (quota.get(curStr) == null) {
					while (startIndex <= cur) {
						String recoveredStr = S.substring(startIndex,
								startIndex + loop);
						if (quota.containsKey(recoveredStr)) {
							quota.put(recoveredStr, quota.get(recoveredStr) + 1);
						}
						startIndex += loop;
					}
					matched = 0;
					cur += loop;
				}
				// ran out of quota, we need to squeeze out the first string, hopefully give space for the new string
				// note we don't increase cur since we will examine the string again
				else if (quota.get(curStr) == 0) {
					String toSqueeze = S.substring(startIndex, startIndex
							+ loop);
					quota.put(toSqueeze, quota.get(toSqueeze) + 1);
					matched--;
					startIndex += loop;
				}
				// find a match, minus quota
				else {
					quota.put(curStr, quota.get(curStr) - 1);
					matched += 1;
					// find a matched chain, add to result
					if (matched == L.length) {
						ret.add(startIndex);
					}
					cur += loop;
				}
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		String S = "ababa";
		String[] L = { "ab", "ba" };
		for (Integer i : new Solution2().findSubstring(S, L)) {
			System.out.println(i);
		}

	}
}
