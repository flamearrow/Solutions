package reconstructItinerary;

import java.util.*;

//Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
//
//        Note:
//
//        If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
//        All airports are represented by three capital letters (IATA code).
//        You may assume all tickets form at least one valid itinerary.
//        One must use all the tickets once and only once.
//        Example 1:
//
//        Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
//        Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
//        Example 2:
//
//        Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//        Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
//        Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
//        But it is larger in lexical order.
public class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        tickets.sort(new Comparator<List<String>>() {
            @Override
            public int compare(List<String> o1, List<String> o2) {
                // if 'from' is the same, compare 'to', otherwise compare 'from'
                return o1.get(0).equals(o2.get(0)) ? o1.get(1).compareTo(o2.get(1)) :
                        o1.get(0).compareTo(o2.get(0));
            }
        });
        LinkedList<String> previous = new LinkedList<>();
        previous.add("JFK");
        if (findRouteFrom("JFK", tickets, previous)) {
            return previous;
        } else {
            return null;
        }
    }

    boolean findRouteFrom(String start, List<List<String>> tickets, LinkedList<String> previous) {
        if (tickets.size() == 0) {
            return true;
        }
        for (int i = 0; i < tickets.size(); i++) {
            List<String> ticket = tickets.get(i);
            // a valid edge
            if (ticket.get(0).equals(start)) {
                previous.addLast(ticket.get(1));
                tickets.remove(i);
                if (findRouteFrom(ticket.get(1), tickets, previous)) {
                    return true;
                } else {
                    tickets.add(i, ticket);
                    previous.removeLast();
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
//        tickets.add(Arrays.asList("MUC", "LHR"));
//        tickets.add(Arrays.asList("JFK", "MUC"));
//        tickets.add(Arrays.asList("SFO", "SJC"));
//        tickets.add(Arrays.asList("LHR", "SFO"));
        tickets.add(Arrays.asList("JFK", "SFO"));
        tickets.add(Arrays.asList("JFK", "ATL"));
        tickets.add(Arrays.asList("SFO", "ATL"));
        tickets.add(Arrays.asList("ATL", "JFK"));
        tickets.add(Arrays.asList("ATL", "SFO"));

        for (String s : new Solution().findItinerary(tickets)) {
            System.out.println(s);
        }
    }

}
