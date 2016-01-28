package hopItr;

import java.util.Iterator;

/**
 * Implement an iterator that hops specified number of times and then returns the next
 * element after the hop. Note: the iterator always returns the first element as
 * it is, and starts hopping only after the first element.
 * <p>
 * Examples:
 * <p>
 * If the original iterator returns: [1, 2, 3, 4, 5] in order, then the hopping
 * iterator will return [1, 3, 5] in order when the hop value is 1.
 * <p>
 * If the original iterator returns: [1, 2, 3, 4, 5] in order, then the hopping
 * iterator will return [1, 4] in order when the hop value is 2.
 * <p>
 * If the original iterator returns: [1, 2, 3, 4, 5] in order, then the hopping
 * iterator will return [1, 5] in order when the hop value is 3.
 * <p>
 * Methods expected to be implemented:
 * <p>
 * public class HoppingIterator implements Iterator {
 * public HoppingIterator(Iterator iterator, int numHops) {…}
 * public boolean hasNext() {…}
 * public T next() {…}
 * }
 */
public class Solution {
}

class HoppingIterator<T> implements Iterator<T> {
    private int mNumHops;
    private Iterator<T> mItr;

    public HoppingIterator(Iterator<T> iterator, int numHops) {
        mNumHops = numHops;
        mItr = iterator;
    }

    public boolean hasNext() {
        return mItr.hasNext();
    }

    public T next() {
        T ret = mItr.next();
        int hops = mNumHops;
        while (hops-- > 0) {
            mItr.next();
        }
        return ret;
    }
}