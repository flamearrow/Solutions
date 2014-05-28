package bTreeIterator;

import java.util.Iterator;
import java.util.Stack;

// implement an iterator for btree with hasNext() and next()
public class Solution {
	public static void main(String[] args) {
		IterableTreeNode root = new IterableTreeNode(1);
		root.setLeft(2).setLeft(3).setLeft(4);
		root.left.left.setRight(5).setLeft(6).setLeft(8);
		root.left.left.right.setRight(7);
		root.left.left.right.left.setRight(9);
		root.left.left.left.setRight(10).setLeft(11).setLeft(12);
		root.setRight(13).setRight(14).setLeft(15);
		root.right.right.setRight(16);
		Iterator<IterableTreeNode> itr = root.iterator();
		while (itr.hasNext())
			System.out.print(itr.next().val + " ");

		//		IterableTreeNode bst = new IterableTreeNode(5);
		//		bst.setLeft(3).setLeft(1);
		//		bst.left.setRight(4);
		//		bst.setRight(7).setLeft(6);
		//		bst.right.setRight(8);
		//		bst.setLeft(4).setLeft(3).setLeft(2).setLeft(1);
		//		//		bst.setRight(6).setRight(7).setRight(8).setRight(9).setRight(10);
		//		Iterator<IterableTreeNode> bstItr = bst.bstIterator();
		//		while (bstItr.hasNext()) {
		//			System.out.print(bstItr.next().val + " ");
		//		}
	}
}

class IterableTreeNode implements Iterable<IterableTreeNode> {

	IterableTreeNode left, right;
	int val;

	public IterableTreeNode(int val) {
		this.val = val;
	}

	public IterableTreeNode(IterableTreeNode left, IterableTreeNode right,
			int val) {
		this.left = left;
		this.right = right;
		this.val = val;
	}

	IterableTreeNode setLeft(int val) {
		left = new IterableTreeNode(val);
		return left;
	}

	IterableTreeNode setRight(int val) {
		right = new IterableTreeNode(val);
		return right;
	}

	@Override
	public Iterator<IterableTreeNode> iterator() {
		return new StackTreeIterator2(this);
	}

	public Iterator<IterableTreeNode> bstIterator() {
		return new BSTIterator(this);
	}
}

// This iterator only works for BST
// The idea is to keep track of the previously visited node and search for it's successor in next()
// to find next, we first check prevValue.right, 
//    if it's empty, then we need to search from root, keep track of the last node that's > then prev and return
//    if it's not empty, then we return the left most child of prev.right
// note we can prob right most in constructor to get the maxValue of the tree and do bound check easily
class BSTIterator implements Iterator<IterableTreeNode> {
	int maxValue;
	IterableTreeNode root;
	IterableTreeNode prevValue;

	public BSTIterator(IterableTreeNode root) {
		prevValue = null;
		this.root = root;
		IterableTreeNode cur = root;
		while (cur.right != null) {
			cur = cur.right;
		}
		maxValue = cur.val;
	}

	@Override
	public boolean hasNext() {
		return prevValue == null || prevValue.val < maxValue;
	}

	@Override
	public IterableTreeNode next() {
		if (prevValue == null) {
			IterableTreeNode cur = root;
			while (cur.left != null)
				cur = cur.left;
			prevValue = cur;
			return cur;
		} else if (prevValue.val == maxValue) {
			throw new MLGB();
		} else {
			IterableTreeNode cur = prevValue;
			// next is in right sub tree
			if (cur.right != null) {
				IterableTreeNode ret = cur.right;
				while (ret.left != null) {
					ret = ret.left;
				}
				prevValue = ret;
				return ret;
			} else {
				IterableTreeNode prev = null, prob = root;
				while (prob != cur) {
					if (prob.val < cur.val) {
						prob = prob.right;
					} else {
						prev = prob;
						prob = prob.left;
					}
				}
				if (prev != null) {
					prevValue = prev;
					return prev;
				} else {
					throw new MLGB();
				}
			}
		}
	}

	@Override
	public void remove() {
		throw new MLGB();
	}

}

// always return the head of the stack.
// when the next value's right child!=null, 
// push the entire left branch of that right child
class StackTreeIterator2 implements Iterator<IterableTreeNode> {
	Stack<IterableTreeNode> _s;

	public StackTreeIterator2(IterableTreeNode root) {
		_s = new Stack<IterableTreeNode>();
		IterableTreeNode cur = root;
		while (cur != null) {
			_s.push(cur);
			cur = cur.left;
		}
	}

	@Override
	public boolean hasNext() {
		return !_s.isEmpty();
	}

	@Override
	public IterableTreeNode next() {
		if (_s.isEmpty())
			throw new MLGB();
		IterableTreeNode next = _s.pop();
		IterableTreeNode cur = next.right;
		while (cur != null) {
			_s.push(cur);
			cur = cur.left;
		}
		return next;
	}

	@Override
	public void remove() {
		throw new MLGB();

	}
}

class MLGB extends RuntimeException {

	private static final long serialVersionUID = 1L;

}
