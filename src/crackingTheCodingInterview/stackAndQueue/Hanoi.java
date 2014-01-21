package crackingTheCodingInterview.stackAndQueue;

import java.util.Stack;

// solve Hanoi with Stack, initially, there are reversely sorted plates in bar1
// move them to bar3 without breaking Hanoi rule
public class Hanoi {

	static void solve(Stack<Integer> bar1, Stack<Integer> bar2,
			Stack<Integer> bar3) {
		move(bar1, bar2, bar3, bar1.size());
	}
	
	// recursion
	// first move count-1 from FROM to THROUGH,
	// them move the last one from FROM to TO
	// them move count - 1 from THROUGH to TO
	static void move(Stack<Integer> FROM, Stack<Integer> THROUGH,
			Stack<Integer> TO, int count) {
		if (count == 0)
			return;
		if (count > 1) {
			move(FROM, TO, THROUGH, count - 1);
			TO.push(FROM.pop());
			move(THROUGH, FROM, TO, count - 1);
		} else {
			TO.push(FROM.pop());
		}
	}

	public static void main(String[] args) {
		Stack<Integer> bar1 = new Stack<Integer>();
		Stack<Integer> bar2 = new Stack<Integer>();
		Stack<Integer> bar3 = new Stack<Integer>();
		addPlates(bar1, 4);
		solve(bar1, bar2, bar3);
		System.out.println(bar3);
	}

	static void addPlates(Stack<Integer> bar, int count) {
		while (count > 0) {
			bar.push(count--);
		}
	}
}
