package findMajority;

// given an array of integers, find the majority(the one appears over 50%)
// find the one appears over(1/3)
// find the one appears over(1/4)
// ...
public class Solution {

	// the idea is similar
	//  we need to do two O(n) pass
	// for the half problem, keep a candidate and its count
	// for the first pass
	// once we see a different number, 
	//  try telescope the original candidate if it has positive count 
	//  or replace the candidate and reset count to one
	// for the second pass
	//  count the leftover candidate is actually more than half and return -1 or correct candidate
	// e.g 1,2,1,2,1
	//  after first run we have can = 1, count = 1
	//  then check if 1 is actually more than half

	// similar idea applies to 1/3
	// in which we keep two candidates, but telescope three distinct numbers if we can
	// then for the left over two candidates, we check if they are acutally more than 1/3
	//  note for 1/3 there might be two result

	class Candidate {
		int can, cnt;

		public Candidate(int can, int cnt) {
			this.can = can;
			this.cnt = cnt;
		}
	}

	int findMajorityHalf(int[] inputs) {
		Candidate c = null;
		for (int i : inputs) {
			if (c == null) {
				c = new Candidate(i, 1);
			} else {
				if (c.can == i)
					c.cnt++;
				else {
					if (--c.cnt == 0) {
						c = null;
					}

				}
			}
		}
		if (c == null)
			return -1;
		else {
			int can = c.can;
			int cnt = 0;
			for (int i : inputs) {
				if (i == can)
					cnt++;
			}
			if (cnt > inputs.length / 2)
				return can;
			else
				return -1;
		}
	}

	class CandidateOneThird {
		int can1, can2, cnt1, cnt2;

		public CandidateOneThird() {
			this(0, 0, -1, -1);
		}

		public CandidateOneThird(int can1, int can2, int cnt1, int cnt2) {
			this.can1 = can1;
			this.can2 = can2;
			this.cnt1 = cnt1;
			this.cnt2 = cnt2;
		}

	}

	int findMajorityOneThrid(int[] inputs) {
		CandidateOneThird c = new CandidateOneThird();
		for (int i : inputs) {
			if (c.cnt1 <= 0 && c.cnt2 <= 0) {
				c.cnt1 = 1;
				c.can1 = i;
			} else {
				if (c.cnt1 > 0 && c.can1 == i) {
					c.cnt1++;
				} else if (c.cnt2 > 0 && c.can2 == i) {
					c.cnt2++;
				} else {
					// telescope
					if (c.cnt1 > 0 && c.cnt2 > 0) {
						c.cnt1--;
						c.cnt2--;
					}
					// otherwise there's an empty slot
					else {
						if (c.cnt1 <= 0) {
							c.cnt1 = 1;
							c.can1 = i;
						} else {
							c.cnt2 = 1;
							c.can2 = i;
						}

					}
				}
			}
		}
		if (c.cnt1 <= 0 && c.cnt2 <= 0)
			return -1;
		else {
			int can = c.cnt1 > c.cnt2 ? c.can1 : c.can2;
			int cnt = 0;
			for (int i : inputs) {
				if (i == can)
					cnt++;
			}
			return cnt > inputs.length / 3 ? can : -1;
		}
	}

	public static void main(String[] args) {
		//		int[] inputs = { 1, 2, 1, 2, 1 };
		//		System.out.println(new Solution().findMajorityHalf(inputs));
		int[] inputs = { 2, 2, 2, 3, 3, 1, 1 };
		System.out.println(new Solution().findMajorityOneThrid(inputs));
	}
}
