package deleteNodeInLinkedLIst;
//
//Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
//
//        Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3,
// the linked list should become 1 -> 2 -> 4 after calling your function.
public class Solution {
    // node can't be tail
    public void deleteNode(ListNode node) {
        ListNode cur = node;
        ListNode pre = null;
        while(cur != null) {
            ListNode next = cur.next;
            if(next == null) {
                pre.next = null;
                break;
            } else {
                cur.val = next.val;
                pre = cur;
                cur = next;
            }
        }
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}