package flipGame2;

import java.util.HashSet;
import java.util.Set;

//You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, 
// you and your friend take turns to flip two consecutive "++" into "--". 
// The game ends when a person can no longer make a move and therefore the other person will be the winner.
//
//Write a function to determine if the starting player can guarantee a win.
//
//For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".
//
//Follow up:
//Derive your algorithm's runtime complexity.
public class FlipGame2 {
	public static void main(String[] args) {
		System.out.println(new FlipGame2().canWin("+++++"));
	}

	public boolean canWin2(String s) {
		for (String sub : createMutates(s)) {
			if (!canWin2(sub)) {
				return true;
			}
		}
		// you can't go further, you loose
		return false;
	}

	public boolean canWin(String s) {
		return dfs(s, 0);
	}

	boolean dfs(String s, int level) {
		Set<String> mutates = createMutates(s);
		// starting player hand
		if (level % 2 == 0) {
			for (String sub : mutates) {
				if (dfs(sub, level + 1)) {
					return true;
				}
			}
			return false;
		}
		// other player hand
		else {
			for (String sub : mutates) {
				if (!dfs(sub, level + 1)) {
					return false;
				}
			}
			return true;
		}
	}

	Set<String> createMutates(String s) {
		Set<String> ret = new HashSet<String>();
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == s.charAt(i - 1) && s.charAt(i) == '+') {
				ret.add(s.substring(0, i - 1) + "--" + s.substring(i + 1));
			}
		}
		return ret;
	}
}
