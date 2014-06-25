package crackingTheCodingInterview.c18;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

// numbers are randomly generated, how to keep track of the median?
public class Question9MedianOfNumberStream {
	static class MedianTracker {
		PriorityQueue<Integer> minHeap;
		PriorityQueue<Integer> maxHeap;

		MedianTracker() {
			// note heap by default returns the smallest one first
			minHeap = new PriorityQueue<Integer>(10, new Comparator<Integer>() {

				@Override
				public int compare(Integer arg0, Integer arg1) {
					return arg0 - arg1;
				}

			});
			maxHeap = new PriorityQueue<Integer>(10, new Comparator<Integer>() {

				@Override
				public int compare(Integer arg0, Integer arg1) {
					return arg1 - arg0;
				}

			});
		}

		// we always make sure the size of minHeap is equal to or 1 more than maxHeap
		void offer(int next) {
			// should be in upper half
			if (minHeap.size() == 0)
				minHeap.offer(next);
			else if (next > minHeap.peek()) {
				if (minHeap.size() > maxHeap.size()) {
					maxHeap.offer(minHeap.poll());
				}
				minHeap.offer(next);
			}
			// should be in lower half
			else {
				if (minHeap.size() == maxHeap.size() && maxHeap.size() > 0) {
					minHeap.offer(maxHeap.poll());
				}
				maxHeap.offer(next);
			}
		}

		double median() {
			if ((minHeap.size() + maxHeap.size()) % 2 == 0) {
				return ((double) (minHeap.peek() + maxHeap.peek())) / 2;
			} else {
				return minHeap.peek();
			}
		}
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		MedianTracker mt = new MedianTracker();
		while (true) {
			int next = s.nextInt();
			mt.offer(next);
			System.out.println(mt.median());
		}
	}
}
