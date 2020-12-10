package dotProductOfTwoSparseVectors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SparseVector {
    List<Integer> nonZeroIndices = new ArrayList<>();
    Map<Integer, Integer> indexValueMap = new HashMap<>();

    SparseVector(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nonZeroIndices.add(i);
                indexValueMap.put(i, nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int result = 0;
        // 0 is self, 1 is the other
        int pointer0 = 0, pointer1 = 0;
        while (pointer0 < nonZeroIndices.size() && pointer1 < vec.nonZeroIndices.size()) {
            int index0 = nonZeroIndices.get(pointer0);
            int index1 = vec.nonZeroIndices.get(pointer1);
            if (index0 == index1) {
                result += indexValueMap.get(index0) * vec.indexValueMap.get(index1);
                pointer0++;
                pointer1++;
            } else if (index0 < index1) {
                pointer0++;
            } else {
                pointer1++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SparseVector v1 = new SparseVector(new int[]{1, 0, 0, 2, 3});
        SparseVector v2 = new SparseVector(new int[]{0, 3, 0, 4, 0});
        System.out.println(v1.dotProduct(v2));
    }
}
