package permutations2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution2 {
	public List<List<Integer>> permuteUnique(int[] num) {
		Arrays.sort(num);
		List<List<Integer>> ret = new ArrayList<List<Integer>>();
		probe(ret, new LinkedList<Integer>(), new boolean[num.length], num, 0);
		return ret;

	}

	// the difference btw permutation and subsets is that 
	// permutation only adds to result when we finished probing the entire array
	//  i.e at the bottom of the bTree
	// while subsets add results in the middle
	// each time a probe is called, we make sure we pick up a new element
	//  and cnt++
	// when cnt == size of array, we've found a solution
	void probe(List<List<Integer>> ret, LinkedList<Integer> cur,
			boolean[] used, int[] num, int cnt) {
		if (cnt == num.length) {
			ret.add(new ArrayList<Integer>(cur));
			return;
		} else {
			for (int i = 0; i < num.length; i++) {
				if (!used[i]) {
					if (i > 0 && num[i - 1] == num[i] && !used[i - 1])
						continue;
					used[i] = true;
					cur.add(num[i]);
					probe(ret, cur, used, num, cnt + 1);
					cur.removeLast();
					used[i] = false;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[] num = { 1, 2, 3 };
		List<List<Integer>> ret = new Solution2().permuteUnique(num);
		System.out.println(ret);
	}
}
