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

    String concatenate2(int[] arr) {
        //   54 < 5 < 55
        //   55555554 < 5 < 5555556
        String[] strs = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            strs[i] = "" + arr[i];
        }
        Arrays.sort(strs, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Integer[] arr = {96, 9, 95, 556, 56, 55, 5, 554, 54, 3, 2, 1};
//        System.out.println(new Solution().concatenate(arr));
        String s1 = "5";
        String s2 = "51";
        String s3 = "55";
        String s4 = "56";
        System.out.println(s1.compareTo(s2));
        System.out.println(s1.compareTo(s3));
        System.out.println(s1.compareTo(s4));
//        System.out.println(s1.compareTo(s2));
    }
}
