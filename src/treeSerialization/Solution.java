package treeSerialization;

import java.util.LinkedList;

//Serialize and deserialize a tree in a leet code way
public class Solution {
	static String serializeTree(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		doSerializeTree(root, sb);
		return sb.toString();
	}

	static void doSerializeTree(TreeNode root, StringBuilder sb) {
		if (root == null)
			sb.append('#');
		else {
			sb.append(root.val);
			doSerializeTree(root.left, sb);
			doSerializeTree(root.right, sb);
		}
	}

	static TreeNode deserializeTree(String input) {
		LinkedList<Character> chars = new LinkedList<Character>();
		for (char c : input.toCharArray()) {
			chars.add(c);
		}
		return doDeserializeTree(chars);
	}

	static TreeNode doDeserializeTree(LinkedList<Character> input) {
		char c = input.removeFirst();
		if (c == '#') {
			return null;
		} else {
			TreeNode ret = new TreeNode(c - '0');
			ret.left = doDeserializeTree(input);
			ret.right = doDeserializeTree(input);
			return ret;
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.setLeft(2).setLeft(3);
		root.left.setRight(4);
		root.setRight(5);
		String s = serializeTree(root);
		TreeNode deserializedRoot = deserializeTree(s);
		System.out.println(deserializedRoot);
	}
}

class TreeNode {
	int val;
	TreeNode left, right;

	public TreeNode(int argVal) {
		val = argVal;
	}

	TreeNode setLeft(int val) {
		left = new TreeNode(val);
		return left;
	}

	TreeNode setRight(int val) {
		right = new TreeNode(val);
		return right;
	}

	@Override
	public String toString() {
		return "" + val;
	}
}
