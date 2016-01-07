package palindromLinkedList;

//Given a singly linked list, determine if it is a palindrome.
public class Solution {
    public boolean isPalindrome(ListNode head) {
        int length = 0;
        ListNode cur = head;
        while (cur != null) {
            length++;
            cur = cur.next;
        }

        return doShit(head, length, "", 0);
    }

    boolean doShit(ListNode curNode, int length, String cur, int index) {
        if (curNode == null) {
            return cur.equals("");
        } else {
            if (index < length / 2) {
                return doShit(curNode.next, length, cur + curNode.val, index + 1);
            } else {
                if (length % 2 == 1 && index == length / 2) {
                    return doShit(curNode.next, length, cur, index + 1);
                } else {
                    return (cur.charAt(0) - '0' == curNode.val) && doShit(curNode.next, length, cur.substring(1), index + 1);
                }
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