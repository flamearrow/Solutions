package generateGroups;

// Given n and g, generate UP to g groups of numbers, added up to n


import java.util.ArrayList;
import java.util.List;

public class Solution {


    // returns a list of sizes of sub groups, added up to max
    List<List<Integer>> generateGroups(int n, int g) {
        List<List<Integer>> results = new ArrayList<>();
        // don't check for n < g here, because we're checking for 'at most g'
        // i.e n = 3, g = 4 is valid as we can get 1, 2, 3 groups for 3
        if (n == 0) {
            return results;
        }
        if (g == 1) {
            List<Integer> slice = new ArrayList<>();
            slice.add(n);
            results.add(slice);
            return results;
        } else {
            for (int lastGroupSize = 1; lastGroupSize < n; lastGroupSize++) {
                // make sure last group size leave at least 1 for recursion call
                for (List<Integer> previousResult : generateGroups(n - lastGroupSize, g - 1)) {
                    List<Integer> newList = new ArrayList<>(previousResult);
                    newList.add(lastGroupSize);
                    results.add(newList);
                }
            }
            List<Integer> singleGroupList = new ArrayList<>();
            singleGroupList.add(n);
            results.add(singleGroupList);
        }
        return results;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().generateGroups(5, 3));
    }

}
