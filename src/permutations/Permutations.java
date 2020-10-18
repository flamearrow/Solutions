package permutations;

import java.util.ArrayList;
import java.util.List;

//Given a collection of numbers, return all possible permutations.
//
//For example,
//[1,2,3] have the following permutations:
//[1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
public class Permutations {
	public static void main(String[] args) {
		int[] num = { 1, 2, 3 };
		new Permutations().permute(num);
	}

	public List<List<Integer>> permute(int[] num) {
		return getPermute(num, 0);
	}

	List<List<Integer>> getPermute(int[] num, int index) {
		if (index == num.length - 1) {
			List<List<Integer>> ret = new ArrayList<>();
			ArrayList<Integer> l = new ArrayList<>();
			l.add(num[index]);
			ret.add(l);
			return ret;
		} else {
			List<List<Integer>> ret = new ArrayList<>();
			List<List<Integer>> sub = getPermute(num, index + 1);
			for (List<Integer> list : sub) {
				for (int i = 0; i <= list.size(); i++) {
					ArrayList<Integer> newList = new ArrayList<>(list);
					newList.add(i, num[index]);
					ret.add(newList);
				}
			}
			return ret;
		}
	}
}
