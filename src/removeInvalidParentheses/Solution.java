package removeInvalidParentheses;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

//Remove the minimum number of invalid parentheses in order to make 
// the input string valid. Return all possible results.
//
//Note: The input string may contain letters other than the parentheses ( and ).
//
//Examples:
//"()())()" -> ["()()()", "(())()"]
//"(a)())()" -> ["(a)()()", "(a())()"]
//")(" -> [""]
public class Solution {

	// for problems to find a couple of mutations, if there's no clever ways,
	// exhaust it, always try bfs or dfs
	public static void main(String[] args) {
		String ori = ")k)))())()())))())";
		for (String s : new Solution().removeInvalidParentheses(ori)) {
			System.out.println(s);
		}
	}

	public List<String> removeInvalidParentheses(String s) {
		return doBFS(s);
	}

	// idea is try to find all mutations of the string - remove a on char
	// from the string each time
	// find all leaves and continue removing from the leaves, check valid
	// for each leaf and add to result
	List<String> doBFS(String s) {
		if (s == null) {
			return new LinkedList<String>();
		}
		// remove leading ) and trailing ( for a shorter input
		int head = 0, tail = s.length() - 1;
		while (head < s.length() && s.charAt(head) == ')') {
			head++;
		}
		while (tail >= 0 && s.charAt(tail) == '(') {
			tail--;
		}
		s = s.substring(head, tail + 1);
		Set<String> visited = new HashSet<String>();
		List<String> rst = new LinkedList<String>();

		Queue<String> q = new LinkedList<>();
		System.out.println(s);
		q.offer(s);
		visited.add(s);
		boolean found = false;
		while (!q.isEmpty()) {
			String next = q.poll();
			if (valid(next)) {
				rst.add(next);
				found = true;
			}
			// trick: the first time we found a hit, we don't want to search
			// further
			// because that's already the minimal removal, any additional
			// removals are not minimal
			// that's why we're doing BFS - it's impossible that later than this
			// node
			// there's another node that's longer than this node and need
			// further removal
			// so we can savely skip all the rest
			if (found) {
				continue;
			}
			for (String leaf : getLeaves(next)) {
				if (!visited.contains(leaf)) {
					q.offer(leaf);
					visited.add(leaf);
				}
			}
		}
		return rst;
	}

	List<String> getLeaves(String root) {
		List<String> ret = new LinkedList<>();
		// remove char at i
		for (int i = 0; i < root.length(); i++) {
			// skip non parens
			if (root.charAt(i) == '(' || root.charAt(i) == ')') {
				// de dup
				if (i > 1 && root.charAt(i) == root.charAt(i - 1)) {
					continue;
				}
				ret.add(root.substring(0, i) + root.substring(i + 1));
			}
		}
		return ret;
	}

	boolean valid(String s) {
		int open = 0;
		for (char c : s.toCharArray()) {
			if (c == '(') {
				open++;
			} else if (c == ')') {
				if (open == 0) {
					return false;
				} else {
					open--;
				}
			}
		}
		return open == 0;
	}

}
