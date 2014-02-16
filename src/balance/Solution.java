package balance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedReader br = new BufferedReader(new FileReader("input"));
		String next = br.readLine();
		// first is the size
		ArrayList<Balance> balances = new ArrayList<Balance>();
		for (int i = 0; i < Integer.parseInt(next); i++) {
			balances.add(new Balance(i));
		}
		int cur = 0;
		next = br.readLine();
		while (next != null) {
			int indexOfSpace = next.indexOf(' ');

			int leftWeight = 0;
			String[] leftBalanceIndices = null;

			if (indexOfSpace > 0) {
				leftWeight = Integer.parseInt(next.substring(0, indexOfSpace));
				leftBalanceIndices = next.substring(indexOfSpace + 1)
						.split(" ");
			} else {
				leftWeight = Integer.parseInt(next);
			}

			next = br.readLine();
			indexOfSpace = next.indexOf(' ');
			int rightWeight = 0;
			String[] rightBalanceIndices = null;
			if (indexOfSpace > 0) {
				rightWeight = Integer.parseInt(next.substring(0, indexOfSpace));
				rightBalanceIndices = next.substring(indexOfSpace + 1).split(
						" ");
			} else {
				rightWeight = Integer.parseInt(next);
			}
			// assign value to balances and link its child
			if (leftBalanceIndices != null)
				for (String s : leftBalanceIndices) {
					balances.get(cur).bLeft.add(balances.get(Integer
							.parseInt(s)));
				}
			if (rightBalanceIndices != null)
				for (String s : rightBalanceIndices) {
					balances.get(cur).bRight.add(balances.get(Integer
							.parseInt(s)));
				}
			balances.get(cur).wLeft = leftWeight;
			balances.get(cur).wRight = rightWeight;

			cur++;
			next = br.readLine();
		}

		br.close();

		Set<Balance> topB = new HashSet<Balance>(balances);
		while (!topB.isEmpty()) {
			Balance tail = getTail(topB);
			topB.remove(tail);
			tail.doBalance();
		}

		for (int i = 0; i < balances.size(); i++) {
			Balance b = balances.get(i);
			System.out.println(i + ": " + b.addedToLeft + " " + b.addedToRight);
		}
	}

	static Balance getTail(Set<Balance> balances) {
		here: for (Balance b : balances) {
			for (Balance bb : b.bLeft) {
				if (balances.contains(bb))
					continue here;
			}
			for (Balance bb : b.bRight) {
				if (balances.contains(bb))
					continue here;
			}
			return b;
		}

		return null;
	}
}

class Balance {
	int wLeft, wRight;
	int addedToLeft, addedToRight;
	LinkedList<Balance> bLeft, bRight;

	int index = -1;

	public Balance(int i) {
		this(0, 0);
		index = i;
	}

	public Balance(int wLeft, int wRight) {
		this.wLeft = wLeft;
		this.wRight = wRight;
		bLeft = new LinkedList<Balance>();
		bRight = new LinkedList<Balance>();
	}

	int totalWeight() {
		int lBWeight = getLeftBalancesTotalWeight();
		int rBWeight = getRightBalancesTotalWeight();
		return 10 + wLeft + addedToLeft + wRight + addedToRight + lBWeight
				+ rBWeight;
	}

	int getLeftBalancesTotalWeight() {
		int lBWeight = 0;
		if (bLeft.size() > 0) {
			for (Balance b : bLeft) {
				lBWeight += b.totalWeight();
			}
		}
		return lBWeight;
	}

	int getRightBalancesTotalWeight() {
		int rBWeight = 0;
		if (bRight.size() > 0) {
			for (Balance b : bRight) {
				rBWeight += b.totalWeight();
			}
		}
		return rBWeight;
	}

	public void doBalance() {
		int leftWeight = wLeft + getLeftBalancesTotalWeight();
		int rightWeight = wRight + getRightBalancesTotalWeight();
		if (leftWeight > rightWeight) {
			addedToRight = leftWeight - rightWeight;
		} else if (leftWeight < rightWeight) {
			addedToLeft = rightWeight - leftWeight;
		}
	}

	@Override
	public String toString() {
		return "" + index;
	}
}
