package countIsland;

// count island in a grid
//      0011010
// 	    0010010
//		1000110
//		0000001
// there are four island
public class Solution {
	int countIsland(int[][] input) {
		boolean[][] buffer = new boolean[input.length][input[0].length];
		int height = input.length;
		int width = input[0].length;
		int ret = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// this is a node on an unvisited island
				if (input[i][j] == 1 && !buffer[i][j]) {
					doProbe(input, i, j, buffer);
					ret++;
				}
			}
		}
		return ret;
	}

	void doProbe(int[][] input, int i, int j, boolean[][] buffer) {
		buffer[i][j] = true;
		// try up
		if (i - 1 >= 0 && !buffer[i - 1][j] && input[i - 1][j] == 1)
			doProbe(input, i - 1, j, buffer);
		// try down
		if (i + 1 < input.length && !buffer[i + 1][j] && input[i + 1][j] == 1)
			doProbe(input, i + 1, j, buffer);
		// try left
		if (j - 1 >= 0 && !buffer[i][j - 1] && input[i][j - 1] == 1)
			doProbe(input, i, j - 1, buffer);
		// try right
		if (j + 1 < input[0].length && !buffer[i][j + 1]
				&& input[i][j + 1] == 1)
			doProbe(input, i, j + 1, buffer);
	}

	//	0011010
	//	0010010
	//	1000110
	//	0000001
	public static void main(String[] args) {
		//		int[][] input = { { 0, 0, 1, 1, 0, 1, 0 }, { 0, 0, 1, 0, 0, 1, 0 },
		//				{ 1, 0, 0, 0, 1, 1, 0 }, { 0, 0, 0, 0, 0, 0, 1 } };
		int[][] input = { { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1 } };
		System.out.println(new Solution().countIsland(input));
	}

}

class Node {
	int x, y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
