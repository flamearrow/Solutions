package flatten2DVector;

import java.util.Iterator;
import java.util.List;

//Implement an iterator to flatten a 2d vector.
//
//For example,
//Given 2d vector =
//
//[
//  [1,2],
//  [3],
//  [4,5,6]
//]
//By calling next repeatedly until hasNext returns false, 
// the order of elements returned by next should be: [1,2,3,4,5,6].

//Follow up:
//As an added challenge, try to code it using only iterators in C++ or iterators in Java.
public class Solution {
}

class Vector2D {
	Iterator<List<Integer>> listItr;
	Iterator<Integer> intItr;

	public Vector2D(List<List<Integer>> vec2d) {
		listItr = vec2d.iterator();
		intItr = listItr.hasNext() ? listItr.next().iterator() : null;
		forwardItr();
	}

	public int next() {
		if (intItr != null && intItr.hasNext()) {
			int ret = intItr.next();
			forwardItr();
			return ret;
		}
		return -1;
	}

	void forwardItr() {
		while (intItr != null && !intItr.hasNext()) {
			if (listItr.hasNext()) {
				intItr = listItr.next().iterator();
			} else {
				intItr = null;
			}
		}
	}

	public boolean hasNext() {
		return intItr == null ? false : intItr.hasNext();
	}

}
/**
 * Your Vector2D object will be instantiated and called as such: Vector2D i =
 * new Vector2D(vec2d); while (i.hasNext()) v[f()] = i.next();
 */
