package ropecut;

// given a rope of length n, cut it in such a way that 
// the product of cut segments is maximized
// you must have at least one cut
// return the maximum product
public class Solution {
	// top-down dp - use a map to record <n,n> 
	// can also do top - down
	// another trick is to find the rule that if n > 4, 
	//   then we just need to repeatly cut it into length of 3
	//   leaving the last part 2, 3 or 4
	//   i.e 10: 3*3*4 = 36
	int maxProductOfRope(int n) {
		if (n == 1)
			return 1;
		if (n == 2)
			return 2;
		int ret = n - 1;
		for (int i = 1; i < n; i++) {
			if (i * maxProductOfRope(n - i) > ret) {
				ret = i * maxProductOfRope(n - i);
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		System.out.println(new Solution().maxProductOfRope(10));
	}
}
