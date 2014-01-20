package crackingTheCodingInterview.linkedLists;

public class Node {
	int val;
	Node next;

	public Node(int val) {
		this.val = val;
		next = null;
	}

	public Node setNext(Node n) {
		next = n;
		return n;
	}
}
