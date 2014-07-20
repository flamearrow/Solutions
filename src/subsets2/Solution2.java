package subsets2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution2 {
	public static void main(String[] args) {
		int[] num = { 1, 2, 2 };
		List<List<Integer>> ret = new Solution2().subsetsWithDup(num);
		System.out.println(ret);
	}

	public List<List<Integer>> subsetsWithDup(int[] num) {
		Arrays.sort(num);
		List<List<Integer>> ret = new LinkedList<List<Integer>>();
		ret.add(new LinkedList<Integer>());
		doProbe(ret, new LinkedList<Integer>(), new boolean[num.length], 0, num);
		return ret;
	}
	
	// do regular BFS, only add to result when going to the 'used' branch
	void doProbe(List<List<Integer>> ret, LinkedList<Integer> cur,
			boolean[] used, int index, int[] num) {
		if (index == num.length)
			return;
		// don't use...
		doProbe(ret, cur, used, index + 1, num);
		if (index > 0 && num[index] == num[index - 1] && !used[index - 1])
			return;
		// do use
		used[index] = true;
		cur.add(num[index]);
		ret.add(new ArrayList<Integer>(cur));
		doProbe(ret, cur, used, index + 1, num);
		used[index] = false;
		cur.removeLast();
	}
}
