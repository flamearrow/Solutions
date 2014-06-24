package crackingTheCodingInterview.c17;

// BiNode has a pointer to left and right BiNode, it can be used to represent a BST
// or a doubly linked list. Given BST represented by BiNode, 
// convert it into a sorted doubly linkedlist
public class Question13BiNode {
	static BiNode convertToList(BiNode root) {
		return doConvert(root).head;
	}

	// using a temp data structure
	static TmpRst doConvert(BiNode root) {
		if (root == null)
			return null;
		TmpRst ret = new TmpRst();
		TmpRst left = doConvert(root.left);
		TmpRst right = doConvert(root.right);
		root.left = null;
		root.right = null;
		if (left != null) {
			ret.head = left.head;
			left.tail.right = root;
			root.left = left.tail;
		} else {
			ret.head = root;
		}

		if (right != null) {
			ret.tail = right.tail;
			right.head.left = root;
			root.right = right.head;
		} else {
			ret.tail = root;
		}
		return ret;
	}

	static BiNode convertToList2(BiNode root) {
		BiNode ret = doConvert2(root);
		// break the circle
		ret.left.right = null;
		ret.left = null;
		return ret;
	}

	// don't use any tmp ds, return the head of the converted linked list
	// note the returned list is a circular linked list
	// i.e head.left = tail and tail.right = head
	static BiNode doConvert2(BiNode root) {
		if (root == null)
			return null;
		BiNode left = doConvert2(root.left);
		BiNode right = doConvert2(root.right);
		// note to flag left and right before we do the re-linkage
		BiNode head = left == null ? root : left;
		BiNode tail = right == null ? root : right.left;
		if (left != null) {
			// left.left is the tail of left part
			left.left.right = root;
			root.left = left.left;
		}
		if (right != null) {
			root.right = right;
			right.left = root;
		}

		head.left = tail;
		tail.right = head;
		return head;
	}

	public static void main(String[] args) {
		BiNode root = new BiNode(5);
		root.setLeft(3).setLeft(1);
		root.left.setRight(4);
		root.setRight(6).setRight(8).setRight(9);
		BiNode head = convertToList2(root);
		System.out.print(head.value);
		while (head.right != null) {
			head = head.right;
			System.out.print(" " + head.value);
		}

		System.out.println("\nreverse...");
		System.out.print(head.value);
		while (head.left != null) {
			head = head.left;
			System.out.print(" " + head.value);
		}

	}

	static final class TmpRst {
		BiNode head, tail;
	}

	static final class BiNode {
		BiNode left, right;
		int value;

		public BiNode(int value) {
			this.value = value;
		}

		public BiNode setLeft(int leftValue) {
			left = new BiNode(leftValue);
			return left;
		}

		public BiNode setRight(int rightValue) {
			right = new BiNode(rightValue);
			return right;
		}
	}
}
