package simplifyPath;

import java.util.LinkedList;

//Given an absolute path for a file (Unix-style), simplify it.
//
//For example,
//path = "/home/", => "/home"
//path = "/a/./b/../../c/", => "/c"
public class Solution {

	public String simplifyPath2(String path) {
		// cheat, use split
		// split() return an array that has array[0]="" if the first char is the spliter
		String[] strs = path.split("/");
		LinkedList<String> l = new LinkedList<String>();

		for (String s : strs) {
			if (s.equals("..")) {
				if (!l.isEmpty())
					l.removeLast();
			} else if (s.equals("") || s.equals("."))
				continue;
			else
				l.addLast(s);

		}
		StringBuilder sb = new StringBuilder();
		for (String s : l) {
			sb.append('/');
			sb.append(s);
		}
		if (l.size() == 0)
			sb.append('/');
		return sb.toString();
	}

	public String simplifyPath(String path) {
		LinkedList<String> paths = new LinkedList<String>();
		while (!path.isEmpty()) {
			// remove first '/'
			path = path.substring(1);
			// path="/"
			if (path.isEmpty())
				break;
			int end = path.indexOf('/');
			// path="//"
			if (end == 0)
				continue;
			String next = null;
			if (end > 0) {
				next = path.substring(0, end);
				path = path.substring(end);
			} else {
				next = path;
				path = "";
			}
			if (next.equals("..")) {
				if (paths.size() > 0)
					paths.removeLast();
			} else if (!next.equals(".")) {
				paths.add(next);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (String s : paths) {
			sb.append('/');
			sb.append(s);
		}
		if (paths.size() == 0)
			sb.append('/');
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out
				.println(new Solution().simplifyPath2("/a/./b/../../c/../.."));
	}
}
