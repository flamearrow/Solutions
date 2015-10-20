package fourSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

	public static void main(String[] args) {

	}

	public List<List<Integer>> fourSum(int[] num, int target) {
		Arrays.sort(num);
		List<List<Integer>> ret = new ArrayList<>();
		for (int i = 0; i < num.length - 3; i++) {
			if (i > 0 && num[i] == num[i - 1]) {
				continue;
			}
			int can1 = num[i];
			int[] sub = new int[num.length - i - 1];
			System.arraycopy(num, i + 1, sub, 0, sub.length);
			List<List<Integer>> list = threeSum(sub, target - can1);
			if (list.size() > 0) {
				for (List<Integer> ll : list) {
					ll.add(0, can1);
					ret.add(ll);
				}
			}
		}
		return ret;
	}

	public static List<List<Integer>> threeSum(int[] num, int mlgb) {
		List<List<Integer>> ret = new ArrayList<>();
		for (int i = 0; i < num.length - 2; i++) {
			if (i > 0 && num[i] == num[i - 1])
				continue;
			int left = i + 1;
			int right = num.length - 1;
			int target = mlgb - num[i];
			while (left < right) {
				int cur = num[left] + num[right];
				if (left > i + 1 && num[left] == num[left - 1]) {
					left++;
				} else if (right < num.length - 2
						&& num[right] == num[right + 1]) {
					right--;
				} else if (cur < target) {
					left++;
				} else if (cur > target) {
					right--;
				} else {
					ArrayList<Integer> newList = new ArrayList<Integer>();
					newList.add(num[i]);
					newList.add(num[left]);
					newList.add(num[right]);
					ret.add(newList);
					left++;
					right--;
				}
			}
		}
		return ret;
	}
}
