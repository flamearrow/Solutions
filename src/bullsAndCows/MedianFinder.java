package bullsAndCows;

import java.util.Comparator;
import java.util.PriorityQueue;

//Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
//
//Examples: 
//[2,3,4] , the median is 3
//
//[2,3], the median is (2 + 3) / 2 = 2.5
//
//Design a data structure that supports the following two operations:
//
//void addNum(int num) - Add a integer number from the data stream to the data structure.
//double findMedian() - Return the median of all elements so far.
//For example:
//
//add(1)
//add(2)
//findMedian() -> 1.5
//add(3) 
//findMedian() -> 2
class MedianFinder {
	public static void main(String[] args) {
		MedianFinder mf = new MedianFinder();
		mf.addNum(40);
		mf.addNum(12);
		mf.addNum(16);
		mf.addNum(14);
		mf.addNum(5);
		mf.addNum(0);
		mf.addNum(6);
		mf.addNum(3);
		mf.addNum(1);
		mf.addNum(0);
		mf.addNum(0);
	}

	PriorityQueue<Double> minHeap;
	PriorityQueue<Double> maxHeap;
	// the idea is to make sure two heaps size diff are no bigger than 1
	// each time determine which queue a num should be added to by checking the peak
	// make sure to resize if one heap size goes bigger than the other after adding
	//  if the number is in between, add to the smaller heap
	public MedianFinder() {
		minHeap = new PriorityQueue<>();
		maxHeap = new PriorityQueue<>(new Comparator<Double>() {

			@Override
			public int compare(Double o1, Double o2) {
				return o2 > o1 ? 1 : o2 == o1 ? 0 : -1;
			}
		});
	}

	// Adds a number into the data structure.
	public void addNum(int num) {
		if (!maxHeap.isEmpty() && num <= maxHeap.peek()) {
			maxHeap.add((double) num);
			if (maxHeap.size() > minHeap.size()) {
				minHeap.add(maxHeap.poll());
			}
		} else if (!minHeap.isEmpty() && num >= minHeap.peek()) {
			minHeap.add((double) num);
			if (minHeap.size() > maxHeap.size()) {
				maxHeap.add(minHeap.poll());
			}
		}
		// the number is somewhere in between, insert to the smaller heap
		else {
			if (maxHeap.size() < minHeap.size()) {
				maxHeap.add((double) num);
			} else {
				minHeap.add((double) num);
			}
		}
	}

	// Returns the median of current data stream
	public double findMedian() {
		int size = minHeap.size() + maxHeap.size();
		if (size % 2 == 0) {
			return (minHeap.peek() + maxHeap.peek()) / 2;
		} else {
			return minHeap.size() > maxHeap.size() ? minHeap.peek() : maxHeap.peek();
		}
	}
};