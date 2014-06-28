package checkSquare;

// Given four points, check if they form a square
public class Solution {
	static boolean isSquare(PointVec[] points) {
		// check if any three nodes form an right Isosceles triangle
		PointVec v1 = new PointVec(points[0].x - points[1].x, points[0].y
				- points[1].y);
		PointVec v2 = new PointVec(points[1].x - points[2].x, points[1].y
				- points[2].y);
		PointVec v3 = new PointVec(points[0].x - points[2].x, points[0].y
				- points[2].y);
		// then use the vector to calculate the forth point
		// compare it with the actual forth point
		if (cross(v1, v2)) {
			return (points[0].x - v2.x == points[3].x)
					&& (points[0].y - v2.y == points[3].y);
		}
		if (cross(v1, v3)) {
			return (points[1].x + v3.x == points[3].x)
					&& (points[1].y + v3.y == points[3].y);
		}
		if (cross(v2, v3)) {
			return (points[0].x + v2.x == points[3].x)
					&& (points[0].y + v2.y == points[3].y);
		}
		return false;
	}

	public static void main(String[] args) {
		PointVec[] points = new PointVec[4];
		points[0] = new PointVec(0, 0);
		points[1] = new PointVec(1, 1);
		points[2] = new PointVec(1, 2);
		points[3] = new PointVec(0, 1);
		System.out.println(isSquare(points));
	}

	static boolean cross(PointVec v1, PointVec v2) {
		return v1.x * v2.x + v1.y * v2.y == 0;
	}

	private static class PointVec {
		int x, y;

		public PointVec(int argX, int argY) {
			x = argX;
			y = argY;
		}
	}

}
