package serializeAndDeserializeBinaryTree;

import java.util.LinkedList;
import java.util.Queue;

//Serialization is the process of converting a data structure or object into
// a sequence of bits so that it can be stored in a file or memory buffer, 
// or transmitted across a network connection link to be reconstructed later 
// in the same or another computer environment.
//
//Design an algorithm to serialize and deserialize a binary tree. 
// There is no restriction on how your serialization/deserialization algorithm should work. 
// You just need to ensure that a binary tree can be serialized to a string and this string 
// can be deserialized to the original tree structure.
//
//For example, you may serialize the following tree
//
//    1
//   / \
//  2   3
//     / \
//    4   5
//as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. 
// You do not necessarily need to follow this format, so please be creative and come up 
// with different approaches yourself.
//Note: Do not use class member/global/static variables to store states. 
// Your serialize and deserialize algorithms should be stateless.
public class Codec {
	public static void main(String[] args) {
		 TreeNode root = new TreeNode(1);
		 root.setLeft(2);
		 root.setRight(3).setLeft(4);
		 root.right.setRight(5);

//		TreeNode root = new TreeNode(-1);
//		root.setLeft(0);
//		root.setRight(-2);

		String data = new Codec().serialize(root);
		System.out.println(data);

		TreeNode s = new Codec().deserialize(data);
		System.out.println("m");
	}

	public String serialize(TreeNode root) {
		// return inPreCodec(root);
		return leetCodeCodec(root);
	}

	public TreeNode deserialize(String data) {
		// return inPreDecode(data);

		return leetCodeDecode(data);
	}

	String leetCodeCodec(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode cur = q.poll();
			if (cur == null) {
				sb.append(" n");
			} else {
				sb.append(" " + cur.val);
				q.add(cur.left);
				q.add(cur.right);
			}

		}
		return sb.toString();
	}

	TreeNode leetCodeDecode(String data) {
		String[] arr = data.trim().split(" ");

		Queue<TreeNode> q = new LinkedList<>();
		
		TreeNode root = createNode(arr[0]);
		q.add(root);

		for (int i = 1; i < arr.length; i += 2) {
			TreeNode left = createNode(arr[i]);
			TreeNode right = createNode(arr[i + 1]);
			q.add(left);
			q.add(right);
			TreeNode preRoot = null;
			while(preRoot == null) {
				preRoot = q.poll();
			}
			preRoot.left = left;
			preRoot.right = right;
		}

		return root;
	}

	TreeNode createNode(String s) {
		if (s.equals("n")) {
			return null;
		} else {
			return new TreeNode(Integer.parseInt(s));
		}
	}

	////////////

	// this codec only works for single/unique digits
	String inPreCodec(TreeNode root) {
		return inOrderTraverse(root) + "/" + preOrderTraverse(root);
	}

	String inOrderTraverse(TreeNode root) {
		if (root == null) {
			return "";
		}
		return inOrderTraverse(root.left) + root.val + inOrderTraverse(root.right);
	}

	String preOrderTraverse(TreeNode root) {
		if (root == null) {
			return "";
		}
		return "" + root.val + preOrderTraverse(root.left) + preOrderTraverse(root.right);
	}

	TreeNode inPreDecode(String data) {
		if (data.equals("/")) {
			return null;
		}
		String[] arr = data.split("/");
		return decodeInPre(arr[0], arr[1]);
	}

	TreeNode decodeInPre(String inOrder, String preOrder) {
		if (inOrder == null || inOrder.length() == 0) {
			return null;
		}
		char rootChar = preOrder.charAt(0);
		int leftSize = inOrder.indexOf(rootChar);
		TreeNode root = new TreeNode(rootChar - '0');
		root.left = decodeInPre(inOrder.substring(0, leftSize), preOrder.substring(1, leftSize + 1));
		root.right = decodeInPre(inOrder.substring(leftSize + 1), preOrder.substring(leftSize + 1));
		return root;
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}

	public TreeNode setLeft(int aLeft) {
		left = new TreeNode(aLeft);
		return left;
	}

	public TreeNode setRight(int aRight) {
		right = new TreeNode(aRight);
		return right;
	}
}