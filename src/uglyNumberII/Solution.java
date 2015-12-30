package uglyNumberII;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

//write a program to find the n-th ugly number.
//
//Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
// For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
//
//Note that 1 is typically treated as an ugly number.
public class Solution {

	public static void main(String[] args) {
		for (int i = 1; i < 11; i++) {
			System.out.println(new Solution().nthUglyNumber(i));
		}

	}

	public int nthUglyNumber(int n) {
		PriorityQueue<Long> queue = new PriorityQueue<>();
		queue.add(1l);
		long ret = 1;
		while (n > 0) {
			ret = queue.poll();
			// remove dup
			while (!queue.isEmpty() && queue.peek() == ret) {
				queue.poll();
			}
			queue.add(ret * 2);
			queue.add(ret * 3);
			queue.add(ret * 5);
			n--;
		}
		return (int) ret;
	}

	public boolean isUgly(int num, Set<Integer> beautiful) {
		if (num == 0) {
			return false;
		}
		for (int i : beautiful) {
			if (i != 0 && num % i == 0) {
				return false;
			}
		}
		while (num % 2 == 0) {
			num = num / 2;
		}

		while (num % 3 == 0) {
			num = num / 3;
		}

		while (num % 5 == 0) {
			num = num / 5;
		}

		return num == 1;
	}
}
