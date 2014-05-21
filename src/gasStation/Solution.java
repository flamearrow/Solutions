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
	
	// need to find a starting point where the sum of path are never negative
	//  do this in a sliding window
	public int canCompleteCircuit2(int[] gas, int[] cost) {
		int[] diff = new int[gas.length];
		for (int i = 0; i < cost.length; i++) {
			diff[i] = gas[i] - cost[i];
		}

		int start = 0, end = 0, sum = 0;
		while (start < gas.length) {
			if (sum < 0) {
				sum -= diff[start];
				start++;
				if (start > end)
					end = start;
			} else {
				sum += diff[end];
				end = (end + 1) % gas.length;
				// we find a circle!
				if (end == start) {
					// the entire cycle's sum >= 0, find one
					if (sum >= 0) {
						return start;
					}
					// the entire cycle's sum < 0, there's no path
					else {
						return -1;
					}
				}
			}
		}
		return -1;
	}
	
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
					if (sum >= 0) {
						return start;
					}
					// sum of entire array is negative, can't find an answer
					else {
						return -1;
					}
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

	public int canCompleteCircuitNew(int[] gas, int[] cost) {
		// do it like a sliding window
		int len = gas.length;
		int[] diff = new int[gas.length];
		for (int i = 0; i < gas.length; i++) {
			diff[i] = gas[i] - cost[i];
		}
		//maintain a sliding window from start to end and advance end
		//if after advancing end, sum from start to end became minus, advance start
		//keep advancing start until the sum is >=0
		//if after advancing end, sum is >=0, keep advancing end until end = start

		int start = 0, end = 1 % len;
		int sum = diff[start];
		// start < len to prevent looping
		while (start < len) {
			if (sum < 0) {
				sum -= diff[start];
				start = start + 1;
				if (start == end) {
					start = end;
					end = (end + 1) % len;
					sum = diff[start];
				}
			} else {
				sum += diff[end];
				end = (end + 1) % len;
				// when there's only one positive, we advance end, and will never advance end
				if (end == start)
					break;
			}
		}
		if (sum >= 0 && start < len)
			return start;
		else
			return -1;
	}

	public static void main(String[] args) {
		int[] gas = { 5 };
		int[] cost = { 4 };
		System.out.println(new Solution().canCompleteCircuitNew(gas, cost));
	}
}
