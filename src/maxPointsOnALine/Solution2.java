package maxPointsOnALine;

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a point. class Point { int x; int y; Point() { x = 0; y = 0; }
 * Point(int a, int b) { x = a; y = b; } }
 * 
 * by houhou
 */
public class Solution2 {
	// a good catch: use String "x_y" to represent a line
	public int gcd(int a, int b) {
		int result;
		if (a > 0) {
			result = gcd(b % a, a);
		} else {
			result = b;
		}

		return result;
	}

	// duplicate points
	public int maxPoints(Point[] points) {

		if (points.length == 0)
			return 0;
		if (points.length == 1)
			return 1;
		int max = 0;

		for (int i = 0; i < points.length - 1; i++) {
			int x1 = points[i].x;
			int y1 = points[i].y;
			// line and point map: how many points are on a line "x_y"?
			// note in x_y, x, y is delta x and delta y of current node and each subsequent node
			// so only x, y is enough to identify two same lines
			Map<String, Integer> slope = new HashMap<String, Integer>();
			String y0 = "x_0";
			String x0 = "0_y";
			int dup = 1;
			for (int j = i + 1; j < points.length; j++) {
				int x2 = points[j].x;
				int y2 = points[j].y;
				Integer deltx = x1 - x2;
				Integer delty = y1 - y2;
				if (deltx == 0 && delty == 0) {
					for (Map.Entry<String, Integer> e : slope.entrySet()) {
						int count = e.getValue() + 1;
						if (count > max) {
							max = count;
						}
						slope.put(e.getKey(), count);
					}
					dup++;
					if (max < dup) {
						max = dup;
					}
					continue;
				}
				String s;

				if (delty == 0) {
					s = y0;
				} else if (deltx == 0) {
					s = x0;
				} else {
					int g = gcd(Math.abs(deltx), Math.abs(delty));
					if (deltx / Math.abs(deltx) == delty / Math.abs(delty)) {
						deltx = Math.abs(deltx / g);
						delty = Math.abs(delty / g);
					} else {
						deltx = Math.abs(deltx / g);
						delty = -Math.abs(delty / g);
					}
					s = deltx.toString() + "_" + delty.toString();
				}

				if (slope.containsKey(s)) {
					int count = slope.get(s);
					count++;
					slope.put(s, count);
					if (count > max) {
						max = count;
					}
				} else {
					int accu = 1 + dup;
					slope.put(s, accu);
					if (max < accu) {
						max = accu;
					}

				}

			}

		}
		return max;
	}
}