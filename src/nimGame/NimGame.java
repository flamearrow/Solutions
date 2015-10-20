package nimGame;

//You are playing the following Nim Game with your friend: There is a heap of stones on the table, 
// each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. 
// You will take the first turn to remove the stones.
//
//Both of you are very clever and have optimal strategies for the game. 
// Write a function to determine whether you can win the game given the number of stones in the heap.
//
//For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove, 
//the last stone will always be removed by your friend.
public class NimGame {
	public boolean canWinNim2(int n) {
		return n % 4 > 0;
	}

	public boolean canWinNim(int n) {
		short[] wins = new short[n];
		return canWin(n, wins);
	}

	boolean canWin(int n, short[] wins) {
		if (wins[n - 1] == 1) {
			return true;
		} else if (wins[n - 1] == -1) {
			return false;
		}
		if (n == 0)
			return false;
		if (n > 0 && n < 4)
			return true;
		boolean ret = canWin(n - 1, wins) || canWin(n - 2, wins)
				|| canWin(n - 3, wins);
		wins[n - 1] = (short) (ret ? 1 : -1);
		return ret;
	}
}
