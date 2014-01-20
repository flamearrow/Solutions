package crackingTheCodingInterview.linkedLists;

// check if a linked list is a palindrome
public class PalindromeLinkedList {
	// can use a stack and two different speed pointer to find mid and end
	// then check

	// try recursion without additional space
	// use a temp Result to store the next node to be check and the result of
	// next block
	
	boolean isPalindrome(Node head) {
		if (head == null)
			return true;
		int len = len(head);
		return doCheck(head, len).b;
	}

	Result doCheck(Node head, int len) {
		if (len == 1) {
			return new Result(head.next, true);
		} else if (len == 2) {
			if (head.val == head.next.val) {
				return new Result(head.next.next, true);
			} else {
				return new Result(null, false);
			}
		}
		// if from cur+1 to current+len-2 is palindrome
		//  and cur is equal to current+len
		// then cur to len is palindrome
		Result sub = doCheck(head.next, len - 2);
		if (sub.b && sub.n.val == head.val)
			return new Result(sub.n.next, true);
		else {
			return new Result(null, false);
		}
	}

	class Result {
		Node n;
		boolean b;

		public Result(Node n, boolean b) {
			this.n = n;
			this.b = b;
		}
	}

	int len(Node head) {
		// head value won't be updated outside the method
		int ret = 0;
		while (head != null) {
			ret++;
			head = head.next;
		}
		return ret;
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.setNext(new Node(2)).setNext(new Node(3)).setNext(new Node(3))
				.setNext(new Node(3)).setNext(new Node(2)).setNext(new Node(1));
		System.out.println(new PalindromeLinkedList().isPalindrome(head));
	}
}
