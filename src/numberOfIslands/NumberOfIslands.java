package numberOfIslands;

import java.util.Arrays;
import java.util.LinkedList;

//Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
// An island is surrounded by water and is formed by connecting adjacent lands horizontally
// or vertically. You may assume all four edges of the grid are all surrounded by water.
//
//Example 1:
//
//11110
//11010
//11000
//00000
//Answer: 1
//
//Example 2:
//
//11000
//11000
//00100
//00011
//Answer: 3
public class NumberOfIslands {
    public static void main(String[] args) {
//        String[] strs = {"1111111", "0000001", "1111101", "1000101", "1010101", "1011101",
//                "1111111"};
        String[] strs = {
                "1111111",
                "0000001",
                "1111101",
                "1000101",
                "1010101",
                "1011101",
                "1111111"
        };
        char[][] grid = toGrid(strs);
//        char[][] grid = {"11000".toCharArray(), "11010".toCharArray(), "11000".toCharArray(),
//                "00011".toCharArray()};
//        char[][] grid = {"10101010101010101".toCharArray()};
        System.out.println(new NumberOfIslands().numIslands2(grid));
    }

    static char[][] toGrid(String[] strs) {
        char[][] ret = new char[strs.length][];
        for (int i = 0; i < strs.length; i++) {
            ret[i] = strs[i].toCharArray();
        }
        return ret;
    }

    // union find
    //  the idea is to mark each node as a root, check if it belongs to another root
    // iterate through each node, if it's 1
    //   first the new node forms a new root itself
    //   then check each node's visited connected neighbour
    //    go to the neighBourRoot(i, j) of that neighbour
    //   if the neighBourRoot != current root, then we need to merge - because they connect and
    // they should have same root
    //   root[currentI, currentJ] = neighBourroot
    //   now start looking at currentI, currentJ - the actual root of tree we find
    //  check against other connected unvisited nodes
    //   if the new root is not equal to current root, again connect current to new root
    public int numIslands2(char[][] grid) {
        int height = grid.length;
        if (height == 0) {
            return 0;
        }
        int width = grid[0].length;
        int[][] roots = new int[height][width];
        for (int[] root : roots) {
            Arrays.fill(root, -1);
        }
        int[][] dirs = {{-1, 0}, {0, -1}};
        int ret = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // don't look at water
                if (grid[i][j] == '0') {
                    continue;
                }
                ret++;
                // form a single island first
                int curRoot = i * width + j;
                int curI = i;
                int curJ = j;
                roots[curI][curJ] = curRoot;
                // check if it's connected with its neighbours
                for (int[] dir : dirs) {
                    int nextI = i + dir[0];
                    int nextJ = j + dir[1];
                    // both grid[i][j] and its neighbour are 1s, they can be merged
                    if (inBound(nextI, nextJ, height, width) && grid[nextI][nextJ] == '1') {
                        int newRoot = findRoot(nextI, nextJ, roots, width);
                        if (newRoot != curRoot) {
                            // newI, newJ is the point of new root
                            // now we'll use this as new subtree
                            //  if there's a even new root found, attached newI, newJ to that new
                            // root
                            int newI = newRoot / width;
                            int newJ = newRoot % width;
                            roots[curI][curJ] = newRoot;
                            curRoot = newRoot;
                            curI = newI;
                            curJ = newJ;
                            ret--;
                        }
                    }
                }
            }
        }
        return ret;
    }

    // root is a point i, j where roots[i][j] == i * width + j
    int findRoot(int i, int j, int[][] roots, int width) {
        int root = roots[i][j];
        int newI = root / width;
        int newJ = root % width;
        if (newI == i && newJ == j) {
            return root;
        } else {
            return findRoot(newI, newJ, roots, width);
        }
    }

    boolean inBound(int i, int j, int height, int width) {
        return (i >= 0 && i < height && j >= 0 && j < width);
    }


    public int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int ret = 0;
        int height = grid.length;
        int width = grid[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == '1') {
                    flood2(grid, i, j);
                    ret++;
                }
            }
        }
        return ret;
    }

    void flood2(char[][] grid, int i, int j) {
        int height = grid.length;
        int width = grid[0].length;
        if (i < 0 || i >= height || j < 0 || j >= width) {
            return;
        }

        if (grid[i][j] == '1') {
            grid[i][j] = 'x';
            flood2(grid, i + 1, j);
            flood2(grid, i - 1, j);
            flood2(grid, i, j + 1);
            flood2(grid, i, j - 1);
        } else {
            return;
        }
    }

    void flood(char[][] grid, int i, int j) {
        LinkedList<Node> q = new LinkedList<>();
        q.add(new Node(i, j));
        int height = grid.length;
        int width = grid[0].length;
        boolean[][] visited = new boolean[height][width];

        while (!q.isEmpty()) {
            Node next = q.removeLast();
            if (next.i < 0 || next.i >= height || next.j < 0 || next.j >= width
                    || visited[i][j]) {
                continue;
            } else if (grid[i][j] == '1') {
                visited[i][j] = true;
                grid[i][j] = 'X';
                q.add(new Node(next.i + 1, next.j));
                q.add(new Node(next.i - 1, next.j));
                q.add(new Node(next.i, next.j + 1));
                q.add(new Node(next.i, next.j - 1));
            }
        }
    }

    class Node {
        int i, j;

        public Node(int argI, int argJ) {
            i = argI;
            j = argJ;
        }
    }
}
