package intersectionOfTwoLinkedLists;


import lists.ListNode;

//Write a program to find the node at which the intersection of two singly linked lists begins.
//
//
//        For example, the following two linked lists:
//
//        A:          a1 → a2
//                            ↘
//                             c1 → c2 → c3
//                            ↗
//        B:     b1 → b2 → b3
//        begin to intersect at node c1.
//
//
//        Notes:
//
//        If the two linked lists have no intersection at all, return null.
//        The linked lists must retain their original structure after the function returns.
//        You may assume there are no cycles anywhere in the entire linked structure.
//        Your code should preferably run in O(n) time and use only O(1) memory.
public class Solution {
    public static void main(String[] args) {
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // say lenA > lenB
        // use two pointers to iterate together until one pointer reaches end
        // now the other pointer P2 is lenA-lenB nodes from end
        // now use another pointer P3 starting at the longer list
        // iterate P2 and P3 together until P2 reaches end
        // now p3 is lenA-lenB from start of longer list
        // now have a P4 starting at shorter list
        // iterate P3 and P4 together till the merge to find the pointer
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != null && p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        ListNode p3 = p1 == null ? headB : headA;
        ListNode p4 = p3 == headA ? headB : headA;
        p1 = p1 == null ? p2 : p1;
        while (p1 != null) {
            p1 = p1.next;
            p3 = p3.next;
        }
        while (p3 != p4) {
            if (p3 == null || p4 == null) {
                return null;
            }
            p3 = p3.next;
            p4 = p4.next;
        }
        return p3;
    }
}
