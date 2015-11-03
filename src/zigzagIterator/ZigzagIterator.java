package zigzagIterator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Given two 1d vectors, implement an iterator to return their elements alternately.
//
//For example, given two 1d vectors:
//
//v1 = [1, 2]
//v2 = [3, 4, 5, 6]
//By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].
//
//Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
//
//Clarification for the follow up question - Update (2015-09-18):
//The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". 
// For example, given the following input:
//
//[1,2,3]
//[4,5,6,7]
//[8,9]
//It should return [1,4,8,2,5,9,3,6,7].
public class ZigzagIterator {
	public static void main(String[] args) {
		int[] list1 = {};
		int[] list2 = { 1 };
		List<Integer> l1 = getList(list1);
		List<Integer> l2 = getList(list2);
		List<List<Integer>> lists = new LinkedList<>();
		lists.add(l1);
		lists.add(l2);
		int[] list3 = { 8, 9 };
		// lists.add(getList(list3));
		ZigzagIterator itr = new ZigzagIterator(lists);
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}

	}

	static List<Integer> getList(int[] list) {
		List<Integer> ret = new ArrayList<>();
		for (int i : list) {
			ret.add(i);
		}
		return ret;

	}

	private int curRow = 0, curColumn = 0, mMaxRow, mMaxColumn;

	private List<List<Integer>> mLists;

	public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
		mLists = new ArrayList<>();
		mLists.add(v1);
		mLists.add(v2);
		initializeLists();
	}

	public ZigzagIterator(List<List<Integer>> lists) {
		mLists = lists;
		initializeLists();
	}

	private void initializeLists() {
		mMaxRow = mLists.size();
		for (List<Integer> l : mLists) {
			if (l.size() > mMaxColumn) {
				mMaxColumn = l.size();
			}
		}
		adjustPointers();
	}

	public int next() {
		int ret = mLists.get(curRow).get(curColumn);
		curRow += 1;
		if (curRow == mMaxRow) {
			curRow = 0;
			curColumn += 1;
		}
		adjustPointers();
		return ret;
	}

	// move the pointer to the next valid position or move it all the way to end
	private void adjustPointers() {
		while (curColumn < mMaxColumn && mLists.get(curRow).size() <= curColumn) {
			curRow += 1;
			if (curRow == mMaxRow) {
				curRow = 0;
				curColumn += 1;
			}
		}
	}

	public boolean hasNext() {
		return curColumn < mMaxColumn;
	}
}
