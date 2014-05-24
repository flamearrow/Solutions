package permutationSequence;


public class Solution2 {
	public String getPermutation(int n, int k) {
		int[] ret = buildFirst(n);
		for (int i = 1; i < k; i++) {
			getNext(ret);
		}
		StringBuilder sb = new StringBuilder();
		for (int i : ret)
			sb.append(i);
		return sb.toString();
	}

	int[] buildFirst(int n) {
		int[] ret = new int[n];
		for (int i = 1; i <= n; i++) {
			ret[i - 1] = i;
		}
		return ret;
	}

	void getNext(int[] current) {
		int ptr = current.length - 1;
		while (current[ptr - 1] > current[ptr]) {
			ptr--;
		}
		int reverseFrom = ptr;
		ptr = current.length - 1;
		while (current[ptr] < current[reverseFrom - 1])
			ptr--;
		int tmp = current[reverseFrom - 1];
		current[reverseFrom - 1] = current[ptr];
		current[ptr] = tmp;

		int reverseTo = current.length - 1;
		while (reverseFrom < reverseTo) {
			tmp = current[reverseFrom];
			current[reverseFrom] = current[reverseTo];
			current[reverseTo] = tmp;
			reverseFrom++;
			reverseTo--;
		}
	}

	public static void main(String[] args) {
		System.out.println(new Solution2().getPermutation(3, 6));
	}
}
