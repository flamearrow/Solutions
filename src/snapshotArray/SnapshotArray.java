package snapshotArray;

import binaryGap.Solution;
import intervalTree.IntervalTree;

import java.util.*;

//Implement a SnapshotArray that supports the following interface:
//
//        SnapshotArray(int length) initializes an array-like data structure with the given length.  Initially, each element equals 0.
//        void set(index, val) sets the element at the given index to be equal to val.
//        int snap() takes a snapshot of the array and returns the snap_id: the total number of times we called snap() minus 1.
//        int get(index, snap_id) returns the value at the given index, at the time we took the snapshot with the given snap_id
//
//
//        Example 1:
//
//        Input: ["SnapshotArray","set","snap","set","get"]
//        [[3],[0,5],[],[0,6],[0,0]]
//        Output: [null,null,0,null,5]
//        Explanation:
//        SnapshotArray snapshotArr = new SnapshotArray(3); // set the length to be 3
//        snapshotArr.set(0,5);  // Set array[0] = 5
//        snapshotArr.snap();  // Take a snapshot, return snap_id = 0
//        snapshotArr.set(0,6);
//        snapshotArr.get(0,0);  // Get the value of array[0] with snap_id = 0, return 5
//
//
//        Constraints:
//
//        1 <= length <= 50000
//        At most 50000 calls will be made to set, snap, and get.
//        0 <= index < length
//0 <= snap_id < (the total number of times we call snap())
//        0 <= val <= 10^9
class SnapshotArrayStupid {
    class Node {
        int startSnap;
        int val;

        public Node(int startSnap, int val) {
            this.startSnap = startSnap;
            this.val = val;
        }

        Node next;
    }


    Node[] buffer;
    int currentSnap;

    public SnapshotArrayStupid(int length) {
        buffer = new Node[length];
        for (int i = 0; i < length; i++) {
            buffer[i] = new Node(0, 0);
        }
        currentSnap = 0;
    }

    public void set(int index, int val) {
        Node head = buffer[index];
        while (head.next != null) {
            head = head.next;
        }
        if (head.startSnap == currentSnap) {
            head.val = val;
        } else {
            if (head.val == val) {
                head.startSnap = currentSnap;
            } else {
                head.next = new Node(currentSnap, val);
            }
        }
    }

    public int snap() {
        int ret = currentSnap;
        currentSnap++;
        return ret;
    }

    public int get(int index, int snap_id) {
        Node head = buffer[index];
        while (head.next != null && head.next.startSnap < snap_id) {
            head = head.next;
        }
        return head.next == null ? head.val : head.next.startSnap == snap_id ? head.next.val :
                head.val;

    }
}


class SnapshotArrayLessStupid {
    HashMap<Integer, Integer>[] buffer;
    int currentSnap;

    public SnapshotArrayLessStupid(int length) {
        currentSnap = 0;
        buffer = new HashMap[length];
        for (int i = 0; i < length; i++) {
            buffer[i] = new HashMap<>();
        }
    }

    public void set(int index, int val) {
        buffer[index].put(currentSnap, val);
    }

    public int snap() {
        int ret = currentSnap;
        currentSnap++;
        return ret;
    }

    public int get(int index, int snap_id) {
        // just search reversely until get a hit
        for (int i = snap_id; i >= 0; i--) {
            if (buffer[index].containsKey(i)) {
                return buffer[index].get(i);
            }
        }
        return 0;
    }

}

public class SnapshotArray {
    NavigableMap<Integer, Integer>[] buffer;
    int currentSnap;

    public SnapshotArray(int length) {
        currentSnap = 0;
        buffer = new TreeMap[length];
        for (int i = 0; i < length; i++) {
            buffer[i] = new TreeMap<>();
        }
    }

    public void set(int index, int val) {
        buffer[index].put(currentSnap, val);
    }

    public int snap() {
        int ret = currentSnap;
        currentSnap++;
        return ret;
    }

    public int get(int index, int snap_id) {
        Integer key = buffer[index].floorKey(snap_id);
        return key == null ? 0 : buffer[index].get(key);
    }

    public static void main(String[] args) {
        SnapshotArray s = new SnapshotArray(3);
//        s.snap();
//        s.snap();
//        s.get(3, 1);
        s.set(0, 5);
        s.snap();
        s.set(0, 6);
        s.get(0, 0);
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */