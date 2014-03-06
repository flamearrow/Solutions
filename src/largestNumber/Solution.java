package largestNumber;

import java.util.Arrays;
import java.util.Comparator;

// given a array of integers, concatenate the integers to max number
//  e.g [9, 91, 4] -> 9914
public class Solution {
	// the idea is to sort the numbers in such a way that 
	//  a > b => ab > ba
	// where ab and ba is the concatenation of a and b
	//  or we want to maintain an order such that 56 > 5 > 55 > 54
	// avoid overflow
	String concatenate(Integer[] arr) {
		Comparator<Integer> cpt = new Comparator<Integer>() {
			@Override
			public int compare(Integer arg0, Integer arg1) {
				// use integer concatenation to cheat
				// or we can use a mask to test how many bits we need to left shift
				int ab = Integer.parseInt("" + arg0 + arg1);
				int ba = Integer.parseInt("" + arg1 + arg0);
				return ab - ba;
			}
		};
		Arrays.sort(arr, cpt);
		StringBuilder sb = new StringBuilder();
		for (int i : arr)
			sb.insert(0, i);
		return sb.toString();
	}

	public static void main(String[] args) {
		Integer[] arr = { 96, 9, 95, 556, 56, 55, 5, 554, 54, 3, 2, 1 };
		System.out.println(new Solution().concatenate(arr));
	}
}
