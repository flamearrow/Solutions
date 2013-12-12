package gasStation;

//There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
//
//You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
//
//Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
//
//Note:
//The solution is guaranteed to be unique. 
public class Solution {
	// naive implementation: starting with each starting point to try if we can
	// circle
	// would be a O(n^2) algorithm

	// a smarter one:
	// calculate the difference btn gas and cost dif[i],
	// find the place p where the sum from dif[p] to dif[p-1] is non-negative
	// could use two pointers start, end to do the trick in O(n) time

	public int canCompleteCircuit(int[] gas, int[] cost) {
		if (gas.length == 1) {
			return gas[0] >= cost[0] ? 0 : -1;
		}
		int len = gas.length;
		int[] dif = new int[len];
		for (int i = 0; i < len; i++) {
			dif[i] = gas[i] - cost[i];
		}
		// start inclusive, end exclusive
		int start = 0, end = 1;
		int sum = dif[start];
		while (start < len) {
			if (sum >= 0) {
				sum += dif[end];
				end = (end + 1) % len;
				// can only find an answer when expanding end
				if (start == end) {
					return start;
				}
			} else {
				sum -= dif[start];
				start = start + 1;
				// if start catches end, then we need to advance end
				// and now sum is 0, becuase we squeezed out everything
				// need to reassign sum to start
				if (start == end) {
					sum = dif[start];
					end = (end + 1) % len;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] gas = { 2, 4 };
		int[] cost = { 3, 4 };
		System.out.println(new Solution().canCompleteCircuit(gas, cost));
	}
}
