package drawLine;

// given an mXn int array with initial values all 0
// and two points start and end, draw a line between start a end
// set them all to 1

// the idea is to iterate through x or y according to which diff is bigger
// note: use double to bypass the divide by zero error, 0/0 in double will return infinity
public class Solution {
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
