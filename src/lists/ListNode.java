package lists;

/**
 * Created by flamearrow on 1/18/16.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public ListNode(int x, ListNode next) {
        val = x;
        this.next = next;
    }


    public ListNode setNext(int nextX) {
        next = new ListNode(nextX);
        return next;
    }
}
