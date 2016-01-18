package dungeonGame;

//The demons had captured the princess (P) and imprisoned her in the bottom-right corner of a
// dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. Our valiant knight (K)
// was initially positioned in the top-left room and must fight his way through the dungeon to
// rescue the princess.
//
//        The knight has an initial health point represented by a positive integer. If at any
// point his health point drops to 0 or below, he dies immediately.
//
//        Some of the rooms are guarded by demons, so the knight loses health (negative integers)
// upon entering these rooms; other rooms are either empty (0's) or contain magic orbs that increase
// the knight's health (positive integers).
//
//        In order to reach the princess as quickly as possible, the knight decides to move only
// rightward or downward in each step.
//
//
//        Write a function to determine the knight's minimum initial health so that he is able to
// rescue the princess.
//
//        For example, given the dungeon below, the initial health of the knight must be at least 7
// if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
//
//        -2 (K)	-3	3
//        -5	-10	1
//        10	30	-5 (P)
//
//        Notes:
//
//        The knight's health has no upper bound.
//        Any room can contain threats or power-ups, even the first room the knight enters and the
// bottom-right room where the princess is imprisoned.
public class Solution {
    public static void main(String[] args) {
//        int[][] matrix = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        int[][] matrix = {{-3, 5}};
        System.out.println(new Solution().calculateMinimumHP(matrix));

    }

    public int calculateMinimumHP(int[][] dungeon) {
        int height = dungeon.length;
        if (height == 0) {
            return 1;
        }
        int width = dungeon[0].length;

        int[][] dp = new int[height][width];
        dp[height - 1][width - 1] = dungeon[height - 1][width - 1] >= 0 ? 1 :
                1 - dungeon[height - 1][width - 1];
        for (int i = height - 1; i >= 0; i--) {
            for (int j = width - 1; j >= 0; j--) {
                if (j == width - 1) {
                    if (i < height - 1) {
                        dp[i][j] = dp[i + 1][j] - dungeon[i][j] <= 0 ? 1 : dp[i + 1][j] -
                                dungeon[i][j];
                    }
                } else if (i == height - 1) {
                    if (j < width - 1) {
                        dp[i][j] = dp[i][j + 1] - dungeon[i][j] <= 0 ? 1 : dp[i][j + 1] -
                                dungeon[i][j];
                    }
                } else {
                    int right = dp[i][j + 1] - dungeon[i][j] <= 0 ? 1 : dp[i][j + 1] -
                            dungeon[i][j];
                    int down = dp[i + 1][j] - dungeon[i][j] <= 0 ? 1 : dp[i + 1][j] - dungeon[i][j];
                    dp[i][j] = Math.min(right, down);
                }
            }
        }
        return dp[0][0];
    }
}
