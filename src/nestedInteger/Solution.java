package nestedInteger;

import java.util.LinkedList;
import java.util.List;

// calculate the sum of weighted nested integer
// weight is depth
// {2, 4, {6, {8}}} 
public class Solution {
	int weight(List<NestedNode> list) {
		return calSumWithWeight(list, 1);
	}

	int calSumWithWeight(List<NestedNode> list, int weight) {
		int ret = 0;
		for (NestedNode n : list) {
			if (n.isValue) {
				ret += n.val;
			} else {
				ret += calSumWithWeight(n.list, weight + 1);
			}
		}
		return ret * weight;
	}

	public static void main(String[] args) {
		List<NestedNode> list = new LinkedList<NestedNode>();
		list.add(new NestedNode(2));
		list.add(new NestedNode(4));
		List<NestedNode> subList = new LinkedList<NestedNode>();
		subList.add(new NestedNode(6));
		subList.add(new NestedNode(new LinkedList<NestedNode>()));
		subList.get(1).list.add(new NestedNode(8));
		list.add(new NestedNode(subList));
		System.out.println(new Solution().weight(list));
	}
}

class NestedNode {
	boolean isValue;
	int val;
	List<NestedNode> list;

	public NestedNode(boolean isVal) {
		isValue = isVal;
	}

	public NestedNode(int val) {
		isValue = true;
		this.val = val;
	}

	public NestedNode(List<NestedNode> list) {
		isValue = false;
		this.list = list;
	}
}