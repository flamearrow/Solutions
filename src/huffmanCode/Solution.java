package huffmanCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

// huffman code
public class Solution {
	private static char LEFT = '1';
	private static char RIGHT = '0';

	// start from nodes with smallest weight, use a priority queue
	// builds the code after we have finished creating the huffman tree, DFS
	static Map<Character, String> generateHuffmanCode(
			Map<Character, Double> frequencyMap) {
		PriorityQueue<Node> heap = new PriorityQueue<Node>();
		for (Entry<Character, Double> entry : frequencyMap.entrySet()) {
			heap.offer(new Node(entry.getKey(), entry.getValue()));
		}
		while (heap.size() > 1) {
			Node left = heap.poll();
			Node right = heap.poll();
			Node newNode = new Node('0', left.weight + right.weight);
			newNode.left = left;
			newNode.right = right;
			heap.offer(newNode);
		}
		Node root = heap.poll();
		return buildResult(root);
	}

	static Map<Character, String> buildResult(Node root) {
		Map<Character, String> ret = new HashMap<Character, String>();
		doProbe(ret, root, "");
		return ret;
	}

	static void doProbe(Map<Character, String> ret, Node cur, String curPath) {
		if (cur.left == null && cur.right == null) {
			ret.put(cur.c, curPath);
			return;
		}
		if (cur.left != null) {
			doProbe(ret, cur.left, curPath + LEFT);
		}

		if (cur.right != null) {
			doProbe(ret, cur.right, curPath + RIGHT);
		}

	}

	public static void main(String[] args) {
		Map<Character, Double> freqMap = new HashMap<Character, Double>();
		freqMap.put('a', 0.1);
		freqMap.put('b', 0.2);
		freqMap.put('c', 0.3);
		freqMap.put('d', 0.4);
		for (Map.Entry<Character, String> entry : generateHuffmanCode(freqMap)
				.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}
}

class Node implements Comparable<Node> {
	double weight;
	char c;
	Node left, right;

	public Node(char c, double weight) {
		this.weight = weight;
		this.c = c;
	}

	@Override
	public int compareTo(Node n) {
		return (int) (weight - n.weight);
	}
}
