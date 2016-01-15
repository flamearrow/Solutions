package removeLinkedlistElements;

//Remove all elements from a linked list of integers that have value val.
//
//        Example
//        Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
//        Return: 1 --> 2 --> 3 --> 4 --> 5
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode pre = null, cur = head, ret = head;
        while (cur != null) {
            if (cur.val == val) {
                if (pre != null) {
                    pre.next = cur.next;
                } else {
                    ret = cur.next;
                }
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return ret;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}