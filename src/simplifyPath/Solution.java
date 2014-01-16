package simplifyPath;

import java.util.LinkedList;

//Given an absolute path for a file (Unix-style), simplify it.
//
//For example,
//path = "/home/", => "/home"
//path = "/a/./b/../../c/", => "/c"
public class Solution {
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
		System.out.println(new Solution().simplifyPath("/a/////b/../"));
	}
}
