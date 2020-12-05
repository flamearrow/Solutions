package convertBinarySearchTreeToSortedDoublyLinkedList;
//Convert a Binary Search Tree to a sorted Circular Doubly-Linked List in place.
//
//        You can think of the left and right pointers as synonymous to the predecessor and successor pointers in a doubly-linked list. For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the first element.
//
//        We want to do the transformation in place. After the transformation, the left pointer of the tree node should point to its predecessor, and the right pointer should point to its successor. You should return the pointer to the smallest element of the linked list.

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}

public class Solution {
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Result r = buildList(root);
        r.head.left = r.tail;
        r.tail.right = r.head;
        return r.head;
    }

    Result buildList(Node root) {
        if (root == null) {
            return null;
        }

        Node head;
        Node tail;
        Result leftResult = buildList(root.left);
        if (leftResult != null) {
            leftResult.tail.right = root;
            root.left = leftResult.tail;
            head = leftResult.head;
        } else {
            head = root;
        }

        Result rightResult = buildList(root.right);
        if (rightResult != null) {
            rightResult.head.left = root;
            root.right = rightResult.head;
            tail = rightResult.tail;
        } else {
            tail = root;
        }
        return new Result(head, tail);
    }

    class Result {
        Node head, tail;

        public Result(Node head, Node tail) {
            this.head = head;
            this.tail = tail;
        }
    }
}
