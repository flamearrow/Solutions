package plusOneLinkedList;

import lists.ListNode;

//Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
//
//        You may assume the integer do not contain any leading zero, except the number 0 itself.
//
//        The digits are stored such that the most significant digit is at the head of the list.
//
//        Example :
//
//        Input: [1,2,3]
//        Output: [1,2,4]
public class Solution {
    public ListNode plusOne(ListNode head) {
        int carry = doPlusOne(head);
        if (carry == 1) {
            return new ListNode(1, head);
        } else {
            return head;
        }

    }

    int doPlusOne(ListNode head) {
        if (head.next == null) {
            int result = head.val + 1;
            head.val = result % 10;
            return result / 10;
        } else {
            int carry = doPlusOne(head.next);
            int result = head.val + carry;
            head.val = result % 10;
            return result / 10;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(9);
        System.out.println(new Solution().plusOne(head));
    }
}
