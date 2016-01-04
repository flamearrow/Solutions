package singleNumber2;

//Given an array of integers, every element appears three times except for one. Find that single one.
//
//Note:
//Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory? 
public class Solution {
    // say sec is 110011, 110011, 110011, 111111, promote each bit from ones to threes
    // 1:  110011
    // 2:  000000
    // 3:  000000

    // 1:  000000
    // 2:  110011
    // 3:  000000

    // 1:  000000
    // 2:  000000
    // 3:  110011

    // 1:  111111
    // 2:  000000
    // 3:  110011

    // return 1

    int clearMask(int num, int mask) {
        return num & (~mask);
    }

    int addMask(int num, int mask) {
        return num | mask;
    }

    // always check for !=0
    public int singleNumberReimpl(int[] nums) {
        int ones = 0, twos = 0, threes = 0;
        for (int i : nums) {
            for (int shift = 0; shift < 32; shift++) {
                int mask = (1 << shift) & i;
                if (mask != 0) {
                    if ((ones & mask) != 0) {
                        ones = clearMask(ones, mask);
                        twos = addMask(twos, mask);
                    } else if ((twos & mask) != 0) {
                        twos = clearMask(twos, mask);
                        threes = addMask(threes, mask);
                    } else if ((threes & mask) != 0) {
                        threes = clearMask(threes, mask);
                        ones = addMask(ones, mask);
                    } else {
                        // first time the bit is saw
                        ones = addMask(ones, mask);
                    }
                }
            }
        }
        return ones;
    }

    // just use three ints to buffer seen bits
    // note to test if a bit is set, we need to check (mask & num) != 0, checking (mask&num)>0 will break!
    public int singleNumber3(int[] A) {
        int ones = A[0], twos = 0, threes = 0;
        for (int i = 1; i < A.length; i++) {
            for (int shift = 0; shift < 32; shift++) {
                int curBit = 0;
                // A[i] has curBit set
                // note we need to check the result != 0, as opposed to > 0
                if (((curBit = (1 << shift)) & A[i]) != 0) {
                    // if 'ones' has this bit set, push it to 'twos'
                    // if 'twos' has this bit set, push it to 'threes'
                    // if 'threes' has this bit set, push it to 'ones'
                    if ((ones & curBit) != 0) {
                        // clear from ones, add to twos
                        ones ^= curBit;
                        twos |= curBit;
                    } else if ((twos & curBit) != 0) {
                        // clear from twos, add to threes
                        twos ^= curBit;
                        threes |= curBit;
                    } else if ((threes & curBit) != 0) {
                        // clear from threes, add to ones
                        threes ^= curBit;
                        ones |= curBit;
                    }
                    // this bit doesn't appear in ones/twos/threes, it's a new bit, add it to ones
                    else {
                        ones |= curBit;
                    }
                }

            }
        }
        return ones;
    }

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

    // this one is easer to understand but have two iterations and uses a bigger buffer
    public int singleNumber2(int[] A) {
        int[] back = new int[32];
        for (int i : A) {
            for (int shift = 0; shift < 32; shift++) {
                if (((i >> shift) & 1) > 0) {
                    back[shift] = (back[shift] + 1) % 3;
                }
            }
        }

        int ret = 0;
        for (int i = 0; i < 32; i++) {
            if (back[i] == 1) {
                ret |= (1 << i);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] A = {-2, 1, 1, 1, 3, 3, 3};
        System.out.println(new Solution().singleNumberReimpl(A));
    }
}
