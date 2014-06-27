package fiboonacci;

public class Solution {
	static long fibo(int n) {
		if (n < 2)
			return 1;
		long ret = 0;
		long prev1 = 1, prev2 = 1;
		for (int i = 2; i <= n; i++) {
			ret = prev1 + prev2;
			prev1 = prev2;
			prev2 = ret;
		}
		return ret;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++)
			System.out.println(fibo(i));
	}
}
