package singleNumber;

//Given an array of integers, every element appears twice except for one. Find that single one.
//
//Note:
//Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
public class Solution {
    public int singleNumber(int[] A) {
        int ret = 0;
        for (int i : A) {
            ret ^= i;
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] A = {};
        System.out.println(new Solution().singleNumber(A));
    }

    // what if there're two numbers appearing once?
    public int[] singleNumber3(int[] nums) {
        // after we got mask=a^b, we just pick a bit that's set by mask^(-mask)
        //  note this bit set must be from a or b, so we just loop though the array again
        //  and xor all numbers that have this bit set
        // we'll end up xoring all dup numbers AND the single number that has this bit set
        //  resulting the single number
        // then xor the single number with the original mask to get the other number
        int mask = 0;
        for (int i : nums) {
            mask ^= i;
        }
        // now mask is a^b
        int xor = mask;
        int lastBitMask = mask & (-mask);
        mask = 0;
        for (int i : nums) {
            if ((i & lastBitMask) != 0) {
                mask ^= i;
            }
        }
        // now mask is a or b
        int another = xor ^ mask;
        // now another is b or a
        int[] ret = {mask, another};
        return ret;
    }

}
