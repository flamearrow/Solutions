package courseSchedule;

//There are a total of n courses you have to take, labeled from 0 to n - 1.

import java.util.*;

//Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
// which is expressed as a pair: [0,1]
//
//Given the total number of courses and a list of prerequisite pairs, is it possible for you to
// finish all courses?
//
//For example:
//
//2, [[1,0]]
//There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it
// is possible.
//
//2, [[1,0],[0,1]]
//There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to
// take course 0 you should also have finished course 1. So it is impossible.There are a total of n
// courses you have to take, labeled from 0 to n - 1.
//
//Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
// which is expressed as a pair: [0,1]
//
//Given the total number of courses and a list of prerequisite pairs, is it possible for you to
// finish all courses?
//
//For example:
//
//2, [[1,0]]
//There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it
// is possible.
//
//2, [[1,0],[0,1]]
//There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to
// take course 0 you should also have finished course 1. So it is impossible.
public class Solution {
    public static void main(String[] args) {
        int numC = 2;
        int[][] pre = {};
        System.out.println(new Solution().canFinish(numC, pre));
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // do a bfs topologically sort and check if the number of nodes visited is smaller than
        // numCourses

        // edges maps a node and all its incoming nodes
        List<Set<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(i, new HashSet<>());
        }

        for (int[] edge : prerequisites) {
            edges.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (edges.get(i).size() == 0) {
                q.add(i);
            }
        }
        int touched = 0;
        while (!q.isEmpty()) {
            int nextNode = q.poll();
            touched++;
            for (int i = 0; i < edges.size(); i++) {
                if (edges.get(i).contains(nextNode)) {
                    edges.get(i).remove(nextNode);
                    if (edges.get(i).size() == 0) {
                        q.add(i);
                    }
                }
            }
        }
        return touched == numCourses;
    }
}
