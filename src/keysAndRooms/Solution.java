package keysAndRooms;
//There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room.
//
//        Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.
//
//        Initially, all the rooms start locked (except for room 0).
//
//        You can walk back and forth between rooms freely.
//
//        Return true if and only if you can enter every room.
//
//        Example 1:
//
//        Input: [[1],[2],[3],[]]
//        Output: true
//        Explanation:
//        We start in room 0, and pick up key 1.
//        We then go to room 1, and pick up key 2.
//        We then go to room 2, and pick up key 3.
//        We then go to room 3.  Since we were able to go to every room, we return true.
//        Example 2:
//
//        Input: [[1,3],[3,0,1],[2],[0]]
//        Output: false
//        Explanation: We can't enter the room with number 2.
//        Note:
//
//        1 <= rooms.length <= 1000
//        0 <= rooms[i].length <= 1000
//        The number of keys in all rooms combined is at most 3000.

import java.util.*;

public class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int numOfRooms = rooms.size();
        Set<Integer> visited = new HashSet<>();

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);

        while (!queue.isEmpty()) {
            Integer currentRoom = queue.removeFirst();
            if (!visited.contains(currentRoom)) {
                visited.add(currentRoom);
                for (Integer nextRoom : rooms.get(currentRoom)) {
                    if (!visited.contains(nextRoom)) {
                        queue.addLast(nextRoom);
                    }
                }
            }
        }

        for (int i = 0; i < numOfRooms; i++) {
            if (!visited.contains(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<List<Integer>> rooms = new ArrayList<>();
        List<Integer> r1 = new ArrayList<>();
        r1.add(1);
        rooms.add(r1);
        List<Integer> r2 = new ArrayList<>();
        r2.add(2);
        rooms.add(r2);
        List<Integer> r3 = new ArrayList<>();
        r3.add(3);
        rooms.add(r3);
        List<Integer> r4 = new ArrayList<>();
        rooms.add(r4);

        System.out.println(new Solution().canVisitAllRooms(rooms));
    }
}
