package nestedListWeightSumII;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int maxLvl = calculateLevel(nestedList, 1);
        return nestedList.stream().mapToInt(nestedItem -> calculateSum(nestedItem, maxLvl)).sum();
    }

    int calculateLevel(List<NestedInteger> nestedList, int level) {
        int levelToReturn = level;
        for (NestedInteger i : nestedList) {
            if (!i.isInteger()) {
                levelToReturn = Math.max(levelToReturn, calculateLevel(i.getList(), level + 1));
            }
        }
        return levelToReturn;
    }

    int calculateSum(NestedInteger nestedInteger, int level) {
        if (nestedInteger.isInteger()) {
            return level * nestedInteger.getInteger();
        } else {
            return nestedInteger.getList().stream().mapToInt(nestedItem -> calculateSum(nestedItem,
                    level - 1)).sum();
        }
    }


    public static void main(String[] args) {
        NestedInteger item1 = new NestedInteger();
        item1.add(new NestedInteger(1));
        item1.add(new NestedInteger(1));

        NestedInteger item2 = new NestedInteger();
        item2.add(new NestedInteger(1));
        item2.add(new NestedInteger(1));

        List<NestedInteger> list = new ArrayList<>(3);
        list.add(item1);
        list.add(new NestedInteger(2));
        list.add(item2);

        System.out.println(new Solution().depthSumInverse(list));

    }
}


class NestedInteger {
    boolean isInteger;
    int i;
    List<NestedInteger> list;

    public NestedInteger(int i) {
        isInteger = true;
        this.i = i;
    }

    public NestedInteger() {
        isInteger = false;
        this.list = new ArrayList<>();

    }
    // Constructor initializes an empty nested list.
//    public NestedInteger();

    // Constructor initializes a single integer.
//    public NestedInteger(int value);

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    public boolean isInteger() {
        return isInteger;
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return i;
    }

    // Set this NestedInteger to hold a single integer.
    public void setInteger(int value) {
        i = value;
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    public void add(NestedInteger ni) {
        list.add(ni);
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        return list;
    }
}