package courseScheduleII;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//There are a total of n courses you have to take, labeled from 0 to n - 1.
//
//Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
//
//Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
//
//There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
//
//For example:
//
//2, [[1,0]]
//There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]
//
//4, [[1,0],[2,0],[3,1],[3,2]]
//There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. 
// Both courses 1 and 2 should be taken after you finished course 0. 
// So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
public class Solution {
	public static void main(String[] args) {
		int[][] pre = { { 1, 0 }, { 2, 0 }, { 3, 1 }, { 3, 2 } };
		for (int i : new Solution().findOrder(4, pre)) {
			System.out.println(i);
		}
	}

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		return doBFS(numCourses, prerequisites);
	}

	private int[] doBFS(int numCourses, int[][] prerequisites) {
		// use a array to store indegree
		int[] inDegree = new int[numCourses];
		for (int[] pre : prerequisites) {
			// initialize the shit
			inDegree[pre[0]]++;
		}

		Queue<Integer> q = new LinkedList<>();
		// add all zero inDegree nodes - these are the course to take
		for (int i = 0; i < inDegree.length; i++) {
			if (inDegree[i] == 0) {
				q.offer(i);
			}
		}

		int[] ret = new int[numCourses];
		int cur = 0;
		while (!q.isEmpty()) {
			int nextCourse = q.poll();
			ret[cur++] = nextCourse;
			for (int[] pre : prerequisites) {
				// the pre is taken, decrement it's successor
				if (pre[1] == nextCourse) {
					inDegree[pre[0]]--;
					if (inDegree[pre[0]] == 0) {
						q.offer(pre[0]);
					}
				}
			}
		}
		// some nodes can't be added - no valid solution
		if (cur < ret.length) {
			return new int[1];
		} else {
			return ret;
		}
	}

	private int[] regularTopo(int numCourses, int[][] prerequisites) {

		Set<Integer> left = new HashSet<>();
		for (int i = 0; i < numCourses; i++) {
			left.add(i);
		}
		LinkedList<Integer> rst = new LinkedList<>();
		while (rst.size() < numCourses) {
			int nextCourse = findNextCourse(left, prerequisites);
			if (nextCourse == -1) {
				return new int[0];
			} else {
				left.remove(nextCourse);
				rst.addFirst(nextCourse);
			}
		}

		int[] arr = new int[rst.size()];
		int cur = 0;
		for (int i : rst) {
			arr[cur++] = i;
		}
		return arr;

	}

	int findNextCourse(Set<Integer> left, int[][] pre) {
		here: for (int i : left) {
			for (int[] pp : pre) {
				if (pp[1] == i && left.contains(pp[0])) {
					continue here;
				}
			}
			return i;
		}
		return -1;
	}
}
