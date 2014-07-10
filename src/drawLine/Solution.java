package drawLine;

// given an mXn int array with initial values all 0
// and two points start and end, draw a line between start a end
// set them all to 1

// the idea is to iterate through x or y according to which diff is bigger
// note: use double to bypass the divide by zero error, 0/0 in double will return infinity
public class Solution {
	// the idea is first choose which coordinates to iterate through(always the wider one)
	// then pick the correct start/end point of the iterating coordinate(start should <= y)
	// when iterating, make sure we are incrementing the other coordinate in correct direction
	//  a tricky part is if we decide to iterate through y, we can use a boolean to mark that 
	//    and we draw arr[y][x] as opposed to arr[x][y] 
	static void drawLine2(int[][] arr, int startX, int startY, int endX,
			int endY) {
		boolean steep = Math.abs(endX - startX) > Math.abs(endY - startY);
		// we should always loop through the coordinates with longer span
		if (!steep) {
			int tmp = startX;
			startX = startY;
			startY = tmp;
			tmp = endX;
			endX = endY;
			endY = tmp;
		}
		// ensure startX <= endX
		if (startX > endX) {
			int tmp = startX;
			startX = endX;
			endX = tmp;
			tmp = startY;
			startY = endY;
			endY = tmp;
		}

		int deltaX = endX - startX;
		int deltaY = endY - startY;
		// Note when we increment err, make sure using abs value
		// because we already take care of direction in yStep
		double slope = Math.abs((double) deltaY / (double) deltaX);
		double err = 0;
		int curY = startY;
		// make sure we increment Y in correct direction
		int yStep = endY > startY ? 1 : -1;
		for (int x = startX; x <= endX; x++) {
			if (steep) {
				arr[x][curY] = 1;
			} else {
				arr[curY][x] = 1;
			}
			err += slope;
			if (err >= 0.5) {
				curY += yStep;
				err -= 1;
			}

		}
	}

	// get rid off division, multiply everything with deltaX
	static void drawLine3(int[][] arr, int startX, int startY, int endX,
			int endY) {
		boolean steep = Math.abs(endX - startX) > Math.abs(endY - startY);
		// we should always loop through the coordinates with longer span
		if (!steep) {
			int tmp = startX;
			startX = startY;
			startY = tmp;
			tmp = endX;
			endX = endY;
			endY = tmp;
		}
		// ensure startX <= endX
		if (startX > endX) {
			int tmp = startX;
			startX = endX;
			endX = tmp;
			tmp = startY;
			startY = endY;
			endY = tmp;
		}

		int deltaX = endX - startX;
		int deltaY = Math.abs(endY - startY);
		double err = deltaX / 2;
		int curY = startY;
		// make sure we increment Y in correct direction
		int yStep = endY > startY ? 1 : -1;
		for (int x = startX; x <= endX; x++) {
			if (steep) {
				arr[x][curY] = 1;
			} else {
				arr[curY][x] = 1;
			}
			// Note when we increment err, make sure using abs value
			// because we already take care of direction in yStep
			err -= deltaY;
			if (err < 0) {
				curY += yStep;
				err += deltaX;
			}

		}
	}
	
	static void drawLine(int[][] arr, int startX, int startY, int endX, int endY) {
		double tilt = (double) (endY - startY) / (double) (endX - startX);
		arr[startX][startY] = 1;
		arr[endX][endY] = 1;
		// should iterate through x
		if (endX - startX > endY - startY) {
			if (startX > endX) {
				int tmp = startX;
				startX = endX;
				endX = tmp;
			}
			for (int x = startX + 1; x < endX; x++) {
				// note: +0.5 here to make it most accurate
				int y = (int) (tilt * (x - startX) + startY + 0.5);
				arr[x][y] = 1;
			}
		}
		// should iterate through y
		else {
			if (startY > endY) {
				int tmp = startY;
				startY = endY;
				endY = tmp;
			}
			for (int y = startY + 1; y < endY; y++) {
				// note: +0.5 here to make it most accurate
				int x = (int) ((y - startY) / tilt + startX + 0.5);
				arr[x][y] = 1;
			}
		}
	}

	public static void main(String[] args) {
		// note when double divide by 0 it won't through /byzero exception, instead we would get 'infinity'
		//		double d1 = 0;
		//		double d2 = 1;
		//		System.out.println(d2 / d1);
		int[][] arr = new int[10][10];
		drawLine(arr, 0, 0, 1, 9);
		for (int[] arrr : arr) {
			for (int i : arrr) {
				System.out.print(i);
			}
			System.out.println();
		}
	}
}
