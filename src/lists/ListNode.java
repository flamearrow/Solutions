package lists;

/**
 * Created by flamearrow on 1/18/16.
 */
public class ListNode {
    int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode setNext(int nextX) {
        next = new ListNode(nextX);
        return next;
    }
}
