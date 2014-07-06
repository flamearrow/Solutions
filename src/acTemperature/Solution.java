package acTemperature;

import java.util.LinkedList;
import java.util.Queue;

// the AC have six buttons: +1 +5 +10 -1 -5 -10
// the initial tempterature is 0, given the required degree, 
// what's the minimum number of keys you need to press to get that temperature?

public class Solution {
	// naive BFS, will take forever for larger numbers like 10
	static int keys(int target) {
		int[] tmps = { 1, 5, 10, -1, -5, -10 };
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(0);
		int size = 1;
		int press = 0;
		while (!q.isEmpty()) {
			int curTemp = q.poll();
			for (int i : tmps) {
				int nextTmp = curTemp + i;
				if (nextTmp == target)
					return press + 1;
				else
					q.offer(nextTmp);
			}
			if (--size == 0) {
				press++;
				size = q.size();
			}
		}
		// can't reach here since we have 1
		return -1;
	}

	// a smarter way is based on the fact that if the target is > 10
	// then we have to press 10 (target/10) times to reach target%10
	// now for the least significant part, we can hard code it
	static int keys2(int target) {
		// buf[i] is the answer for target = i
		int[] buf = { 0, 1, 2, 3, 2, 1, 2, 3, 3, 2, 1 };
		return target / 10 + buf[target % 10];
	}

	public static void main(String[] args) {
		//		for (int i = 1; i < 80; i++)
		//			System.out.println(keys(i) + ",");
		System.out.println("=======================");
		for (int i = 1; i < 80; i++)
			System.out.print(keys2(i) + ",");
	}
}
