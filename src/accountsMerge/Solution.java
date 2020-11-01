package accountsMerge;

import java.util.*;

//Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
//
//        Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts.
//        Note that even if two accounts have the same name, they may belong to different people as people could have the same name.
//        A person can have any number of accounts initially, but all of their accounts definitely have the same name.
//
//        After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
//
//        Example 1:
//        Input:
//        accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
//        Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
//        Explanation:
//        The first and third John's are the same person as they have the common email "johnsmith@mail.com".
//        The second John and Mary are different people as none of their email addresses are used by other accounts.
//        We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
//        ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
//        Note:
//
//        The length of accounts will be in the range [1, 1000].
//        The length of accounts[i] will be in the range [1, 10].
//        The length of accounts[i][j] will be in the range [1, 30].
public class Solution {

    // find all connnected emails and group them
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        Map<String, Set<String>> emailEdges = new HashMap<>();
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                // add itself, when there's only one email the stack still gets touched once
                emailEdges.computeIfAbsent(account.get(1), x -> new HashSet<>()).add(account.get(i));
                emailEdges.computeIfAbsent(account.get(i), x -> new HashSet<>()).add(account.get(1));
                emailToName.put(account.get(i), account.get(0));
            }

        }

        List<List<String>> ret = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        for (String email1 : emailEdges.keySet()) {
            if (seen.contains(email1)) {
                continue;
            }
            List<String> emails = new ArrayList<>();

            LinkedList<String> stack = new LinkedList<>();
            stack.addLast(email1);

            while (!stack.isEmpty()) {
                String nextEmail = stack.removeLast();
                if (seen.contains(nextEmail)) {
                    continue;
                }
                seen.add(nextEmail);
                emails.add(nextEmail);
                for (String email : emailEdges.get(nextEmail)) {
                    if (!seen.contains(email)) {
                        stack.addLast(email);
                    }
                }
            }

            Collections.sort(emails);
            emails.add(0, emailToName.get(email1));
            ret.add(emails);
        }
        return ret;
    }


    // use the Disjoint Unit Set shit to reimplement
    // map emails to 10000, use parent[i] = j to indicate an edge
    public List<List<String>> accountsMergeDisjointUnitSet(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap<>();
        DUS dus = new DUS();
        for (List<String> account : accounts) {
            String name = account.get(0);
            String firstEmail = account.get(1);
            int firstId = dus.getEmailId(firstEmail);
            int root = dus.findRoot(firstId);
            for (int i = 1; i < account.size(); i++) {
                String currentEmail = account.get(i);
                emailToName.putIfAbsent(currentEmail, name);
                int currentId = dus.getEmailId(currentEmail);
                dus.parents[dus.findRoot(currentId)] = root;
            }
        }

        Map<Integer, List<String>> parentIdToEmails = new HashMap<>();
        for (String email : emailToName.keySet()) {
            parentIdToEmails.computeIfAbsent(dus.findRoot(email),
                    x -> new ArrayList<>()).add(email);
        }
        List<List<String>> ret = new ArrayList<>();
        for (List<String> newRecord : parentIdToEmails.values()) {
            Collections.sort(newRecord);
            newRecord.add(0, emailToName.get(newRecord.get(0)));
            ret.add(newRecord);
        }
        return ret;
    }

    static class DUS {
        int[] parents;
        Map<String, Integer> emailIds;
        int length;

        public DUS() {
            int size = 10000;
            parents = new int[size];
            emailIds = new HashMap<>();
            for (int i = 0; i < 10000; i++) {
                parents[i] = i;
            }
            length = 0;
        }

        int findRoot(int i) {
            while (i != parents[i]) {
                i = parents[i];
            }
            return i;
        }

        int findRoot(String email) {
            return findRoot(emailIds.get(email));
        }

        int getEmailId(String email) {
            if (!emailIds.containsKey(email)) {
                emailIds.put(email, length);
                length++;
            }
            return emailIds.get(email);
        }
    }


    public static void main(String[] args) {
        //[["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
        //[["Alex","Alex5@m.co","Alex4@m.co","Alex0@m.co"],["Ethan","Ethan3@m.co","Ethan3@m.co","Ethan0@m.co"],["Kevin","Kevin4@m.co","Kevin2@m.co","Kevin2@m.co"],["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe2@m.co"],["Gabe","Gabe3@m.co","Gabe4@m.co","Gabe2@m.co"]]
        // [["Alex","Alex0@m.co","Alex4@m.co","Alex5@m.co"],["Ethan","Ethan0@m.co","Ethan3@m.co"],["Gabe","Gabe0@m.co","Gabe2@m.co","Gabe3@m.co","Gabe4@m.co"],["Kevin","Kevin2@m.co","Kevin4@m.co"]]
        // [["Gabe","Gabe0@m.co","Gabe2@m.co","Gabe3@m.co","Gabe4@m.co"],["Kevin","Kevin2@m.co","Kevin4@m.co"],["Alex","Alex0@m.co","Alex4@m.co","Alex5@m.co"],["Ethan","Ethan0@m.co","Ethan3@m.co"]]


//        [["David","David0@m.co","David4@m.co","David3@m.co"],["David","David5@m.co","David5@m.co","David0@m.co"],["David","David1@m.co","David4@m.co","David0@m.co"],["David","David0@m.co","David1@m.co","David3@m.co"],["David","David4@m.co","David1@m.co","David3@m.co"]]
        List<List<String>> input = new ArrayList<>();
//        input.add(Arrays.asList("David", "David0@m.co", "David4@m.co", "David3@m.co"));
        input.add(Arrays.asList("David", "David5@m.co", "David5@m.co", "David0@m.co"));
        input.add(Arrays.asList("David", "David1@m.co", "David4@m.co", "David0@m.co"));
//        input.add(Arrays.asList("David", "David0@m.co", "David1@m.co", "David3@m.co"));
//        input.add(Arrays.asList("David", "David4@m.co", "David1@m.co", "David3@m.co"));

        for (List<String> account : new Solution().accountsMergeDisjointUnitSet(input)) {
            for (String s : account) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
