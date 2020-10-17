package maxPointsOnALine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Given n points on a 2D plane, find the maximum number of points 
// that lie on the same straight line.
public class Solution {
	
	
	public int maxPoints3(Point[] points) {
		Map<Line, Set<Integer>> map = new HashMap<Line, Set<Integer>>();
		boolean allSameNodes = true;
		int max = 0;
		for (int i = 0; i < points.length; i++) {
			for (int j = i + 1; j < points.length; j++) {
				if (points[i].x == points[j].x && points[i].y == points[j].y) {
					continue;
				} else {
					allSameNodes = false;
					Line newLine = new Line(points[i], points[j]);
					Set<Integer> newSet = null;
					if (map.containsKey(newLine)) {
						newSet = map.get(newLine);
						newSet.add(i);
						newSet.add(j);
					} else {
						newSet = new HashSet<Integer>();
						newSet.add(i);
						newSet.add(j);
						map.put(newLine, newSet);
					}
					if (newSet.size() > max) {
						max = newSet.size();
					}
				}
			}
		}
		if (allSameNodes)
			return points.length;
		else
			return max;

	}

	
	
	// the idea:
	// just go over the n^2 nodes once,
	// have a HashMaps storing line and point INDEX on the line
	// update maxCount when necessary
	//
	// also keep a boolean 'allPointsSame' to track the scenario that
	// 	all points are at the same position, in which case we should
	// 	return the size of points
	public int maxPoints(Point[] points) {
		if (points.length <= 1)
			return points.length;
		HashMap<Line, Set<Integer>> lineCount = new HashMap<Line, Set<Integer>>();
		boolean allPointsSame = true;
		int len = points.length;
		int count = -1, maxCount = -1;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				Point p1 = points[i];
				Point p2 = points[j];
				if (p1.x != p2.x || p1.y != p2.y) {
					allPointsSame = false;
					Line l = new Line(p1, p2);
					// add the line to set, remove duplicates
					if (lineCount.containsKey(l)) {
						Set<Integer> set = lineCount.get(l);
						set.add(i);
						set.add(j);
						count = set.size();
					} else {
						Set<Integer> set = new HashSet<Integer>();
						set.add(i);
						set.add(j);
						lineCount.put(l, set);
						count = set.size();
					}
				}
				if (count > maxCount) {
					maxCount = count;
				}
			}
		}
		if (allPointsSame)
			return points.length;
		else
			return maxCount;
	}

	// the idea:
	// first go through all combinations, create all lines possible
	// then if there's no line, it means either there's no nodes, or all nodes
	// are the same node - return the size of the input
	// then for all lines created, go over the points array again, check if a
	// node is in one of the lines, create the Line, LineInfo map, check for max
	// 2 * n^2
	public int maxPoints2(Point[] points) {
		HashSet<Line2> lines = new HashSet<Line2>();
		int len = points.length;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				Point p1 = points[i];
				Point p2 = points[j];
				// if the two nodes are equal, can't create a line, just
				// continue
				if (p1.x == p2.x && p1.y == p2.y) {
					continue;
				}
				Line2 l = new Line2(p1, p2);
				// add the line to set, remove duplicates
				lines.add(l);
			}
		}
		if (lines.size() == 0)
			return points.length;
		else {
			Map<Line2, Integer> backMap = new HashMap<Line2, Integer>();
			int maxCount = -1;
			int count = -1;
			for (Point p : points) {
				for (Line2 l : lines) {
					// if the point is on the line, update backMap
					if (l.contains(p)) {
						if (backMap.containsKey(l)) {
							count = backMap.get(l) + 1;
							backMap.put(l, count);
						} else {
							count = 1;
							backMap.put(l, 1);
						}
						if (count > maxCount) {
							maxCount = count;
						}
					}

				}
			}
			return maxCount;
		}
	}

	public static void main(String[] args) {
		Point[] pts = new Point[3];
		pts[0] = new Point(2, 4);
		pts[1] = new Point(4, 4);
		pts[2] = new Point(2, 4);
		// pts[1] = new Point(2, 2);
		// pts[2] = new Point(0, 0);
		// pts[3] = new Point(8, 2);
		// pts[5] = new Point(16, 4);
		// pts[6] = new Point(20, 5);
		Solution s = new Solution();
		System.out.println(s.maxPoints(pts));
	}

}

class Line {
	int a = 0, b = 0, c = 0;

	public Line(Point p1, Point p2) {
		a = p1.y - p2.y;
		b = p1.x - p2.x;
		// otherwise it's parralel to X or Y axis
		if (a != 0 && b != 0)
			updateAB();
		else if (a == 0) {
			a = p1.y;
			b = 0;
			c = 0;
		} else if (b == 0) {
			a = 0;
			b = p1.x;
			c = 0;
		}
		c = b * p1.y - a * p1.x;
	}

	// find GCD of a and b, then divide them by GCD
	void updateAB() {
		int big = a > b ? a : b;
		int small = big == a ? b : a;
		while (small != 0) {
			int tmp = small;
			small = big % small;
			big = tmp;
		}
		// now big is gcd
		a /= big;
		b /= big;
	}

	public boolean equals(Object o) {
		if (!(o instanceof Line))
			return false;
		else if (o == this)
			return true;
		Line l = (Line) o;
		return l.a == a && l.b == b && l.c == c;
	}

	public int hashCode() {
		return a * 32 + b * 7 + c;
	}

}

class Line2 {
	double tilt;
	/* where does this line intersect with x axis */
	double x0 = 0d, y0 = 0d;

	public Line2(Point p1, Point p2) {
		if (p2.x == p1.x) {
			tilt = Integer.MAX_VALUE;
			x0 = p1.x;
		} else {
			tilt = ((double) (p2.y - p1.y) / (double) (p2.x - p1.x));
			if (tilt == 0)
				y0 = p1.y;
			else
				x0 = p2.x - p2.y / tilt;
		}

	}

	boolean contains(Point p) {
		if (tilt == 0) {
			return y0 == p.y;
		} else if (tilt == Integer.MAX_VALUE) {
			return x0 == p.x;
		} else {
			return tilt * p.x - p.y == tilt * x0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Line2))
			return false;
		Line2 line = (Line2) obj;
		return (line.x0 == x0) && (line.tilt == tilt) && (line.y0 == y0);
	}

	@Override
	public int hashCode() {
		return (int) (tilt * 32 + x0 + y0);
	}
}

class Line3 {
	int a, b, c;
	// the is the real correct representation of a line
	// ax + by + c = 0
	// note when we calculate a and b their sign should be opposite
	public Line3(Point p1, Point p2) {
		a = p2.y - p1.y;
		b = p1.x - p2.x;
		if (a != 0 && b != 0) {
			// note there's no 'divide by zero' issue here
			// as long as there's one number that's not 0
			int gcd = gcd(a, b);
			a /= gcd;
			b /= gcd;
			// we need always make a positive
			// so that we don't mess up a=-1, b=1 and a=1, b=-1
			if (a < 0) {
				a = -a;
				b = -b;
			}
			c = 0 - a * p1.x - b * p1.y;
		} else {
			// (2, 5), (2, 6) -> x=2: a = 1, b = 0, c = 2
			if (a == 0) {
				c = p1.x;
				a = 1;
				b = 0;
			}
			// (3, 9), (6, 9) -> y=9: a = 0, b = 1, c = 9 
			else {
				c = p1.y;
				a = 0;
				b = 1;
			}
		}
	}

	private int gcd(int a, int b) {
		int bigger = a > b ? a : b;
		int smaller = bigger == a ? b : a;
		while (smaller != 0) {
			int tmp = smaller;
			smaller = bigger % smaller;
			bigger = tmp;
		}
		return bigger;
	}

	@Override
	public int hashCode() {
		return (a << 3) | (b << 2) | (c << 1);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof Line))
			return false;
		Line l = (Line) o;
		return l.a == a && l.b == b && l.c == c;
	}
}

class Point {
	int x;
	int y;

	Point() {
		x = 0;
		y = 0;
	}

	Point(int a, int b) {
		x = a;
		y = b;
	}
}
