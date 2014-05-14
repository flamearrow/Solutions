package celebrityProblem;

import java.util.Stack;

// given an 2D array boolean know[][] indicating if i knows j
// find the celebrity or influencer i such that everyone else knows i
//  but i doesn't know anyone else
//  know[i][i] is always true
public class Solution {
	// first solution O(n^2), just do a nested loop
	static int findCelebrity(boolean[][] know) {
		int len = know.length;
		int ret = -1;
		here: for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (i != j && know[i][j] || !know[j][i])
					continue here;
			}
			ret = i;
		}
		return ret;
	}

	// a O(n) solution: do this by elimination
	//  for each pair(a, b), 
	//	 if a knows b, then a can't be celebrity
	//   if a doesn't know b, then b might be a celebrity
	// we can eliminate until we only left 1 people
	// if we leave more than 2 ppl, then it means there're no celebrity 
	//  since there's no know that's know by every one

	// just use a stack to pop and push
	static int findCelebrity2(boolean[][] know) {
		Stack<Integer> s = new Stack<Integer>();
		for (int i = know.length - 1; i >= 0; i--)
			s.push(i);
		while (s.size() > 1) {
			int ppl1 = s.pop();
			int ppl2 = s.pop();
			boolean removeP1 = know[ppl1][ppl2];
			boolean removeP2 = know[ppl2][ppl1];
			// p1 and p2 doesn't know each other, there won't be any celebrity
			if (!removeP1 && !removeP2) {
				return -1;
			}
			if (!removeP1 || !removeP2) {
				if (removeP1) {
					s.push(ppl2);
				} else {
					s.push(ppl1);
				}
			}
		}
		if (s.size() == 0)
			return -1;
		else {
			int ret = s.pop();
			for (int i = 0; i < know.length; i++) {
				if (ret != i && (know[ret][i] || !know[i][ret]))
					return -1;
			}
			return ret;
		}

	}

	public static void main(String[] args) {
		// celebrity is 0 
		boolean[][] know = { { true, false, false, false },
				{ true, true, false, true }, { true, false, true, true },
				{ true, false, false, true } };
		System.out.println(findCelebrity2(know));
	}
}
