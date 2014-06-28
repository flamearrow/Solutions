package pathBtnNodes;

import java.util.LinkedList;
import java.util.List;

// Given a binary tree, find the path between two nodes
//     1
// 2       3
//4       5 6
// 
//4, 6 -> (4-2-1-3-6)
public class Solution {
	// assume there're no dups
	static List<Integer> findPath(TreeNode root, int start, int end) {
		return doProbe(root, start, end).path;
	}

	static Rst doProbe(TreeNode root, int start, int end) {
		if (root == null) {
			return new Rst(false);
		}
		Rst ret = null;
		if (root.val == start || root.val == end) {
			// find a half
			ret = new Rst(false);
			ret.node = root.val;
			ret.path.add(ret.node);

		}
		Rst left = doProbe(root.left, start, end);
		if (left.found)
			return left;
		Rst right = doProbe(root.right, start, end);
		if (right.found)
			return right;

		if (ret != null) {
			// found the path
			if (left.node > 0) {
				ret.found = true;
				for (int i : left.path)
					// need to add reversely because left.path is stored starting from dest, 
					// we need to store end to dest
					ret.path.add(1, i);
			} else if (right.node > 0) {
				ret.found = true;
				for (int i : right.path)
					ret.path.add(1, i);
			}
			return ret;
		} else {
			// root is the parent of two nodes
			if (left.found) {
				return left;
			} else if (right.found) {
				return right;
			} else {
				// root is the parent of two nodes
				if (left.node > 0 && right.node > 0) {
					ret = new Rst(true);
					for (int i : left.path) {
						ret.path.add(i);
					}
					ret.path.add(root.val);
					int cur = ret.path.size();
					for (int i : right.path) {
						ret.path.add(cur, i);
					}
				}
				// we're on the way back track
				else if (left.node > 0 || right.node > 0) {
					ret = new Rst(false);
					for (int i : (left.node > 0 ? left.path : right.path)) {
						ret.path.add(i);
					}
					ret.node = left.node > 0 ? left.node : right.node;
					ret.path.add(root.val);
				}
				// this is a dead branch - root is none of the node and none of its children have touched any node
				else {
					ret = new Rst(false);
				}
				return ret;
			}
		}

	}

	private static class Rst {
		List<Integer> path;
		boolean found;
		int node;

		public Rst(boolean argFound) {
			found = argFound;
			path = new LinkedList<Integer>();
			node = -1;
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.setLeft(2).setLeft(4);
		root.setRight(3).setLeft(5);
		root.right.setRight(6);
		for (int i : findPath(root, 4, 6)) {
			System.out.print(i + " ");
		}
	}
}

class TreeNode {
	TreeNode left, right;
	int val;

	public TreeNode(int argVal) {
		val = argVal;
	}

	public TreeNode setLeft(int leftVal) {
		left = new TreeNode(leftVal);
		return left;
	}

	public TreeNode setRight(int rightVal) {
		right = new TreeNode(rightVal);
		return right;
	}
}
