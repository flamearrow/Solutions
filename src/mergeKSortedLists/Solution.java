package mergeKSortedLists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

//Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
public class Solution {
	public ListNode mergeKLists3(ArrayList<ListNode> lists) {
        if(lists.size() == 0)
            return null;
        ListNode ret = null, prev = null;
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>(){
            public int compare(ListNode l1, ListNode l2) {
                return l1.val - l2.val;
            }
        });
        for(ListNode node : lists) {
        	// offer() will throw NPE for inserting null
        	// do the precheck here
            if(node != null)
                heap.offer(node);
        }
        while(!heap.isEmpty()) {
            ListNode cur = heap.poll();
            if(prev == null) {
                ret = cur;
                prev = cur;
            } else {
                prev.next = cur;
                prev = cur;
            }
            if(cur.next != null) {
                cur = cur.next;
                heap.offer(cur);
            }
        }
        return ret; 
    }

	// use a  priority queue of size k
	public ListNode mergeKLists2(ArrayList<ListNode> lists) {
		Comparator<ListNode> minCpt = new Comparator<ListNode>() {
			@Override
			public int compare(ListNode arg0, ListNode arg1) {
				return arg0.val - arg1.val;
			}

		};
		// Note priority queue defaults to Natural ordering, poll() always returns the SMALLEST value
		PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(
				lists.size(), minCpt);
		ListNode ret = null, cur = null;
		for (int i = 0; i < lists.size(); i++) {
			heap.add(lists.get(i));
		}
		while (!heap.isEmpty()) {
			ListNode min = heap.poll();
			if (ret == null) {
				ret = min;
				cur = ret;
			} else {
				cur.next = min;
				cur = cur.next;
			}
			if (min.next != null)
				heap.offer(min.next);
		}
		return ret;
	}

	public ListNode mergeKLists(ArrayList<ListNode> lists) {
		ListNode ret = null;
		ListNode cur = null;
		ListNode next = getMin(lists);
		while (next != null) {
			if (ret == null) {
				ret = next;
				cur = ret;
			} else {
				cur.next = next;
				cur = cur.next;
			}
			next = getMin(lists);
		}
		return ret;
	}

	ListNode getMin(ArrayList<ListNode> lists) {
		int min = Integer.MAX_VALUE;
		int minIndex = -1;
		for (int i = 0; i < lists.size(); i++) {
			ListNode node = lists.get(i);
			if (node == null)
				continue;
			if (node.val < min) {
				min = node.val;
				minIndex = i;
			}
		}
		if (minIndex == -1)
			return null;
		ListNode ret = lists.get(minIndex);
		lists.remove(minIndex);
		if (ret.next != null)
			lists.add(ret.next);
		ret.next = null;
		return ret;
	}

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		n1.setNext(4).setNext(7);

		ListNode n2 = new ListNode(2);
		n2.setNext(5).setNext(8);

		ListNode n3 = new ListNode(3);
		n3.setNext(6).setNext(9);
		ArrayList<ListNode> lists = new ArrayList<ListNode>();
		lists.add(n1);
		lists.add(n2);
		lists.add(n3);
		ListNode ret = new Solution().mergeKLists2(lists);
		while (ret != null) {
			System.out.print(ret.val + " ");
			ret = ret.next;
		}
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}

	ListNode setNext(int i) {
		next = new ListNode(i);
		return next;
	}
}