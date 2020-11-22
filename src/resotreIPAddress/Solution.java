package resotreIPAddress;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Given a string containing only digits, restore it by returning all possible valid IP address combinations.
//
//For example:
//Given "25525511135",
//
//return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
public class Solution {

    public static void main(String[] args) {
        List<String> ret = new Solution().restoreIpAddresses4("25525511135");
        for (String s : ret)
            System.out.println(s);
    }

    public List<String> restoreIpAddresses4(String s) {
        List<List<String>> results = findCombination(s, 4);
        List<String> ret = new ArrayList<>();
        for (List<String> result : results) {
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for (String ss : result) {
                if (first) {
                    sb.append(ss);
                    first = false;
                } else {
                    sb.append(".").append(ss);
                }
            }
            ret.add(sb.toString());
        }

        return ret;
    }

    List<List<String>> findCombination(String s, int num) {
        if (num == 1) {
            List<List<String>> list = new ArrayList<>();
            if (isValidIP(s)) {
                List<String> cur = new ArrayList<>();
                cur.add(s);
                list.add(cur);
            }
            return list;
        }
        List<List<String>> res = new ArrayList<>();
        for (int i = 1; i < s.length() && i <= 3; i++) {
            String nextIP = s.substring(0, i);
            if (isValidIP(nextIP)) {
                for (List<String> subResult : findCombination(s.substring(i), num - 1)) {
                    subResult.add(0, nextIP);
                    res.add(subResult);
                }
            }
        }
        return res;
    }

    boolean isValidIP(String s) {
        if (s.equals("0")) {
            return true;
        }
        if (s.length() > 3 || s.startsWith("0")) {
            return false;
        }
        int intValue = Integer.parseInt(s);
        if (intValue < 0 || intValue > 255) {
            return false;
        }
        return true;
    }


    public List<String> restoreIpAddresses3(String s) {
        List<String> ret = new LinkedList<String>();
        LinkedList<String> cur = new LinkedList<String>();
        doProbe(s, 0, cur, ret);
        return ret;
    }

    void doProbe(String s, int curIndex, LinkedList<String> curStr,
                 List<String> ret) {
        if (curStr.size() > 4)
            return;
        if (curIndex == s.length() && curStr.size() == 4) {
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for (String ss : curStr) {
                if (first) {
                    sb.append(ss);
                    first = false;
                } else {
                    sb.append('.');
                    sb.append(ss);
                }
            }
            ret.add(sb.toString());
            return;
        }
        for (int i = 1; i < 4 && curIndex + i <= s.length(); i++) {
            if (valid(s.substring(curIndex, curIndex + i))) {
                curStr.add(s.substring(curIndex, curIndex + i));
                doProbe(s, curIndex + i, curStr, ret);
                curStr.removeLast();
            }
        }
    }

    boolean valid(String s) {
        int i = Integer.parseInt(s);
        return (s.charAt(0) != '0' && s.length() > 1 || s.length() == 1)
                && i >= 0 && i <= 255;
    }

    public ArrayList<String> restoreIpAddresses2(String s) {
        ArrayList<String> ret = new ArrayList<String>();
        ArrayList<String> curPath = new ArrayList<String>(4);
        doProbe(ret, curPath, s);
        return ret;
    }

    void doProbe(ArrayList<String> ret, ArrayList<String> curPath, String s) {
        int left = 4 - curPath.size();
        if (left == 0) {
            if (s.length() > 0)
                return;
            String newRst = curPath.get(0) + "." + curPath.get(1) + "."
                    + curPath.get(2) + "." + curPath.get(3);
            ret.add(newRst);
            return;
        }
        if (s.length() == 0)
            return;
        for (int i = 1; i <= s.length() && i < 4; i++) {
            String newInt = s.substring(0, i);
            // 010 is not a valid candidate
            if (newInt.length() > 1 && newInt.charAt(0) == '0')
                continue;
            if (Integer.parseInt(newInt) <= 255) {
                curPath.add(newInt);
                doProbe(ret, curPath, s.substring(i));
                curPath.remove(curPath.size() - 1);
            }
        }
    }

    ArrayList<String> ret = new ArrayList<String>();

    // a similar solution with the dictionary partitioning, use probe
    //
    // another solution would be using a three for loop to represent all
    // possible subStrings and check if it's valid
    public ArrayList<String> restoreIpAddresses(String s) {
        if (s.length() > 12 || s.length() < 4)
            return ret;
        LinkedList<String> buffer = new LinkedList<String>();
        probe(s, 0, buffer);
        return ret;
    }

    void probe(String s, int index, LinkedList<String> buffer) {
        if (index == s.length()) {
            if (buffer.size() == 4)
                ret.add(buffer.get(0) + "." + buffer.get(1) + "."
                        + buffer.get(2) + "." + buffer.get(3));
        } else {
            if (buffer.size() < 4) {
                // probe one digit if applicable
                if (s.charAt(index) >= '0') {
                    buffer.add(s.substring(index, index + 1));
                    probe(s, index + 1, buffer);
                    buffer.removeLast();
                }

                // probe two digits if applicable
                if (index + 1 < s.length() && s.charAt(index) > '0') {
                    buffer.add(s.substring(index, index + 2));
                    probe(s, index + 2, buffer);
                    buffer.removeLast();
                }

                // probe three digits if applicable
                if (index + 2 < s.length()
                        && s.charAt(index) > '0'
                        && Integer.parseInt(s.substring(index, index + 3)) <= 255) {
                    buffer.add(s.substring(index, index + 3));
                    probe(s, index + 3, buffer);
                    buffer.removeLast();
                }
            }
        }
    }
}
