package resotreIP;

import java.util.ArrayList;
import java.util.LinkedList;

//Given a string containing only digits, restore it by returning all possible valid IP address combinations.
//
//For example:
//Given "25525511135",
//
//return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
public class Solution {

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

	public static void main(String[] args) {
		ArrayList<String> ret = new Solution().restoreIpAddresses2("1231111");
		for (String s : ret)
			System.out.println(s);
	}
}
