package singleNumber2;

//Given an array of integers, every element appears three times except for one. Find that single one.
//
//Note:
//Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory? 
public class Solution {
	// have 3 bitmaps of 32 bit, representing at ith bit we have seen
	// one/two/three '1's so far
	// i.e
	// say there's only three bits to be count
	// initially
	// ones = 000, twos = 000, threes = 000
	// A={001, 001, 011, 001}
	// A[0]:001
	// ones = 001, twos = 000, threes = 000
	// A[1]:001
	// ones = 000, twos = 001, threes = 000
	// A[2]:011
	// ones = 010, twos = 000, threes = 001
	// A[3]:001
	// ones = 011, twos = 000, threes = 000
	// therefore we know 011 is the number left
	public int singleNumber(int[] A) {
		int ones = 0;
		int twos = 0;
		int threes = 0;
		int oneToTwo;
		int twoToThree;
		int threeToOne;
 		for (int i : A) {
 			// how to shift the bit:
 			//  first we need to know which bit to be moved to next level
 			//   i.e 
 			// 		ones = 011, i = 001, then the last bit needs to be moved, so oneToTwo=ones&i = 001
 			//  after calculating all bits to be moved to next level, 
 			//   we cleared the bit moved from here to next, and add the bit moved from previous to here
 			//   i.e
 			// 		ones = 011, i = 001, threeToOne = 100
 			// 		 then we first clear the last bit of ones, since it's moved to twos
 			// 				ones = 010
 			// 		 then we move the bit from three
 			// 				ones = 110
 			//
 			//  note in the case where the ith bit are all 0, and i contains 1 for that bit, 
 			//   the previous approach won't work
 			//   i.e 
 			//  	ones = 000, twos = 000, threes = 000, i = 111
 			//   in this case we need to add the additional bit to ones
 			//   so we have ones |= (^(ones|twos|threes)) & i
			oneToTwo = i & ones;
			twoToThree = i & twos;
			threeToOne = i & threes;
			ones = (ones & (~oneToTwo)) | threeToOne;
			twos = (twos & (~twoToThree)) | oneToTwo;
			threes = (threes & (~threeToOne)) | twoToThree;
			ones |= (~(ones | twos | threes)) & i;
		}
		return ones;
	}

	public static void main(String[] args) {
		int[] A = { 1, 1, 1, 2, 3, 3, 3 };
		System.out.println(new Solution().singleNumber(A));
	}
}
