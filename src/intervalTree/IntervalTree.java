package intervalTree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IntervalTree {
	INode root;

	public IntervalTree(List<Interval> intervals) {
		Iterator<Interval> itr = intervals.iterator();
		if (!itr.hasNext())
			return;

		root = new INode(itr.next());
		root.max = root.interval.end;

		while (itr.hasNext()) {
			insert(itr.next());
		}
	}

	public static void main(String[] args) {
		List<Interval> itvs = new LinkedList<Interval>();
		itvs.add(new Interval(15, 20));
		itvs.add(new Interval(10, 30));
		itvs.add(new Interval(17, 19));
		itvs.add(new Interval(5, 20));
		itvs.add(new Interval(12, 15));
		itvs.add(new Interval(30, 40));
		IntervalTree tree = new IntervalTree(itvs);
		//		System.out.println(tree);
		//		System.out.println(tree.overLap(new Interval(6, 7)));
		for (Interval i : tree.overLaps(new Interval(41, 50)))
			System.out.println(i);
	}

	void insert(Interval interval) {
		INode cur = root;
		while (true) {
			if (interval.end > cur.max) {
				cur.max = interval.end;
			}
			if (interval.start < cur.interval.start) {
				if (cur.left != null) {
					cur = cur.left;
				} else {
					cur.left = new INode(interval);
					cur.left.max = interval.end;
					break;
				}
			} else {
				if (cur.right != null) {
					cur = cur.right;
				} else {
					cur.right = new INode(interval);
					cur.right.max = interval.end;
					break;
				}
			}
		}
	}

	// return one interval
	public Interval overLap(Interval interval) {
		INode cur = root;
		// while there's a possible overlap 
		while (cur.max > interval.start) {
			if (cur.interval.start > interval.end) {
				cur = cur.left;
			} else if (cur.interval.end < interval.start) {
				cur = cur.right;
			} else {
				return cur.interval;
			}
		}
		return null;
	}

	// return all overlapped intervals
	public List<Interval> overLaps(Interval interval) {
		List<Interval> ret = new LinkedList<Interval>();
		probe(root, ret, interval);
		return ret;
	}

	private void probe(INode cur, List<Interval> ret, Interval interval) {
		if (cur == null || cur.max < interval.start)
			return;
		// over lap
		if (!(cur.interval.start > interval.end || cur.interval.end < interval.start)) {
			ret.add(cur.interval);
		}
		probe(cur.left, ret, interval);
		probe(cur.right, ret, interval);
	}
}

class Interval {
	int start, end;

	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public String toString() {
		return start + ":" + end;
	}
}

class INode {
	Interval interval;
	int max;
	INode left, right;

	public INode(Interval interval) {
		this.interval = interval;
	}
}