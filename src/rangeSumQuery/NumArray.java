package rangeSumQuery;

//Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
//
//The update(i, val) function modifies nums by updating the element at index i to val.
//Example:
//Given nums = [1, 3, 5]
//
//sumRange(0, 2) -> 9
//update(1, 2)
//sumRange(0, 2) -> 8
//Note:
//The array is only modifiable by the update function.
//You may assume the number of calls to update and sumRange function is distributed evenly.
public class NumArray {
	int[] arr;
	int[] buf;

	public static void main(String args[]) {
		int a[] = { 0, 4, -2, 8, 5, 1, 9, 6, 3, 5, 8, 6, 6, -3, 5, 2 };
		BIT ft = new BIT(a);

		System.out.println("getSum(1) :\t" + ft.getSum(1));
		System.out.println("getSum(3) :\t" + ft.getSum(3));
		System.out.println("getSum(5) :\t" + ft.getSum(5));
		System.out.println("get(5) :\t" + ft.get(5));
		System.out.println("getSum(9) :\t" + ft.getSum(9));
		System.out.println("getSum(15) :\t" + ft.getSum(15));

		System.out.println("addValue(5,4)");
		ft.addValue(5, 4);
		System.out.println("getSum(1) :\t" + ft.getSum(1));
		System.out.println("getSum(3) :\t" + ft.getSum(3));
		System.out.println("getSum(5) :\t" + ft.getSum(5));
		System.out.println("get(5) :\t" + ft.get(5));
		System.out.println("getSum(9) :\t" + ft.getSum(9));
		System.out.println("getSum(15) :\t" + ft.getSum(15));

	}

	public NumArray(int[] nums) {
		arr = new int[nums.length];
		buf = nums;
		for (int i = arr.length - 1; i >= 0; i--) {
			arr[i] = (i == arr.length - 1 ? 0 : arr[i + 1]) + nums[i];
		}
	}

	void update(int i, int val) {
		for (int j = 0; j <= i; j++) {
			arr[j] = arr[j] - buf[i] + val;
		}
		buf[i] = val;
	}

	public int sumRange(int i, int j) {
		return arr[i] - (j >= arr.length - 1 ? 0 : arr[j + 1]);
	}
}

class BIT {
	int BIT[];

	public BIT(int a[]) {
		BIT = new int[a.length];
		for (int i = 1; i < BIT.length; i++) {
			addValue(i, a[i]);
		}
	}

	int getSum(int i) {
		int ix = i;
		int sum = 0;
		while (ix > 0) {
			sum += BIT[ix];
			ix = ix - (ix & -ix);
		}
		return sum;
	}

	void addValue(int i, int v) {
		int ix = i;
		while (ix < BIT.length) {
			BIT[ix] += v;
			ix = ix + (ix & -ix);
		}
	}

	int get(int i) {
		return getSum(i) - getSum(i - 1);
	}

}
